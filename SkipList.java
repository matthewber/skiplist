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
      SkipListNode nextUp = null;
      SkipListNode nextElement = head;
      //find spot to insert element
      while(node.item.compareTo(nextElement.item) > 0){
        //continue until spot found
        //if end of list reached break;
        if(nextElement.right == null){
          break;
        }
        nextElement = nextElement.right; //iterates through list
        if(nextElement.left.up != null){ //update lastup if valid
          lastUp = nextElement.left.up;
        }
      }
      SkipListNode insertSpot = nextElement;
      //continue iterating until
      while(nextElement.right != null){
        if(nextElement.up != null){
          nextUp = nextElement.up;
          break;
        }
        nextElement = nextElement.right;
      }
      //add node to spot before insertSpot
      //if insertSpot.right == null then insert to end of list
      //sometimes(random) add this node to higher levels

    }
  }

  public static void main(String[] args){ //used for testing purposes
    SkipList test = new SkipList();
    SkipListNode testNode = new SkipListNode("test pass", null, null, null, null);
    test.insert(testNode);
    boolean empt = test.isEmpty();
    System.out.println(empt);
  }
}
