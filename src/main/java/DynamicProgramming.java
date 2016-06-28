import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZSong on 6/26/16.
 */
public class DynamicProgramming {
  /**
   * given a sequence 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15
   * a longest subsequence is 0, 2, 6, 9, 11, 15, length is 6
   * @param inputArr an unsorted sequence
   * @return length of the longest increasing subsequence
   *
   * let LIS(i) to be length of the longest increasing subsequence ending at element i
   * then LIS(i) = 1 + max{LIS(j)} where 1<=j<i and A(i) > A(j)
   *        or  = 1 if no such j can be found
   * we also keep a parent array p, where p(i) is the index of predecessor element of i in
   * the longest increasing subsequence ending at i, so that we could reconstruct the longest subsequence
   */
  public int longestIncreasingSubsequence_nsquare(int[] inputArr){
    int result;
    int arrLen = inputArr.length;
    if(arrLen == 0){
      result = 0;
    }else{
      result = 1;
      int[] longestSubLength = new int[arrLen];
      longestSubLength[0] = 1;
      for(int i=1;i<arrLen;++i){
        //LIS(i) = 1+max{LS(j)} part
        for(int j=0;j<i;++j){
          if(inputArr[i] > inputArr[j] && longestSubLength[j]+1 > longestSubLength[i]){
            longestSubLength[i] = longestSubLength[j] + 1;
          }
        }
        //LIS(i) = 1 part, did not find any qualify j.
        if(longestSubLength[i] == 0){
          longestSubLength[i] = 1;
        }
        //update the final result
        if(longestSubLength[i] > result){
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
   * @param inputArr
   * @return
   */
  public int longestIncreasingSubsequence_nlogn_length(int[] inputArr){
    int longestIncreasingLength = 0;
    int arrLen = inputArr.length;
    if(arrLen == 0){
      return longestIncreasingLength;
    }else{
      longestIncreasingLength = 1;
      int[] auxArray = new int[arrLen];
      int[] parentArray = new int[arrLen];
      auxArray[0] = 0;  //store index of inputArr
      parentArray[0] = -1;
      for(int i=1;i<arrLen;++i){
        if(inputArr[i] > inputArr[auxArray[longestIncreasingLength-1]]){
          auxArray[longestIncreasingLength] = i;
          parentArray[i] = auxArray[longestIncreasingLength-1];
          longestIncreasingLength++;
        }else{
          int low = 0;
          int high = longestIncreasingLength-1;
          int mid;
          while(low <= high){
            mid = (low + high)/2;
            if(inputArr[i] > auxArray[mid]){
              low = mid + 1;
            }else{
              high = mid - 1;
            }
          }
          auxArray[low] = i;
          if(low == 0){
            parentArray[i] = -1;
          }else {
            parentArray[i] = auxArray[low - 1];
          }
        }
      }
    }
    return longestIncreasingLength;
  }

  public int[] longestIncreasingSubsequence_nlogn_array(int[] inputArr){
    int[] result;
    int arrLen = inputArr.length;
    if(arrLen == 0){
      result = new int[0];
    }else{
      int longestIncreasingLength = 1;
      int[] auxArray = new int[arrLen];
      int[] parentArray = new int[arrLen];
      auxArray[0] = 0;  //store index of inputArr
      parentArray[0] = -1;
      for(int i=1;i<arrLen;++i){
        if(inputArr[i] > inputArr[auxArray[longestIncreasingLength-1]]){
          auxArray[longestIncreasingLength] = i;
          parentArray[i] = auxArray[longestIncreasingLength-1];
          longestIncreasingLength++;
        }else{
          int low = 0;
          int high = longestIncreasingLength-1;
          int mid;
          while(low <= high){
            mid = (low + high)/2;
            if(inputArr[i] > inputArr[auxArray[mid]]){
              low = mid + 1;
            }else{
              high = mid - 1;
            }
          }
          auxArray[low] = i;
          if(low == 0){
            parentArray[i] = -1;
          }else {
            parentArray[i] = auxArray[low - 1];
          }
        }
      }
      result = new int[longestIncreasingLength];
      int k = auxArray[longestIncreasingLength-1];
      for(int i=longestIncreasingLength-1;i>=0; i--){
        result[i] = inputArr[k];
        k = parentArray[k];
      }
    }
    return result;
  }

}
