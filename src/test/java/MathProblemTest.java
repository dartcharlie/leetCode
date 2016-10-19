import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

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
  public void firstMissingPositiveTest(){
    assertEquals(_mathProblem.firstMissingPositive(new int[]{1,2,0}),3);
    assertEquals(_mathProblem.firstMissingPositive(new int[]{1}),2);
    assertEquals(_mathProblem.firstMissingPositive(new int[]{10,4,-6,1,2,5,3}),6);
    assertEquals(_mathProblem.firstMissingPositive(new int[]{}),1);
    assertEquals(_mathProblem.firstMissingPositive(new int[]{-1,-2,-3}),1);
    assertEquals(_mathProblem.firstMissingPositive(new int[]{0}),1);
    assertEquals(_mathProblem.firstMissingPositive(new int[]{0,1,2,3,4}),5);
    assertEquals(_mathProblem.firstMissingPositive(new int[]{5,4,3,1}),2);
  }

  @Test
  public void lengthOfLastWordTest(){
    assertEquals(_mathProblem.lengthOfLastWord(""),0);
    assertEquals(_mathProblem.lengthOfLastWord("  "),0);
    assertEquals(_mathProblem.lengthOfLastWord("s "),1);
    assertEquals(_mathProblem.lengthOfLastWord(" s"),1);
    assertEquals(_mathProblem.lengthOfLastWord("Hello World"),5);
    assertEquals(_mathProblem.lengthOfLastWord("oneword"),7);
    assertEquals(_mathProblem.lengthOfLastWord("ni hao ma"),2);
    assertEquals(_mathProblem.lengthOfLastWord("blah blah blah   "),4);
  }

  @Test
  public void generateMatrixTest(){
    int[][] expected_res1 = new int[][]{{1}};
    int[][] expected_res2 = new int[][]{{1,2},{4,3}};
    int[][] expected_res3 = new int[][]{{1,2,3},{8,9,4},{7, 6, 5}};
    int[][] expected_res4 = new int[][]{{1, 2, 3, 4},{12,13,14, 5},{11,16,15, 6},{10, 9, 8, 7}};
    int[][] expected_res5 = new int[][]{{1,2,3,4,5},{16,17,18,19,6},{15,24,25,20,7},{14,23,22,21,8},{13,12,11,10,9}};
    ArrayAsserts.assertArrayEquals(_mathProblem.generateMatrix(1),expected_res1);
    ArrayAsserts.assertArrayEquals(_mathProblem.generateMatrix(2),expected_res2);
    ArrayAsserts.assertArrayEquals(_mathProblem.generateMatrix(3),expected_res3);
    ArrayAsserts.assertArrayEquals(_mathProblem.generateMatrix(4),expected_res4);
    ArrayAsserts.assertArrayEquals(_mathProblem.generateMatrix(5),expected_res5);
  }

  @Test
  public void setZeroesTest(){
    int[][] matrix1 = new int[][]{{1,1},{1,0}};
    int[][] expected_res1 = new int[][]{{1,0},{0,0}};
    _mathProblem.setZeroes(matrix1);
    ArrayAsserts.assertArrayEquals(expected_res1,matrix1);

    int[][] matrix2 = new int[][]{{0}};
    int[][] expected_res2 = new int[][]{{0}};
    _mathProblem.setZeroes(matrix2);
    ArrayAsserts.assertArrayEquals(expected_res2,matrix2);

    int[][] matrix3 = new int[][]{{1,0}};
    int[][] expected_res3 = new int[][]{{0,0}};
    _mathProblem.setZeroes(matrix3);
    ArrayAsserts.assertArrayEquals(expected_res3,matrix3);

    int[][] matrix4 = new int[][]{{1,1,1},{0,0,1},{1,1,1}};
    int[][] expected_res4 = new int[][]{{0,0,1},{0,0,0},{0,0,1}};
    _mathProblem.setZeroes(matrix4);
    ArrayAsserts.assertArrayEquals(expected_res4,matrix4);

    int[][] matrix5 = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
    int[][] expected_res5 = new int[][]{{1,0,1},{0,0,0},{1,0,1}};
    _mathProblem.setZeroes(matrix5);
    ArrayAsserts.assertArrayEquals(expected_res5,matrix5);
  }
}
