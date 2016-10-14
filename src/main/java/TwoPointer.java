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
}
