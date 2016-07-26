import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.Assert;

import java.util.*;

/**
 * Created by ZSong on 7/8/16.
 */
public class UtilsTest {
  Utils _utils;

  @BeforeTest
  public void setup() {
    _utils = new Utils();
  }

  @Test
  public void reversePolishNotationCalculationTest() {
    String[] input1 = {"2", "1", "+", "3", "*"};
    Assert.assertEquals(_utils.reversePolishNotationCalculation(input1), 9);

    String[] input2 = {"4", "13", "5", "/", "+"};
    Assert.assertEquals(_utils.reversePolishNotationCalculation(input2), 6);

  }

  @Test
  public void shuntingYardSimplifiedTest() {
    String input1 = "(2 + 1) * 3";
    List<String> expected_output1 = Arrays.asList(new String[]{"2", "1", "+", "3", "*"});
    Assert.assertEquals(_utils.shuntingYardSimplified(input1), expected_output1);

    String input2 = "4 + (13 / 5)";
    List<String> expected_output2 = Arrays.asList(new String[]{"4", "13", "5", "/", "+"});
    Assert.assertEquals(_utils.shuntingYardSimplified(input2), expected_output2);

  }

  @Test
  public void shuntingYardAndRPNTest() {
    String input1 = "12 / (4 / (1 + 1))";
    List<String> RPN1 = _utils.shuntingYardSimplified(input1);
    Assert.assertEquals(_utils.reversePolishNotationCalculation(RPN1.toArray(new String[RPN1.size()])), 6);

    String input2 = "(1 + (3 * 4)) / 13";
    List<String> RPN2 = _utils.shuntingYardSimplified(input2);
    Assert.assertEquals(_utils.reversePolishNotationCalculation(RPN2.toArray(new String[RPN2.size()])), 1);
  }

  @Test
  public void buildTrieTest(){
    List<String> input1 = Arrays.asList(new String[]{"oath", "pea", "eat", "rain"});
    Utils.TrieNode expected_output1 = _utils.buildTrie(input1);
    Assert.assertEquals(expected_output1.getPrefix(),"");
    Set<Character> childSet = new HashSet<>();
    childSet.add('o');
    childSet.add('p');
    childSet.add('e');
    childSet.add('r');
    Assert.assertEquals(expected_output1.getChildren().keySet(),childSet);
    Utils.TrieNode peaNode = expected_output1.getChildren().get('p').getChildren().get('e').getChildren().get('a');
    Assert.assertEquals(peaNode.getPrefix(),"pea");
    Assert.assertEquals(peaNode.getInDictionary(),true);
  }

  @Test void searchWordInTrieTest(){
    List<String> input1 = Arrays.asList(new String[]{"oath", "pea", "eat", "rain"});
    Utils.TrieNode root1 = _utils.buildTrie(input1);
    Assert.assertEquals(_utils.searchWordInTrie(root1,"oath"),true);
    Assert.assertEquals(_utils.searchWordInTrie(root1,"pea"),true);
    Assert.assertEquals(_utils.searchWordInTrie(root1,"eat"),true);
    Assert.assertEquals(_utils.searchWordInTrie(root1,"rain"),true);
    Assert.assertEquals(_utils.searchWordInTrie(root1,"pull"),false);
    Assert.assertEquals(_utils.searchWordInTrie(root1,"ea"),false);
    Assert.assertEquals(_utils.searchWordInTrie(root1,""),false);
    Assert.assertEquals(_utils.searchWordInTrie(root1,"o"),false);
  }

  @Test void letterOutsideStatesTest(){
    String[] states = new String[]{"Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia",
        "Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","NewHampshire","NewJersey","NewMexico","NewYork","NorthCarolina","NorthDakota",
        "Ohio","Oklahoma","Oregon","Pennsylvania","RhodeIsland","SouthCarolina","SouthDakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","WestVirginia","Wisconsin","Wyoming"};
    Assert.assertEquals(_utils.letterOutsideStates(states),"q");
  }
}
