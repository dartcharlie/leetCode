import java.util.ArrayList;
import java.util.List;

public class Recursion {
  /**
   * given an input array, return all permutations of such array, no duplicate element inside input array
   * for example, input array = [1,2,3], output should be [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
   *
   * @param inputList input list to be permutated
   * @return list of permutations
   */
  public List<List<Integer>> permutations(List<Integer> inputList) {
    int inputSize = inputList.size();
    List<List<Integer>> result = new ArrayList<>();
    if (inputSize == 1) {
      List<Integer> oneElementList = new ArrayList<>();
      oneElementList.add(inputList.get(0));
      result.add(oneElementList);
    } else {
      List<List<Integer>> permutatedListwithFirstElement = new ArrayList<>();
      for (int i = 0; i < inputSize; ++i) {
        List<Integer> swapedList = permutationHelperSwap(inputList, i);
        List<Integer> swapedSubList = swapedList.subList(1, swapedList.size());
        permutatedListwithFirstElement = permutationHelperAddElementInFront(permutations(swapedSubList), swapedList.get(0));
        for (int j = 0; j < permutatedListwithFirstElement.size(); ++j) {
          result.add(permutatedListwithFirstElement.get(j));
        }
        inputList = permutationHelperSwap(swapedList, i);
      }
    }
    return result;
  }

  /**
   * swap first element with element in pos
   *
   * @param inputList input list
   * @param pos       postion of element to be swaped with first element
   * @return the swaped array
   */
  private List<Integer> permutationHelperSwap(List<Integer> inputList, int pos) {
    int firstElement = inputList.get(0);
    inputList.set(0, inputList.get(pos));
    inputList.set(pos, firstElement);
    return inputList;
  }

  /**
   * add an element to the front of the recursive result
   *
   * @param recursiveResult
   * @param firstElement
   * @return added result
   */
  private List<List<Integer>> permutationHelperAddElementInFront(List<List<Integer>> recursiveResult, int firstElement) {
    int recursiveSize = recursiveResult.size();
    List<List<Integer>> addFirstElementResult = new ArrayList<>();
    for (int i = 0; i < recursiveSize; ++i) {
      List<Integer> addFirstElement = new ArrayList<>();
      addFirstElement.add(firstElement);
      addFirstElement.addAll(recursiveResult.get(i));
      addFirstElementResult.add(addFirstElement);
    }
    return addFirstElementResult;
  }

  /**
   * DFS: Given an matrix, find the length of longest increasing path, only up, left, down, right direction allowed
   * 6, 8, 15
   * 9, 7, 10
   * 3, 5, 2
   * the example above has the longest path 3->5->7>10>15, and the length is 5.
   *
   * @param matrix input matrix
   * @return the length of longest increasing path.
   */
  public int matrixLongestIncreasingPathLength(int[][] matrix) {
    int result = 0;
    int mXLen = matrix.length;
    int currLen;
    if (mXLen != 0) {
      int mYLen = matrix[0].length;
      int[][] maxLengthMap = new int[mXLen][mYLen];
      for (int i = 0; i < mXLen; ++i) {
        for (int j = 0; j < mYLen; ++j) {
          currLen = helperMatrixLIP(matrix, maxLengthMap, i, j, mXLen, mYLen);
          if (currLen > result) {
            result = currLen;
          }
        }
      }
    }
    return result;
  }

  private int helperMatrixLIP(int[][] matrix, int[][] maxLengthMap, int posX, int posY, int mXLen, int mYLen) {
    int up = 0;
    int down = 0;
    int left = 0;
    int right = 0;
    if (posX > 0 && matrix[posX][posY] < matrix[posX - 1][posY]) {
      //go up
      if (maxLengthMap[posX - 1][posY] > 0) {
        up = maxLengthMap[posX - 1][posY];
      } else {
        up = helperMatrixLIP(matrix, maxLengthMap, posX - 1, posY, mXLen, mYLen);
      }
    }
    if (posY > 0 && matrix[posX][posY] < matrix[posX][posY - 1]) {
      //go left
      if (maxLengthMap[posX][posY - 1] > 0) {
        left = maxLengthMap[posX][posY - 1];
      } else {
        left = helperMatrixLIP(matrix, maxLengthMap, posX, posY - 1, mXLen, mYLen);
      }
    }
    if (posX < mXLen - 1 && matrix[posX][posY] < matrix[posX + 1][posY]) {
      if (maxLengthMap[posX + 1][posY] > 0) {
        down = maxLengthMap[posX + 1][posY];
      } else {
        down = helperMatrixLIP(matrix, maxLengthMap, posX + 1, posY, mXLen, mYLen);
      }
    }
    if (posY < mYLen - 1 && matrix[posX][posY] < matrix[posX][posY + 1]) {
      //go right
      if (maxLengthMap[posX][posY + 1] > 0) {
        right = maxLengthMap[posX][posY + 1];
      } else {
        right = helperMatrixLIP(matrix, maxLengthMap, posX, posY + 1, mXLen, mYLen);
      }
    }
    int currMax = Math.max(Math.max(Math.max(up, left), down), right) + 1;
    maxLengthMap[posX][posY] = currMax;
    return currMax;
  }
}
