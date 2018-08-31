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

  /**
  * isEmpty()
  * @return boolean value indicating whether or not the list is currently isEmpty
  */
  public boolean isEmpty(){
    if(head == null){
      return true;
    }
    return false;
  }

  /**
  * flipCoin() returns a boolean value at Psuedo-Random
  */
  public static boolean flipCoin(){
    Random rand = new Random();
    int result = rand.nextInt(2);//returns 0 or 1
    if(result == 1){
      return true;
    }
    return false;
  }

  /**
  * addToHigherLevels() is a helper function for insert()
  * recursively adds a node to higher levels
  * @param SkipListNode node to be added
  */
  private void addToHigherLevels(SkipListNode node){
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
    while(!traversal.isStart()){
      if(traversal.left.isUp()){
        lastUp(traversal.left.up, node.up);
        break;
      }
      traversal = traversal.left;
    }

    traversal = node;
    while(!traversal.isEnd()){
      if(traversal.right.isUp()){
        nextUp(traversal.right.up, node.up);
        break;
      }
      traversal = traversal.right;
    }

    addToHigherLevels(higherLevel);
  }

  /**
  * lastUp() is a helper function for addToHigherLevels()
  * attaches a new node to another on its left
  * @param SkipListNode node to be attached
  * @param SkipListNode lUp next node on the higher level to be attached to
  */
  private void lastUp(SkipListNode lUp, SkipListNode node){
    lUp.right = node;
    node.left = lUp;
  }

  /**
  * nextUp() is a helper function for addToHigherLevels()
  * attaches a new node to another on its right
  * @param SkipListNode node to be attached
  * @param SkipListNode nUp next node on the higher level to be attached to
  */
  private void nextUp(SkipListNode nUp, SkipListNode node){
    nUp.left = node;
    node.right = nUp;
  }

  /**
  * insertFrontOfList() is a helped function for insert()
  * inserts a node into the front of the list
  * @param SkipListNode node to be inserted
  */
  private void insertFrontOfList(SkipListNode node){
    head.left = node;
    node.right = head;
    head = node;
    addToHigherLevels(node);
  }

  /**
  * insertBottomRow() is a helped function for insert()
  * inserts a node into a predetermined location
  * @param SkipListNode node to be inserted
  * @param SkipListNode location (to be inserted to the right of locationNode)
  */
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

  /**
  * insert() inserts a node into the list
  * @param SkipListNode node to be inserted
  */
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

  /**
  * printBottomRow() prints each element in the list to stdout
  * used for testing purposes
  */
  public void printBottomRow(){
    SkipListNode i = head;
    while(i != null){
      System.out.println(i.item);
      i = i.right;
    }
  }

  /**
  * deleteLevels() is a helper function for delete()
  * used to recursively remove a node from all levels
  * @param SkipListNode node to be deleted
  */
  private void deleteLevels(SkipListNode deleted){
    breakDown(deleted);
    if(deleted.isStart()){
      deleteStart(deleted);
    }else if(deleted.isEnd()){
      deleteEnd(deleted);
    }else if(deleted.isMiddle()){
      deleteMiddle(deleted);
    }//if single nothing needs to be done
    if(deleted.isUp()){
      deleteLevels(deleted.up);
    }

  }

  /**
  * deleteMiddle() deletes node from inbetween two nodes on a single layer
  * @param SkipListNode node to be deleted
  */
  private void deleteMiddle(SkipListNode node){
    if(node.right == null || node.left == null){
      System.err.println("error: deleteMiddle() called incorrectly");
      return;
    }else{
      node.left.right = node.right;
      node.right.left = node.left;
    }
  }

  /**
  * deleteEnd() deletes node from the end of a single layer
  * @param SkipListNode node to be deleted
  */
  private void deleteEnd(SkipListNode node){
    if(node.left == null){
      System.err.println("error: deleteEnd() called incorrectly");
      return;
    }else{
      node.left.right = null;
    }
  }

  /**
  * deleteStart() deletes node from the start of a single layer
  * doesn't update head pointer
  * @param SkipListNode node to be deleted
  */
  private void deleteStart(SkipListNode node){
    if(node.left != null || node.right == null){
      System.err.println("error: deleteStart() called incorrectly");
      return;
    }else{
      node.right.left = null;
      node.right = null;
    }
  }

  /**
  * breakDown() breaks the connection to a lower node if it exists
  * @param SkipListNode node to be deleted
  */
  private void breakDown(SkipListNode node){
    if(node.down == null){
      return;
    }
    node.down.up = null;
    node.down = null;
  }

  /**
  * moveUp() moves the pointer as far up as possible
  * @param SkipListNode node pointer to be moved
  */
  private void moveUp(SkipListNode node){
    while(node.up != null){
      node = node.up;
    }
  }

  /**
  * moveTowardsSolution() moves the node pointer one step closer to where
  * name would be located if it exists.
  * @return boolean : false if Solution doesn't exist, true otherwise
  */
  private boolean moveTowardSolution(SkipListNode node, String name){
    if(node.isBigger(name) && node.right != null){
      node = node.right;
      return true;
    }else{
      if(node.down == null){
        return false;
      }else{
        node = node.down;
        return true;
      }
    }

  }

  /**
  * find() is a recursive helper function for the retrieve() method
  * @param SkipListNode currNode (passed as head on initial call)
  * @param String name of node to be found
  */
  private SkipListNode find(String name, SkipListNode currNode){
    if(currNode.isEqual(name)){
      return currNode;
    }else{
      if(currNode.isBigger(name) && currNode.right != null){
        return find(name, currNode.right);
      }else if(currNode.down != null){
        return find(name, currNode.down);
      }
    }
    return null;
  }

  /**
  * retrieve() is used to find if an item exists within the list
  * @param String name to be FileNotFoundException
  * @return SkipListNode containing item
  * @return null if item doesn't exist in list
  */
  public SkipListNode retrieve(String item){
    SkipListNode node = head;
    moveUp(node);
    node = find(item, node);
    return node;
  }

  /**
  * delete() removes an item from the List
  * @param SkipListNode node to be deleted
  */
  public void delete(String item){
    SkipListNode node = retrieve(item);
    if(node == null){

    }else if(node.isSingle()){
      head = null;
    }else{
      deleteLevels(node);
    }
  }

  public static void main(String[] args){ //used for testing purposes
    SkipList test = new SkipList();
    SkipListNode testNode = new SkipListNode("test pass");
    SkipListNode testNode2 = new SkipListNode("zzzz");
    SkipListNode testNode3 = new SkipListNode("abc");
    SkipListNode testNode4 = new SkipListNode("aaa");
    test.insert(testNode);
    test.insert(testNode2);
    test.insert(testNode3);
    test.insert(testNode4);
    test.delete("abc");
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
