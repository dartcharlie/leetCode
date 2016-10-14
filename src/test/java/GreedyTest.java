import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;
import org.testng.internal.junit.ArrayAsserts;

/**
 * Created by ZSong on 9/17/16.
 */
public class GreedyTest {
  Greedy _greedy;

  @BeforeTest
  public void setup() {
    _greedy = new Greedy();
  }

  @Test
  public void canJumpDPTest() {
    int[] nums1 = new int[]{2, 3, 1, 1, 4};
    assertEquals(_greedy.canJumpDP(nums1), true);

    int[] nums2 = new int[]{3, 2, 1, 0, 4};
    assertEquals(_greedy.canJumpDP(nums2), false);

    int[] nums3 = new int[]{0, 1, 2, 3, 4};
    assertEquals(_greedy.canJumpDP(nums3), false);

    int[] nums4 = new int[]{0};
    assertEquals(_greedy.canJumpDP(nums4), true);

    int[] nums5 = new int[]{2};
    assertEquals(_greedy.canJumpDP(nums5), true);
  }

  @Test
  public void canJumpGreedyTest() {
    int[] nums1 = new int[]{2, 3, 1, 1, 4};
    assertEquals(_greedy.canJumpGreedy(nums1), true);

    int[] nums2 = new int[]{3, 2, 1, 0, 4};
    assertEquals(_greedy.canJumpGreedy(nums2), false);

    int[] nums3 = new int[]{0, 1, 2, 3, 4};
    assertEquals(_greedy.canJumpGreedy(nums3), false);

    int[] nums4 = new int[]{0};
    assertEquals(_greedy.canJumpGreedy(nums4), true);

    int[] nums5 = new int[]{2};
    assertEquals(_greedy.canJumpGreedy(nums5), true);
  }

  @Test
  public void jumpTest() {
    int[] nums1 = new int[] {2,3,1,1,4};
    assertEquals(_greedy.jump(nums1),2);

    int[] nums2 = new int[] {3,1,5,1,2,1,1,3};
    assertEquals(_greedy.jump(nums2),2);

    assertEquals(_greedy.jump(new int[]{0}),0);
    assertEquals(_greedy.jump(new int[]{1,2}),1);
    assertEquals(_greedy.jump(new int[]{0,1}),-1);
  }

  @Test
  public void removeDuplicateTest() {
    assertEquals(_greedy.removeDuplicateLetters("bcabc"),"abc");
    assertEquals(_greedy.removeDuplicateLetters("cbacdcbc"),"acdb");
    assertEquals(_greedy.removeDuplicateLetters("abcdabcdabcd"),"abcd");
    assertEquals(_greedy.removeDuplicateLetters(""),"");
    assertEquals(_greedy.removeDuplicateLetters("a"),"a");
    assertEquals(_greedy.removeDuplicateLetters("cbaabc"),"abc");
  }

  @Test
  public void maxSubArrayTest(){
    ArrayAsserts.assertArrayEquals(_greedy.maxSubArray(new int[]{3,4,6,5},2),new int[]{6,5});
    ArrayAsserts.assertArrayEquals(_greedy.maxSubArray(new int[]{},0),new int[]{});
    ArrayAsserts.assertArrayEquals(_greedy.maxSubArray(new int[]{1},1),new int[]{1});
    ArrayAsserts.assertArrayEquals(_greedy.maxSubArray(new int[]{1,2},1),new int[]{2});
    ArrayAsserts.assertArrayEquals(_greedy.maxSubArray(new int[]{3,4,6,5},4),new int[]{3,4,6,5});
    ArrayAsserts.assertArrayEquals(_greedy.maxSubArray(new int[]{9,1,2,5,8,3},3),new int[]{9,8,3});
    ArrayAsserts.assertArrayEquals(_greedy.maxSubArray(new int[]{6,6,5,5,6,6},3),new int[]{6,6,6});
  }

  @Test
  public void greaterOrEqualTest() {
    assertTrue(_greedy.greaterOrEqual(new int[]{},new int[]{}));
    assertTrue(_greedy.greaterOrEqual(new int[]{1},new int[]{}));
    assertTrue(_greedy.greaterOrEqual(new int[]{1},new int[]{1}));
    assertTrue(_greedy.greaterOrEqual(new int[]{1,3,2},new int[]{1,2,3}));
    assertFalse(_greedy.greaterOrEqual(new int[]{1,2,3},new int[]{1,3,2}));
    assertFalse(_greedy.greaterOrEqual(new int[]{2},new int[]{2,3}));
  }

  @Test
  public void mergeTest(){
    ArrayAsserts.assertArrayEquals(_greedy.merge(new int[]{1},new int[]{2}),new int[]{2,1});
    ArrayAsserts.assertArrayEquals(_greedy.merge(new int[]{1},new int[]{}),new int[]{1});
    ArrayAsserts.assertArrayEquals(_greedy.merge(new int[]{},new int[]{2}),new int[]{2});
    ArrayAsserts.assertArrayEquals(_greedy.merge(new int[]{2,3,4},new int[]{2,3,4,5}),new int[]{2,3,4,5,2,3,4});
    ArrayAsserts.assertArrayEquals(_greedy.merge(new int[]{9,5,8,3},new int[]{6}),new int[]{9,6,5,8,3});
  }

  @Test
  public void maxNumberTest(){
    ArrayAsserts.assertArrayEquals(_greedy.maxNumber(new int[]{3,4,6,5}, new int[] {9,1,2,5,8,3}, 5), new int[]{9,8,6,5,3});
    ArrayAsserts.assertArrayEquals(_greedy.maxNumber(new int[]{6, 7}, new int[] {6, 0, 4}, 5), new int[]{6, 7, 6, 0, 4});
    ArrayAsserts.assertArrayEquals(_greedy.maxNumber(new int[]{3, 9}, new int[] {8, 9}, 3), new int[]{9,8,9});
  }
}
