import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
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

  @Test
  public void wordSearchIITest(){
    char[][] board1 = new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
    String[] words1 = new String[]{"oath","pea","eat","rain"};
    List<String> expected_output1 = Arrays.asList(new String[]{"oath","eat"});
    assertEquals(_recursion.findWordsII(board1,words1),expected_output1);
  }

  @Test
  public void numberIslandsTest(){
    assertEquals(_recursion.numIslands(null),0);
    char[][] grid1 = new char[][]{{'0'}};
    assertEquals(_recursion.numIslands(grid1),0);
    char[][] grid2 = new char[][]{{'1'}};
    assertEquals(_recursion.numIslands(grid2),1);
    char[][] grid3 = new char[][]{{'0','1','0'},{'1','0','1'},{'0','1','0'}};
    assertEquals(_recursion.numIslands(grid3),4);
    char[][] grid4 = new char[][]{{'0','1','0'},{'0','1','0'},{'0','1','0'}};
    assertEquals(_recursion.numIslands(grid4),1);
    char[][] grid5 = new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
    assertEquals(_recursion.numIslands(grid5),1);
    char[][] grid6 = new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
    assertEquals(_recursion.numIslands(grid6),3);
  }

  @Test
  public void surroundedRegionsTest(){
    assertEquals(_recursion.surroundedRegions(null),null);
    char[][] board1 = new char[][]{{'O'}};
    char[][] res1 = new char[][]{{'O'}};
    ArrayAsserts.assertArrayEquals(_recursion.surroundedRegions(board1),res1);

    char[][] board2 = new char[][]{{'O','X'},{'X','O'}};
    char[][] res2 = new char[][]{{'O','X'},{'X','O'}};
    ArrayAsserts.assertArrayEquals(_recursion.surroundedRegions(board2),res2);

    char[][] board3 = new char[][]{{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
    char[][] res3 = new char[][]{{'X','X','X','X'},{'X','X','X','X'},{'X','X','X','X'},{'X','O','X','X'}};
    ArrayAsserts.assertArrayEquals(_recursion.surroundedRegions(board3),res3);
  }

  @Test
  public void findLaddersIITest(){
    List<String> stringList1 = Arrays.asList(new String[]{"hot","dot","dog","lot","log"});
    Set<String> dic1 = new HashSet<>(stringList1);
    List<List<String>> expected_res1 = new ArrayList<>();
    expected_res1.add(Arrays.asList(new String[]{"hit","hot","dot","dog","cog"}));
    expected_res1.add(Arrays.asList(new String[]{"hit","hot","lot","log","cog"}));
    List<List<String>> res1 = _recursion.findLaddersII("hit","cog",dic1);
    assertEquals(res1.size(),expected_res1.size());
    assertTrue(res1.containsAll(expected_res1));
    assertTrue(expected_res1.containsAll(res1));

    List<String> stringList2 = Arrays.asList(new String[]{"a","b","c"});
    Set<String> dic2 = new HashSet<>(stringList2);
    List<List<String>> expected_res2 = new ArrayList<>();
    expected_res2.add(Arrays.asList(new String[]{"a","c"}));
    List<List<String>> res2 = _recursion.findLaddersII("a","c",dic2);
    assertEquals(res2.size(),expected_res2.size());
    assertTrue(res2.containsAll(expected_res2));
    assertTrue(expected_res2.containsAll(res2));

    List<String> stringList3 = Arrays.asList(new String[]{"ted","tex","red","tax","tad","den","rex","pee"});
    Set<String> dic3 = new HashSet<>(stringList3);
    List<List<String>> expected_res3 = new ArrayList<>();
    expected_res3.add(Arrays.asList(new String[]{"red","ted","tad","tax"}));
    expected_res3.add(Arrays.asList(new String[]{"red","ted","tex","tax"}));
    expected_res3.add(Arrays.asList(new String[]{"red","rex","tex","tax"}));
    List<List<String>> res3 = _recursion.findLaddersII("red","tax",dic3);
    assertEquals(res3.size(),expected_res3.size());
    assertTrue(res3.containsAll(expected_res3));
    assertTrue(expected_res3.containsAll(res3));
  }

  @Test
  public void countAndSayRecursiveTest(){
    assertEquals(_recursion.countAndSayRecursive(1),"1");
    assertEquals(_recursion.countAndSayRecursive(2),"11");
    assertEquals(_recursion.countAndSayRecursive(3),"21");
    assertEquals(_recursion.countAndSayRecursive(4),"1211");
    assertEquals(_recursion.countAndSayRecursive(5),"111221");
    assertEquals(_recursion.countAndSayRecursive(6),"312211");
  }

  @Test
  public void countAndSayTest(){
    assertEquals(_recursion.countAndSay(1),"1");
    assertEquals(_recursion.countAndSay(2),"11");
    assertEquals(_recursion.countAndSay(3),"21");
    assertEquals(_recursion.countAndSay(4),"1211");
    assertEquals(_recursion.countAndSay(5),"111221");
    assertEquals(_recursion.countAndSay(6),"312211");
  }

  @Test
  public void permuteUniqueTest(){
    List<List<Integer>> expected_res1 = new ArrayList<>();

    expected_res1.add(Arrays.asList(1,1,2));
    expected_res1.add(Arrays.asList(1,2,1));
    expected_res1.add(Arrays.asList(2,1,1));
    assertEquals(_recursion.permuteUnique(new int[]{1,1,2}),expected_res1);

    List<List<Integer>> expected_res2 = new ArrayList<>();
    expected_res2.add(Arrays.asList(new Integer[]{1,1,1}));
    assertEquals(_recursion.permuteUnique(new int[]{1,1,1}),expected_res2);

    List<List<Integer>> expected_res3 = new ArrayList<>();
    assertEquals(_recursion.permuteUnique(new int[]{}),expected_res3);

    List<List<Integer>> expected_res4 = new ArrayList<>();
    expected_res4.add(Arrays.asList(1, 2, 3));
    expected_res4.add(Arrays.asList(1, 3, 2));
    expected_res4.add(Arrays.asList(2, 1, 3));
    expected_res4.add(Arrays.asList(2, 3, 1));
    expected_res4.add(Arrays.asList(3, 1, 2));
    expected_res4.add(Arrays.asList(3, 2, 1));
    assertEquals(_recursion.permuteUnique(new int[]{1,2,3}),expected_res4);
  }

  @Test
  public void solveNQueensTest(){
    List<List<String>> expected_res1 = new ArrayList<>();
    expected_res1.add(Arrays.asList("Q"));
    assertEquals(_recursion.solveNQueens(1),expected_res1);

    List<List> expected_res2 = new ArrayList<>();
    expected_res2.add(Arrays.asList(".Q..","...Q","Q...","..Q."));
    expected_res2.add(Arrays.asList("..Q.","Q...","...Q",".Q.."));
    assertEquals(_recursion.solveNQueens(4),expected_res2);

    List<List> expected_res3 = new ArrayList<>();
    expected_res3.add(Arrays.asList(".Q....","...Q..",".....Q","Q.....","..Q...","....Q."));
    expected_res3.add(Arrays.asList("..Q...",".....Q",".Q....","....Q.","Q.....","...Q.."));
    expected_res3.add(Arrays.asList("...Q..","Q.....","....Q.",".Q....",".....Q","..Q..."));
    expected_res3.add(Arrays.asList("....Q.","..Q...","Q.....",".....Q","...Q..",".Q...."));
    assertEquals(_recursion.solveNQueens(6),expected_res3);
  }

  @Test
  public void subsetsTest(){
    List<List<Integer>> expected1 = new ArrayList<>();
    Integer[][] expected_resArray1 = new Integer[][] {{},{1},{2},{3},{1,2},{1,3},{2,3},{1,2,3}};
    for(int i=0;i<expected_resArray1.length;++i){
      expected1.add(Arrays.asList(expected_resArray1[i]));
    }
    List<List<Integer>> actual1 = _recursion.subsets(new int[]{1,2,3});
    assertEquals(actual1.size(),expected1.size());
    assertTrue(actual1.containsAll(expected1));
    assertTrue(expected1.containsAll(actual1));
  }

  @Test
  public void existTest(){
    char[][] board1 = new char[][]{
        {'A','B','C','E'},
        {'S','F','C','S'},
        {'A','D','E','E'}};
    assertTrue(_recursion.exist(board1,"ABCCED"));
    assertTrue(_recursion.exist(board1,"SEE"));
    assertTrue(_recursion.exist(board1,""));
    assertFalse(_recursion.exist(board1,"ABCB"));

    char[][] board2 = new char[][]{{'a','a'}};
    assertFalse(_recursion.exist(board2,"aaa"));
  }

  @Test
  public void subsetsWithDupTest(){
    List<List<Integer>> expected1 = new ArrayList<>();
    Integer[][] expected_resArray1 = new Integer[][] {{},{1},{2},{1,2},{2,2},{1,2,2}};
    for(int i=0;i<expected_resArray1.length;++i){
      expected1.add(Arrays.asList(expected_resArray1[i]));
    }
    List<List<Integer>> actual1 = _recursion.subsetsWithDup(new int[]{1,2,2});
    assertEquals(actual1.size(),expected1.size());
    assertTrue(actual1.containsAll(expected1));
    assertTrue(expected1.containsAll(actual1));


    List<List<Integer>> expected2 = new ArrayList<>();
    Integer[][] expected_resArray2 = new Integer[][] {{},{3},{3,3},{3,3,3}};
    for(int i=0;i<expected_resArray2.length;++i){
      expected2.add(Arrays.asList(expected_resArray2[i]));
    }
    List<List<Integer>> actual2 = _recursion.subsetsWithDup(new int[]{3,3,3});
    assertEquals(actual2.size(),expected2.size());
    assertTrue(actual2.containsAll(expected2));
    assertTrue(expected2.containsAll(actual2));
  }
}
