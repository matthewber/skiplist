public class SkipList{

  private SkipListNode head;
  int num_levels = 1;
  private static int MAX_LEVELS = 10;

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

  public void insert(SkipListNode node){
    if(isEmpty()){
      this.head = node;
    }else{
      //variables used to find insert position
      SkipListNode lastUp = null;
      SkipListNode insertSpot = head;
      SkipListNode nextUp = null;
      SkipListNode nextElement = head;
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
      //sometimes add this node to higher levels
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


    }
  }

  public static void main(String[] args){ //used for testing purposes
    SkipList test = new SkipList();
    SkipListNode testNode = new SkipListNode("test pass", null, null, null, null);
    SkipListNode testNode2 = new SkipListNode("atest pass", null, null, null, null);
    test.insert(testNode);
    test.insert(testNode2);
    boolean empt = test.isEmpty();
    System.out.println(empt);
  }
}
