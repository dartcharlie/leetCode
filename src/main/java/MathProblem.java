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
      }else {
        i++;
      }
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

  /**
   * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
   * @param matrix
   */
  public void setZeroes(int[][] matrix) {
    int m = matrix.length;
    if(m == 0){
      return;
    }
    int n = matrix[0].length;
    boolean firstRow = false, firstColumn = false;
    for(int i=0;i<n;++i){
      if(matrix[0][i] == 0){
        firstRow = true;
      }
    }
    for(int i=0;i<m;++i){
      if(matrix[i][0] == 0){
        firstColumn = true;
      }
    }
    for(int i=0;i<m;++i){
      for(int j=0;j<n;++j){
        if(matrix[i][j] == 0){
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }
    //set row
    for(int i=1;i<m;++i){
      if(matrix[i][0] == 0){
        for(int j=0;j<n;++j){
          matrix[i][j] = 0;
        }
      }
    }
    //set column
    for(int j=1;j<n;++j){
      if(matrix[0][j] == 0){
        for(int i=0;i<m;++i){
          matrix[i][j] = 0;
        }
      }
    }
    //set first row
    if(firstRow){
      for(int i=0;i<n;++i){
        matrix[0][i] = 0;
      }
    }
    //set first colum
    if(firstColumn){
      for(int i=0;i<m;++i){
        matrix[i][0] = 0;
      }
    }
  }

  /**
   * You are given an n x n 2D matrix representing an image.
   * Rotate the image by 90 degrees (clockwise).
   * @param matrix
   */
  public void rotate(int[][] matrix) {
    int n = matrix.length;
    int l = 0;
    while (l < n / 2) {
      for (int i = l; i <= n - 2 - l; ++i) {
        int temp = matrix[n - 1 - i][l];
        matrix[n - 1 - i][l] = matrix[n - 1 - l][n - 1 - i];
        matrix[n - 1 - l][n - 1 - i] = matrix[i][n - 1 - l];
        matrix[i][n - 1 - l] = matrix[l][i];
        matrix[l][i] = temp;
      }
      l++;
    }
  }

  /*
   * Given two numbers represented as strings, return multiplication of the numbers as a string.
   * The numbers can be arbitrarily large and are non-negative.
   * You should NOT use internal library such as BigInteger.
   * @param num1
   * @param num2
   * @return
   */
  public String multiply(String num1, String num2) {
    int m = num1.length();
    int n = num2.length();
    int[] cal = new int[m+n];
    for(int i=m-1;i>=0;i--){
      int sum,p1,p2;
      for(int j=n-1;j>=0;j--){
        sum = (num1.charAt(i) - '0') * (num2.charAt(j)-'0');
        p1 = i+j;
        p2 = i+j+1;
        sum += cal[p2];
        cal[p1] += sum/10;
        cal[p2] = sum%10;
      }
    }
    StringBuilder sb = new StringBuilder();
    for(int i=0;i<m+n;++i){
      if(!(sb.length() == 0 && cal[i] == 0)) {
        sb.append(cal[i]);
      }
    }
    return sb.length() == 0?"0":sb.toString();
  }

  /**
   * implement x power of n, binary search
   * @param x
   * @param n
   * @return
   */
  public double myPow(double x, int n) {
    if(n == 0 || x == 1){
      return 1;
    }
    if(x == 0){
      return 0;
    }
    if(n == 1){
      return x;
    }
    if(n == -1){
      return 1/x;
    }
    double squareRoot;
    squareRoot = myPow(x, n / 2);
    if (n % 2 == 0) {
      return squareRoot * squareRoot;
    } else {
      if(n<0){
        return squareRoot * squareRoot * 1/x;
      }
      return squareRoot * squareRoot*x;
    }
  }
}
