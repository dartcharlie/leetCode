import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by ZSong on 6/26/16.
 */
public class SearchAndSortTest {
  SearchAndSort _searchAndSort;

  @BeforeTest
  public void setup() {
    _searchAndSort = new SearchAndSort();
  }

  @Test
  public void binarySearchTest() {
    int[] sortedArray = new int[]{1, 2, 3, 5, 7, 11, 13, 15, 17, 20, 23, 25, 31, 35, 39};
    assertEquals(_searchAndSort.binarySearch(sortedArray, 5), 3);
    assertEquals(_searchAndSort.binarySearch(sortedArray, 1), 0);
    assertEquals(_searchAndSort.binarySearch(sortedArray, 39), 14);
    assertEquals(_searchAndSort.binarySearch(sortedArray, 22), -1);
  }

  @Test
  public void isPerfectSquareTest() {
    assertEquals(_searchAndSort.isPerfectSquare(16), true);
    assertEquals(_searchAndSort.isPerfectSquare(14), false);
    assertEquals(_searchAndSort.isPerfectSquare(2147483647), false);
  }

  @Test
  void findSubstringTest() {
    String s1 = "barfoothefoobarman";
    String[] words1 = {"foo", "bar"};
    List<Integer> res1 = new ArrayList<>();
    res1.add(0);
    res1.add(9);
    assertEquals(_searchAndSort.findSubstring(s1, words1), res1);

    String s2 = "wordgoodgoodgoodbestword";
    String[] words2 = {"word", "good", "best", "good"};
    List<Integer> res2 = new ArrayList<>();
    res2.add(8);
    assertEquals(_searchAndSort.findSubstring(s2, words2), res2);
  }

  @Test
  void nextPermutationTest() {
    int[] nums1 = new int[]{1, 2, 3};
    int[] res1 = new int[]{1, 3, 2};
    _searchAndSort.nextPermutation(nums1);
    assertEquals(nums1, res1);

    int[] nums2 = new int[]{4, 3, 2, 1};
    int[] res2 = new int[]{1, 2, 3, 4};
    _searchAndSort.nextPermutation(nums2);
    assertEquals(nums2, res2);

    int[] nums3 = new int[]{1, 3, 2, 7, 5};
    int[] res3 = new int[]{1, 3, 5, 2, 7};
    _searchAndSort.nextPermutation(nums3);
    assertEquals(nums3, res3);
  }

  @Test
  void binarySearchLeftTest() {
    int[] nums1 = new int[]{5, 7, 7, 8, 8, 10};
    assertEquals(_searchAndSort.binarySearchLeft(nums1, 8), 3);

    int[] nums2 = new int[]{8, 8, 8, 8, 8, 8};
    assertEquals(_searchAndSort.binarySearchLeft(nums2, 8), 0);

    int[] nums3 = new int[]{5, 7, 7, 8, 8, 10};
    assertEquals(_searchAndSort.binarySearchLeft(nums1, 3), -1);
  }

  @Test
  void binarySearchRightTest() {
    int[] nums1 = new int[]{5, 7, 7, 8, 8, 10};
    assertEquals(_searchAndSort.binarySearchRight(nums1, 8), 4);

    int[] nums2 = new int[]{8, 8, 8, 8, 8, 8};
    assertEquals(_searchAndSort.binarySearchRight(nums2, 8), 5);

    int[] nums3 = new int[]{5, 7, 7, 8, 8, 10};
    assertEquals(_searchAndSort.binarySearchRight(nums3, 3), -1);
  }

  @Test
  void searchRangeTest() {
    int[] nums1 = new int[]{5, 7, 7, 8, 8, 10};
    int[] range1 = new int[]{3, 4};
    assertEquals(_searchAndSort.searchRange(nums1, 8), range1);

    int[] nums2 = new int[]{5, 7, 7, 8, 8, 10};
    int[] range2 = new int[]{-1, -1};
    assertEquals(_searchAndSort.searchRange(nums1, 3), range2);
  }

  @Test
  void searchInsertTest() {
    int[] nums = new int[]{1, 3, 5, 6};
    int target1 = 5, target2 = 2, target3 = 7, target4 = 0;
    int res1 = 2, res2 = 1, res3 = 4, res4 = 0;
    assertEquals(_searchAndSort.searchInsert(nums, target1), res1);
    assertEquals(_searchAndSort.searchInsert(nums, target2), res2);
    assertEquals(_searchAndSort.searchInsert(nums, target3), res3);
    assertEquals(_searchAndSort.searchInsert(nums, target4), res4);
  }
}

