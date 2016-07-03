import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

import java.util.ArrayList;
import java.util.List;

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
    assertEquals(_dynamicProgramming.longestIncreasingSubsequence_nsquare(empty), 0);
  }

  @Test
  public void validInputReturnCorrectLength_nsquare() {
    int[] input1 = {1, 4, 2, 3};
    assertEquals(_dynamicProgramming.longestIncreasingSubsequence_nsquare(input1), 3);

    int[] input2 = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
    assertEquals(_dynamicProgramming.longestIncreasingSubsequence_nsquare(input2), 6);
  }

  @Test
  public void validInputReturnCorrectLength_nlogn() {
    int[] input1 = {10, 20, 30, 5, 60, 70};
    assertEquals(_dynamicProgramming.longestIncreasingSubsequence_nlogn_length(input1), 5);
    int[] expected_output1 = {10, 20, 30, 60, 70};
    ArrayAsserts.assertArrayEquals(_dynamicProgramming.longestIncreasingSubsequence_nlogn_array(input1), expected_output1);
    int[] input2 = {8, 3, 4, 2, 5, 6};
    int[] expected_output2 = {3, 4, 5, 6};
    ArrayAsserts.assertArrayEquals(_dynamicProgramming.longestIncreasingSubsequence_nlogn_array(input2), expected_output2);
  }

  @Test
  public void largestDivisibleSubsetTest() {
    int[] input1 = {2, 3, 5, 6, 8, 18, 21};
    List<Integer> expected_output1 = new ArrayList<>();
    expected_output1.add(3);
    expected_output1.add(6);
    expected_output1.add(18);
    assertEquals(_dynamicProgramming.largestDivisibleSubset(input1), expected_output1);

    int[] input2 = {1, 2, 3};
    List<Integer> expected_output2 = new ArrayList<>();
    expected_output2.add(1);
    expected_output2.add(2);
    assertEquals(_dynamicProgramming.largestDivisibleSubset(input2), expected_output2);

    int[] input3 = {1, 2, 4, 8};
    List<Integer> expected_output3 = new ArrayList<>();
    expected_output3.add(1);
    expected_output3.add(2);
    expected_output3.add(4);
    expected_output3.add(8);
    assertEquals(_dynamicProgramming.largestDivisibleSubset(input3), expected_output3);
  }

  @Test
  public void editDistanceTest() {
    String input1_word1 = "a";
    String input1_word2 = "b";
    assertEquals(_dynamicProgramming.editDistance(input1_word1, input1_word2), 1);

    String input2_word1 = "axe";
    String input2_word2 = "bake";
    assertEquals(_dynamicProgramming.editDistance(input2_word1, input2_word2), 2);

    String input3_word1 = "at";
    String input3_word2 = "bat";
    assertEquals(_dynamicProgramming.editDistance(input3_word1, input3_word2), 1);

    String input4_word1 = "";
    String input4_word2 = "superlonglonglonglongword";
    assertEquals(_dynamicProgramming.editDistance(input4_word1, input4_word2), 25);

  }
}
