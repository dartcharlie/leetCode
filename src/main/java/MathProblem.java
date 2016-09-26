/**
 * Created by ZSong on 7/6/16.
 */
public class MathProblem {
  /**
   * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
   * @param n
   * @return nth digit
   * example:
   * n = 3, return 3
   * n = 11, return 0, The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
   */
  public int findNthDigit(int n) {
    long count = 9;
    int digit = 1;
    int start = 1;
    while(n > count * digit){
      n -= count * digit;
      count *= 10;
      digit++;
      start *= 10;
    }
    int number = (n - 1)/digit + start;
    String numberStr = Integer.toString(number);
    return Character.getNumericValue(numberStr.charAt((n-1)%digit));
  }

  /**
   * leetcode 41 Given an unsorted integer array, find the first missing positive integer.
   * For example,
   * Given [1,2,0] return 3, and [3,4,-1,1] return 2.
   * algorithm should run in O(n) time and uses constant space
   * @param nums
   * @return
   */
  public int firstMissingPositive(int[] nums) {
    int numLen = nums.length;
    if(numLen == 0){
      return 1;
    }
    int i=0;
    while(i<numLen){
      if(nums[i] >0 && nums[i] <=numLen && nums[i] != i+1 && nums[i] != nums[nums[i]-1]){
        int swap = nums[nums[i]-1];
        nums[nums[i]-1] = nums[i];
        nums[i] = swap;
        i--;
      }
      i++;
    }
    i = 0;
    while(i<numLen && nums[i] == i+1 ){
      i++;
    }
    return i+1;
  }
}
