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

  @Test
  public void firstMissingPositive(){
    assertEquals(_mathProblem.firstMissingPositive(new int[]{1,2,0}),3);
    assertEquals(_mathProblem.firstMissingPositive(new int[]{1}),2);
    assertEquals(_mathProblem.firstMissingPositive(new int[]{10,4,-6,1,2,5,3}),6);
    assertEquals(_mathProblem.firstMissingPositive(new int[]{}),1);
    assertEquals(_mathProblem.firstMissingPositive(new int[]{-1,-2,-3}),1);
    assertEquals(_mathProblem.firstMissingPositive(new int[]{0}),1);
    assertEquals(_mathProblem.firstMissingPositive(new int[]{0,1,2,3,4}),5);
    assertEquals(_mathProblem.firstMissingPositive(new int[]{5,4,3,1}),2);
  }
}
