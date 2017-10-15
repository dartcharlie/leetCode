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

  @Test
  public void rotateTest() {
    int[][] matrix1 = new int[][]{{1}};
    int[][] expected_res1 = new int[][]{{1}};
    _mathProblem.rotate(matrix1);
    ArrayAsserts.assertArrayEquals(expected_res1, matrix1);

    int[][] matrix2 = new int[][]{{1, 2}, {3, 4}};
    int[][] expected_res2 = new int[][]{{3, 1}, {4, 2}};
    _mathProblem.rotate(matrix2);
    ArrayAsserts.assertArrayEquals(expected_res2, matrix2);

    int[][] matrix3 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    int[][] expected_res3 = new int[][]{{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
    _mathProblem.rotate(matrix3);
    ArrayAsserts.assertArrayEquals(expected_res3, matrix3);

    int[][] matrix4 = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    int[][] expected_res4 = new int[][]{{13, 9, 5, 1}, {14, 10, 6, 2}, {15, 11, 7, 3}, {16, 12, 8, 4}};
    _mathProblem.rotate(matrix4);
    ArrayAsserts.assertArrayEquals(expected_res4, matrix4);


    int[][] matrix5 = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
    int[][] expected_res5 = new int[][]{{21, 16, 11, 6, 1}, {22, 17, 12, 7, 2}, {23, 18, 13, 8, 3}, {24, 19, 14, 9, 4}, {25, 20, 15, 10, 5}};
    _mathProblem.rotate(matrix5);
    ArrayAsserts.assertArrayEquals(expected_res5, matrix5);
  }

  @Test
  public void multiplyTest() {
    assertEquals(_mathProblem.multiply("1","2"),"2");
    assertEquals(_mathProblem.multiply("12","0"),"0");
    assertEquals(_mathProblem.multiply("12345","321"),"3962745");
    assertEquals(_mathProblem.multiply("1928","234569091029"),"452249207503912");
  }

  @Test
  public void myPowTest(){
    assertEquals(_mathProblem.myPow(2.0,2),4.0);
    assertEquals(_mathProblem.myPow(2.0,3),8.0);
    assertEquals(_mathProblem.myPow(2.0,0),1.0);
    assertEquals(_mathProblem.myPow(2.0,-2),0.25);
    assertEquals(_mathProblem.myPow(2.0,-3),0.125);
    assertEquals(_mathProblem.myPow(0.00001,2147483647),0.0);
    assertEquals(_mathProblem.myPow(2,-2147483647),0.0);
  }

  @Test
  public void find132patternTest() {
    assertEquals(_mathProblem.find132pattern(new int[]{}), false);
    assertEquals(_mathProblem.find132pattern(new int[]{1,2,3,4,5}), false);
    assertEquals(_mathProblem.find132pattern(new int[]{1,1,1,1}), false);
    assertEquals(_mathProblem.find132pattern(new int[]{3, 1, 4, 2}), true);
    assertEquals(_mathProblem.find132pattern(new int[]{-1, 3, 2, 0}), true);
    assertEquals(_mathProblem.find132pattern(new int[]{0, 1, -1, 0, 1}), false);
    assertEquals(_mathProblem.find132pattern(new int[]{3, 5, 0, 3, 4}), true); // 3, 5, 4
  }
}
