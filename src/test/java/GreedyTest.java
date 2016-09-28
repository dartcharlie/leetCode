import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

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
}
