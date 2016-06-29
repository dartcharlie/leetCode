import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
    int[] sortedArray = new int[]{1,2,3,5,7,11,13,15,17,20,23,25,31,35,39};
    assertEquals(_searchAndSort.binarySearch(sortedArray,5),3);
    assertEquals(_searchAndSort.binarySearch(sortedArray,1),0);
    assertEquals(_searchAndSort.binarySearch(sortedArray,39),14);
    assertEquals(_searchAndSort.binarySearch(sortedArray,22),-1);
  }

  @Test
  public void isPerfectSquareTest(){
    assertEquals(_searchAndSort.isPerfectSquare(16),true);
    assertEquals(_searchAndSort.isPerfectSquare(14),false);
    assertEquals(_searchAndSort.isPerfectSquare(2147483647),false);
  }
}
