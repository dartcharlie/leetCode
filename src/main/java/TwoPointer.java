import java.util.HashMap;
import java.util.Map;

public class TwoPointer {
  /**
   * given one long string and one short string, find the shortest substring of the longer string that
   * contains all characters of the short string.
   * i.e. long string: abcdbtnmca, shorter string: banc, return 6 (btnmca)
   *      long string: aaaaaaabbbbbbb, shorter string: ab, return 2 (ab)
   * @param haystack long string
   * @param needle short string
   * @return
   */
  public int shortestSubString(String haystack, String needle) {
    Map<Character, Integer> dic = new HashMap<>();
    Map<Character, Integer> foundSoFar = new HashMap<>();
    if (needle.length() == 0) {
      return 0;
    }
    if (haystack.length() == 0) {
      return -1;
    }
    char[] lookup = needle.toCharArray();
    for (char c : lookup) {
      if (dic.containsKey(c)) {
        dic.put(c, dic.get(c) + 1);
      } else {
        dic.put(c, 1);
        foundSoFar.put(c, 0);
      }
    }

    int shortest = Integer.MAX_VALUE;
    int start = 0, end = 0;
    char[] hayChars = haystack.toCharArray();
    if (foundSoFar.containsKey(hayChars[start])){
      foundSoFar.put(hayChars[start], 1);
    }
    while(start<=end){
      if(foundAll(dic,foundSoFar)){
        shortest = Math.min(shortest,end-start+1);
        if(foundSoFar.containsKey(hayChars[start])) {
          foundSoFar.put(hayChars[start], foundSoFar.get(hayChars[start]) - 1);
        }
        start++;

      } else if(end < hayChars.length-1){
        end++;
        if(foundSoFar.containsKey(hayChars[end])){
          foundSoFar.put(hayChars[end],foundSoFar.get(hayChars[end])+1);
        }
      } else {
        break;
      }
    }
    return shortest == Integer.MAX_VALUE? -1 : shortest;
  }

  private boolean foundAll(Map<Character,Integer> dic, Map<Character,Integer> foundSoFar){
    for(Character c:foundSoFar.keySet()){
      if(foundSoFar.get(c) < dic.get(c)){
        return false;
      }
    }
    return true;
  }

  /**
   * leetcode 76 Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
   * @param s string to search in
   * @param t
   * @return
   */
  public String minWindow(String s, String t){
    Map<Character,Integer> dic = new HashMap<>();
    Map<Character,Integer> foundSoFar = new HashMap<>();
    if(s.length() == 0 || t.length() == 0){
      return "";
    }

    char[] lookup = t.toCharArray();
    for(char c:lookup){
      if(dic.containsKey(c)){
        dic.put(c,dic.get(c)+1);
      }else{
        dic.put(c,1);
        foundSoFar.put(c,0);
      }
    }

    int shortest = Integer.MAX_VALUE;
    String ans = "";
    int start = 0, end = 0;
    char[] hayChars = s.toCharArray();
    if (foundSoFar.containsKey(hayChars[start])){
      foundSoFar.put(hayChars[start], 1);
    }
    while(start<=end){
      if(foundAll(dic,foundSoFar)){
        if(end-start+1 < shortest){
          shortest = end-start+1;
          ans = s.substring(start,end+1);
        }
        if(foundSoFar.containsKey(hayChars[start])) {
          foundSoFar.put(hayChars[start], foundSoFar.get(hayChars[start]) - 1);
        }
        start++;

      } else if(end < hayChars.length-1){
        end++;
        if(foundSoFar.containsKey(hayChars[end])){
          foundSoFar.put(hayChars[end],foundSoFar.get(hayChars[end])+1);
        }
      } else {
        break;
      }
    }
    return ans;
  }

  /**
   * leetcode 167 Given an array of integers that is already sorted in ascending order,
   * find two numbers such that they add up to a specific target number.
   * Input: numbers={2, 7, 11, 15}, target=9
   * Output: index1=1, index2=2
   * You may assume that each input would have exactly one solution.
   * @param numbers
   * @param target
   * @return
   */
  public int[] twoSum(int[] numbers, int target) {
    int[] ans = new int[2];
    int head=0,tail=numbers.length-1;
    while(head<tail){
      if(numbers[head] + numbers[tail] == target){
        ans[0] = head+1;
        ans[1] = tail+1;
        break;
      } else if(numbers[head] + numbers[tail] < target) {
        head++;
      } else{
        tail--;
      }
    }
    return ans;
  }

  /**
   * Given a list, rotate the list to the right by k places, where k is non-negative.
   * For example:
   * Given 1->2->3->4->5->NULL and k = 2,
   * return 4->5->1->2->3->NULL.
   * @param head
   * @param k
   * @return
   */
  public DataStructure.ListNode rotateRight(DataStructure.ListNode head, int k) {

    if(k==0 || head == null){
      return head;
    }
    int len=1;
    DataStructure.ListNode rotateHead = head;
    while(rotateHead.next != null){
      rotateHead = rotateHead.next;
      len++;
    }
    //make it a circle
    rotateHead.next = head;

    int travel = len - k%len;
    while(travel > 1){
      head = head.next;
      travel--;
    }
    rotateHead = head.next;
    head.next = null;
    return rotateHead;
  }

}
