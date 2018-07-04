public class ArrayProblem {
  public int longestMountain(int[] A) {
    boolean topDown = false;
    int currStart = 0;
    int longest = 0;
    if(A.length < 3) {
      return 0;
    }
    for(int i=1;i<A.length-1;++i) {
      if(A[i] == A[i-1]) {
        currStart = i;
      } else if(A[i] < A[i-1] && A[i] < A[i+1]) {
        currStart = i;
      } else if(A[i] > A[i-1] && A[i] > A[i+1]) {
        while(i<A.length-1 && A[i] > A[i+1]) {
          i++;
        }
        longest = Math.max(longest, i-currStart+1);
        currStart = i;
      }
    }
    return longest;
  }
}
