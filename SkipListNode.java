public class SkipListNode{

  public SkipListNode left;
  public SkipListNode right;
  public SkipListNode up;
  public SkipListNode down;

  public SkipListNode(SkipListNode left, SkipListNode right, SkipListNode up, SkipListNode down){
    this.left = left;
    this.right = right;
    this.up = up;
    this.down = down;
  }

}
