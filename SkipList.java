import java.util.Random;

public class SkipList{

  private SkipListNode head;
  int num_levels = 1;
  private static int MAX_NUM_LEVELS = 10;

  public SkipList(SkipListNode head){
    this.head = head;
  }

  public SkipList(){
    this(null);
  }

  public boolean isEmpty(){
    if(head == null){
      return true;
    }
    return false;
  }

  public static boolean flipCoin(){ //returns a true or false at psuedo random
    Random rand = new Random();
    int result = rand.nextInt(2);//returns 0 or 1
    if(result == 1){
      return true;
    }
    return false;
  }


  public void addToHigherLevels(SkipListNode node){
    if(flipCoin()){//only add node to higher levels sometimes
      return;
    }
    if(node == null){
      System.err.println("Error: null passed to addToHigherLevels()");
      return;
    }

    SkipListNode higherLevel = new SkipListNode(node.item);
    node.up = higherLevel;
    higherLevel.down = node;

    SkipListNode traversal = node;
    while(traversal.left != null){
      if(traversal.left.up != null){
        lastUp(traversal.left.up, node.up);
        break;
      }
      traversal = traversal.left;
    }
    traversal = node;
    while(traversal.right != null){
      if(traversal.right.up != null){
        nextUp(traversal.right.up, node.up);
        break;
      }
      traversal = traversal.right;
    }

    addToHigherLevels(higherLevel);
  }

  public void lastUp(SkipListNode lUp, SkipListNode node){
    lUp.right = node;
    node.left = lUp;
  }

  public void nextUp(SkipListNode nUp, SkipListNode node){
    nUp.left = node;
    node.right = nUp;
  }

  private void insertFrontOfList(SkipListNode node){
    head.left = node;
    node.right = head;
    head = node;
    addToHigherLevels(node);
  }

  private void insertBottomRow(SkipListNode node, SkipListNode location){
    if(location.right == null){
      location.right = node;
      node.left = location;
    }else{
      location.right.left = node;
      node.right = location.right;
      location.right = node;
      node.left = location;
    }
    addToHigherLevels(node);
  }

  public void insert(SkipListNode node){
    if(isEmpty()){
      this.head = node;
    }else{
      if(node.item.compareTo(head.item) < 0){
        insertFrontOfList(node);
      }else{
        SkipListNode i = head; //used to iterate through the bottom level of the list
        for(;;){
          if(i.right == null || (i.right.item.compareTo(node.item) > 0)){
            insertBottomRow(node, i);
            break;
          }
          i = i.right;
        }
      }
    }
  }



  public void printBottomRow(){//used for testing purposes
    SkipListNode i = head;
    while(i != null){
      System.out.println(i.item);
      if(i.up != null){
        System.out.println("up");
      }
      i = i.right;
    }
  }

  public void deleteHigherLevels(SkipListNode deleted){//only called if deleted.up exists
    if(deleted.up == null){
      System.err.println("updateHigherLevels() called incorrectly - no higher levels exist");
      return;
    }

  }

  public void delete(String item){
    SkipListNode curr = head;
    for(;;){
      if(curr.item == item){
        if(curr == head && curr.right == null){
          head = null;
        }else if(curr.left == null){
          //delete from front of list
          head = head.right;
          head.left = null;
        }else if(curr.right == null){
          curr.left.right = null;
        }else{
          curr.left.right = curr.right;
          curr.right.left = curr.left;
        }
        if(curr.up != null){
          deleteHigherLevels(curr);
        }
        break;
      }
      if(curr.right == null){
        break; //item not found
      }
      curr = curr.right;
    }
  }

  public static void main(String[] args){ //used for testing purposes
    SkipList test = new SkipList();
    SkipListNode testNode = new SkipListNode("test pass", null, null, null, null);
    SkipListNode testNode2 = new SkipListNode("zzzz", null, null, null, null);
    SkipListNode testNode3 = new SkipListNode("abc", null, null, null, null);
    SkipListNode testNode4 = new SkipListNode("aaa", null, null, null, null);
    test.insert(testNode);
    test.insert(testNode2);
    test.insert(testNode3);
    test.insert(testNode4);
    test.delete("aaa");
    boolean empt = test.isEmpty();
    boolean coinTest = flipCoin();
    System.out.println(coinTest);
    coinTest = flipCoin();
    System.out.println(coinTest);
    coinTest = flipCoin();
    System.out.println(coinTest);
    test.printBottomRow();
  }
}
