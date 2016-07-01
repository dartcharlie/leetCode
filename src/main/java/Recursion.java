import java.util.ArrayList;
import java.util.List;

public class Recursion {
  /**
   * given an input array, return all permutations of such array, no duplicate element inside input array
   * for example, input array = [1,2,3], output should be [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
   * @param inputList input list to be permutated
   * @return list of permutations
   */
  public List<List<Integer>> permutations(List<Integer> inputList){
    int inputSize = inputList.size();
    List<List<Integer>> result = new ArrayList<>();
    if(inputSize == 1){
      List<Integer> oneElementList = new ArrayList<>();
      oneElementList.add(inputList.get(0));
      result.add(oneElementList);
    }else {
      List<List<Integer>> permutatedListwithFirstElement = new ArrayList<>();
      for (int i = 0; i < inputSize; ++i) {
        List<Integer> swapedList = permutationHelperSwap(inputList, i);
        List<Integer> swapedSubList = swapedList.subList(1, swapedList.size());
        permutatedListwithFirstElement = permutationHelperAddElementInFront(permutations(swapedSubList), swapedList.get(0));
        for(int j=0;j<permutatedListwithFirstElement.size();++j){
          result.add(permutatedListwithFirstElement.get(j));
        }
        inputList = permutationHelperSwap(swapedList, i);
      }
    }
    return result;
  }

  /**
   * swap first element with element in pos
   * @param inputList input list
   * @param pos postion of element to be swaped with first element
   * @return the swaped array
   */
  private List<Integer> permutationHelperSwap(List<Integer> inputList, int pos){
    int firstElement = inputList.get(0);
    inputList.set(0,inputList.get(pos));
    inputList.set(pos,firstElement);
    return inputList;
  }

  /**
   * add an element to the front of the recursive result
   * @param recursiveResult
   * @param firstElement
   * @return added result
   */
  private List<List<Integer>> permutationHelperAddElementInFront(List<List<Integer>> recursiveResult, int firstElement){
    int recursiveSize = recursiveResult.size();
    List<List<Integer>> addFirstElementResult = new ArrayList<>();
    for(int i=0;i<recursiveSize;++i){
      List<Integer> addFirstElement = new ArrayList<>();
      addFirstElement.add(firstElement);
      addFirstElement.addAll(recursiveResult.get(i));
      addFirstElementResult.add(addFirstElement);
    }
    return addFirstElementResult;
  }
}
