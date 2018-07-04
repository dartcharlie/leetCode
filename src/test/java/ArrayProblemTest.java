import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ArrayProblemTest {
  ArrayProblem _arrayProblem;
  @BeforeTest
  public void setup() {
    _arrayProblem = new ArrayProblem();
  }
  @Test
  public void longestMountainTest() {
    Assert.assertEquals(_arrayProblem.longestMountain(new int[] {2,2,2}), 0);
    Assert.assertEquals(_arrayProblem.longestMountain(new int[] {2,1,4,7,3,2,5}), 5);
    Assert.assertEquals(_arrayProblem.longestMountain(new int[] {9,8,7,6,5,4,3}), 0);
    Assert.assertEquals(_arrayProblem.longestMountain(new int[] {1,2,3,4,5}), 0);
    Assert.assertEquals(_arrayProblem.longestMountain(new int[] {1,2,3,4,5,4}), 6);
    Assert.assertEquals(_arrayProblem.longestMountain(new int[] {1,2,2,4,5,3,3}), 4);
  }
}
