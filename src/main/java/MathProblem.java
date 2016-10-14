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

  /**
   * @param s string s consists of upper/lower-case alphabets and empty space characters ' '
   * @return the length of last word in the string.
   * If the last word does not exist, return 0.
   * Given s = "Hello World", return 5
   */
  public int lengthOfLastWord(String s) {
    int sLen = s.length();

    int currPos = sLen-1;
    while(currPos >= 0 && s.charAt(currPos) == ' '){
      currPos--;
    }
    int startCount = currPos;
    while(currPos >= 0){
      if(s.charAt(currPos) == ' '){
        break;
      } else{
        currPos--;
      }
    }
    return startCount - currPos;
  }


  /**
   * @param n integer n
   * @return a square matrix filled with elements from 1 to n^2 in spiral order
   * Given n = 3,
   * should return
   * [[ 1, 2, 3 ],
   * [ 8, 9, 4 ],
   * [ 7, 6, 5 ]]
   */
  public int[][] generateMatrix(int n) {
    int x,y;
    int[][] res = new int[n][n];
    int level = 0;
    int curr = 1;
    while(level <= n/2){
      for(x=level,y=level;y<=n-level-1;y++){
        res[x][y] = curr;
        curr++;
      }
      for(x=level+1,y=n-level-1;x<=n-level-1;x++){
        res[x][y] = curr;
        curr++;
      }
      for(x=n-level-1,y=n-level-2;y>=level;y--){
        res[x][y] = curr;
        curr++;
      }
      for(x=n-level-2,y=level;x>level;x--){
        res[x][y] = curr;
        curr++;
      }
      level++;
    }
    return res;
  }
}
