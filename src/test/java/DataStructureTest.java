import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    List<String> ballots_input1 = Arrays.asList(new String[]{"abc", "acb", "bac", "cba", "bca", "abc"});
    Assert.assertEquals(_dataStructure.preferentialVoting(ballots_input1), 'a');

    //need redistribute vote to win case
    List<String> ballots_input2 = Arrays.asList(new String[]{"abcd", "acbd", "bacd", "bcad", "cbad", "cabd", "dabc"});
    Assert.assertEquals(_dataStructure.preferentialVoting(ballots_input2), 'a');

    //need to count the 3rd candidates in some ballot to get winner
    List<String> ballots_input3 = Arrays.asList(new String[]{"abcd", "acbd", "abcd", "acbd", "bdac", "bcad", "bcda", "cbda", "dcba"});
    Assert.assertEquals(_dataStructure.preferentialVoting(ballots_input3), 'b');

  }

  @Test
  public void isParenthesisValidTest() {
    Assert.assertEquals(_dataStructure.isParenthesisValid(""),true);
    Assert.assertEquals(_dataStructure.isParenthesisValid("()"),true);
    Assert.assertEquals(_dataStructure.isParenthesisValid("()[]{}"),true);
    Assert.assertEquals(_dataStructure.isParenthesisValid("]"),false);
    Assert.assertEquals(_dataStructure.isParenthesisValid("(]"),false);
    Assert.assertEquals(_dataStructure.isParenthesisValid("([)]"),false);
  }

  @Test
  public void listEqualityTest() {
    DataStructure.ListNode l0 = _dataStructure.createList(new int[]{});
    DataStructure.ListNode l1 = _dataStructure.createList(new int[]{1,2,3,4});
    DataStructure.ListNode l2 = _dataStructure.createList(new int[]{1,2,3,4});
    Assert.assertEquals(_dataStructure.listEqual(l0, l0),true);
    Assert.assertEquals(_dataStructure.listEqual(l1,l2),true);
    DataStructure.ListNode l3 = _dataStructure.createList(new int[]{1,2});
    DataStructure.ListNode l4 = _dataStructure.createList(new int[]{1,2,4,3});
    Assert.assertEquals(_dataStructure.listEqual(l2, l3),false);
    Assert.assertEquals(_dataStructure.listEqual(l2, l4),false);
  }

  @Test
  public void mergeTwoListsTest(){
    DataStructure.ListNode l1 = _dataStructure.createList(new int[]{0,2,8,15});
    DataStructure.ListNode l2 = _dataStructure.createList(new int[]{1,7,13,14});
    DataStructure.ListNode l12 = _dataStructure.createList(new int[]{0,1,2,7,8,13,14,15});
    DataStructure.ListNode l12Merged = _dataStructure.mergeTwoLists(l1,l2);
    Assert.assertEquals(_dataStructure.listEqual(l12Merged,l12),true);

    DataStructure.ListNode l3 = _dataStructure.createList(new int[]{-1,2,20,22,25,30});
    DataStructure.ListNode l4 = _dataStructure.createList(new int[]{1,7,13,14});
    DataStructure.ListNode l34 = _dataStructure.createList(new int[]{-1,1,2,7,13,14,20,22,25,30});
    DataStructure.ListNode l34Merged = _dataStructure.mergeTwoLists(l3,l4);
    Assert.assertEquals(_dataStructure.listEqual(l34Merged,l34),true);

    DataStructure.ListNode l5 = _dataStructure.createList(new int[]{});
    DataStructure.ListNode l6 = _dataStructure.createList(new int[]{1,7,13,14});
    DataStructure.ListNode l56 = _dataStructure.createList(new int[]{1,7,13,14});
    DataStructure.ListNode l56Merged = _dataStructure.mergeTwoLists(l5,l6);
    Assert.assertEquals(_dataStructure.listEqual(l56Merged, l56),true);
  }

  @Test
  public void swapPairsTest(){
    DataStructure.ListNode head1 = _dataStructure.createList(new int[]{1,2,3,4});
    DataStructure.ListNode expected_head1 = _dataStructure.createList(new int[]{2,1,4,3});
    DataStructure.ListNode swaped_head1 = _dataStructure.swapPairs(head1);
    Assert.assertEquals(_dataStructure.listEqual(swaped_head1,expected_head1),true);

    DataStructure.ListNode head2 = _dataStructure.createList(new int[]{1,2,3,4,5,6,7});
    DataStructure.ListNode expected_head2 = _dataStructure.createList(new int[]{2,1,4,3,6,5,7});
    DataStructure.ListNode swaped_head2 = _dataStructure.swapPairs(head2);
    Assert.assertEquals(_dataStructure.listEqual(swaped_head2,expected_head2),true);

    DataStructure.ListNode head3 = _dataStructure.createList(new int[]{1,2});
    DataStructure.ListNode expected_head3 = _dataStructure.createList(new int[]{2,1});
    DataStructure.ListNode swaped_head3 = _dataStructure.swapPairs(head3);
    Assert.assertEquals(_dataStructure.listEqual(swaped_head3,expected_head3),true);
  }

  @Test
  public void TreeSerDeserTest(){

    String input1 = "1,2,3,#,#,4,5";
    Assert.assertEquals(_treeCodec.serialize(_treeCodec.deserialize(input1)),input1);

    String input2 = "#";
    Assert.assertEquals(_treeCodec.serialize(_treeCodec.deserialize(input2)),input2);

    String input3 = "1";
    Assert.assertEquals(_treeCodec.serialize(_treeCodec.deserialize(input3)),input3);

    String input4 = "1,2,3";
    Assert.assertEquals(_treeCodec.serialize(_treeCodec.deserialize(input4)),input4);

    String input5 = "1,#,2,3";
    Assert.assertEquals(_treeCodec.serialize(_treeCodec.deserialize(input5)),input5);

    String input6 = "5,4,7,3,#,2,#,-1,#,9";
    Assert.assertEquals(_treeCodec.serialize(_treeCodec.deserialize(input6)),input6);
  }

  @Test
  public void zigzagLevelOrderTest(){
    String input1 = "3,9,20,#,#,15,7";
    DataStructure.TreeNode root1 = _treeCodec.deserialize(input1);
    List<List<Integer>> expected1 = new ArrayList<List<Integer>>();
    expected1.add(Arrays.asList(new Integer[]{3}));
    expected1.add(Arrays.asList(new Integer[]{20, 9}));
    expected1.add(Arrays.asList(new Integer[]{15,7}));
    Assert.assertEquals(_dataStructure.zigzagLevelOrder(root1),expected1);

    String input2 = "1,2,3,4,#,#,5";
    DataStructure.TreeNode root2 = _treeCodec.deserialize(input2);
    List<List<Integer>> expected2 = new ArrayList<List<Integer>>();
    expected2.add(Arrays.asList(new Integer[]{1}));
    expected2.add(Arrays.asList(new Integer[]{3,2}));
    expected2.add(Arrays.asList(new Integer[]{4,5}));
    Assert.assertEquals(_dataStructure.zigzagLevelOrder(root2),expected2);
  }

  @Test
  public void mergeKListsTest(){
    DataStructure.ListNode list11 = _dataStructure.createList(new int[]{4,8,12,16});
    DataStructure.ListNode list12 = _dataStructure.createList(new int[]{3,6,9,15});
    DataStructure.ListNode list13 = _dataStructure.createList(new int[]{2,5,11,17,19,20});
    DataStructure.ListNode list14 = _dataStructure.createList(new int[]{1,7,10,13,14,18});
    DataStructure.ListNode expected1 = _dataStructure.createList(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20});

    DataStructure.ListNode[] listNodes1 = new DataStructure.ListNode[]{list11,list12,list13,list14};
    Assert.assertEquals(_dataStructure.listEqual(_dataStructure.mergeKLists(listNodes1),expected1),true);

    DataStructure.ListNode list21 = _dataStructure.createList(new int[]{});
    DataStructure.ListNode[] listNodes2 = new DataStructure.ListNode[]{list21};
    DataStructure.ListNode expected2 = _dataStructure.createList(new int[]{});
    Assert.assertEquals(_dataStructure.listEqual(_dataStructure.mergeKLists(listNodes2),expected2),true);

    DataStructure.ListNode list31 = _dataStructure.createList(new int[]{});
    DataStructure.ListNode list32 = _dataStructure.createList(new int[]{});
    DataStructure.ListNode[] listNodes3 = new DataStructure.ListNode[]{list31,list32};
    DataStructure.ListNode expected3 = _dataStructure.createList(new int[]{});
    Assert.assertEquals(_dataStructure.listEqual(_dataStructure.mergeKLists(listNodes3),expected3),true);

  }
}
