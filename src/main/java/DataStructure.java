import java.util.*;

public class DataStructure {
  /**
   * In a preferential instant runoff voting system, each voter ranks all candidates in order. To determine the winner, the following steps are followed:
   * (1) The first-rank votes for each candidate are tallied. If any candidate has more than 50% of the vote, that candidate wins
   * (2) If no candidate has more than(or equal to) 50% of the vote, the candidate with the lowest number of votes is removed.
   * That candidate is removed from each ballot, and the rankings are adjusted accordingly (so, voters who ranked the losing candidate first now rank their second candidate first)
   * (3) The process is repeated with the new rankings
   *
   * @param ballots list of ballots, each ballot has a ranked vote for candidates, i.e. abc means rank candidate a as first, b as second, c as the third.
   * @return the winner candidate
   */
  public char preferentialVoting(List<String> ballots) {
    int ballotSize = ballots.size();
    if (ballotSize == 0) {
      return ' ';
    }
    Map<String, Integer> ballotCount = new HashMap<>();
    Map<Character, Set<String>> candidateBallotDetail = new HashMap<>();
    Map<Character, Integer> candidateBallotCount = new HashMap<>();
    ArrayList<Character> loserList = new ArrayList<>();
    int magicNumberToWin = ballotSize % 2 == 0 ? ballotSize / 2 : ballotSize / 2 + 1;

    //calculate ballot once
    for (int i = 0; i < ballotSize; ++i) {
      String currentBallot = ballots.get(i);
      if (ballotCount.containsKey(currentBallot)) {
        ballotCount.put(currentBallot, ballotCount.get(currentBallot) + 1);
      } else {
        ballotCount.put(currentBallot, 1);
      }
      char firstRankedCandidate = currentBallot.charAt(0);
      Set<String> currentCandidateBallotDetail;
      if (candidateBallotDetail.containsKey(firstRankedCandidate)) {
        currentCandidateBallotDetail = candidateBallotDetail.get(firstRankedCandidate);
        currentCandidateBallotDetail.add(currentBallot);
      } else {
        currentCandidateBallotDetail = new HashSet<>();
        currentCandidateBallotDetail.add(currentBallot);
        candidateBallotDetail.put(firstRankedCandidate, currentCandidateBallotDetail);
      }
      if (candidateBallotCount.containsKey(firstRankedCandidate)) {
        candidateBallotCount.put(firstRankedCandidate, candidateBallotCount.get(firstRankedCandidate) + 1);
      } else {
        candidateBallotCount.put(firstRankedCandidate, 1);
      }
    }

    //first pass try to find winner without redistribute
    boolean findWinner = false;
    char winner = ballots.get(0).charAt(0);
    int minVoteFound = Integer.MAX_VALUE;
    char loser = ballots.get(0).charAt(0);
    while (!findWinner) {
      //check whether a candidate votes are more than threshold, at same time identify loser
      for (Character currCandidate : candidateBallotCount.keySet()) {
        int currCandidateVotes = candidateBallotCount.get(currCandidate);
        if (currCandidateVotes >= magicNumberToWin) {
          winner = currCandidate;
          findWinner = true;
          break;
        }
        if (currCandidateVotes < minVoteFound) {
          minVoteFound = currCandidateVotes;
          loser = currCandidate;
        }
      }
      loserList.add(loser);
      //redistribute votes if no winner found
      if (!findWinner) {
        for (char pastLoser : loserList) {
          Set<String> loserBallots = candidateBallotDetail.get(pastLoser);
          for (String ballot : loserBallots) {
            int ballotLength = ballot.length();
            for (int i = 0; i < ballotLength; ++i) {
              char candidateToConsider = ballot.charAt(i);
              if (!loserList.contains(candidateToConsider)) {
                candidateBallotCount.put(candidateToConsider, candidateBallotCount.get(candidateToConsider) + 1);
                break;
              }
            }
          }
        }
      }
    }
    return winner;
  }

  /**
   * leet code 20
   * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
   * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
   * @param s
   * @return
   */
  public boolean isParenthesisValid(String s) {
    int sLen = s.length();
    boolean result = true;
    if(sLen > 0){
      Stack<Character> parenthesises = new Stack<>();
      for(int i=0;i<sLen;++i){
        if(result){
          switch (s.charAt(i)) {
            case '(':
              parenthesises.push('(');
              break;
            case '{':
              parenthesises.push('{');
              break;
            case '[':
              parenthesises.push('[');
              break;
            case ')':
              if(!parenthesises.empty() && parenthesises.peek() == '('){
                parenthesises.pop();
              }else{
                result = false;
              }
              break;
            case '}':
              if(!parenthesises.empty() && parenthesises.peek() == '{'){
                parenthesises.pop();
              }else{
                result = false;
              }
              break;
            case ']':
              if(!parenthesises.empty() && parenthesises.peek() == '['){
                parenthesises.pop();
              }else{
                result = false;
              }
              break;
          }

        }else{
          break;
        }
      }
      result = result && parenthesises.empty();
    }
    return result;
  }

  public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
  }

  public boolean listEqual(ListNode l1, ListNode l2){
    boolean equal = true;
    while(l1 != null && l2 !=null){
      if(l1.val != l2.val){
        equal = false;
        break;
      }
      l1 = l1.next;
      l2 = l2.next;
    }
    equal = equal && l1 == null && l2 == null;
    return equal;
  }

  public ListNode createList(int[] inputs){
    int inputLen = inputs.length;
    ListNode head = null;
    if(inputLen > 0){
      head = new ListNode(inputs[0]);
      ListNode currHead = head;
      for(int i=1;i<inputLen;++i){
        currHead.next = new ListNode(inputs[i]);
        currHead = currHead.next;
      }
    }
    return head;
  }

  /**
   * leet code 21
   * Merge two sorted linked lists and return it as a new list.
   * The new list should be made by splicing together the nodes of the first two lists.
   * @param l1
   * @param l2
   * @return
   */
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if(l1 == null){
      return l2;
    }else if(l2 == null){
      return l1;
    }
    ListNode head;
    if(l1.val < l2.val){
      head = l1;
      l1 = l1.next;
    }else{
      head = l2;
      l2 = l2.next;
    }
    ListNode currHead = head;
    while(l1 != null && l2 != null){
      if(l1.val < l2.val){
        currHead.next = l1;
        l1 = l1.next;

      }else{
        currHead.next = l2;
        l2 = l2.next;
      }
      currHead = currHead.next;
    }
    while(l1 != null){
      currHead.next = l1;
      l1 = l1.next;
      currHead = currHead.next;
    }
    while(l2 != null){
      currHead.next = l2;
      l2 = l2.next;
      currHead = currHead.next;
    }
    return head;
  }

  /**
   * leetcode 24 Given a linked list, swap every two adjacent nodes and return its head.
   * For example,Given 1->2->3->4, you should return the list as 2->1->4->3.
   * @param head
   * @return
   */
  public ListNode swapPairs(ListNode head) {
    if(head == null || head.next == null){
      return head;
    }
    ListNode pre,first,second,res;
    pre = new ListNode(0); //dummy node
    first = head;
    second = head.next;
    res = head.next;
    while(second != null){
      pre.next = second;
      first.next = second.next;
      second.next = first;
      pre = first;
      first = first.next;
      if(first == null){
        break;
      }
      second = first.next;
    }
    return res;
  }

  //Definition for a binary tree node.
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }

  public static class TreeCodec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
      if(root == null){
        return "#";
      }
      List<Integer> serializedNodes = new ArrayList<>();
      List<TreeNode> nodes = new LinkedList<>();
      TreeNode currNode = root;
      serializedNodes.add(currNode.val);
      while(currNode != null){
        if(currNode.left != null){
          nodes.add(currNode.left);
          serializedNodes.add(currNode.left.val);
        }else{
          serializedNodes.add(null);
        }
        if(currNode.right != null){
          serializedNodes.add(currNode.right.val);
          nodes.add(currNode.right);
        }else{
          serializedNodes.add(null);
        }
        if(!nodes.isEmpty()){
          currNode = nodes.remove(0);
        }else{
          currNode = null;
        }
      }

      StringBuilder resBuilder = new StringBuilder();
      int serializedNodesLen = serializedNodes.size();
      for(int i=serializedNodesLen-1;i>=0;--i){
        if(serializedNodes.get(i) == null){
          serializedNodesLen--;
        }else{
          break;
        }
      }
      for(int i=0;i<serializedNodesLen;++i){
        Integer currInteger = serializedNodes.get(i);
        if(currInteger !=null){
          resBuilder.append(currInteger.toString());
        }else{
          resBuilder.append('#');
        }
        resBuilder.append(',');
      }
      resBuilder.deleteCharAt(resBuilder.length()-1); //trim last ','
      return resBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
      if(data.equals("#")){
        return null;
      }
      String[] tokens = data.split(",");
      List<Integer> integers = new ArrayList<>();
      int tokensLen = tokens.length;
      for(int i=0;i<tokensLen;++i){
        if(tokens[i].equals("#")){
          integers.add(null);
        }else{
          integers.add(Integer.parseInt(tokens[i]));
        }
      }
      int integersLen = integers.size();
      List<TreeNode> nodeToProcess = new LinkedList<>();
      TreeNode root = new TreeNode(integers.get(0));
      nodeToProcess.add(root);
      int i;
      for(i=1;i<integersLen-1;i=i+2){
        TreeNode curr = nodeToProcess.remove(0);
        Integer leftInt = integers.get(i);
        Integer rightInt = integers.get(i+1);
        if( leftInt != null){
          TreeNode leftChild = new TreeNode(leftInt);
          curr.left = leftChild;
          nodeToProcess.add(leftChild);
        }
        if(rightInt != null){
          TreeNode rightChild = new TreeNode(rightInt);
          curr.right = rightChild;
          nodeToProcess.add(rightChild);
        }
      }
      if(!nodeToProcess.isEmpty() && i<integersLen){
        TreeNode curr = nodeToProcess.remove(0);
        curr.left = new TreeNode(integers.get(i));
      }
      return root;
    }
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    List<List<Integer>> res = new ArrayList<>();
    if(root != null){
      queue.offer(root);
      int level = 0;
      while(!queue.isEmpty()){
        List<Integer> levelList = new ArrayList<>();
        int queueSize = queue.size();
        for(int i=0;i<queueSize;++i){
          TreeNode currNode = queue.poll();
          if(level%2 == 0){
            levelList.add(currNode.val);
          }else{
            levelList.add(0,currNode.val);
          }
          if(currNode.left!= null){
            queue.offer(currNode.left);
          }
          if(currNode.right != null){
            queue.offer(currNode.right);
          }

        }
        res.add(levelList);
        level++;
      }
    }
    return res;
  }

  /**
   * java heap questions
   * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
   */
  public ListNode mergeKLists(ListNode[] lists) {
    int k = lists.length;
    if(k == 0){
      return null;
    }
    PriorityQueue<ListNode> pq = new PriorityQueue<>(k, (ListNode n1, ListNode n2)-> n1.val - n2.val);

    for(int i=0;i<k;++i){
      if(lists[i] != null) {
        pq.offer(lists[i]);
      }
    }
    if(pq.isEmpty()){
      return null;
    }
    ListNode minimum = pq.poll();
    if(minimum.next != null){
      pq.add(minimum.next);
    }
    ListNode itr = new ListNode(minimum.val);
    ListNode root = itr;
    while(!pq.isEmpty()){
      minimum = pq.poll();
      if(minimum.next != null){
        pq.offer(minimum.next);
      }
      itr.next = new ListNode(minimum.val);
      itr = itr.next;
    }
    return root;
  }
}
