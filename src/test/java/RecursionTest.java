import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class RecursionTest {
  Recursion _recursion;

  @BeforeTest
  public void setup() {
    _recursion = new Recursion();
  }

  @Test
  public void permutationTest() {
    List<Integer> input1 = new ArrayList<>();
    input1.add(1);
    input1.add(2);
    input1.add(3);

    List<List<Integer>> expected_output1 = new ArrayList<>();
    expected_output1.add(Arrays.asList(new Integer[]{1, 2, 3}));
    expected_output1.add(Arrays.asList(new Integer[]{1, 3, 2}));
    expected_output1.add(Arrays.asList(new Integer[]{2, 1, 3}));
    expected_output1.add(Arrays.asList(new Integer[]{2, 3, 1}));
    expected_output1.add(Arrays.asList(new Integer[]{3, 2, 1}));
    expected_output1.add(Arrays.asList(new Integer[]{3, 1, 2}));
    assertEquals(_recursion.permutations(input1), expected_output1);
  }

  @Test
  public void matrixLongestIncreasingPathLengthTest() {
    //edge case test
    int[][] input1 = new int[][]{{}};
    assertEquals(_recursion.matrixLongestIncreasingPathLength(input1), 0);

    //example case test
    int[][] input2 = new int[][]{{6, 8, 15}, {9, 7, 10}, {3, 5, 2}};
    assertEquals(_recursion.matrixLongestIncreasingPathLength(input2), 5);

    //simple test case
    int[][] input3 = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
    assertEquals(_recursion.matrixLongestIncreasingPathLength(input3), 2);

    //full travel test
    int[][] input4 = new int[][]{{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}};
    assertEquals(_recursion.matrixLongestIncreasingPathLength(input4), 16);
  }

  @Test
  public void addToANumberTest(){
    String input1 = "123";
    assertEquals(_recursion.addToANumber(input1,6),Arrays.asList(new String[]{"1+2+3"}));
    String input2 = "3210";
    assertEquals(_recursion.addToANumber(input2,22),Arrays.asList(new String[]{"32-10"}));
    String input3 = "123456789";
    assertEquals(_recursion.addToANumber(input3,100),
        Arrays.asList(new String[]{"123+45-67+8-9","123+4-5+67-89","123-45-67+89",
                                  "123-4-5-6-7+8-9","12+3+4+5-6-7+89","12+3-4+5+67+8+9",
                                  "12-3-4+5-6+7+89","1+23-4+56+7+8+9","1+23-4+5+6+78-9",
                                  "1+2+34-5+67-8+9","1+2+3-4+5+6+78+9","-1+2-3+4+5+6+78+9"}));
  }

  @Test
  public void generateParenthesisTest(){
    assertEquals(_recursion.generateParenthesis(2),Arrays.asList(new String[]{"(())","()()"}));
    assertEquals(_recursion.generateParenthesis(3),Arrays.asList(new String[]{"((()))","(()())","(())()","()(())","()()()"}));
  }

}
