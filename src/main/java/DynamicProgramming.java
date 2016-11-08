import java.util.*;

/**
 * Created by ZSong on 6/26/16.
 */
public class DynamicProgramming {
  /**
   * given a sequence 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15
   * a longest subsequence is 0, 2, 6, 9, 11, 15, length is 6
   *
   * @param inputArr an unsorted sequence
   * @return length of the longest increasing subsequence
   * <p>
   * let LIS(i) to be length of the longest increasing subsequence ending at element i
   * then LIS(i) = 1 + max{LIS(j)} where 1<=j<i and A(i) > A(j)
   * or  = 1 if no such j can be found
   * we also keep a parent array p, where p(i) is the index of predecessor element of i in
   * the longest increasing subsequence ending at i, so that we could reconstruct the longest subsequence
   */
  public int longestIncreasingSubsequence_nsquare(int[] inputArr) {
    int result;
    int arrLen = inputArr.length;
    if (arrLen == 0) {
      result = 0;
    } else {
      result = 1;
      int[] longestSubLength = new int[arrLen];
      longestSubLength[0] = 1;
      for (int i = 1; i < arrLen; ++i) {
        //LIS(i) = 1+max{LS(j)} part
        for (int j = 0; j < i; ++j) {
          if (inputArr[i] > inputArr[j] && longestSubLength[j] + 1 > longestSubLength[i]) {
            longestSubLength[i] = longestSubLength[j] + 1;
          }
        }
        //LIS(i) = 1 part, did not find any qualify j.
        if (longestSubLength[i] == 0) {
          longestSubLength[i] = 1;
        }
        //update the final result
        if (longestSubLength[i] > result) {
          result = longestSubLength[i];
        }
      }
    }
    return result;
  }

  /**
   * keep 3 params:
   * 1) longest increasing subsequence length found so far
   * 2) auxiliary longest increasing subsequence, for each new element a, perform binary search on such sequence
   * replace b's position if smaller than or equal to b in the sequence, append if larger than the last element in this auxiliary sequence
   * 3) parent array p, where p(i) is the index of predecessor element of i in the longest increasing subsequence ending at i.
   *
   * @param inputArr
   * @return
   */
  public int longestIncreasingSubsequence_nlogn_length(int[] inputArr) {
    int longestIncreasingLength = 0;
    int arrLen = inputArr.length;
    if (arrLen == 0) {
      return longestIncreasingLength;
    } else {
      longestIncreasingLength = 1;
      int[] auxArray = new int[arrLen];
      int[] parentArray = new int[arrLen];
      auxArray[0] = 0;  //store index of inputArr
      parentArray[0] = -1;
      for (int i = 1; i < arrLen; ++i) {
        if (inputArr[i] > inputArr[auxArray[longestIncreasingLength - 1]]) {
          auxArray[longestIncreasingLength] = i;
          parentArray[i] = auxArray[longestIncreasingLength - 1];
          longestIncreasingLength++;
        } else {
          int low = 0;
          int high = longestIncreasingLength - 1;
          int mid;
          while (low <= high) {
            mid = (low + high) / 2;
            if (inputArr[i] > auxArray[mid]) {
              low = mid + 1;
            } else {
              high = mid - 1;
            }
          }
          auxArray[low] = i;
          if (low == 0) {
            parentArray[i] = -1;
          } else {
            parentArray[i] = auxArray[low - 1];
          }
        }
      }
    }
    return longestIncreasingLength;
  }

  /**
   * logic is the same as above function, return a valid longestIncreasingSequence instead of the length
   *
   * @param inputArr
   * @return
   */
  public int[] longestIncreasingSubsequence_nlogn_array(int[] inputArr) {
    int[] result;
    int arrLen = inputArr.length;
    if (arrLen == 0) {
      result = new int[0];
    } else {
      int longestIncreasingLength = 1;
      int[] auxArray = new int[arrLen];
      int[] parentArray = new int[arrLen];
      auxArray[0] = 0;  //store index of inputArr
      parentArray[0] = -1;
      for (int i = 1; i < arrLen; ++i) {
        if (inputArr[i] > inputArr[auxArray[longestIncreasingLength - 1]]) {
          auxArray[longestIncreasingLength] = i;
          parentArray[i] = auxArray[longestIncreasingLength - 1];
          longestIncreasingLength++;
        } else {
          int low = 0;
          int high = longestIncreasingLength - 1;
          int mid;
          while (low <= high) {
            mid = (low + high) / 2;
            if (inputArr[i] > inputArr[auxArray[mid]]) {
              low = mid + 1;
            } else {
              high = mid - 1;
            }
          }
          auxArray[low] = i;
          if (low == 0) {
            parentArray[i] = -1;
          } else {
            parentArray[i] = auxArray[low - 1];
          }
        }
      }
      result = new int[longestIncreasingLength];
      int k = auxArray[longestIncreasingLength - 1];
      for (int i = longestIncreasingLength - 1; i >= 0; i--) {
        result[i] = inputArr[k];
        k = parentArray[k];
      }
    }
    return result;
  }

  /**
   * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset
   * satisfies: Si % Sj = 0 or Sj % Si = 0.
   *
   * @param nums set of distinct positive integers
   * @return one largest subset, if there are multiple answers, return any of them is fine
   */
  public List<Integer> largestDivisibleSubset(int[] nums) {
    LinkedList<Integer> result = new LinkedList<Integer>();
    int numLen = nums.length;
    if (numLen != 0) {
      result = new LinkedList<Integer>();
      int largestSubsetEndAt = 0;
      int largestSize = 1;
      Arrays.sort(nums);
      int[] dpArray = new int[numLen];
      int[] parentArray = new int[numLen];
      //initialize dpArray to be 1
      for (int i = 0; i < numLen; ++i) {
        dpArray[i] = 1;
      }
      for (int i = 1; i < numLen; ++i) {
        for (int j = 0; j < i; ++j) {
          if (nums[i] % nums[j] == 0) {
            dpArray[i] = dpArray[j] + 1;
            if (dpArray[i] > largestSize) {
              largestSubsetEndAt = i;
              largestSize = dpArray[i];
            }
            parentArray[i] = j;
          }
        }
      }
      for (int i = 0; i < largestSize; i++) {
        result.addFirst(nums[largestSubsetEndAt]);
        largestSubsetEndAt = parentArray[largestSubsetEndAt];
      }
    }
    return result;
  }

  /**
   * classic dynamic programming question, given 2 strings return minimum edits the first word need to transform to
   * the second word, each add/modify/delete operation is considered to be one distance
   *
   * @param word1
   * @param word2
   * @return edit distance between word1 and word2
   */
  /*
  public int editDistance(String word1, String word2) {
    int word1Len = word1.length();
    int word2Len = word2.length();
    int[][] distanceArray = new int[word1Len + 1][word2Len + 1];
    for (int i = 0; i <= word1Len; ++i) {
      distanceArray[i][0] = i;
    }
    for (int i = 0; i <= word2Len; ++i) {
      distanceArray[0][i] = i;
    }
    for (int i = 1; i <= word1Len; ++i) {
      for (int j = 1; j <= word2Len; ++j) {
        int modify = word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1;
        distanceArray[i][j] = Math.min(Math.min(distanceArray[i - 1][j - 1] + modify, distanceArray[i - 1][j] + 1), distanceArray[i][j - 1] + 1);
      }
    }
    return distanceArray[word1Len][word2Len];
  }
  */
  /**
   * find a subarray in array Input that contains the largest sum and subject to the constraint that such sum is less than k.
   * algorithm time complexity should be O(nlogn)
   * brutal force method time complexity is O(n^2)
   * @param input input array
   * @param k the magic number that the largest sum of subArray can't exceed.
   * @return
   */
  public int maxSumSubarrayNoLargerThanK(int[] input, int k){
    TreeSet<Integer> treeSet = new TreeSet<Integer>();
    int inputLen = input.length;
    int sum = 0;
    int result = Integer.MIN_VALUE;
    for(int i=0;i<inputLen;++i){
      sum += input[i];
      Integer currMax = treeSet.ceiling(sum-k);
      if(currMax != null){
        result = Math.max(sum - currMax, result);
      }
      if(sum <= k){
        result = Math.max(sum, result);
      }
      treeSet.add(sum);
    }
    return result;
  }

  /**
   * Given a non-empty 2D matrix matrix and an integer k,
   * find the max sum of a rectangle in the matrix such that its sum is no larger than k.
   * 1) The rectangle inside the matrix must have an area > 0.
   * 2) What if the number of rows is much larger than the number of columns?
   * @param matrix
   * @param k
   * @return
   */
  public int maxSumSubmatrix(int[][] matrix, int k){
    int row = matrix.length;
    int result = Integer.MIN_VALUE;
    if(row != 0){
      int col = matrix[0].length;
      int longerDimension = Math.max(row, col);
      int shorterDimension = Math.min(row, col);

      for(int i=0;i<shorterDimension;++i){
        int[] sums = new int[longerDimension];
        for(int j=i;j<shorterDimension;++j){
          TreeSet<Integer> numSet = new TreeSet<Integer>();
          int num = 0;
          for(int x=0;x<longerDimension;++x){
            sums[x] += row > col? matrix[x][j] :matrix[j][x];
            num += sums[x];
            Integer currMax = numSet.ceiling(num-k);
            if(currMax != null){
              result = Math.max(result, num - currMax);
            }
            if(num <= k){
              result = Math.max(result, num);
            }
            numSet.add(num);
          }
        }
      }
    }
    return result;
  }

  /**
   * given 2 strings, return edit distance between them.
   * Edit distance is defined as the shortest transformation steps(include add, delete or replace)
   * needed to transform one string to another.
   * @param s1
   * @param s2
   * @return
   */
  public int editDistance(String s1, String s2){
    int s1Len = s1.length(), s2Len = s2.length();
    int[][] editMap = new int[s1Len+1][s2Len+1];
    for(int i= 0;i<=s1Len;++i){
      editMap[i][0] = i;
    }
    for(int i=1;i<=s2Len;++i){
      editMap[0][i] = i;
    }
    for(int i=1;i<=s1Len;++i){
      for(int j=1;j<=s2Len;++j){
        int modify = s1.charAt(i-1) == s2.charAt(j-1) ? 0 : 1;
        editMap[i][j] = Math.min(Math.min(editMap[i-1][j]+1,editMap[i][j-1]+1),editMap[i-1][j-1]+modify);
        //int minimum = Math.min(Math.min(editMap[i-1][j],editMap[i][j-1]),editMap[i-1][j-1]);
        //editMap[i][j] = s1.charAt(i-1) == s2.charAt(j-1) ? minimum :minimum+1;
      }
    }
    return editMap[s1Len][s2Len];
  }

  /**
   * A robot is located at the top-left corner of a m x n grid.
   * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid.
   * How many possible unique paths are there?
   * @param m
   * @param n
   * @return
   */
  public int uniquePaths(int m, int n) {
    if(m<=0 || n<=0){
      return 0;
    }
    int[][] pathCount = new int[m][n];
    for(int i=0;i<=m-1;++i){
      pathCount[i][0] = 1;
    }
    for(int i=0;i<=n-1;++i){
      pathCount[0][i] = 1;
    }

    for(int i=1;i<=m-1;i++){
      for(int j=1;j<=n-1;j++){
        pathCount[i][j] = pathCount[i-1][j] + pathCount[i][j-1];
      }
    }
    return pathCount[m-1][n-1];
  }

  /**
   * Follow up for "Unique Paths":
   * Now consider if some obstacles are added to the grids. How many unique paths would there be?
   * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
   * There is one obstacle in the middle of a 3x3 grid as illustrated below.
   * [[0,0,0],
   * [0,1,0],
   * [0,0,0]]
   * @param obstacleGrid
   * @return
   */

  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    if(m > 0){
      int n = obstacleGrid[0].length;
      if(n<=0){
        return 0;
      }
      int[][] pathCount = new int[m][n];

      boolean blocked = false;
      for(int i=0;i<=m-1;i++){
        if(!blocked){
          blocked = obstacleGrid[i][0] == 1;
          pathCount[i][0] = 1-obstacleGrid[i][0];
        }else {
          pathCount[i][0] = 0;
        }
      }
      blocked = false;
      for(int i=0;i<=n-1;++i){
        if(!blocked){
          blocked = obstacleGrid[0][i] == 1;
          pathCount[0][i] = 1-obstacleGrid[0][i];
        }else {
          pathCount[0][i] = 0;
        }
      }
      for(int i=1;i<=m-1;i++){
        for(int j=1;j<=n-1;j++){
          pathCount[i][j] = obstacleGrid[i][j] == 0? pathCount[i-1][j] + pathCount[i][j-1]:0;
        }
      }
      return pathCount[m-1][n-1];
    }
    return 0;
  }

  /**
   * Scramble String
   * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
   * To scramble the string, we may choose any non-leaf node and swap its two children.
   * Given two strings @param s1 and @param s2 of the same length
   * @return if s2 is a scrambled string of s1
   */
  public boolean isScramble(String s1, String s2) {
    if (s1.equals(s2)) return true;
    //check if two strings are anagram, if not, return false
    int[] letters = new int[26];
    for (int i=0; i<s1.length(); i++) {
      letters[s1.charAt(i)-'a']++;
      letters[s2.charAt(i)-'a']--;
    }
    for (int i=0; i<26; i++) if (letters[i]!=0) return false;
    for (int i=1; i<s1.length(); i++) {
      if (isScramble(s1.substring(0,i), s2.substring(0,i)) && isScramble(s1.substring(i), s2.substring(i))) {
        return true;
      }
      if (isScramble(s1.substring(0,i), s2.substring(s2.length()-i))
          && isScramble(s1.substring(i), s2.substring(0,s2.length()-i))) {
        return true;
      }
    }
    return false;
  }

  /**
   * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
   * For example,given n=3, there are 5 structurally unique BST
   *   1         3     3      2      1
   *    \       /     /      / \      \
   *     3     2     1      1   3      2
   *    /     /       \                 \
   *   2     1         2                 3
   * Given @param n
   * @return number of structurally unique BST's
   */
  public int numTrees(int n) {
    if(n == 0){
      return 0;
    }
    int[] preNum = new int[n+1];
    return numTreesHelper(n,preNum);
  }
  private int numTreesHelper(int n, int[] preNum){
    if(n == 0 || n == 1){
      return n;
    }
    if(preNum[n] > 0){
      return preNum[n];
    }
    int sum = 0;
    sum += 2*numTreesHelper(n-1,preNum);
    for(int i=2;i<=n-1;++i){
      sum += numTreesHelper(i-1,preNum) * numTreesHelper(n-i,preNum);

    }
    preNum[n] = sum;
    return sum;
  }

  /**
   * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
   * Given s1 = "aabcc",s2 = "dbbca",
   * When s3 = "aadbbcbcac", return true.
   * When s3 = "aadbbbaccc", return false.
   * @param s1
   * @param s2
   * @param s3
   * @return
   */
  public boolean isInterleave(String s1, String s2, String s3) {
    int s1Len = s1.length(), s2Len = s2.length(),s3Len = s3.length();
    if(s3Len != s1Len + s2Len){
      return false;
    }
    if(s1Len == 0){
      return s2.equals(s3);
    }
    if(s2Len == 0){
      return s1.equals(s3);
    }
    boolean[][] dp = new boolean[s1Len+1][s2Len+1];
    dp[0][0] = true;
    for(int i=1;i<=s1Len;++i){
      dp[i][0] = dp[i-1][0] && (s1.charAt(i-1) == s3.charAt(i-1));
    }
    for(int i=1;i<=s2Len;++i){
      dp[0][i] = dp[0][i-1] && (s2.charAt(i-1) == s3.charAt(i-1));
    }
    for(int i=1;i<=s1Len;++i){
      for(int j=1;j<=s2Len;++j){
        dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1))
            || (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
      }
    }
    return dp[s1Len][s2Len];
  }


}
