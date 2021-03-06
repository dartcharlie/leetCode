import java.util.*;
import java.util.stream.Collectors;

public class Recursion {
  /**
   * given an input array, return all permutations of such array, no duplicate element inside input array
   * for example, input array = [1,2,3], output should be [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
   *
   * @param inputList input list to be permutated
   * @return list of permutations
   */
  Utils _utils = new Utils();
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

  /**
   * given a list of digit, add '+' or '-' between digit so that resulted equation equal to target number
   * i.e. given "12345" and target 15, one element of result list would be "1+2+3+4+5"
   * @param digits
   * @param target
   * @return all combinations of digests and signs that equal to the target
   */
  public List<String> addToANumber(String digits, int target){
    List<String> results = helper_addToANumber("", digits, 0, target);
    return results;
  }

  private List<String> helper_addToANumber(String prefix, String remainingDigits, int prefixResult, int target){
    List<String> result = new ArrayList<String>();
    int remainingLen = remainingDigits.length();
    if(remainingLen == 0 ){
      if(target == prefixResult) {
        result.add(prefix);
      }
    }else {
      //remaining digits length at least 1.
      String newPrefix = "";
      int newPrefixResult = 0;
      String nextDigit = remainingDigits.substring(0,1);
      for (int j = 0; j < 3; ++j) {
        switch(j) {
          case 0:
            newPrefix = prefix + remainingDigits.substring(0, 1);
            newPrefixResult = helper_addToANumber_eval(newPrefix);
            break;
          case 1:
            if (!prefix.isEmpty()) {
              newPrefix = prefix + '+' + nextDigit;
              newPrefixResult = prefixResult + Integer.parseInt(nextDigit);
              break;
            } else {
              continue;
            }
          case 2:
            newPrefix = prefix + '-' + remainingDigits.substring(0, 1);
            newPrefixResult = prefixResult - Integer.parseInt(nextDigit);
            break;
        }
        List<String> recursiveResults;
        if (remainingLen > 1) {
          String remainDigits = remainingDigits.substring(1);
          recursiveResults = helper_addToANumber(newPrefix, remainDigits, newPrefixResult, target);
        } else {
          recursiveResults = helper_addToANumber(newPrefix, "", newPrefixResult, target);
        }
        for (String recursiveResult : recursiveResults) {
          result.add(recursiveResult);
        }
      }
    }
    return result;
  }
  //helper function to calculate intermediate function result
  private int helper_addToANumber_eval(String function){
    int sum = 0;
    int currNum = 0;
    boolean add = true;
    int functionLen = function.length();
    for(int i=0;i<functionLen;++i){
      if(function.charAt(i) == '+' || function.charAt(i) == '-'){
        if(add){
          sum += currNum;
        }else{
          sum -= currNum;
        }
        currNum = 0;
        add = function.charAt(i) == '+'? true:false;
      }else{
        currNum = currNum*10 + (function.charAt(i) - '0');
      }
    }
    if(add){
      sum += currNum;
    }else{
      sum -= currNum;
    }
    return sum;
  }

  public List<String> generateParenthesis(int n){
    List<String> result = new ArrayList<String>();
    helper_generateParenthesis(n,n,"",result);
    return result;
  }

  private void helper_generateParenthesis(int left, int right, String prefix, List<String> result){
    if(left == 0 && right == 0){
      result.add(prefix);
    }else {
      String nextPrefix;
      if (left > right) {
        return;
      } else if (left == right) {
        nextPrefix = prefix + '(';
        helper_generateParenthesis(left-1,right,nextPrefix,result);
      }else{
        if(left == 0){
          nextPrefix = prefix + ')';
          helper_generateParenthesis(left,right-1,nextPrefix,result);
        }else{
          nextPrefix = prefix + '(';
          helper_generateParenthesis(left-1,right,nextPrefix,result);
          nextPrefix = prefix + ')';
          helper_generateParenthesis(left,right-1,nextPrefix,result);
        }
      }
    }
  }

  /**
   * a game to draw 4 cards of poker, A=1,J=11,Q=12,K=13, use those 4 numbers and +,-,x,/ and () to get the target to 24.
   * @param inputs, list of numbers should range from 1 to 13
   * @param target, the target number, by definition, it should be 24
   * @return all combinations that resulted to target
   * TODO: missing some results now
   */

  public List<String> arithmeticToTarget(int[] inputs, int target){
    List<String> answers = new ArrayList<String>();
    List<Integer> inputNums = new LinkedList<Integer>();
    for(int i:inputs){
      inputNums.add(i);
    }
    List<List<Integer>> perms = permutations(inputNums);
    Set<List<Integer>> uniqPerms = new HashSet<List<Integer>>();
    for(List<Integer> perm: perms){
      uniqPerms.add(perm);
    }
    for(List<Integer> uniqPerm:uniqPerms) {
      helper_arithmeticToTarget(uniqPerm, "",0, target, answers);
    }
    return answers;
  }

  private void helper_arithmeticToTarget(List<Integer> remainingNums, String prefix, int prefixResult, int target, List<String> answer){
    int remainingNumsLen = remainingNums.size();
    if(remainingNumsLen == 0){
      if(prefixResult == target){
        answer.add(prefix);
      }
    }else {
      List<Integer> remainingNumsCopy = new LinkedList<Integer>(remainingNums);
      Integer currInteger = remainingNumsCopy.remove(0);

      if (prefix.isEmpty()) {
        prefix = currInteger.toString();
        helper_arithmeticToTarget(remainingNumsCopy, prefix, currInteger, target, answer);
      } else {
        //try add
        int newPrefixResult = 0;
        newPrefixResult = prefixResult + currInteger;
        helper_arithmeticToTarget(remainingNumsCopy, prefix + '+' + currInteger,newPrefixResult, target, answer);
        //try minus
        newPrefixResult = prefixResult - currInteger;
        helper_arithmeticToTarget(remainingNumsCopy, prefix + '-' + currInteger,newPrefixResult, target, answer);
        //try product
        newPrefixResult = prefixResult * currInteger;
        String newPrefix = '(' + prefix + ')' + '*' + currInteger;
        helper_arithmeticToTarget(remainingNumsCopy, newPrefix,newPrefixResult, target, answer);
        //try divide
        if(prefixResult % currInteger == 0){
          newPrefixResult = prefixResult / currInteger;
          newPrefix = '(' + prefix + ')' + '/' + currInteger;
          helper_arithmeticToTarget(remainingNumsCopy, newPrefix, newPrefixResult, target, answer);
        }
      }
    }
  }

  /**
   * leetcode 212, back tracking, trie
   * @param board
   * @param words
   * @return
   */
  public List<String> findWordsII(char[][] board, String[] words){
    List<String> wordList = Arrays.asList(words);
    Utils.TrieNode root = _utils.buildTrie(wordList);
    Set<String> resultSet = new HashSet<>();

    int lenX = board.length;
    if(lenX != 0) {
      int lenY = board[0].length;
      boolean[][] visitedMap = new boolean[lenX][lenY];
      for(int i=0;i<lenX;++i){
        for(int j=0;j<lenY;++j){
          if(root.hasChild(board[i][j])) {
            visitedMap[i][j] = true;
            helper_findWordsII(""+ board[i][j], board, visitedMap, i, j, lenX, lenY, root.getChildren().get(board[i][j]), resultSet);
            visitedMap[i][j] = false;
          }
        }
      }
    }
    List<String> answer = new ArrayList<>();
    for(String result :resultSet){
      answer.add(result);
    }
    return answer;
  }

  private void helper_findWordsII(String prefix, char[][] board, boolean[][] visited, int currX, int currY, int lenX, int lenY, Utils.TrieNode currNode, Set<String> result) {
    if (prefix.equals(currNode.getPrefix()) && currNode.getInDictionary()) {
      result.add(prefix);
    }
    char childChar;
    //going up
    if (currY > 0 && !visited[currX][currY - 1]) {
      childChar = board[currX][currY - 1];
      if (currNode.hasChild(childChar)) {
        String updatedPrefix = prefix + board[currX][currY - 1];
        Utils.TrieNode updatedNode = currNode.getChildren().get(childChar);
        visited[currX][currY - 1] = true;
        helper_findWordsII(updatedPrefix, board, visited, currX, currY - 1, lenX, lenY, updatedNode, result);
        visited[currX][currY - 1] = false;
      }
    }
    //going left
    if (currX > 0 && !visited[currX - 1][currY]) {
      childChar = board[currX - 1][currY];
      if (currNode.hasChild(childChar)) {
        String updatedPrefix = prefix + board[currX - 1][currY];
        Utils.TrieNode updatedNode = currNode.getChildren().get(childChar);
        visited[currX - 1][currY] = true;
        helper_findWordsII(updatedPrefix, board, visited, currX - 1, currY, lenX, lenY, updatedNode, result);
        visited[currX - 1][currY] = false;
      }
    }
    //going down
    if (currY < lenY-1 && !visited[currX][currY + 1]) {
      childChar = board[currX][currY + 1];
      if (currNode.hasChild(childChar)) {
        String updatedPrefix = prefix + board[currX][currY + 1];
        Utils.TrieNode updatedNode = currNode.getChildren().get(childChar);
        visited[currX][currY + 1] = true;
        helper_findWordsII(updatedPrefix, board, visited, currX, currY + 1, lenX, lenY, updatedNode, result);
        visited[currX][currY + 1] = false;
      }
    }
    //going right
    if (currX < lenX-1 && !visited[currX + 1][currY]) {
      childChar = board[currX + 1][currY];
      if (currNode.hasChild(childChar)) {
        String updatedPrefix = prefix + board[currX + 1][currY];
        Utils.TrieNode updatedNode = currNode.getChildren().get(childChar);
        visited[currX + 1][currY] = true;
        helper_findWordsII(updatedPrefix, board, visited, currX + 1, currY, lenX, lenY, updatedNode, result);
        visited[currX + 1][currY] = false;
      }
    }
  }


  /**
   * leetcode 200 Number of Islands
   * breadth-first-search, depth-first-search
   */
  public int numIslands(char[][] grid) {
    int result = 0;
    if(grid != null && grid.length != 0 && grid[0].length != 0) {
      int gridx = grid.length;
      int gridy = grid[0].length;

      for (int i = 0; i < gridx; ++i) {
        for (int j = 0; j < gridy; ++j) {
          if(grid[i][j] == '1'){
            result++;
            visitIsland(i,j,grid,gridx,gridy);
          }
        }
      }
    }
    return result;
  }


  public void visitIsland(int currX, int currY,char[][] grid, int gridx, int gridy){
    if(currX <0 || currX >= gridx || currY<0 || currY >=gridy){
      return;
    }
    if(grid[currX][currY] == '1') {
      grid[currX][currY] = 'X';
      visitIsland(currX - 1, currY,  grid, gridx, gridy);
      visitIsland(currX + 1, currY,  grid, gridx, gridy);
      visitIsland(currX, currY - 1,  grid, gridx, gridy);
      visitIsland(currX, currY + 1,  grid, gridx, gridy);
    }
  }

  /**
   * leetcode 130 surrounded regions
   */
  public char[][] surroundedRegions(char[][] board) {
    if(board != null && board.length != 0 && board[0].length !=0){
      int sizeX = board.length;
      int sizeY = board[0].length;

      for(int i = 0;i<sizeX;++i){
        markBoard(i,0,board,sizeX,sizeY);
        markBoard(i,sizeY-1,board,sizeX,sizeY);
      }
      for(int i=0;i<sizeY;++i){
        markBoard(0,i,board,sizeX,sizeY);
        markBoard(sizeX-1,i,board,sizeX,sizeY);
      }
      for(int i=0;i<sizeX;++i){
        for(int j=0;j<sizeY;++j){
          if(board[i][j] == 'O'){
            board[i][j] = 'X';
          }
          if(board[i][j] == '1'){
            board[i][j] = 'O';
          }
        }
      }
    }
    return board;
  }

  public void markBoard(int currX, int currY, char[][] board, int sizeX, int sizeY){
    if(board[currX][currY] != 'O'){
      return;
    }else{
      //inprovements over recursive solution
      Queue<Integer> visitQueue = new LinkedList<>();
      visitQueue.offer(currX*sizeY + currY);
      board[currX][currY] = '1';
      while(!visitQueue.isEmpty()){
        int currNode = visitQueue.poll();
        currX = currNode/sizeY;
        currY = currNode%sizeY;
        fillBoard(currX-1, currY,board,sizeX,sizeY,visitQueue);
        fillBoard(currX+1, currY,board,sizeX,sizeY,visitQueue);
        fillBoard(currX, currY-1,board,sizeX,sizeY,visitQueue);
        fillBoard(currX, currY+1,board,sizeX,sizeY,visitQueue);

      }
    }
  }

  public void fillBoard(int currX, int currY, char[][] board, int sizeX, int sizeY, Queue<Integer> visitQueue){
    if(currX <0 || currX >= sizeX || currY<0 || currY >=sizeY){
      return;
    }
    if(board[currX][currY] == 'O'){
      visitQueue.offer(currX*sizeY+currY);
      board[currX][currY] = '1';
    }
  }

  /**
   * leetcode 126, Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
   * Only one letter can be changed at a time
   * Each intermediate word must exist in the word list
   * Given:
   * beginWord = "hit"
   * endWord = "cog"
   * wordList = ["hot","dot","dog","lot","log"]
   * Return [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
   * @param beginWord
   * @param endWord
   * @param wordList
   * @return
   */
  public List<List<String>> findLaddersII(String beginWord, String endWord, Set<String> wordList) {
    //distance between key word and beginWord through bfs search
    Map<String, Integer> distance = new HashMap<>();
    Map<String, List<String>> neighbors = new HashMap<>();
    List<List<String>> result = new ArrayList<>();
    wordList.add(endWord);
    findLaddersBFS(beginWord,endWord,wordList,distance,neighbors);
    findLaddersDFS(beginWord,endWord,distance,neighbors,new ArrayList<>(),result);

    return result;
  }

  public void findLaddersBFS(String beginWord, String endWord, Set<String> dic, Map<String,Integer> distance, Map<String,List<String>> neighbors){
    Queue<String> queue = new LinkedList<>();
    queue.offer(beginWord);
    distance.put(beginWord,0);
    neighbors.put(beginWord,new ArrayList<>());
    while(!queue.isEmpty()){
      int queueSize = queue.size();
      boolean foundEnd = false;
      for(int i=0;i<queueSize;++i){
        String currWord = queue.poll();
        List<String> currNeighbors = neighborWord(dic,currWord);
        int currDistance = distance.get(currWord);
        for(String word:currNeighbors){
          neighbors.get(currWord).add(word);
          if(!distance.containsKey(word)){
            distance.put(word,currDistance+1);
            neighbors.put(word,new ArrayList<>());
            if(word.equals(endWord)){
              foundEnd = true;
            }else{
              queue.offer(word);
            }
          }
        }
      }
      if(foundEnd){
        break;
      }
    }
  }

  public void findLaddersDFS(String currWord, String endWord, Map<String,Integer> distance, Map<String,List<String>> neighbors, List<String> prefix, List<List<String>> result){
    prefix.add(currWord);
    if(currWord.equals(endWord)){
      List<String> ladder = new ArrayList<>(prefix);
      result.add(ladder);
    } else{
      for(String neighbor:neighbors.get(currWord)){
        if(distance.get(neighbor) == distance.get(currWord) +1 ) {
          findLaddersDFS(neighbor, endWord, distance, neighbors, prefix, result);
        }
      }
    }
    prefix.remove(prefix.size()-1);
  }

  public List<String> neighborWord(Set<String> wordList, String currWord){
    List<String> neighbors = new ArrayList<>();
    char[] currCharArray = currWord.toCharArray();
    int wordLen = currCharArray.length;
    //there are 2 approaches to get distance 1 word list
    //(1) try to swap character with current word, time complexity O(26*w)
    for(int i=0;i<wordLen;++i){
      for(char ch='a';ch<='z';++ch){
        if(ch != currCharArray[i]){
          char oldChar = currCharArray[i];
          currCharArray[i] = ch;
          String transformWord = String.valueOf(currCharArray);
          if(wordList.contains(transformWord)){
            neighbors.add(transformWord);
          }
          currCharArray[i] = oldChar;
        }
      }
    }
    /* (2) iterate through all words in the dictionary, time complexity O(n*w)
    for(String word:wordList){
      int distance = 0;
      for(int i=0;i<wordLen;++i){
        if(word.charAt(i) != currWord.charAt(i)){
          distance++;
        }
      }
      if(distance == 1){
        neighbors.add(word);
      }
    }
    */
    return neighbors;
  }

  /**
   * leetcode 38 count and say
   * The count-and-say sequence is the sequence of integers beginning as follows:
   * 1, 11, 21, 1211, 111221, ...
   * 1 is read off as "one 1" or 11.
   * 11 is read off as "two 1s" or 21.
   * 21 is read off as "one 2, then one 1" or 1211.
   * Given an integer n, generate the nth sequence.
   * Note: recursive method may cause calling stack overflow if n is large
   * @param n
   * @return
   */
  public String countAndSayRecursive(int n) {
    if(n == 1){
      return "1";
    }
    StringBuilder sb = new StringBuilder();
    String previous = countAndSayRecursive(n-1);
    char[] previousCharArray = previous.toCharArray();
    char currChar = previousCharArray[0];
    int currCount = 1;
    for(int i=1;i<previousCharArray.length;++i){
      if(previousCharArray[i] == currChar){
        currCount++;
      }else{
        sb.append(String.valueOf(currCount));
        sb.append(currChar);
        currChar = previousCharArray[i];
        currCount = 1;
      }
    }
    sb.append(String.valueOf(currCount));
    sb.append(currChar);
    return sb.toString();
  }


  public String countAndSay(int n) {
    int base = 1;
    String previous = "1";
    StringBuilder sb = new StringBuilder();
    while(base < n){
      char[] previousCharArray = previous.toCharArray();
      char currChar = previousCharArray[0];
      int currCount = 1;
      for(int i=1;i<previousCharArray.length;++i){
        if(previousCharArray[i] == currChar){
          currCount++;
        }else{
          sb.append(String.valueOf(currCount));
          sb.append(currChar);
          currChar = previousCharArray[i];
          currCount = 1;
        }
      }
      sb.append(String.valueOf(currCount));
      sb.append(currChar);
      previous = sb.toString();
      sb.setLength(0);
      base++;
    }
    return previous;
  }

  /**
   * leetcode permutation ii
   * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
   * @param nums
   * @return
   */
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(nums);
    LinkedList<Integer> currentPermutation = new LinkedList<>();
    for(int num: nums) currentPermutation.add(num);
    permuteUniqueRecursive(currentPermutation,0,ans);
    return ans;
  }

  public void permuteUniqueRecursive(LinkedList<Integer> nums,int pos,List<List<Integer>> res){
    if(pos == nums.size() -1){
      List<Integer> list = new ArrayList<>(nums);
      res.add(list);
      return;
    }
    for(int i=pos;i<=nums.size()-1;++i){
      if(i > pos && nums.get(i) == nums.get(i-1)) {
        continue;
      }
      nums.add(pos,nums.get(i));
      nums.remove(i+1);

      permuteUniqueRecursive(nums,pos+1,res);
      nums.add(i+1,nums.get(pos));
      nums.remove(pos);

    }
  }

  /**
   * Given an integer n, return all distinct solutions to the n-queens puzzle.
   * Each solution contains a distinct board configuration of the n-queens' placement,
   * where 'Q' and '.' both indicate a queen and an empty space respectively.
   * @param n
   * @return
   */
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> ans = new ArrayList<>();
    int[] prefix = new int[n];
    nQueensRecursive(n,0,prefix,ans);
    return ans;
  }

  private void nQueensRecursive(int n, int currRow, int[] prefix, List<List<String>> ans){
    if(currRow+1 == n){
      for(int col=0;col<n;++col){
        if(!queenMeet(currRow,col,prefix)){
          prefix[currRow] = col;
          //convert prefix array to ans
          List<String> solution = new ArrayList<>();
          for(int row=0;row<=currRow;++row){
            char[] line = new char[n];
            for(int i=0;i<n;++i){
              line[i] = '.';
            }
            line[prefix[row]] = 'Q';
            solution.add(String.valueOf(line));
          }
          ans.add(solution);
        }
      }
    }else{
      for(int col = 0;col<n;++col){
        if(!queenMeet(currRow,col,prefix)){
          prefix[currRow] = col;
          nQueensRecursive(n,currRow+1,prefix,ans);
        }
      }
    }

  }

  private boolean queenMeet(int row, int col, int[] prefix){
    for(int i=0;i<row;++i){
      int preRow = i;
      int preCol = prefix[i];
      if(row != preRow && col != preCol && row-col != preRow-preCol && row+col != preCol+preRow){
        continue;
      }
      return true;
    }
    return false;
  }

  /**
   * Given a set of distinct integers, nums, return all possible subsets.
   * Note: The solution set must not contain duplicate subsets.
   * @param nums
   * @return
   */
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    if(nums.length != 0){
      subsetsRecursvie(nums,0,new ArrayList<>(),ans);
    }
    return ans;
  }

  private void subsetsRecursvie(int[] nums, int index, List<Integer> prefix, List<List<Integer>> res){
    if(index == nums.length-1){
      //not chose current num
      List<Integer> subsetExclusive = new ArrayList<>(prefix);
      //chose current num
      List<Integer> subsetInclusive = new ArrayList<>(prefix);
      subsetInclusive.add(nums[index]);
      res.add(subsetExclusive);
      res.add(subsetInclusive);
      return;
    }

    subsetsRecursvie(nums,index+1,prefix,res);
    prefix.add(nums[index]);
    subsetsRecursvie(nums,index+1,prefix,res);
    prefix.remove(prefix.size()-1);
  }

  /**
   * Given a 2D board and a word, find if the word exists in the grid.
   * The word can be constructed from letters of sequentially adjacent cell,
   * where "adjacent" cells are those horizontally or vertically neighboring.
   * The same letter cell may not be used more than once.
   * board =
   * [['A','B','C','E'],
   *  ['S','F','C','S'],
   *  ['A','D','E','E']]
   *  word = "ABCCED", -> returns true,
   *  word = "SEE", -> returns true,
   *  word = "ABCB", -> returns false.
   * @param board
   * @param word
   * @return
   */
  public boolean exist(char[][] board, String word) {
    int m = board.length;
    if(m == 0){
      return false;
    }
    if(word.length() == 0){
      return true;
    }
    int n = board[0].length;
    boolean[][] visit = new boolean[m][n];
    for(int i=0;i<m;++i){
      for(int j=0;j<n;++j){
        if(searchRecursive(board,visit,0,word,i,j)){
          return true;
        }
      }
    }
    return false;
  }

  private boolean searchRecursive(char[][] board,boolean[][] visit, int index, String word, int x, int y){
    if(board[x][y] == word.charAt(index)){
      visit[x][y] = true;
      if(index == word.length()-1){
        return true;
      }
      int[] dirx = new int[]{-1,0,1,0};
      int[] diry = new int[]{0,-1,0,1};
      for(int i=0;i<=3;i++){
        if(x+dirx[i]>=0 && x+dirx[i]<board.length && y+diry[i]>=0 && y+diry[i]<board[0].length && !visit[x+dirx[i]][y+diry[i]]){
          if(searchRecursive(board,visit,index+1,word,x+dirx[i],y+diry[i])){
            return true;
          }
        }
      }
      visit[x][y] = false;
    }
    return false;
  }

  /**
   * leetcode 90 Given a collection of integers that might contain duplicates, nums, return all possible subsets.
   * @param nums
   * @return
   */
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> prefix = new ArrayList<>();
    Arrays.sort(nums);
    subsetsWithDupRecurisve(nums, 0, prefix, res);
    return res;
  }

  private void subsetsWithDupRecurisve(int[] nums, int index, List<Integer> prefix, List<List<Integer>> res){
    if(index <= nums.length){
      List<Integer> prefixCopy = new ArrayList<>(prefix);
      res.add(prefixCopy);
    }

    for(int i=index;i<=nums.length-1;++i){
      if(i>index && nums[i] == nums[i-1]) continue;
      prefix.add(nums[i]);
      subsetsWithDupRecurisve(nums,i+1,prefix,res);
      prefix.remove(prefix.size()-1);
    }
  }

  public List<Integer> splitIntoFibonacci(String S) {
    List<Integer> result = new ArrayList<>();
    splitHelper(S, result, 0);
    return result;
  }

  private boolean splitHelper(String S, List<Integer> splits, int index) {
    int splitSize = splits.size();
    if(index == S.length() && splitSize >= 3) {
      return true;
    }
    for(int i=index; i<S.length(); ++i) {
      if(S.charAt(index) == '0' && i > index) {
        break;
      }
      long currNum = Long.parseLong(S.substring(index,i+1));
      if(currNum > Integer.MAX_VALUE) {
        break;
      }
      if(splitSize >= 2 && currNum > (long)splits.get(splitSize-2) + (long)splits.get(splitSize-1)) {
        break;
      }
      if(splitSize <= 1 || currNum == splits.get(splitSize-2) + splits.get(splitSize-1)) {
        splits.add((int) currNum);
        if(splitHelper(S, splits, i+1)) {
          return true;
        }
        splits.remove(splits.size() -1);
      }
    }
    return false;
  }

  public boolean isAdditiveNumber(String num) {
    return additiveNumberHelper(num, new ArrayList<Long>(), 0);
  }

  private boolean additiveNumberHelper(String num, List<Long> numList, int index) {
    if(index == num.length() && numList.size() >=3) {
      return true;
    }
    for(int i=index; i<num.length();++i) {
      if(num.charAt(index) == '0' && i>index) {
        break;
      }
      if(i - index > 10) {
        break;
      }
      long currNum = Long.parseLong(num.substring(index,i+1));

      if(numList.size() >=2 && currNum > numList.get(numList.size() - 2)+ numList.get(numList.size() - 1)) {
        break;
      }
      if(numList.size() <=1 && 2*i > num.length() + index) {
        break;
      }
      if(numList.size() <= 1 || currNum == numList.get(numList.size() - 2) + numList.get(numList.size() - 1)) {
        numList.add(currNum);
        if(additiveNumberHelper(num, numList, i+1)) {
          return true;
        }
        numList.remove(numList.size() - 1);
      }
    }
    return false;
  }

  private String convertCharListToString(List<Character> charList) {
    return charList.stream().map(c -> c.toString()).collect(Collectors.joining());
  }

  public List<String> letterCasePermutation(String S) {
    List<String> result = new ArrayList<>();
    permutationHelper(S.toLowerCase(), 0, result, new String());
    return result;
  }

  private void permutationHelper(String S, int index, List<String> permutations, String temp) {
    if(index == S.length()) {
      permutations.add(temp);
      return;
    }
    char currChar = S.charAt(index);
    if(Character.isDigit(currChar)) {
      permutationHelper(S, index+1, permutations, temp + currChar);
    } else {
      permutationHelper(S, index+1, permutations, temp + currChar);
      permutationHelper(S, index+1, permutations, temp + Character.toUpperCase(currChar));
    }
  }

  /**
   * convert String sticker[i] to an integer array int[i][26]
   * we only care about characters that in target, and store each character count in the array.
   */
  protected int[][] preProcessStickers (String[] stickers, String target) {
    int[] targetCharCount = new int[26];
    for(int i=0;i<26;++i) {
      for(int j=0;j<target.length();++j) {
        targetCharCount[target.charAt(j)-'a']++;
      }
    }
    int[][] processed = new int[stickers.length][26];
    for(int i=0;i<stickers.length;++i) {
      for(int j=0;j<stickers[i].length();++j) {
        if(targetCharCount[stickers[i].charAt(j) - 'a'] > 0) {
          processed[i][stickers[i].charAt(j) - 'a']++;
        }
      }
    }
    return processed;
  }


  public int minStickers(String[] stickers, String target) {
    int[][] processed = new int[stickers.length][26];
    for(int i=0;i<stickers.length;++i) {
      for(int j=0;j<stickers[i].length(); ++j){
        processed[i][stickers[i].charAt(j)-'a']++;
      }
    }
    Map<String, Integer> dp = new HashMap<>();
    dp.put("", 0);
    stickerHelper(processed, target, dp);
    return dp.get(target);
  }

  private int stickerHelper(int[][] stickers, String target, Map<String, Integer> dp) {
    if(dp.containsKey(target)) return dp.get(target);
    int[] tarCount = new int[26];

    for(int i=0;i<target.length();++i) {
      tarCount[target.charAt(i)-'a']++;
    }

    int minStickerCount = Integer.MAX_VALUE;
    for(int i=0;i<stickers.length;++i) {
      if(stickers[i][target.charAt(0) -'a'] == 0) continue;
      StringBuilder sb = new StringBuilder();
      for(int j=0;j<26;++j) {
        if(tarCount[j] >0) {
          for(int k=0;k<Math.max(0, tarCount[j]-stickers[i][j]);++k) {
            sb.append((char)(j + 'a'));
          }
        }
      }
      int temp = stickerHelper(stickers,sb.toString(),dp);
      if (temp != -1) minStickerCount = Math.min(minStickerCount, temp+1);
    }
    dp.put(target, minStickerCount == Integer.MAX_VALUE? -1:minStickerCount);
    return dp.get(target);
  }

  public int countArrangement(int N) {
    List<List<Integer>> result = new ArrayList<>();
    arrangementHelper(N, new HashSet<>(), new ArrayList<>(), result);
    return result.size();
  }

  private void arrangementHelper(int N, Set<Integer> visited, List<Integer> arrangement, List<List<Integer>> arrangements) {
    if(visited.size() == N) {
      List<Integer> copy = new ArrayList<>(arrangement);
      arrangements.add(copy);
    }
    for(int i=1;i<=N;++i) {
      if(!visited.contains(i)) {
        int currPos = arrangement.size()+1;
        if(i%currPos == 0 || currPos%i == 0) {
          visited.add(i);
          arrangement.add(i);
          arrangementHelper(N,visited,arrangement,arrangements);
          visited.remove(i);
          arrangement.remove(arrangement.size()-1);
        }
      }
    }
  }
}
