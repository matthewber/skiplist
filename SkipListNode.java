public class SkipListNode{

  public SkipListNode left;
  public SkipListNode right;
  public SkipListNode up;
  public SkipListNode down;
  public String item;

  public SkipListNode(String item){
    this.item = item;
  }

  public SkipListNode(){
    this(null);
  }

  public boolean isSingle(){//returns true if this Node is the only node on its level
    if(right == null && left == null){
      return true;
    }
    return false;
  }

  public boolean isMiddle(){//returns true if Node has nodes on either side of it
    if(right != null && left != null){
      return true;
    }
    return false;
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

  public boolean isEqual(String compare){
    if(item == null){
      return false;
    }else{
      if(compare.compareTo(item) == 0){
        return true;
      }else{
        return false;
      }
    }
  }

  public boolean isBigger(String name){//returns true if the node containing name should appear after this node
    if(item == null){
      return false;
    }else{
      if(name.compareTo(item) > 0){
        return true;
      }else{
        return false;
      }
    }
  }

  public static void main(String[] args){
    SkipListNode test = new SkipListNode();
  }

}
