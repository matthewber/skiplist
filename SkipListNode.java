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

  public SkipListNode(String item){
    this(item, null, null, null, null);
  }

  public SkipListNode(){
    this(null, null, null, null, null);
  }

  public boolean isEnd(){//returns true if this Node is end of its list
    if(right == null){
      return true;
    }
    return false;
  }

  public boolean isStart(){//returns true if this Node is the start of its list
    if(left == null){
      return true;
    }
    return false;
  }

  public boolean isUp(){//returns true if this Node is also on the level one up from it
    if(up == null){
      return false;
    }
    return true;
  }

  public static void main(String[] args){
    SkipListNode test = new SkipListNode();
  }

}
