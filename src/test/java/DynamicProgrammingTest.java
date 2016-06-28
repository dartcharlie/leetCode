import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

import static org.testng.Assert.assertEquals;

/**
 * Created by ZSong on 6/26/16.
 */
public class DynamicProgrammingTest {
  DynamicProgramming _dynamicProgramming;
  @BeforeTest
  public void setup() {
    _dynamicProgramming = new DynamicProgramming();
  }

  @Test
  public void emptyArrayReturnZero() {
    int[] empty = new int[0];
    assertEquals(_dynamicProgramming.longestIncreasingSubsequence_nsquare(empty),0);
  }

  @Test
  public void validInputReturnCorrectLength_nsquare() {
    int[] input1 = {1,4,2,3};
    assertEquals(_dynamicProgramming.longestIncreasingSubsequence_nsquare(input1),3);

    int[] input2 = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
    assertEquals(_dynamicProgramming.longestIncreasingSubsequence_nsquare(input2),6);
  }

  @Test
  public void validInputReturnCorrectLength_nlogn() {
    int[] input1 = {10,20,30,5,60,70};
    assertEquals(_dynamicProgramming.longestIncreasingSubsequence_nlogn_length(input1),5);
    int[] expected_output1 = {10,20,30,60,70};
    ArrayAsserts.assertArrayEquals(_dynamicProgramming.longestIncreasingSubsequence_nlogn_array(input1),expected_output1);
    int[] input2 = {8,3,4,2,5,6};
    int[] expected_output2 = {3,4,5,6};
    ArrayAsserts.assertArrayEquals(_dynamicProgramming.longestIncreasingSubsequence_nlogn_array(input2),expected_output2);
  }
}
