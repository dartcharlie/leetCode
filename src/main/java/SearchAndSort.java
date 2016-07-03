import java.util.Arrays;

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
      mid = (start + end) / 2;
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

  public int[] builtInArraySort(int[] unsorted) {
    Arrays.sort(unsorted);
    return unsorted;
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
}
