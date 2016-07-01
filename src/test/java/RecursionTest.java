import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
public class RecursionTest {
  Recursion _recursion;
  @BeforeTest
  public void setup(){
    _recursion = new Recursion();
  }

  @Test
  public void permutationTest(){
    List<Integer> input1 = new ArrayList<>();
    input1.add(1);
    input1.add(2);
    input1.add(3);

    List<List<Integer>> expected_output1 = new ArrayList<>();
    expected_output1.add(Arrays.asList(new Integer[]{1,2,3}));
    expected_output1.add(Arrays.asList(new Integer[]{1,3,2}));
    expected_output1.add(Arrays.asList(new Integer[]{2,1,3}));
    expected_output1.add(Arrays.asList(new Integer[]{2,3,1}));
    expected_output1.add(Arrays.asList(new Integer[]{3,2,1}));
    expected_output1.add(Arrays.asList(new Integer[]{3,1,2}));
    assertEquals(_recursion.permutations(input1),expected_output1);
  }

}
