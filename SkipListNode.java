public class SkipListNode{

  public SkipListNode left;
  public SkipListNode right;
  public SkipListNode up;
  public SkipListNode down;
  public String item;

  public SkipListNode(String item, SkipListNode left, SkipListNode right, SkipListNode up, SkipListNode down){
    this.item = item;
    this.left = left;
    this.right = right;
    this.up = up;
    this.down = down;
  }

  public SkipListNode(){
    this(null, null, null, null, null);
  }

  public static void main(String[] args){
    SkipListNode test = new SkipListNode();
  }

}
