import java.util.*;

/**
 * Created by ZSong on 6/26/16.
 */
public class SearchAndSort {
  /**
   * @param arrayToSearch sorted array to search from
   * @param element       element to search
   * @return index of array if element is found, otherwise -1 indicating no such element in array
   */
  public int binarySearch(int[] arrayToSearch, int element) {
    int arrayLength = arrayToSearch.length;
    int start = 0;
    int end = arrayLength - 1;
    int mid;
    int result = -1;
    while (start <= end) {
      mid = (end - start) / 2 + start;
      if (arrayToSearch[mid] == element) {
        result = mid;
        break;
      } else if (arrayToSearch[mid] < element) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    return result;
  }

  public boolean isPerfectSquare(int num) {
    boolean result = false;
    if (num == 1) {
      result = true;
    }
    int low = 1;
    int high = num - 1;
    int mid;
    long product;
    while (low <= high) {
      mid = (low + high) / 2;
      product = (long) mid * mid;
      if (product == num) {
        result = true;
        break;
      } else if (product < num) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return result;
  }

  public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> indices = new ArrayList<>();
    if(s == null || s.length() == 0 || words == null || words.length == 0){

    }else{
      int wordLen = words[0].length();
      int strLen = s.length();
      int wordNum = words.length;
      int currStart, currEnd;
      Map<String,Integer> wordMap = new HashMap<>();
      for(String word: words){
        if(wordMap.containsKey(word)){
          int currCount = wordMap.get(word);
          wordMap.put(word,currCount+1);
        }else{
          wordMap.put(word,1);
        }
      }
      for(currStart = 0;currStart <= strLen - (wordNum*wordLen); currStart++){
        Map<String,Integer> currMap = new HashMap<String,Integer>(wordMap);
        int matchCounter = 0;
        for(currEnd = currStart + wordLen; currEnd <= strLen; currEnd = currEnd + wordLen){
          String sub = s.substring(currEnd - wordLen,currEnd);
          if(currMap.containsKey(sub)){
            int remainingCount = currMap.get(sub);
            if(remainingCount > 0) {
              currMap.put(sub,remainingCount-1);
              matchCounter++;
            }else{
              break;
            }
          }else{
            break;
          }
        }
        if(matchCounter == wordNum){
          indices.add(currStart);
        }
      }
    }
    return indices;
  }

  /**
   * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
   *
   * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
   * for example
   * 1, 2, 3 -> 1, 3, 2
   * 6，3，4，9，8，7，1 -> 6，3，7，1，4，8，9
   * 5,4,3,2,1 -> 1,2,3,4,5
   */
  public void nextPermutation(int[] nums) {
    if(nums != null && nums.length > 0){
      int numsLen = nums.length;
      int cutOffIndex = -1;
      for(int i=numsLen-1;i>0;i--){
        if(nums[i] > nums[i-1]){
          cutOffIndex = i-1;
          break;
        }
      }
      if(cutOffIndex > -1){
        int swapPos = cutOffIndex;
        for(int i=numsLen-1;i>cutOffIndex;i--){
          if(nums[i] > nums[cutOffIndex]){
            swapPos = i;
            break;
          }
        }
        swap(nums,cutOffIndex,swapPos);
      }
      reverse(nums,cutOffIndex+1);
    }
  }

  private void swap(int[] nums, int i, int j){
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  private void reverse(int[] nums, int i){
    int numsLen = nums.length;
    int start = i;
    int end = numsLen -1;
    while(start < end){
      swap(nums,start,end);
      start++;
      end--;
    }
  }

  public int binarySearchLeft(int[] nums,  int target){
    int low = 0;
    int high = nums.length -1;
    int candidate = -1;
    while(low <= high){
      int mid = (high - low )/2 + low;
      if(nums[mid] < target){
        low = mid +1;
      }else if(nums[mid] > target){
        high = mid-1;
      }else{
        candidate = mid;
        high = mid-1;
      }
    }
    return candidate;
  }

  public int binarySearchRight(int[] nums,  int target){
    int low = 0;
    int high = nums.length -1;
    int candidate = -1;
    while(low <= high){
      int mid = (high - low)/2 + low;
      if(nums[mid] < target){
        low = mid +1;
      }else if(nums[mid] > target){
        high = mid-1;
      }else{
        candidate = mid;
        low = mid+1;
      }
    }
    return candidate;
  }

  /**
   * Given a sorted array of integers, find the starting and ending position of a given target value.
   * @param nums sorted array of integers
   * @param target target value
   * @return starting and ending position for target value
   * example: Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
   * Given [5, 7, 7, 8, 8, 10] and target value 3, return [-1, 1].
   */
  public int[] searchRange(int[] nums, int target) {
    int[] range = new int[2];
    int leftPos = binarySearchLeft(nums,target);
    if(leftPos == -1){
      range[0] = -1;
      range[1] = -1;
    }else{
      int rightPos = binarySearchRight(nums,target);
      range[0] = leftPos;
      range[1] = rightPos;
    }
    return range;
  }

  /**
   * leetcode 35
   * Given a sorted array and a target value, return the index if the target is found.
   * If not, return the index where it would be if it were inserted in order.
   * @param nums
   * @param target
   * @return
   */
  public int searchInsert(int[] nums, int target) {
    int low = 0;
    int high = nums.length-1;
    int ret = -1;
    while(low <= high){
      int mid = (high - low)/2 + low;
      if(nums[mid] > target){
        high = mid -1;
      }else if(nums[mid] < target){
        low = mid +1;
      }else{
        ret = mid;
        break;
      }
    }
    if(ret == -1){
      ret = low > high? low: nums[low] > target? low :low+1;
    }
    return ret;
  }
}
