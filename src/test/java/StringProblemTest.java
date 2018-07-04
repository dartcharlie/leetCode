import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StringProblemTest {
  StringProblem _stringProblem;
  @BeforeTest
  public void setup() {
    _stringProblem = new StringProblem();
  }
  @Test
  public void backspaceCompareTest() {
    Assert.assertTrue(_stringProblem.backspaceCompare("ab#c", "ad#c"));
    Assert.assertTrue(_stringProblem.backspaceCompare("a##c", "#a#c"));
    Assert.assertTrue(_stringProblem.backspaceCompare("y#fo##f", "y#f#o##f"));
    Assert.assertFalse(_stringProblem.backspaceCompare("y#fo", "y#f#o##f"));
  }
}
