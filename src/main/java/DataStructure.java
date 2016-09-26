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
   *
   * @param s
   * @return
   */
  public boolean isParenthesisValid(String s) {
    int sLen = s.length();
    boolean result = true;
    if (sLen > 0) {
      Stack<Character> parenthesises = new Stack<>();
      for (int i = 0; i < sLen; ++i) {
        if (result) {
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
              if (!parenthesises.empty() && parenthesises.peek() == '(') {
                parenthesises.pop();
              } else {
                result = false;
              }
              break;
            case '}':
              if (!parenthesises.empty() && parenthesises.peek() == '{') {
                parenthesises.pop();
              } else {
                result = false;
              }
              break;
            case ']':
              if (!parenthesises.empty() && parenthesises.peek() == '[') {
                parenthesises.pop();
              } else {
                result = false;
              }
              break;
          }

        } else {
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

    ListNode(int x) {
      val = x;
    }
  }

  public boolean listEqual(ListNode l1, ListNode l2) {
    boolean equal = true;
    while (l1 != null && l2 != null) {
      if (l1.val != l2.val) {
        equal = false;
        break;
      }
      l1 = l1.next;
      l2 = l2.next;
    }
    equal = equal && l1 == null && l2 == null;
    return equal;
  }

  public ListNode createList(int[] inputs) {
    int inputLen = inputs.length;
    ListNode head = null;
    if (inputLen > 0) {
      head = new ListNode(inputs[0]);
      ListNode currHead = head;
      for (int i = 1; i < inputLen; ++i) {
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
   *
   * @param l1
   * @param l2
   * @return
   */
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    }
    ListNode head;
    if (l1.val < l2.val) {
      head = l1;
      l1 = l1.next;
    } else {
      head = l2;
      l2 = l2.next;
    }
    ListNode currHead = head;
    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        currHead.next = l1;
        l1 = l1.next;

      } else {
        currHead.next = l2;
        l2 = l2.next;
      }
      currHead = currHead.next;
    }
    while (l1 != null) {
      currHead.next = l1;
      l1 = l1.next;
      currHead = currHead.next;
    }
    while (l2 != null) {
      currHead.next = l2;
      l2 = l2.next;
      currHead = currHead.next;
    }
    return head;
  }

  /**
   * leetcode 24 Given a linked list, swap every two adjacent nodes and return its head.
   * For example,Given 1->2->3->4, you should return the list as 2->1->4->3.
   *
   * @param head
   * @return
   */
  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode pre, first, second, res;
    pre = new ListNode(0); //dummy node
    first = head;
    second = head.next;
    res = head.next;
    while (second != null) {
      pre.next = second;
      first.next = second.next;
      second.next = first;
      pre = first;
      first = first.next;
      if (first == null) {
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

    TreeNode(int x) {
      val = x;
    }
  }

  public static class TreeCodec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
      if (root == null) {
        return "#";
      }
      List<Integer> serializedNodes = new ArrayList<>();
      List<TreeNode> nodes = new LinkedList<>();
      TreeNode currNode = root;
      serializedNodes.add(currNode.val);
      while (currNode != null) {
        if (currNode.left != null) {
          nodes.add(currNode.left);
          serializedNodes.add(currNode.left.val);
        } else {
          serializedNodes.add(null);
        }
        if (currNode.right != null) {
          serializedNodes.add(currNode.right.val);
          nodes.add(currNode.right);
        } else {
          serializedNodes.add(null);
        }
        if (!nodes.isEmpty()) {
          currNode = nodes.remove(0);
        } else {
          currNode = null;
        }
      }

      StringBuilder resBuilder = new StringBuilder();
      int serializedNodesLen = serializedNodes.size();
      for (int i = serializedNodesLen - 1; i >= 0; --i) {
        if (serializedNodes.get(i) == null) {
          serializedNodesLen--;
        } else {
          break;
        }
      }
      for (int i = 0; i < serializedNodesLen; ++i) {
        Integer currInteger = serializedNodes.get(i);
        if (currInteger != null) {
          resBuilder.append(currInteger.toString());
        } else {
          resBuilder.append('#');
        }
        resBuilder.append(',');
      }
      resBuilder.deleteCharAt(resBuilder.length() - 1); //trim last ','
      return resBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
      if (data.equals("#")) {
        return null;
      }
      String[] tokens = data.split(",");
      List<Integer> integers = new ArrayList<>();
      int tokensLen = tokens.length;
      for (int i = 0; i < tokensLen; ++i) {
        if (tokens[i].equals("#")) {
          integers.add(null);
        } else {
          integers.add(Integer.parseInt(tokens[i]));
        }
      }
      int integersLen = integers.size();
      List<TreeNode> nodeToProcess = new LinkedList<>();
      TreeNode root = new TreeNode(integers.get(0));
      nodeToProcess.add(root);
      int i;
      for (i = 1; i < integersLen - 1; i = i + 2) {
        TreeNode curr = nodeToProcess.remove(0);
        Integer leftInt = integers.get(i);
        Integer rightInt = integers.get(i + 1);
        if (leftInt != null) {
          TreeNode leftChild = new TreeNode(leftInt);
          curr.left = leftChild;
          nodeToProcess.add(leftChild);
        }
        if (rightInt != null) {
          TreeNode rightChild = new TreeNode(rightInt);
          curr.right = rightChild;
          nodeToProcess.add(rightChild);
        }
      }
      if (!nodeToProcess.isEmpty() && i < integersLen) {
        TreeNode curr = nodeToProcess.remove(0);
        curr.left = new TreeNode(integers.get(i));
      }
      return root;
    }
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    List<List<Integer>> res = new ArrayList<>();
    if (root != null) {
      queue.offer(root);
      int level = 0;
      while (!queue.isEmpty()) {
        List<Integer> levelList = new ArrayList<>();
        int queueSize = queue.size();
        for (int i = 0; i < queueSize; ++i) {
          TreeNode currNode = queue.poll();
          if (level % 2 == 0) {
            levelList.add(currNode.val);
          } else {
            levelList.add(0, currNode.val);
          }
          if (currNode.left != null) {
            queue.offer(currNode.left);
          }
          if (currNode.right != null) {
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
    if (k == 0) {
      return null;
    }
    PriorityQueue<ListNode> pq = new PriorityQueue<>(k, (ListNode n1, ListNode n2) -> n1.val - n2.val);

    for (int i = 0; i < k; ++i) {
      if (lists[i] != null) {
        pq.offer(lists[i]);
      }
    }
    if (pq.isEmpty()) {
      return null;
    }
    ListNode minimum = pq.poll();
    if (minimum.next != null) {
      pq.add(minimum.next);
    }
    ListNode itr = new ListNode(minimum.val);
    ListNode root = itr;
    while (!pq.isEmpty()) {
      minimum = pq.poll();
      if (minimum.next != null) {
        pq.offer(minimum.next);
      }
      itr.next = new ListNode(minimum.val);
      itr = itr.next;
    }
    return root;
  }

  /**
   * leetcode-218 skyline problem
   * brute force solution
   * will have memory limitation error when have input such as [[0,2147483647,2147483647]]
   */
  public List<int[]> getSkylineBruteForce(int[][] buildings) {
    int buildingCount = buildings.length;
    List<int[]> ans = new ArrayList<int[]>();
    if (buildingCount > 0) {
      //find right most building end
      int rightEnd = 0;
      for (int i = 0; i < buildingCount; ++i) {
        if (buildings[i][1] > rightEnd) {
          rightEnd = buildings[i][1];
        }
      }
      int[] tallestPoints = new int[rightEnd];
      for (int i = 0; i < buildingCount; ++i) {
        for (int j = buildings[i][0]; j < buildings[i][1]; ++j) {
          if (buildings[i][2] > tallestPoints[j]) {
            tallestPoints[j] = buildings[i][2];
          }
        }
      }

      int currHeight = 0;
      for (int i = 0; i < rightEnd; ++i) {
        if (tallestPoints[i] != currHeight) {
          int[] anchorPoint = new int[2];
          anchorPoint[0] = i;
          anchorPoint[1] = tallestPoints[i];
          currHeight = tallestPoints[i];
          ans.add(anchorPoint);
        }
      }
      int[] endpoint = new int[2];
      endpoint[0] = rightEnd;
      endpoint[1] = 0;
      ans.add(endpoint);
    }
    return ans;
  }

  /**
   * 218 skyline problem
   * use max heap and customized comparator to sort input
   */
  public class skylineComparator implements Comparator<int[]> {
    @Override
    public int compare(int[] a, int[] b) {
      if (a[0] == b[0]) {
        if (a[2] == b[2]) {
          if (a[2] == 0) {
            //both are start points
            return b[1] - a[1];
          } else {
            //both are end points
            return a[1] - b[1];
          }
        } else {
          //if at the same point, start point always comes before end point
          if (a[2] == 0) {
            return -1;
          } else {
            return 1;
          }
        }
      } else {
        return a[0] - b[0];
      }
    }
  }

  /**
   * TODO: use TreeMap instead of max heap to add and remove points
   *
   * @param buildings
   * @return
   */
  public List<int[]> getSkyline(int[][] buildings) {
    int buildingsCount = buildings.length;
    List<int[]> ans = new ArrayList<int[]>();
    if (buildingsCount > 0) {
      List<int[]> keyPoints = new ArrayList<>();
      for (int i = 0; i < buildingsCount; ++i) {
        keyPoints.add(new int[]{buildings[i][0], buildings[i][2], 0});
        keyPoints.add(new int[]{buildings[i][1], buildings[i][2], 1});
      }
      Collections.sort(keyPoints, new skylineComparator());

      PriorityQueue<Integer> pq = new PriorityQueue<>(buildingsCount + 1, (a, b) -> b - a);
      pq.offer(0);
      for (int[] keyPoint : keyPoints) {
        if (keyPoint[2] == 0) {
          if (pq.peek() < keyPoint[1]) {
            ans.add(new int[]{keyPoint[0], keyPoint[1]});
          }
          pq.offer(keyPoint[1]);
        }
        if (keyPoint[2] == 1) {
          pq.remove(keyPoint[1]);
          if (pq.peek() < keyPoint[1]) {
            ans.add(new int[]{keyPoint[0], pq.peek()});
          }
        }
      }
    }
    return ans;
  }

  /**
   * leetcode 366
   * Find Leaves of Binary Tree
   * The goal of this problem is to list out in which order the leaves would "fall" off a tree, a node only falls from the tree
   * if it has no children. At each step, several leaves will fall, giving an ordered list of sets of leaves that have fallen
   */
  public List<List<TreeNode>> fallingLeaves(TreeNode root) {
    List<List<TreeNode>> ans = new ArrayList<>();
    if (root != null) {
      Map<Integer, List<TreeNode>> nodeLevelMap = new HashMap<>();
      int maxLevel = helper_fallingLeaves(root, nodeLevelMap);
      for (int i = 0; i <= maxLevel; ++i) {
        ans.add(nodeLevelMap.get(i));
      }
    }
    return ans;
  }

  private int helper_fallingLeaves(TreeNode curr, Map<Integer, List<TreeNode>> nodeLevelMap) {
    int currHeight;
    if (curr == null) {
      currHeight = Integer.MIN_VALUE;
    } else {
      if (curr.left == null && curr.right == null) {
        currHeight = 0;
      } else {
        int leftHeight = helper_fallingLeaves(curr.left, nodeLevelMap);
        int rightHeight = helper_fallingLeaves(curr.right, nodeLevelMap);
        currHeight = Math.max(leftHeight, rightHeight) + 1;
      }
      if (nodeLevelMap.containsKey(currHeight)) {
        List<TreeNode> nodes = nodeLevelMap.get(currHeight);
        nodes.add(curr);
      } else {
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(curr);
        nodeLevelMap.put(currHeight, nodes);
      }
    }
    return currHeight;
  }

  public static class SegmentTree {
    private int[] _segTree;
    private int[] _input;
    private int _inputLen;

    public enum QueryType {
      SUM_RANGE,
      MIN_RANGE,
      MAX_RANGE
    }

    private QueryType _queryType;

    public SegmentTree(int[] input, QueryType type) {
      _input = input;
      _inputLen = input.length;
      if (_inputLen > 0) {
        //allocate enough memory for a complete balanced binary tree
        int height = (int) Math.ceil(Math.log(_inputLen) / Math.log(2));
        _segTree = new int[2 * (int) Math.pow(2, height) - 1];
        _queryType = type;
        switch (_queryType) {
          case SUM_RANGE:
            buildSumSegTreeFromInput(0, 0, _inputLen - 1);
            break;
          case MIN_RANGE:
            buildMinSegTreeFromInput(0, 0, _inputLen - 1);
            break;
          case MAX_RANGE:
            buildMaxSegTreeFromInput(0, 0, _inputLen - 1);
            break;
          default:
            buildSumSegTreeFromInput(0, 0, _inputLen - 1);
            break;
        }
      }
    }

    private int buildSumSegTreeFromInput(int currIndex, int rangeStart, int rangeEnd) {
      if (rangeStart == rangeEnd) {
        _segTree[currIndex] = _input[rangeStart];
      } else {
        int rangeMid = (rangeStart + rangeEnd) / 2;
        int leftChild = buildSumSegTreeFromInput(2 * currIndex + 1, rangeStart, rangeMid);
        int rightChild = buildSumSegTreeFromInput(2 * currIndex + 2, rangeMid + 1, rangeEnd);
        _segTree[currIndex] = leftChild + rightChild;
      }
      return _segTree[currIndex];
    }

    private int buildMinSegTreeFromInput(int currIndex, int rangeStart, int rangeEnd) {
      if (rangeStart == rangeEnd) {
        _segTree[currIndex] = _input[rangeStart];
      } else {
        int rangeMid = (rangeStart + rangeEnd) / 2;
        int leftChild = buildMinSegTreeFromInput(2 * currIndex + 1, rangeStart, rangeMid);
        int rightChild = buildMinSegTreeFromInput(2 * currIndex + 2, rangeMid + 1, rangeEnd);
        _segTree[currIndex] = Math.min(leftChild, rightChild);
      }
      return _segTree[currIndex];
    }

    private int buildMaxSegTreeFromInput(int currIndex, int rangeStart, int rangeEnd) {
      if (rangeStart == rangeEnd) {
        _segTree[currIndex] = _input[rangeStart];
      } else {
        int rangeMid = (rangeStart + rangeEnd) / 2;
        int leftChild = buildMaxSegTreeFromInput(2 * currIndex + 1, rangeStart, rangeMid);
        int rightChild = buildMaxSegTreeFromInput(2 * currIndex + 2, rangeMid + 1, rangeEnd);
        _segTree[currIndex] = Math.max(leftChild, rightChild);
      }
      return _segTree[currIndex];
    }

    public int query(int queryStart, int queryEnd) {
      int ans;
      switch (_queryType) {
        case SUM_RANGE:
          ans = querySum(0, 0, _inputLen - 1, queryStart, queryEnd);
          break;
        case MIN_RANGE:
          ans = queryMin(0, 0, _inputLen - 1, queryStart, queryEnd);
          break;
        case MAX_RANGE:
          ans = queryMax(0, 0, _inputLen - 1, queryStart, queryEnd);
          break;
        default:
          ans = querySum(0, 0, _inputLen - 1, queryStart, queryEnd);
          break;
      }
      return ans;
    }

    private int querySum(int currIndex, int rangeStart, int rangeEnd, int queryStart, int queryEnd) {
      int ans;
      if (rangeStart >= queryStart && rangeEnd <= queryEnd) {
        ans = _segTree[currIndex];
      } else if (queryEnd < rangeStart || queryStart > rangeEnd) {
        return 0;
      } else {
        int rangeMid = (rangeStart + rangeEnd) / 2;
        int leftChild = querySum(2 * currIndex + 1, rangeStart, rangeMid, queryStart, queryEnd);
        int rightChild = querySum(2 * currIndex + 2, rangeMid + 1, rangeEnd, queryStart, queryEnd);
        ans = leftChild + rightChild;
      }
      return ans;
    }

    private int queryMin(int currIndex, int rangeStart, int rangeEnd, int queryStart, int queryEnd) {
      int ans;
      if (rangeStart >= queryStart && rangeEnd <= queryEnd) {
        ans = _segTree[currIndex];
      } else if (queryEnd < rangeStart || queryStart > rangeEnd) {
        return Integer.MAX_VALUE;
      } else {
        int rangeMid = (rangeStart + rangeEnd) / 2;
        int leftChild = queryMin(2 * currIndex + 1, rangeStart, rangeMid, queryStart, queryEnd);
        int rightChild = queryMin(2 * currIndex + 2, rangeMid + 1, rangeEnd, queryStart, queryEnd);
        ans = Math.min(leftChild, rightChild);
      }
      return ans;
    }

    private int queryMax(int currIndex, int rangeStart, int rangeEnd, int queryStart, int queryEnd) {
      int ans;
      if (rangeStart >= queryStart && rangeEnd <= queryEnd) {
        ans = _segTree[currIndex];
      } else if (queryEnd < rangeStart || queryStart > rangeEnd) {
        return Integer.MIN_VALUE;
      } else {
        int rangeMid = (rangeStart + rangeEnd) / 2;
        int leftChild = queryMax(2 * currIndex + 1, rangeStart, rangeMid, queryStart, queryEnd);
        int rightChild = queryMax(2 * currIndex + 2, rangeMid + 1, rangeEnd, queryStart, queryEnd);
        ans = Math.max(leftChild, rightChild);
      }
      return ans;
    }

    public void update(int updateIndex, int updateValue) {
      int originalValue = _input[updateIndex];
      int delta = updateValue - originalValue;
      _input[updateIndex] = updateValue;
      int segTreeIndex;
      switch (_queryType) {
        case SUM_RANGE:
          updateSum(0, 0, _inputLen - 1, updateIndex, delta);
          break;
        case MIN_RANGE:
          segTreeIndex = findSegTreeIndex(0, 0, _inputLen - 1, updateIndex);
          updateMin(segTreeIndex, updateValue);
          break;
        case MAX_RANGE:
          segTreeIndex = findSegTreeIndex(0, 0, _inputLen - 1, updateIndex);
          updateMax(segTreeIndex, updateValue);
          break;
        default:
          updateSum(0, 0, _inputLen - 1, updateIndex, delta);
          break;
      }
    }

    private void updateSum(int currIndex, int rangeStart, int rangeEnd, int updateIndex, int delta) {

      if (rangeStart <= updateIndex && rangeEnd >= updateIndex) {
        _segTree[currIndex] += delta;
        if (rangeStart == updateIndex && rangeEnd == updateIndex) {
          return;
        }
        int rangeMid = (rangeStart + rangeEnd) / 2;
        updateSum(2 * currIndex + 1, rangeStart, rangeMid, updateIndex, delta);
        updateSum(2 * currIndex + 2, rangeMid + 1, rangeEnd, updateIndex, delta);
      }
    }

    private int findSegTreeIndex(int currIndex, int rangeStart, int rangeEnd, int updateIndex) {
      if (rangeStart <= updateIndex && rangeEnd >= updateIndex) {
        if (rangeStart == updateIndex && rangeEnd == updateIndex) {
          return currIndex;
        }
        int rangeMid = (rangeStart + rangeEnd) / 2;
        int leftChild = findSegTreeIndex(2 * currIndex + 1, rangeStart, rangeMid, updateIndex);
        int rightChild = findSegTreeIndex(2 * currIndex + 2, rangeMid + 1, rangeEnd, updateIndex);
        return leftChild == -1 ? rightChild : leftChild;
      }
      return -1;

    }

    private void updateMin(int segTreeIndex, int updateValue) {
      if (segTreeIndex == -1) {
        return;
      }
      _segTree[segTreeIndex] = updateValue;
      while (segTreeIndex > 0) {
        segTreeIndex = (segTreeIndex - 1) / 2;
        _segTree[segTreeIndex] = Math.min(_segTree[2 * segTreeIndex + 1], _segTree[2 * segTreeIndex + 2]);
      }

    }

    private void updateMax(int segTreeIndex, int updateValue) {
      if (segTreeIndex == -1) {
        return;
      }
      _segTree[segTreeIndex] = updateValue;
      while (segTreeIndex > 0) {
        segTreeIndex = (segTreeIndex - 1) / 2;
        _segTree[segTreeIndex] = Math.max(_segTree[2 * segTreeIndex + 1], _segTree[2 * segTreeIndex + 2]);
      }
    }
  }

  /**
   * * A tournament tree is a binary tree
   * where the parent is the minimum of the two children.
   * Given a tournament tree find the second minimum value in the tree.
   * A node in the tree will always have 2 or 0 children.
   * Also all leaves will have distinct and unique values.
   *
   * @param root
   * @return
   */
  public Integer tournamentTree2ndMinimum(TreeNode root) {
    if (root == null) {
      return null;
    }
    if (root.left == null || root.right == null) {
      return null;
    }
    int secondMin = Integer.MAX_VALUE;
    return helper_TournamentTree(root, secondMin);
  }

  public Integer helper_TournamentTree(TreeNode root, int currMax) {
    if (root.left == null || root.right == null) {
      return currMax;
    }
    if (root.left.val > root.right.val) {
      currMax = Math.min(currMax, root.left.val);
      return helper_TournamentTree(root.right, currMax);
    } else {
      currMax = Math.min(currMax, root.right.val);
      return helper_TournamentTree(root.left, currMax);
    }
  }

  /**
   * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
   *
   * @param board sodoku board
   * @return true if it's valid board, false otherwise
   */
  public boolean isValidSudoku(char[][] board) {
    for (int i = 0; i < 9; ++i) {
      boolean[] horizonalArray = new boolean[9];
      boolean[] verticalArray = new boolean[9];
      for (int j = 0; j < 9; ++j) {
        //check horizontal
        if (!checkAndSet(horizonalArray, board[i][j])) {
          return false;
        }
        //check vertical
        if (!checkAndSet(verticalArray, board[j][i])) {
          return false;
        }
      }
    }
    for (int i = 0; i < 9; i += 3) {
      for (int j = 0; j < 9; j += 3) {
        boolean[] matrixArray = new boolean[9];
        for (int k = 0; k < 9; ++k) {
          if (!checkAndSet(matrixArray, board[i + k / 3][j + k % 3])) {
            return false;
          }
        }
      }
    }
    return true;
  }

  private boolean checkAndSet(boolean[] numArray, char currChar) {
    if (currChar != '.') {
      int num = currChar - '0';
      if (num < 1 || num > 9 || numArray[num - 1]) {
        return false;
      } else {
        numArray[num - 1] = true;
      }
    }
    return true;
  }

  public static class MinStack {
    Stack<Element> eStack;

    static class Element {
      int val;
      int min;

      public Element(int v, int m) {
        val = v;
        min = m;
      }
    }

    /**
     * initialize your data structure here.
     */
    public MinStack() {
      eStack = new Stack<>();
    }

    public void push(int x) {
      if (eStack.size() == 0) {
        Element e = new Element(x, x);
        eStack.push(e);
      } else {
        int currMin = eStack.peek().min;
        if (currMin <= x) {
          eStack.push(new Element(x, currMin));
        } else {
          eStack.push(new Element(x, x));
        }
      }
    }

    public void pop() {
      eStack.pop();
    }

    public int top() {
      return eStack.peek().val;
    }

    public int getMin() {
      return eStack.peek().min;
    }
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) {
      return root;
    }
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    return left == null ? right : (right == null ? left : root);
  }

  class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<UndirectedGraphNode>();
    }
  }

  ;

  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node == null) {
      return null;
    }

    Map<Integer, UndirectedGraphNode> clonedNodeMap = new HashMap<>();
    Set<UndirectedGraphNode> visitedNode = new HashSet<>();
    Queue<UndirectedGraphNode> nodeQueue = new LinkedList<>();
    nodeQueue.offer(node);
    UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.label);
    clonedNodeMap.put(node.label, cloneNode);
    while (!nodeQueue.isEmpty()) {
      int queueSize = nodeQueue.size();
      for (int i = 0; i < queueSize; ++i) {
        UndirectedGraphNode currentNode = nodeQueue.poll();
        UndirectedGraphNode clonedCurrentNode = clonedNodeMap.get(currentNode.label);
        if (!visitedNode.contains(currentNode)) {
          visitedNode.add(currentNode);
          for (UndirectedGraphNode neighbor : currentNode.neighbors) {
            UndirectedGraphNode clonedNeighbor;
            if (clonedNodeMap.containsKey(neighbor.label)) {
              clonedNeighbor = clonedNodeMap.get(neighbor.label);
            } else {
              clonedNeighbor = new UndirectedGraphNode(neighbor.label);
              clonedNodeMap.put(neighbor.label, clonedNeighbor);
            }
            clonedCurrentNode.neighbors.add(clonedNeighbor);
            nodeQueue.offer(neighbor);
          }
        }
      }
    }
    return cloneNode;
  }

  public static class Interval{
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }

    @Override
    public boolean equals(Object obj){
      if(obj == null){
        return false;
      }
      if (!Interval.class.isAssignableFrom(obj.getClass())) {
        return false;
      }
      final Interval interval = (Interval) obj;
      return this.start == interval.start && this.end == interval.end;
    }
  }

  /**
   * leetcode 56 merge intervals
   * Given a collection of intervals, merge all overlapping intervals.
   * For example,
   * Given [1,3],[2,6],[8,10],[15,18],
   * return [1,6],[8,10],[15,18].
   * @param intervals
   * @return
   */
  public List<Interval> merge(List<Interval> intervals) {
    List<Interval> result = new ArrayList<>();
    if(intervals != null && !intervals.isEmpty()){
      Collections.sort(intervals,new IntervalComparator());
      Interval last = intervals.get(0);
      int intervalSize = intervals.size();
      for(int i=1;i<intervalSize;++i){
        Interval currentInterval = intervals.get(i);
        if(last.end >= currentInterval.start){
          last.end = Math.max(last.end,currentInterval.end);
        }
        else{
          result.add(last);
          last = currentInterval;
        }
      }
      result.add(last);
    }
    return result;
  }

  public class IntervalComparator implements Comparator<Interval> {
    public int compare(Interval i1, Interval i2){
      return i1.start - i2.start;
    }
  }
}
