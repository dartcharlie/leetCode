import java.util.*;

public class LRUCache {
  public class doubleLinkedListNode {
    int k;
    int val;
    doubleLinkedListNode pre;
    doubleLinkedListNode next;
    public doubleLinkedListNode(int key, int value){
      k = key;
      val = value;
      pre = null;
      next = null;
    }
    public void setValue(int value){
      val = value;
    }
    public int getValue(){
      return val;
    }
    public int getKey(){
      return k;
    }
  }
  int _capacity, currVolume;
  Map<Integer,doubleLinkedListNode> cacheMap;
  doubleLinkedListNode head, tail;
  public LRUCache(int capacity) {
    _capacity = capacity;
    currVolume = 0;
    cacheMap = new HashMap<>();
    head = new doubleLinkedListNode(-1,-1);
    tail = new doubleLinkedListNode(-1,-1);
    head.next = tail;
    tail.pre = head;
  }

  public int get(int key) {
    if(cacheMap.containsKey(key)){
      doubleLinkedListNode currNode;
      currNode = cacheMap.get(key);
      removeNodeFromList(currNode);
      PutNodeToHead(currNode);
      return currNode.getValue();
    } else {
      return -1;
    }
  }

  public void set(int key, int value) {
    doubleLinkedListNode currNode;
    if(cacheMap.containsKey(key)){
      currNode = cacheMap.get(key);
      removeNodeFromList(currNode);
      currNode.setValue(value);
    } else {
      currNode = new doubleLinkedListNode(key,value);
      cacheMap.put(key,currNode);
      if(currVolume < _capacity){
        currVolume++;
      } else {
        doubleLinkedListNode nodeToBeRemoved = tail.pre;
        removeNodeFromList(nodeToBeRemoved);
        cacheMap.remove(nodeToBeRemoved.getKey());
      }
    }
    PutNodeToHead(currNode);
  }

  private void removeNodeFromList(doubleLinkedListNode node){
    node.pre.next = node.next;
    node.next.pre = node.pre;
  }

  private void PutNodeToHead(doubleLinkedListNode node){
    node.next = head.next;
    node.next.pre = node;
    node.pre = head;
    head.next = node;
  }
}
