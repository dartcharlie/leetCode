/**
 * Created by ZSong on 9/17/16.
 */
public class Greedy {
  /**
   * leetcode-55 jump game
   * Given an array of non-negative integers, you are initially positioned at the first index of the array.
   * Each element in the array represents your maximum jump length at that position.
   * @param nums array of non negative integers
   * @return True if you are able to reach the last index.
   */
  public boolean canJumpDP(int[] nums) {
    if(nums != null && nums.length > 0){
      int numLen = nums.length;
      boolean[] feasible = new boolean[numLen];
      feasible[0] = true;
      for(int i=1;i<numLen;++i){
        for(int j=i-1;j>=0;--j){
          if(feasible[j] && nums[j] >= i-j){
            feasible[i] = true;
            break;
          }
        }
      }
      return feasible[numLen-1];
    }
    return false;
  }

  /**
   * furthest next index (maxJump) is current index i + jump steps nums[i];
   * update maxJump along array traverse
   * there are 2 conditions we need stop the loop immediately:
   * 1) current index is not reachable
   * 2) from current index we could already jump to the last index.
   * @param nums
   * @return
   */
  public boolean canJumpGreedy(int[] nums) {
    int maxJump = 0;
    int numLen = nums.length;
    for(int i=0;i<numLen-1;++i){
      if(i>maxJump || maxJump >= numLen) break;
      maxJump = Math.max(maxJump, i+nums[i]);
    }
    return maxJump >= (nums.length -1);
  }

  /**
   * leetcode 45 jump game II
   * Given an array of non-negative integers, you are initially positioned at the first index of the array.
   * Each element in the array represents your maximum jump length at that position.
   * Your goal is to reach the last index in the minimum number of jumps.
   * Given array A = [2,3,1,1,4]
   * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
   * @param nums
   * @return
   */
  public int jump(int[] nums) {
    int lastJump = 0, numJumps = 0, i=0;
    while(lastJump < nums.length -1){
      int currMax = lastJump;
      for(;i<=currMax;++i){
        lastJump = Math.max(lastJump,nums[i]+i);
      }
      numJumps++;
      if(lastJump == currMax){
        return -1;
      }
    }
    return numJumps;
  }
}
