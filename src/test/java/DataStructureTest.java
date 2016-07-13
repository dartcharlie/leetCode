import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class DataStructureTest {
  DataStructure _dataStructure;

  @BeforeTest
  public void setup() {
    _dataStructure = new DataStructure();
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
    Assert.assertEquals(_dataStructure.listEqual(l0,l0),true);
    Assert.assertEquals(_dataStructure.listEqual(l1,l2),true);
    DataStructure.ListNode l3 = _dataStructure.createList(new int[]{1,2});
    DataStructure.ListNode l4 = _dataStructure.createList(new int[]{1,2,4,3});
    Assert.assertEquals(_dataStructure.listEqual(l2,l3),false);
    Assert.assertEquals(_dataStructure.listEqual(l2,l4),false);
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
    Assert.assertEquals(_dataStructure.listEqual(l56Merged,l56),true);
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
}
