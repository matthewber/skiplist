public class SkipList{

  public SkipListNode<String> head;

  public SkipList(SkipListNode<String> head){
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

  public void insert(SkipListNode<String> node){
    if(isEmpty()){
      this.head = node;
    }else{
      //add insert implementation
    }
  }

  public static void main(String[] args){
    SkipList test = new SkipList();
    SkipListNode<String> testNode = new SkipListNode<String>("test pass", null, null, null, null);
    test.insert(testNode);
    boolean empt = test.isEmpty();
    System.out.println(empt);
  }
}
