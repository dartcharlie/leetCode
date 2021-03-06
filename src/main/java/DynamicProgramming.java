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

  /**
   * leetcode 132 Palindrome Partitioning II
   * Given a string s, partition s such that every substring of the partition is a palindrome.
   * Return the minimum cuts needed for a palindrome partitioning of s.
   * For example, given s = "aab",
   * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
   * @param s input string
   * @return
   */
  public int minCut(String s) {
    boolean[][] matrix = palindromeMatrix(s);
    int sLen = s.length();
    int[] dp = new int[sLen+1];
    dp[0] = -1;
    for(int i=1;i<=sLen;++i){
      dp[i] = i;
      for(int j=0;j<i;++j){
        if(matrix[j][i-1]){
          dp[i] = Math.min(dp[i],dp[j]+1);
        }
      }
    }
    return dp[sLen];
  }

  /**
   * recursive solution, will have time out limitation exceed error
   * @param s
   * @param memory
   * @return
   */
  private int minCut(String s, Map<String, Integer> memory){
    int sLen = s.length();
    int minimum = Integer.MAX_VALUE;
    if(sLen == 0 || sLen == 1){
      minimum = 0;
    } else if(memory.containsKey(s)){
      minimum = memory.get(s);
    } else if(isPalindrome(s)) {
      minimum = 0;
      memory.put(s,0);
    } else {
      int currMin;
      for(int i=1;i<sLen;++i){
        currMin = minCut(s.substring(0,i),memory) + minCut(s.substring(i)) + 1;
        if(currMin < minimum){
          minimum = currMin;
        }
      }
      memory.put(s,minimum);
    }
    return minimum;
  }

  private boolean isPalindrome(String s){
    int startIndex = 0;
    int endIndex = s.length()-1;
    while(startIndex<endIndex){
      if(s.charAt(startIndex) != s.charAt(endIndex)){
        return false;
      }
      startIndex++;
      endIndex--;
    }
    return true;
  }

  /**
   * given a string s, palindromeMatrix[i][j] represents whether s.substring(i,j+1) is a palindrome
   * @param s
   * @return
   */
  private boolean[][] palindromeMatrix(String s){
    int sLen = s.length();
    boolean[][] matrix = new boolean[sLen][sLen];
    for(int i=0;i<sLen;++i){
      //each character is a palindrome
      matrix[i][i] = true;
    }
    for(int i=0;i<sLen-1;++i){
      //if adjacent characters are the same, then s.substring(i,i+2) is a palindrome
      matrix[i][i+1] = s.charAt(i) == s.charAt(i+1);
    }
    for(int length=2;length<sLen;length++){
      for(int startPos=0;startPos+length<sLen;++startPos){
        matrix[startPos][startPos+length] = matrix[startPos+1][startPos+length-1] && (s.charAt(startPos) == s.charAt(startPos+length));
      }
    }
    return matrix;
  }

  /**
   * given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee
   * @param prices
   * @param fee
   * @return the maximum profit you can make
   * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
   * You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
   */
  public int maxProfit(int[] prices, int fee) {
    int arrayLen = prices.length;
    int[] buy = new int[arrayLen];
    int[] hold = new int[arrayLen];
    int[] sell = new int[arrayLen];
    int[] skip = new int[arrayLen];

    buy[0] = -prices[0];
    hold[0] = Integer.MIN_VALUE;
    sell[0] = Integer.MIN_VALUE;
    skip[0] = 0;
    for(int i=1;i<arrayLen;++i){
      buy[i] = Math.max(sell[i-1],skip[i-1]) - prices[i];
      hold[i] = Math.max(buy[i-1],hold[i-1]);
      sell[i] = Math.max(buy[i-1],hold[i-1]) - fee + prices[i];
      skip[i] = Math.max(sell[i-1],skip[i-1]);
    }
    return Math.max(sell[arrayLen-1],skip[arrayLen-1]);
  }

  /**
   * Given two integer arrays A and B
   * @param A
   * @param B
   * @return the maximum length of an subarray that appears in both arrays.
   * a similar problem is find longest common string between 2 strings
   */
  public int findLength(int[] A, int[] B) {
    int aLen = A.length;
    int bLen = B.length;
    int[][] matchMap = new int[aLen+1][bLen+1];
    for(int i=0; i<=aLen; ++i){
      matchMap[i][0] = 0;
    }
    for(int i=1;i<=bLen;++i) {
      matchMap[0][i] = 0;
    }
    int maxLen = 0;
    for(int i=1;i<=aLen;++i) {
      for(int j=1;j<=bLen;++j) {
        if(A[i-1] == B[j-1]){
          matchMap[i][j] = matchMap[i-1][j-1] +1;
          maxLen = Math.max(maxLen,matchMap[i][j]);
        }
      }
    }
    return maxLen;
  }

  public int shortestPathLength(int[][] graph) {
    int dp[][] = new int[graph.length][1 << graph.length];
    Queue<NodeState> visitQueue = new LinkedList<>();
    for(int i = 0; i< graph.length; ++ i) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
      dp[i][1<<i] = 0;
      visitQueue.offer(new NodeState(1<<i,i));
    }
    while(!visitQueue.isEmpty()) {
      NodeState ns = visitQueue.poll();
      for(int next: graph[ns.source]) {
        int nextState = (1<<next)|(ns.state);
        if(dp[next][nextState] > dp[ns.source][ns.state] + 1) {
          dp[next][nextState] = dp[ns.source][ns.state] + 1;
          visitQueue.offer(new NodeState(nextState,next));
        }
      }
    }
    int shortest = Integer.MAX_VALUE;
    for(int i=0;i<graph.length;++i) {
      shortest = Math.min(shortest, dp[i][(1<<graph.length)-1]);
    }
    return shortest;
  }

  private class NodeState {
    int state;
    int source;
    public NodeState(int s, int sourceNode) {
      state = s;
      source = sourceNode;
    }
  }
}
