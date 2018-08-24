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


  public void insert(SkipListNode node){
    if(isEmpty()){
      this.head = node;
    }else{
      //variables used to find insert position
      SkipListNode lastUp = null; //used if node is inserted on above level
      SkipListNode insertSpot = head; //indicates the Spot that node should be inserted behind
      SkipListNode nextUp = null; //used if node is inserted on above level
      SkipListNode nextElement = head; //used to iterate through the bottom level of the list
      //find spot to insert element
      while(node.item.compareTo(nextElement.item) > 0){
        //continue until spot found
        //if to be appended to end of list, nullify nextElement;
        if(nextElement.isEnd()){
          insertSpot = nextElement;
          nextElement = null;
          break;
        }
        nextElement = nextElement.right; //iterates through list
        if(nextElement.left.up != null){ //continually update lastUp with nodes in list above
          lastUp = nextElement.left.up;
        }
        insertSpot = nextElement;
      }
      //continue iterating until element found or end of list
      while(nextElement.right != null){
        if(nextElement.up != null){
          nextUp = nextElement.up;
          break;
        }
        nextElement = nextElement.right;
      }

      //add node to spot before insertSpot
      //if insertSpot.right == null then insert to end of list
      if(nextElement == null){//if to be appended to end
        //in this case insertSpot points to the last element
        node.left = insertSpot;
        insertSpot.right = node;
      }else if(nextElement.isStart()){
        node.right = insertSpot;
        insertSpot.left = node;
      }else{
        insertSpot.left.right = node;
        node.left = insertSpot.left;
        insertSpot.left = node;
        node.left = insertSpot;
      }
      //sometimes this node should be added to higher levels
      int currLevel = 1;
      while(flipCoin() && currLevel != MAX_NUM_LEVELS){
        //duplicate node
        SkipListNode newNode = new SkipListNode(node.item);
        newNode.down = node;
        node.up = newNode;
        if(lastUp != null){
          newNode.left = lastUp;
          lastUp.right = newNode;
        }
        if(nextUp != null){
          newNode.right = nextUp;
          nextUp.left = newNode;
        }

        //update nextUp and lastUp for this new level
      }


    }
  }

  public static void main(String[] args){ //used for testing purposes
    SkipList test = new SkipList();
    SkipListNode testNode = new SkipListNode("test pass", null, null, null, null);
    SkipListNode testNode2 = new SkipListNode("atest pass", null, null, null, null);
    test.insert(testNode);
    test.insert(testNode2);
    boolean empt = test.isEmpty();
    boolean coinTest = flipCoin();
    System.out.println(coinTest);
    coinTest = flipCoin();
    System.out.println(coinTest);
    coinTest = flipCoin();
    System.out.println(coinTest);
  }
}
