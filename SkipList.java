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
      }
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



  public void printBottomRow(){//used for testing purposes
    SkipListNode i = head;
    while(i != null){
      System.out.println(i.item);
      i = i.right;
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
    //test.insert(testNode4);
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
