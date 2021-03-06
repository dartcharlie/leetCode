import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class DataStructureTest {
  DataStructure _dataStructure;
  DataStructure.TreeCodec _treeCodec;

  @BeforeTest
  public void setup() {
    _dataStructure = new DataStructure();
    _treeCodec = new DataStructure.TreeCodec();
  }

  @Test
  public void preferentialVotingTest() {
    //basic case testing, a wins in the first round
    List<String> ballots_input1 = asList(new String[]{"abc", "acb", "bac", "cba", "bca", "abc"});
    Assert.assertEquals(_dataStructure.preferentialVoting(ballots_input1), 'a');

    //need redistribute vote to win case
    List<String> ballots_input2 = asList(new String[]{"abcd", "acbd", "bacd", "bcad", "cbad", "cabd", "dabc"});
    Assert.assertEquals(_dataStructure.preferentialVoting(ballots_input2), 'a');

    //need to count the 3rd candidates in some ballot to get winner
    List<String> ballots_input3 = asList(new String[]{"abcd", "acbd", "abcd", "acbd", "bdac", "bcad", "bcda", "cbda", "dcba"});
    Assert.assertEquals(_dataStructure.preferentialVoting(ballots_input3), 'b');

    List<String> ballots_input4 = asList(new String[]{"adc", "acd", "bcd", "bdc", "dab", "cba"});
    Assert.assertEquals(_dataStructure.preferentialVoting(ballots_input4), 'b');
  }

  @Test
  public void isParenthesisValidTest() {
    Assert.assertEquals(_dataStructure.isParenthesisValid(""), true);
    Assert.assertEquals(_dataStructure.isParenthesisValid("()"), true);
    Assert.assertEquals(_dataStructure.isParenthesisValid("()[]{}"), true);
    Assert.assertEquals(_dataStructure.isParenthesisValid("]"), false);
    Assert.assertEquals(_dataStructure.isParenthesisValid("(]"), false);
    Assert.assertEquals(_dataStructure.isParenthesisValid("([)]"), false);
  }

  @Test
  public void listEqualityTest() {
    DataStructure.ListNode l0 = _dataStructure.createList(new int[]{});
    DataStructure.ListNode l1 = _dataStructure.createList(new int[]{1, 2, 3, 4});
    DataStructure.ListNode l2 = _dataStructure.createList(new int[]{1, 2, 3, 4});
    Assert.assertEquals(_dataStructure.listEqual(l0, l0), true);
    Assert.assertEquals(_dataStructure.listEqual(l1, l2), true);
    DataStructure.ListNode l3 = _dataStructure.createList(new int[]{1, 2});
    DataStructure.ListNode l4 = _dataStructure.createList(new int[]{1, 2, 4, 3});
    Assert.assertEquals(_dataStructure.listEqual(l2, l3), false);
    Assert.assertEquals(_dataStructure.listEqual(l2, l4), false);
  }

  @Test
  public void mergeTwoListsTest() {
    DataStructure.ListNode l1 = _dataStructure.createList(new int[]{0, 2, 8, 15});
    DataStructure.ListNode l2 = _dataStructure.createList(new int[]{1, 7, 13, 14});
    DataStructure.ListNode l12 = _dataStructure.createList(new int[]{0, 1, 2, 7, 8, 13, 14, 15});
    DataStructure.ListNode l12Merged = _dataStructure.mergeTwoLists(l1, l2);
    Assert.assertEquals(_dataStructure.listEqual(l12Merged, l12), true);

    DataStructure.ListNode l3 = _dataStructure.createList(new int[]{-1, 2, 20, 22, 25, 30});
    DataStructure.ListNode l4 = _dataStructure.createList(new int[]{1, 7, 13, 14});
    DataStructure.ListNode l34 = _dataStructure.createList(new int[]{-1, 1, 2, 7, 13, 14, 20, 22, 25, 30});
    DataStructure.ListNode l34Merged = _dataStructure.mergeTwoLists(l3, l4);
    Assert.assertEquals(_dataStructure.listEqual(l34Merged, l34), true);

    DataStructure.ListNode l5 = _dataStructure.createList(new int[]{});
    DataStructure.ListNode l6 = _dataStructure.createList(new int[]{1, 7, 13, 14});
    DataStructure.ListNode l56 = _dataStructure.createList(new int[]{1, 7, 13, 14});
    DataStructure.ListNode l56Merged = _dataStructure.mergeTwoLists(l5, l6);
    Assert.assertEquals(_dataStructure.listEqual(l56Merged, l56), true);
  }

  @Test
  public void swapPairsTest() {
    DataStructure.ListNode head1 = _dataStructure.createList(new int[]{1, 2, 3, 4});
    DataStructure.ListNode expected_head1 = _dataStructure.createList(new int[]{2, 1, 4, 3});
    DataStructure.ListNode swaped_head1 = _dataStructure.swapPairs(head1);
    Assert.assertEquals(_dataStructure.listEqual(swaped_head1, expected_head1), true);

    DataStructure.ListNode head2 = _dataStructure.createList(new int[]{1, 2, 3, 4, 5, 6, 7});
    DataStructure.ListNode expected_head2 = _dataStructure.createList(new int[]{2, 1, 4, 3, 6, 5, 7});
    DataStructure.ListNode swaped_head2 = _dataStructure.swapPairs(head2);
    Assert.assertEquals(_dataStructure.listEqual(swaped_head2, expected_head2), true);

    DataStructure.ListNode head3 = _dataStructure.createList(new int[]{1, 2});
    DataStructure.ListNode expected_head3 = _dataStructure.createList(new int[]{2, 1});
    DataStructure.ListNode swaped_head3 = _dataStructure.swapPairs(head3);
    Assert.assertEquals(_dataStructure.listEqual(swaped_head3, expected_head3), true);
  }

  @Test
  public void TreeSerDeserTest() {

    String input1 = "1,2,3,#,#,4,5";
    Assert.assertEquals(_treeCodec.serialize(_treeCodec.deserialize(input1)), input1);

    String input2 = "#";
    Assert.assertEquals(_treeCodec.serialize(_treeCodec.deserialize(input2)), input2);

    String input3 = "1";
    Assert.assertEquals(_treeCodec.serialize(_treeCodec.deserialize(input3)), input3);

    String input4 = "1,2,3";
    Assert.assertEquals(_treeCodec.serialize(_treeCodec.deserialize(input4)), input4);

    String input5 = "1,#,2,3";
    Assert.assertEquals(_treeCodec.serialize(_treeCodec.deserialize(input5)), input5);

    String input6 = "5,4,7,3,#,2,#,-1,#,9";
    Assert.assertEquals(_treeCodec.serialize(_treeCodec.deserialize(input6)), input6);
  }

  @Test
  public void zigzagLevelOrderTest() {
    String input1 = "3,9,20,#,#,15,7";
    DataStructure.TreeNode root1 = _treeCodec.deserialize(input1);
    List<List<Integer>> expected1 = new ArrayList<>();
    expected1.add(asList(3));
    expected1.add(asList(20, 9));
    expected1.add(asList(15, 7));
    Assert.assertEquals(_dataStructure.zigzagLevelOrder(root1), expected1);

    String input2 = "1,2,3,4,#,#,5";
    DataStructure.TreeNode root2 = _treeCodec.deserialize(input2);
    List<List<Integer>> expected2 = new ArrayList<>();
    expected2.add(asList(new Integer[]{1}));
    expected2.add(asList(new Integer[]{3, 2}));
    expected2.add(asList(new Integer[]{4, 5}));
    Assert.assertEquals(_dataStructure.zigzagLevelOrder(root2), expected2);
  }

  @Test
  public void mergeKListsTest() {
    DataStructure.ListNode list11 = _dataStructure.createList(new int[]{4, 8, 12, 16});
    DataStructure.ListNode list12 = _dataStructure.createList(new int[]{3, 6, 9, 15});
    DataStructure.ListNode list13 = _dataStructure.createList(new int[]{2, 5, 11, 17, 19, 20});
    DataStructure.ListNode list14 = _dataStructure.createList(new int[]{1, 7, 10, 13, 14, 18});
    DataStructure.ListNode expected1 = _dataStructure.createList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20});

    DataStructure.ListNode[] listNodes1 = new DataStructure.ListNode[]{list11, list12, list13, list14};
    Assert.assertEquals(_dataStructure.listEqual(_dataStructure.mergeKLists(listNodes1), expected1), true);

    DataStructure.ListNode list21 = _dataStructure.createList(new int[]{});
    DataStructure.ListNode[] listNodes2 = new DataStructure.ListNode[]{list21};
    DataStructure.ListNode expected2 = _dataStructure.createList(new int[]{});
    Assert.assertEquals(_dataStructure.listEqual(_dataStructure.mergeKLists(listNodes2), expected2), true);

    DataStructure.ListNode list31 = _dataStructure.createList(new int[]{});
    DataStructure.ListNode list32 = _dataStructure.createList(new int[]{});
    DataStructure.ListNode[] listNodes3 = new DataStructure.ListNode[]{list31, list32};
    DataStructure.ListNode expected3 = _dataStructure.createList(new int[]{});
    Assert.assertEquals(_dataStructure.listEqual(_dataStructure.mergeKLists(listNodes3), expected3), true);

  }

  @Test
  public void getSkylineBruteForceTest() {
    int[][] buildings1 = new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
    List<int[]> expected_keyPoints1 = new ArrayList<>();
    expected_keyPoints1.add(new int[]{2, 10});
    expected_keyPoints1.add(new int[]{3, 15});
    expected_keyPoints1.add(new int[]{7, 12});
    expected_keyPoints1.add(new int[]{12, 0});
    expected_keyPoints1.add(new int[]{15, 10});
    expected_keyPoints1.add(new int[]{20, 8});
    expected_keyPoints1.add(new int[]{24, 0});
    List<int[]> program_output1 = _dataStructure.getSkylineBruteForce(buildings1);
    int outputLen1 = expected_keyPoints1.size();
    for (int i = 0; i < outputLen1; ++i) {
      Assert.assertTrue(Arrays.equals(program_output1.get(i), expected_keyPoints1.get(i)));
    }

    int[][] buildings2 = new int[][]{};
    List<int[]> expected_keyPoints2 = new ArrayList<>();
    Assert.assertEquals(_dataStructure.getSkylineBruteForce(buildings2), expected_keyPoints2);

    /* this test will fail with "Requested array size exceeds VM limit" error
    int[][] buildings3 = new int[][]{{0,2147483647,2147483647}};
    List<int[]> expected_keyPoints3 = new ArrayList<>();
    expected_keyPoints3.add(new int[]{0,2147483647});
    expected_keyPoints3.add(new int[]{2147483647,0});
    List<int[]> program_output3 = _dataStructure.getSkylineBruteForce(buildings3);
    int outputLen3 = program_output3.size();
    for(int i=0;i<outputLen3;++i){
      Assert.assertTrue(Arrays.equals(program_output3.get(i),expected_keyPoints3.get(i)));
    }
    */

  }

  @Test
  public void getSkylineTest() {
    int[][] buildings1 = new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
    List<int[]> expected_keyPoints1 = new ArrayList<>();
    expected_keyPoints1.add(new int[]{2, 10});
    expected_keyPoints1.add(new int[]{3, 15});
    expected_keyPoints1.add(new int[]{7, 12});
    expected_keyPoints1.add(new int[]{12, 0});
    expected_keyPoints1.add(new int[]{15, 10});
    expected_keyPoints1.add(new int[]{20, 8});
    expected_keyPoints1.add(new int[]{24, 0});
    List<int[]> program_output1 = _dataStructure.getSkyline(buildings1);
    int outputLen1 = expected_keyPoints1.size();
    Assert.assertEquals(outputLen1, program_output1.size());
    for (int i = 0; i < outputLen1; ++i) {
      Assert.assertTrue(Arrays.equals(program_output1.get(i), expected_keyPoints1.get(i)));
    }
    //corner case empty input
    int[][] buildings2 = new int[][]{};
    List<int[]> expected_keyPoints2 = new ArrayList<>();
    Assert.assertEquals(_dataStructure.getSkyline(buildings2), expected_keyPoints2);

    //corner case large range
    int[][] buildings3 = new int[][]{{0, 2147483647, 2147483647}};
    List<int[]> expected_keyPoints3 = new ArrayList<int[]>();
    expected_keyPoints3.add(new int[]{0, 2147483647});
    expected_keyPoints3.add(new int[]{2147483647, 0});
    List<int[]> program_output3 = _dataStructure.getSkyline(buildings3);
    int outputLen3 = expected_keyPoints3.size();
    Assert.assertEquals(outputLen3, program_output3.size());
    for (int i = 0; i < outputLen3; ++i) {
      Assert.assertTrue(Arrays.equals(program_output3.get(i), expected_keyPoints3.get(i)));
    }

    //corner case 2 buildings with the same height start and end at the same point
    int[][] buildings4 = new int[][]{{0, 2, 3}, {2, 5, 3}};
    List<int[]> expected_keyPoints4 = new ArrayList<int[]>();
    expected_keyPoints4.add(new int[]{0, 3});
    expected_keyPoints4.add(new int[]{5, 0});
    List<int[]> program_output4 = _dataStructure.getSkyline(buildings4);
    int outputLen4 = expected_keyPoints4.size();
    Assert.assertEquals(outputLen4, program_output4.size());
    for (int i = 0; i < outputLen4; ++i) {
      Assert.assertTrue(Arrays.equals(program_output4.get(i), expected_keyPoints4.get(i)));
    }

    //corner case 2 buildings with different height have exactly the same location and
    // 2 buildings with the same height start and end at the same point
    int[][] buildings5 = new int[][]{{0, 5, 7}, {5, 10, 7}, {5, 10, 12}, {10, 15, 7}, {15, 20, 7}, {15, 20, 12}, {20, 25, 7}};
    List<int[]> expected_keyPoints5 = asList(new int[][]{{0, 7}, {5, 12}, {10, 7}, {15, 12}, {20, 7}, {25, 0}});
    List<int[]> program_output5 = _dataStructure.getSkyline(buildings5);
    int outputLen5 = expected_keyPoints5.size();
    Assert.assertEquals(program_output5.size(), expected_keyPoints5.size());
    for (int i = 0; i < outputLen5; ++i) {
      Assert.assertTrue(Arrays.equals(program_output5.get(i), expected_keyPoints5.get(i)));
    }
  }

  @Test
  public void fallingLeavesTest() {
    String input1 = "#";
    DataStructure.TreeNode root1 = _treeCodec.deserialize(input1);
    Assert.assertEquals(_dataStructure.fallingLeaves(root1).size(), 0);

    String input2 = "11,7,17,3,8,13,25,2,4,#,#,#,16";
    DataStructure.TreeNode root2 = _treeCodec.deserialize(input2);
    List<List<Integer>> expected2 = new ArrayList<>();
    expected2.add(asList(2, 4, 8, 16, 25));
    expected2.add(asList(3, 13));
    expected2.add(asList(7, 17));
    expected2.add(asList(11));
    List<List<DataStructure.TreeNode>> actual2 = _dataStructure.fallingLeaves(root2);
    faillingLeavesTestHelper(actual2, expected2);

    String input3 = "1,2,#,3,#,4,#,5";
    DataStructure.TreeNode root3 = _treeCodec.deserialize(input3);
    List<List<Integer>> expected3 = new ArrayList<>();
    expected3.add(asList(new Integer[]{5}));
    expected3.add(asList(new Integer[]{4}));
    expected3.add(asList(new Integer[]{3}));
    expected3.add(asList(new Integer[]{2}));
    expected3.add(asList(new Integer[]{1}));
    List<List<DataStructure.TreeNode>> actual3 = _dataStructure.fallingLeaves(root3);
    faillingLeavesTestHelper(actual3, expected3);
  }

  private void faillingLeavesTestHelper(List<List<DataStructure.TreeNode>> actual, List<List<Integer>> expected) {
    int expectedListLen = expected.size();
    Assert.assertEquals(actual.size(), expectedListLen);
    for (int i = 0; i < expectedListLen; ++i) {
      int expectedLen = expected.get(i).size();
      Assert.assertEquals(actual.get(i).size(), expectedLen);
      for (int j = 0; j < expectedLen; ++j) {
        Assert.assertEquals(actual.get(i).get(j).val, expected.get(i).get(j).intValue());
      }
    }
  }

  @Test
  public void tournamentTreeTest() {
    String input1 = "2,2,3,4,2,5,3";
    DataStructure.TreeNode root1 = _treeCodec.deserialize(input1);
    Assert.assertEquals(_dataStructure.tournamentTree2ndMinimum(root1), new Integer(3));

    String input2 = "2,2,4,3,2,5,4";
    DataStructure.TreeNode root2 = _treeCodec.deserialize(input2);
    Assert.assertEquals(_dataStructure.tournamentTree2ndMinimum(root2), new Integer(3));

    String input3 = "2,2,40,30,2,#,#,#,#,2,6,#,#,6,8";
    DataStructure.TreeNode root3 = _treeCodec.deserialize(input3);
    Assert.assertEquals(_dataStructure.tournamentTree2ndMinimum(root3), new Integer(6));

    String input4 = "2,2,4,3,2,#,#,#,#,2,6,#,#,6,8";
    DataStructure.TreeNode root4 = _treeCodec.deserialize(input4);
    Assert.assertEquals(_dataStructure.tournamentTree2ndMinimum(root4), new Integer(3));
  }

  @Test
  public void sumSegmentTreeTest() {
    int[] input1 = new int[]{1, 3, 5, 7, 9};
    DataStructure.SegmentTree segTree1 = new DataStructure.SegmentTree(
        input1, DataStructure.SegmentTree.QueryType.SUM_RANGE);
    Assert.assertEquals(segTree1.query(0, 4), 25);
    Assert.assertEquals(segTree1.query(0, 2), 9);
    segTree1.update(1, 2);
    Assert.assertEquals(segTree1.query(0, 2), 8);

    int[] input2 = new int[]{-28, -39, 53, 65, 11, -56, -65, -39, -43, 97};
    DataStructure.SegmentTree segTree2 = new DataStructure.SegmentTree(
        input2, DataStructure.SegmentTree.QueryType.SUM_RANGE);
    Assert.assertEquals(segTree2.query(5, 6), -121);
    segTree2.update(9, 27);
    Assert.assertEquals(segTree2.query(2, 3), 118);
    Assert.assertEquals(segTree2.query(6, 7), -104);
    segTree2.update(1, -82);
    segTree2.update(3, -72);
    Assert.assertEquals(segTree2.query(3, 7), -221);
    Assert.assertEquals(segTree2.query(1, 8), -293);
    segTree2.update(5, 13);
    segTree2.update(4, -67);

  }

  @Test
  public void minSegmentTreeTest() {
    int[] input1 = new int[]{2, 1, 6, -7, 0, 3, 15, 18, -3};
    DataStructure.SegmentTree segTree1 = new DataStructure.SegmentTree(
        input1, DataStructure.SegmentTree.QueryType.MIN_RANGE);
    Assert.assertEquals(segTree1.query(0, 6), -7);
    Assert.assertEquals(segTree1.query(4, 6), 0);
    Assert.assertEquals(segTree1.query(0, 4), -7);
    segTree1.update(3, 4);
    Assert.assertEquals(segTree1.query(0, 4), 0);
  }

  @Test
  public void maxSegmentTreeTest() {
    int[] input1 = new int[]{2, 1, 6, -7, 0, 3, 15, 18, -3};
    DataStructure.SegmentTree segTree1 = new DataStructure.SegmentTree(
        input1, DataStructure.SegmentTree.QueryType.MAX_RANGE);
    Assert.assertEquals(segTree1.query(0, 6), 15);
    Assert.assertEquals(segTree1.query(3, 5), 3);
    segTree1.update(4, 11);
    Assert.assertEquals(segTree1.query(3, 5), 11);
  }

  @Test
  void isValidSudokuTest() {
    char[][] board1 =
        {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
    Assert.assertTrue(_dataStructure.isValidSudoku(board1));
    char[][] board2 =
        {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '0', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
    Assert.assertFalse(_dataStructure.isValidSudoku(board2));
    char[][] board3 =
        {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '2', '9'}
        };
    Assert.assertFalse(_dataStructure.isValidSudoku(board3));
    char[][] board4 =
        {
            {'.', '.', '.', '.', '7', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '9', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'5', '.', '.', '.', '.', '.', '.', '.', '3'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '.', '.', '.', '.', '.', '.', '8', '.'},
            {'.', '.', '.', '4', '1', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
        };
    Assert.assertTrue(_dataStructure.isValidSudoku(board4));
    char[][] board5 =
        {
            "....5..1.".toCharArray(),
            ".4.3.....".toCharArray(),
            ".....3..1".toCharArray(),
            "8......2.".toCharArray(),
            "..2.7....".toCharArray(),
            ".15......".toCharArray(),
            ".....2...".toCharArray(),
            ".2.9.....".toCharArray(),
            "..4......".toCharArray()

        };
    Assert.assertFalse(_dataStructure.isValidSudoku(board5));
  }

  @Test
  public void minStackTest() {
    DataStructure.MinStack minStack1 = new DataStructure.MinStack();
    minStack1.push(-2);
    minStack1.push(0);
    minStack1.push(-3);
    Assert.assertEquals(minStack1.getMin(), -3);
    minStack1.pop();
    Assert.assertEquals(minStack1.top(), 0);
    Assert.assertEquals(minStack1.getMin(), -2);

  }

  @Test
  public void lowestCommonAncestorTest() {
    String input1 = "3,5,1,6,2,0,8,#,#,7,4";
    DataStructure.TreeNode root1 = _treeCodec.deserialize(input1);
    DataStructure.TreeNode commonAncestor11 = _dataStructure.lowestCommonAncestor(root1, root1.left, root1.right);
    Assert.assertEquals(commonAncestor11, root1);
    DataStructure.TreeNode commonAncestor12 = _dataStructure.lowestCommonAncestor(root1, root1.left.left, root1.left.right.right);
    Assert.assertEquals(commonAncestor12, root1.left);

    String input2 = "37,-34,-48,#,-100,-100,48,#,#,#,#,-54,#,-71,-22,#,#,#,8";
    DataStructure.TreeNode root2 = _treeCodec.deserialize(input2);
    DataStructure.TreeNode commonAncestor21 = _dataStructure.lowestCommonAncestor(root2, root2.right.left, root2.right.right.left.left);
    Assert.assertEquals(commonAncestor21, root2.right);
  }

  @Test
  public void mergeTest() {

    List<DataStructure.Interval> intervals1 = asList(
        new DataStructure.Interval(1, 3),
        new DataStructure.Interval(2, 6),
        new DataStructure.Interval(8, 10),
        new DataStructure.Interval(12, 15));
    List<DataStructure.Interval> expected_res1 = asList(
        new DataStructure.Interval(1, 6),
        new DataStructure.Interval(8, 10),
        new DataStructure.Interval(12, 15));
    Assert.assertEquals(_dataStructure.merge(intervals1), expected_res1);

    List<DataStructure.Interval> intervals2 = asList(
        new DataStructure.Interval(3, 6),
        new DataStructure.Interval(0, 2),
        new DataStructure.Interval(2, 4),
        new DataStructure.Interval(7, 10));
    List<DataStructure.Interval> expected_res2 = asList(
        new DataStructure.Interval(0, 6),
        new DataStructure.Interval(7, 10));
    Assert.assertEquals(_dataStructure.merge(intervals2), expected_res2);
  }

  @Test
  public void insertTest() {
    List<DataStructure.Interval> intervals1 = asList(
        new DataStructure.Interval(1, 3),
        new DataStructure.Interval(6, 9));
    DataStructure.Interval newInterval1 = new DataStructure.Interval(2,5);
    List<DataStructure.Interval> expected_res1 = asList(
        new DataStructure.Interval(1, 5),
        new DataStructure.Interval(6, 9));
    Assert.assertEquals(_dataStructure.insert(intervals1,newInterval1), expected_res1);

    List<DataStructure.Interval> intervals2 = asList();
    DataStructure.Interval newInterval2 = new DataStructure.Interval(2,5);
    List<DataStructure.Interval> expected_res2 = asList(
        new DataStructure.Interval(2, 5));
    Assert.assertEquals(_dataStructure.insert(intervals2,newInterval2), expected_res2);

    List<DataStructure.Interval> intervals3 = asList(
        new DataStructure.Interval(1, 3),
        new DataStructure.Interval(6, 9));
    DataStructure.Interval newInterval3 = new DataStructure.Interval(4,5);
    List<DataStructure.Interval> expected_res3 = asList(
        new DataStructure.Interval(1, 3),
        new DataStructure.Interval(4, 5),
        new DataStructure.Interval(6, 9));
    Assert.assertEquals(_dataStructure.insert(intervals3,newInterval3), expected_res3);

    List<DataStructure.Interval> intervals4 = asList(
        new DataStructure.Interval(1, 2),
        new DataStructure.Interval(3, 5),
        new DataStructure.Interval(6, 7),
        new DataStructure.Interval(8, 10),
        new DataStructure.Interval(12, 16));
    DataStructure.Interval newInterval4 = new DataStructure.Interval(4,9);
    List<DataStructure.Interval> expected_res4 = asList(
        new DataStructure.Interval(1, 2),
        new DataStructure.Interval(3, 10),
        new DataStructure.Interval(12, 16));
    Assert.assertEquals(_dataStructure.insert(intervals4,newInterval4), expected_res4);

    List<DataStructure.Interval> intervals5 = asList(
        new DataStructure.Interval(1, 5)
    );
    DataStructure.Interval newInterval5 = new DataStructure.Interval(2,3);
    List<DataStructure.Interval> expected_res5 = asList(
        new DataStructure.Interval(1, 5));
    Assert.assertEquals(_dataStructure.insert(intervals5,newInterval5), expected_res5);
  }

  @Test
  public void groupAnagramsTest(){
    List<List<String>> expected_res1 = new ArrayList<>();
    expected_res1.add(Arrays.asList("eat", "tea","ate"));
    expected_res1.add(Arrays.asList("bat"));
    expected_res1.add(Arrays.asList("tan","nat"));
    Assert.assertEquals(_dataStructure.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}),expected_res1);

    List<List<String>> expected_res2 = new ArrayList<>();
    Assert.assertEquals(_dataStructure.groupAnagrams(new String[]{}),expected_res2);

    List<List<String>> expected_res3 = new ArrayList<>();
    expected_res3.add(Arrays.asList("add","dad"));
    expected_res3.add(Arrays.asList("abc", "acb","cba","cab"));

    Assert.assertEquals(_dataStructure.groupAnagrams(new String[]{"abc", "acb","cba","cab","add","dad"}),expected_res3);

  }

  @Test
  public void largestRectangleAreaTest(){
    Assert.assertEquals(_dataStructure.largestRectangleArea(new int[]{5,2,3,4}),8);
    Assert.assertEquals(_dataStructure.largestRectangleArea(new int[]{2,1,5,6,2,3}),10);
    Assert.assertEquals(_dataStructure.largestRectangleArea(new int[]{3,3,3,3}),12);
    Assert.assertEquals(_dataStructure.largestRectangleArea(new int[]{1,1,5}),5);
    Assert.assertEquals(_dataStructure.largestRectangleArea(new int[]{0,1,2,3,4,5,6,7,8}),20);
  }

  @Test
  public void maximalRectangleTest(){
    char[][] matrix1 = new char[][]{
        "10100".toCharArray(),
        "10111".toCharArray(),
        "11111".toCharArray(),
        "10010".toCharArray()
    };
    Assert.assertEquals(_dataStructure.maximalRectangle(matrix1),6);
  }

  @Test
  public void deleteDuplicatesTest(){
    DataStructure.ListNode head1 = _dataStructure.createList(new int[]{1,2,3,3,4,4,5});
    DataStructure.ListNode expect1 = _dataStructure.createList(new int[]{1,2,5});
    DataStructure.ListNode actual1 = _dataStructure.deleteDuplicates(head1);
    Assert.assertTrue(_dataStructure.listEqual(actual1,expect1));

    DataStructure.ListNode head2 = _dataStructure.createList(new int[]{1,1,2,3,4});
    DataStructure.ListNode expect2 = _dataStructure.createList(new int[]{2,3,4});
    DataStructure.ListNode actual2 = _dataStructure.deleteDuplicates(head2);
    Assert.assertTrue(_dataStructure.listEqual(actual2,expect2));

    DataStructure.ListNode head3 = _dataStructure.createList(new int[]{1,2,3,3});
    DataStructure.ListNode expect3 = _dataStructure.createList(new int[]{1,2});
    DataStructure.ListNode actual3 = _dataStructure.deleteDuplicates(head3);
    Assert.assertTrue(_dataStructure.listEqual(actual3,expect3));

    DataStructure.ListNode head4 = _dataStructure.createList(new int[]{1,2,3});
    DataStructure.ListNode expect4 = _dataStructure.createList(new int[]{1,2,3});
    DataStructure.ListNode actual4 = _dataStructure.deleteDuplicates(head4);
    Assert.assertTrue(_dataStructure.listEqual(actual4,expect4));

    DataStructure.ListNode head5 = _dataStructure.createList(new int[]{1,1,2,2,2,2,3,3,3});
    DataStructure.ListNode expect5 = _dataStructure.createList(new int[]{});
    DataStructure.ListNode actual5 = _dataStructure.deleteDuplicates(head5);
    Assert.assertTrue(_dataStructure.listEqual(actual5,expect5));

  }

  @Test
  public void generateTreesTest(){
    List<String> expected1 = new ArrayList<>();
    expected1.add("1");
    generateTreesTestHelper(_dataStructure.generateTrees(1),expected1);

    List<String> expected2 = new ArrayList<>();
    expected2.add("1,#,2");
    expected2.add("2,1");
    generateTreesTestHelper(_dataStructure.generateTrees(2),expected2);

    List<String> expected3 = new ArrayList<>();
    expected3.add("1,#,2,#,3");
    expected3.add("1,#,3,2");
    expected3.add("2,1,3");
    expected3.add("3,1,#,#,2");
    expected3.add("3,2,#,1");
    generateTreesTestHelper(_dataStructure.generateTrees(3),expected3);

    List<String> expected4 = new ArrayList<>();
    expected4.add("1,#,2,#,3,#,4");
    expected4.add("1,#,2,#,4,3");
    expected4.add("1,#,3,2,4");
    expected4.add("1,#,4,2,#,#,3");
    expected4.add("1,#,4,3,#,2");
    expected4.add("2,1,3,#,#,#,4");
    expected4.add("2,1,4,#,#,3");
    expected4.add("3,1,4,#,2");
    expected4.add("3,2,4,1");
    expected4.add("4,1,#,#,2,#,3");
    expected4.add("4,1,#,#,3,2");
    expected4.add("4,2,#,1,3");
    expected4.add("4,3,#,1,#,#,2");
    expected4.add("4,3,#,2,#,1");
    generateTreesTestHelper(_dataStructure.generateTrees(4),expected4);
  }

  private void generateTreesTestHelper(List<DataStructure.TreeNode> actual, List<String> expected) {
    int expectedListLen = expected.size();
    Assert.assertEquals(actual.size(), expectedListLen);
    for (int i = 0; i < expectedListLen; ++i) {
      String deser = _treeCodec.serialize(actual.get(i));
      Assert.assertEquals(deser,expected.get(i));
    }
  }

  @Test
  public void isValidBSTTest(){
    Assert.assertTrue(_dataStructure.isValidBST(_treeCodec.deserialize("4,1,#,#,2,#,3")));
    Assert.assertFalse(_dataStructure.isValidBST(_treeCodec.deserialize("4,1,#,#,2,#,5")));
  }

  @Test
  public void recoverTreeTest(){
    DataStructure.TreeNode broke1 = _treeCodec.deserialize("0,1");
    _dataStructure.recoverTree(broke1);
    Assert.assertEquals(_treeCodec.serialize(broke1),"1,0");

    DataStructure.TreeNode broke2 = _treeCodec.deserialize("6,3,5,2,9,7");
    _dataStructure.recoverTree(broke2);
    Assert.assertEquals(_treeCodec.serialize(broke2),"6,3,9,2,5,7");
  }

  @Test
  public void buildTreeTest(){
    DataStructure.TreeNode buildTree1 = _dataStructure.buildTree(new int[]{5,4,2,9,7,8},new int[]{2,4,9,5,7,8});
    Assert.assertEquals(_treeCodec.serialize(buildTree1),"5,4,7,2,9,#,8");

    DataStructure.TreeNode buildTree2 = _dataStructure.buildTree(new int[]{5},new int[]{5});
    Assert.assertEquals(_treeCodec.serialize(buildTree2),"5");
  }

  @Test
  public void InOrderIterativeTest(){
    DataStructure.TreeNode simpleTree = _treeCodec.deserialize("1,2,3");
    List<Integer> simpleInorder = _dataStructure.inOrderIterative(simpleTree);
    Assert.assertEquals(simpleInorder,asList(2,1,3));
    List<Integer> simplePreorder = _dataStructure.preOrderIterative(simpleTree);
    Assert.assertEquals(simplePreorder,asList(1,2,3));
    List<Integer> simplePostorder = _dataStructure.postOrderIterative(simpleTree);
    Assert.assertEquals(simplePostorder,asList(2,3,1));

    DataStructure.TreeNode TreeRoot1 = _treeCodec.deserialize("6,2,7,1,4,#,9,#,#,3,5,8");
    List<Integer> inorder1 = _dataStructure.inOrderIterative(TreeRoot1);
    List<Integer> preorder1 = _dataStructure.preOrderIterative(TreeRoot1);
    List<Integer> postorder1 = _dataStructure.postOrderIterative(TreeRoot1);

    Assert.assertEquals(inorder1,asList(1,2,3,4,5,6,7,8,9));
    Assert.assertEquals(preorder1,asList(6,2,1,4,3,5,7,9,8));
    Assert.assertEquals(postorder1,asList(1,3,5,4,2,8,9,7,6));
  }

  @Test
  public void sortedArrayToBSTTest() {
    DataStructure.TreeNode bst1 = _dataStructure.sortedArrayToBST(new int[]{1,2,3,4,5,6,7});
    Assert.assertEquals(_treeCodec.serialize(bst1),"4,2,6,1,3,5,7");

    DataStructure.TreeNode bst2 = _dataStructure.sortedArrayToBST(new int[]{1,2,3,4,5,6});
    Assert.assertEquals(_treeCodec.serialize(bst2),"4,2,6,1,3,5");

    DataStructure.TreeNode bst3 = _dataStructure.sortedArrayToBST(new int[]{1,2,3,4,5});
    Assert.assertEquals(_treeCodec.serialize(bst3),"3,2,5,1,#,4");
  }

  @Test
  public void originalDigitsTest() {
    Assert.assertEquals(_dataStructure.originalDigits("owoztneoer"),"012");
    Assert.assertEquals(_dataStructure.originalDigits("fviefuro"),"45");
    Assert.assertEquals(_dataStructure.originalDigits("xis"),"6");
    Assert.assertEquals(_dataStructure.originalDigits("htere"),"3");
    Assert.assertEquals(_dataStructure.originalDigits("nein"),"9");
  }

  @Test
  public void buildBSTTest() {
    DataStructure.TreeNode bstRoot1 = _dataStructure.buildBST(0,0,new int[]{1});
    Assert.assertEquals(bstRoot1.val,1);
    Assert.assertEquals(bstRoot1.left,null);
    Assert.assertEquals(bstRoot1.right,null);

    DataStructure.TreeNode bstRoot2 = _dataStructure.buildBST(0,1,new int[]{1,2});
    Assert.assertEquals(bstRoot2.val,2);
    Assert.assertEquals(bstRoot2.left.val,1);
    Assert.assertEquals(bstRoot2.right,null);

    DataStructure.TreeNode bstRoot3 = _dataStructure.buildBST(0,2,new int[]{1,2,3});
    Assert.assertEquals(bstRoot3.val,2);
    Assert.assertEquals(bstRoot3.left.val,1);
    Assert.assertEquals(bstRoot3.right.val,3);

    DataStructure.TreeNode bstRoot4 = _dataStructure.buildBST(0,7,new int[]{2,5,7,8,9,10,13,15});
    Assert.assertEquals(bstRoot4.val,9);
    Assert.assertEquals(bstRoot4.left.left.left.val,2);
    Assert.assertEquals(bstRoot4.right.right.val,15);
  }

  @Test
  public void topKFrequent1Test() {
    Assert.assertEquals(_dataStructure.topKFrequent1(new String[]{"a"},1),asList("a"));
    Assert.assertEquals(_dataStructure.topKFrequent1(new String[]{"love", "i", "leetcode", "love", "i", "coding"},2),asList("i", "love"));
    Assert.assertEquals(_dataStructure.topKFrequent1(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"},4),asList("the", "is", "sunny", "day"));
  }

  @Test
  public void topKFrequent2Test() {
    Assert.assertEquals(_dataStructure.topKFrequent2(new String[]{"a"},1),asList("a"));
    Assert.assertEquals(_dataStructure.topKFrequent2(new String[]{"love", "i", "leetcode", "love", "i", "coding"},2),asList("i", "love"));
    Assert.assertEquals(_dataStructure.topKFrequent2(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"},4),asList("the", "is", "sunny", "day"));

  }

  @Test
  public void mincostToHireWorkersTest() {

    Assert.assertEquals(Math.round(_dataStructure.mincostToHireWorkers(new int[] {10,20,5}, new int[] {70,50,30}, 2) * 100000d)/100000d, 105.00000);
    Assert.assertEquals(Math.round(_dataStructure.mincostToHireWorkers(new int[] {3,1,10,10,1}, new int[] {4,8,2,2,7}, 3)* 100000d)/ 100000d, 30.66667);
    Assert.assertEquals(Math.round(_dataStructure.mincostToHireWorkers(new int[] {25,68,35,62,52,57,35,83,40,51}, new int[] {147,97,251,129,438,443,120,366,362,343}, 6)* 100000d)/ 100000d, 1979.31429);
  }

  @Test
  public void scoreOfParenthesesTest() {
    Assert.assertEquals(_dataStructure.scoreOfParentheses("()"), 1);
    Assert.assertEquals(_dataStructure.scoreOfParentheses("(())"), 2);
    Assert.assertEquals(_dataStructure.scoreOfParentheses("()()"), 2);
    Assert.assertEquals(_dataStructure.scoreOfParentheses("(()(()))"), 6);
    Assert.assertEquals(_dataStructure.scoreOfParentheses("(((())))"), 8);
    Assert.assertEquals(_dataStructure.scoreOfParentheses("(()())()()()"), 7);
  }

  @Test
  public void shiftingLettersTest() {
    Assert.assertEquals(_dataStructure.shiftingLetters("abc", new int[] {3,5,9}), "rpl");
    Assert.assertEquals(_dataStructure.shiftingLetters("ab", new int[] {26,1}), "bc");
  }
}
