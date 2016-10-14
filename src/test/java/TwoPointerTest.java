import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TwoPointerTest {
  TwoPointer _twoPointer;
  @BeforeTest
  public void setup(){
    _twoPointer = new TwoPointer();
  }

  @Test
  public void shortestSubStringTest(){
    assertEquals(_twoPointer.shortestSubString("blah",""),0);
    assertEquals(_twoPointer.shortestSubString("","a"),-1);
    assertEquals(_twoPointer.shortestSubString("a","a"),1);
    assertEquals(_twoPointer.shortestSubString("a","b"),-1);
    assertEquals(_twoPointer.shortestSubString("aaa","a"),1);
    assertEquals(_twoPointer.shortestSubString("abcdbtnmca","banc"),6);
    assertEquals(_twoPointer.shortestSubString("aaaaaabbbbbb","ab"),2);
    assertEquals(_twoPointer.shortestSubString("abcdbtnmca","bbanc"),7);
    assertEquals(_twoPointer.shortestSubString("ADOBECODEBANC","ABC"),4);
  }

  @Test
  public void minWindowTest(){
    assertEquals(_twoPointer.minWindow("blah",""),"");
    assertEquals(_twoPointer.minWindow("","a"),"");
    assertEquals(_twoPointer.minWindow("a","a"),"a");
    assertEquals(_twoPointer.minWindow("a","b"),"");
    assertEquals(_twoPointer.minWindow("aaa","a"),"a");
    assertEquals(_twoPointer.minWindow("abcdbtnmca","banc"),"btnmca");
    assertEquals(_twoPointer.minWindow("aaaaaabbbbbb","ab"),"ab");
    assertEquals(_twoPointer.minWindow("abcdbtnmca","bbanc"),"abcdbtn");
    assertEquals(_twoPointer.minWindow("ADOBECODEBANC","ABC"),"BANC");

  }
}
