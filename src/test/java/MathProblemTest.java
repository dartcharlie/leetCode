import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
/**
 * Created by ZSong on 7/6/16.
 */
public class MathProblemTest {
  MathProblem _mathProblem;
  @BeforeTest
  public void setup() {
    _mathProblem = new MathProblem();
  }

  @Test
  public void nthDigitTest(){
    assertEquals(_mathProblem.findNthDigit(3),3);
    assertEquals(_mathProblem.findNthDigit(11),0);
    assertEquals(_mathProblem.findNthDigit(189),9);
    assertEquals(_mathProblem.findNthDigit(190),1);
    assertEquals(_mathProblem.findNthDigit(2147483647),2);
  }
}
