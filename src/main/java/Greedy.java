import java.util.*;

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

  /**
   * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once.
   * You must make sure your result is the smallest in lexicographical order among all possible results.
   * Given "bcabc"  Return "abc"
   * Given "cbacdcbc" Return "acdb"
   * @param s input string
   * @return
   */
  public String removeDuplicateLetters(String s) {
    Map<Character,Integer> counter = new HashMap<>();
    Set<Character> visited = new HashSet<>();
    Stack<Character> sequence = new Stack<>();

    char[] input = s.toCharArray();
    for(char c:input){
      if(counter.containsKey(c)){
        counter.put(c,counter.get(c)+1);
      }else{
        counter.put(c,1);
      }
    }
    for(char c:input){
      counter.put(c,counter.get(c)-1);
      if(!visited.contains(c)){
        visited.add(c);
        while(!sequence.empty()){
          char last = sequence.peek();
          if(last > c && counter.get(last) > 0){
            sequence.pop();
            visited.remove(last);
          }else{
            break;
          }
        }
        sequence.push(c);
      }
    }
    StringBuilder sb = new StringBuilder();
    while(!sequence.empty()){
      sb.insert(0,sequence.pop());
    }
    return sb.toString();
  }

  /**
   * given an array A contains only digits 0-9
   * return a maximum number of length k from digits of A. The relative order of digits from A must be preserved
   * @param nums
   * @param k
   * @return
   */
  int[] maxSubArray(int[] nums, int k){
    int[] result = new int[k];
    int idx = 0;
    for(int i=0;i<nums.length && idx<=k;++i){
      //use result array as a stack, idx as stack index;
      //when stack is not empty, there are enough numbers to fill k-idx empty slots and current number is greater than stack top
      //pop out current stack top number
      while(idx>0 && nums.length-1-i>=k-idx && nums[i] > result[idx-1]){
        idx--;
      }
      if(idx < k) {
        result[idx] = nums[i];
        idx++;
      }

    }
    return result;
  }

  /**
   * compare 2 arrays, return true if first array is greater or equal than second array
   * array order defines as follows:
   *
   * start form index 0, whichever array has larger element first will be greater
   * if finished traverse one array, but still can't find greater element, longer array consider greater.
   * @param a first array
   * @param b second array
   * @return true if a greater or equal than b, false if a smaller than b
   */
  public boolean greaterOrEqual(int[] a, int[] b){
    int m = a.length;
    int n = b.length;
    int i=0;
    while(i<m && i<n && a[i] == b[i]){
      i++;
    }
    return i== n || (i<m && a[i] > b[i]);
  }

  public boolean greaterOrEqual(int[] a, int i, int[] b, int j){
    int m = a.length;
    int n = b.length;

    while(i<m && j<n && a[i] == b[j]){
      i++;
      j++;
    }
    return j== n || (i<m && a[i] > b[j]);
  }

  /**
   *
   * @param a
   * @param b
   * @return
   */
  public int[] merge(int[] a, int[] b){
    int m = a.length;
    int n = b.length;
    int[] result =new int[m+n];
    for(int idx=0,i=0,j=0;idx<m+n;++idx){
      result[idx] = greaterOrEqual(a,i,b,j)? a[i++] : b[j++];
    }
    return result;
  }

  /**
   * leetcode 321 create maximum number
   *
   * @param nums1
   * @param nums2
   * @param k
   * @return
   */
  public int[] maxNumber(int[] nums1, int[] nums2, int k) {
    int m = nums1.length;
    int n = nums2.length;
    int[] ans = new int[k];
    for (int i = Math.max(0, k - n); i <= k && i <= m; ++i) {
      int[] candidate = merge(maxSubArray(nums1, i), maxSubArray(nums2, k - i));
      if (greaterOrEqual(candidate,0, ans,0)) ans = candidate;
    }
    return ans;
  }
}
