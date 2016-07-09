import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ZSong on 7/8/16.
 */
public class UtilsTest {
  Utils _utils;

  @BeforeTest
  public void setup() {
    _utils = new Utils();
  }

  @Test
  public void reversePolishNotationCalculationTest() {
    String[] input1 = {"2", "1", "+", "3", "*"};
    Assert.assertEquals(_utils.reversePolishNotationCalculation(input1), 9);

    String[] input2 = {"4", "13", "5", "/", "+"};
    Assert.assertEquals(_utils.reversePolishNotationCalculation(input2), 6);

  }

  @Test
  public void shuntingYardSimplifiedTest() {
    String input1 = "(2 + 1) * 3";
    List<String> expected_output1 = Arrays.asList(new String[]{"2", "1", "+", "3", "*"});
    Assert.assertEquals(_utils.shuntingYardSimplified(input1), expected_output1);

    String input2 = "4 + (13 / 5)";
    List<String> expected_output2 = Arrays.asList(new String[]{"4", "13", "5", "/", "+"});
    Assert.assertEquals(_utils.shuntingYardSimplified(input2), expected_output2);

  }

  @Test
  public void shuntingYardAndRPNTest() {
    String input1 = "12 / (4 / (1 + 1))";
    List<String> RPN1 = _utils.shuntingYardSimplified(input1);
    Assert.assertEquals(_utils.reversePolishNotationCalculation(RPN1.toArray(new String[RPN1.size()])), 6);

    String input2 = "(1 + (3 * 4)) / 13";
    List<String> RPN2 = _utils.shuntingYardSimplified(input2);
    Assert.assertEquals(_utils.reversePolishNotationCalculation(RPN2.toArray(new String[RPN2.size()])), 1);
  }
}
