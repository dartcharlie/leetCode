import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

import static org.testng.Assert.assertEquals;

public class TwoPointerTest {
  TwoPointer _twoPointer;
  DataStructure _dataStructure;
  @BeforeTest
  public void setup(){
    _twoPointer = new TwoPointer();
    _dataStructure = new DataStructure();
  }

  @Test
  public void shortestSubStringTest(){
    assertEquals(_twoPointer.shortestSubString("blah",""),0);
    assertEquals(_twoPointer.shortestSubString("","a"),-1);
    assertEquals(_twoPointer.shortestSubString("a","a"),1);
    assertEquals(_twoPointer.shortestSubString("a","b"),-1);
    assertEquals(_twoPointer.shortestSubString("aaa","a"),1);
    assertEquals(_twoPointer.shortestSubString("abcdbtnmca","banc"),6);
    assertEquals(_twoPointer.shortestSubString("aaaaaabbbbbb","ab"),2);
    assertEquals(_twoPointer.shortestSubString("abcdbtnmca","bbanc"),7);
    assertEquals(_twoPointer.shortestSubString("ADOBECODEBANC","ABC"),4);
  }

  @Test
  public void minWindowTest(){
    assertEquals(_twoPointer.minWindow("blah",""),"");
    assertEquals(_twoPointer.minWindow("","a"),"");
    assertEquals(_twoPointer.minWindow("a","a"),"a");
    assertEquals(_twoPointer.minWindow("a","b"),"");
    assertEquals(_twoPointer.minWindow("aaa","a"),"a");
    assertEquals(_twoPointer.minWindow("abcdbtnmca","banc"),"btnmca");
    assertEquals(_twoPointer.minWindow("aaaaaabbbbbb","ab"),"ab");
    assertEquals(_twoPointer.minWindow("abcdbtnmca","bbanc"),"abcdbtn");
    assertEquals(_twoPointer.minWindow("ADOBECODEBANC","ABC"),"BANC");
  }

  @Test
  public void twoSumTest() {
    ArrayAsserts.assertArrayEquals(_twoPointer.twoSum(new int[]{2, 7, 11, 15}, 9),new int[]{1,2});
    ArrayAsserts.assertArrayEquals(_twoPointer.twoSum(new int[]{2, 15}, 17),new int[]{1,2});
  }

  @Test
  public void rotateRightTest() {
    DataStructure.ListNode l0 = _dataStructure.createList(new int[]{1, 2,3,4,5});
    DataStructure.ListNode e0 = _dataStructure.createList(new int[]{4, 5,1,2,3});
    Assert.assertTrue(_dataStructure.listEqual(_twoPointer.rotateRight(l0,2),e0));

    DataStructure.ListNode l1 = _dataStructure.createList(new int[]{1,2});
    DataStructure.ListNode e1 = _dataStructure.createList(new int[]{1,2});
    Assert.assertTrue(_dataStructure.listEqual(_twoPointer.rotateRight(l1,2),e1));

    DataStructure.ListNode l2 = _dataStructure.createList(new int[]{1,2,3});
    DataStructure.ListNode e2 = _dataStructure.createList(new int[]{1,2,3});
    Assert.assertTrue(_dataStructure.listEqual(_twoPointer.rotateRight(l2,0),e2));

    DataStructure.ListNode l3 = _dataStructure.createList(new int[]{1,2});
    DataStructure.ListNode e3 = _dataStructure.createList(new int[]{2,1});
    Assert.assertTrue(_dataStructure.listEqual(_twoPointer.rotateRight(l3,3),e3));
  }
}
