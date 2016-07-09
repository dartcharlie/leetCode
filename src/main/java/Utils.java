import java.util.*;

/**
 * Created by ZSong on 7/7/16.
 */
public class Utils {
  /**
   * shutting yard algorithm: https://en.wikipedia.org/wiki/Shunting-yard_algorithm
   * is to convert human readable mathematical expression(infix) to computer friendly notation(reverse polish notation)
   * a simplified shutting yard algorithm implementation that supports +,-,*,/,^
   * @param infix human readable math expression
   * @return RPN reverse polish notation in the form of list of tokens, i.e. ["4","13","5","/","+"]
   */

  public List<String> shuntingYardSimplified(String infix) {
    Map<Character,Integer> operatorPriorities = new HashMap<Character, Integer>();
    operatorPriorities.put('+',2);
    operatorPriorities.put('-',2);
    operatorPriorities.put('*',3);
    operatorPriorities.put('/',3);
    int infixLen = infix.length();

    Stack<Character> operators = new Stack<Character>();
    List<String> outputs = new ArrayList<String>();
    int currNum = 0;
    boolean hasNum = false;
    for(int i=0;i<infixLen;++i){
      char currChar = infix.charAt(i);
      if(currChar == ' '){
        continue;
      }
      if(currChar >='0' && currChar <= '9'){
        hasNum = true;
        currNum = currNum*10 + currChar - '0';
      }else {
        if(hasNum){
          hasNum = false;
          outputs.add(new Integer(currNum).toString());
          currNum = 0;
        }
        if (operatorPriorities.containsKey(currChar)) {
          int currOperatorPriority = operatorPriorities.get(currChar);
          while (!operators.empty() && operators.peek() != '(') {
            int stackTopOperatorPriority = operatorPriorities.get(operators.peek());
            if (stackTopOperatorPriority >= currOperatorPriority) {
              outputs.add(operators.pop().toString());
            } else {
              break;
            }
          }
          operators.push(currChar);
        } else if (currChar == '(') {
          operators.push(currChar);
        } else if (currChar == ')') {
          while (operators.peek() != '(') {
            outputs.add(operators.pop().toString());
          }
          operators.pop(); //pop out left parenthesis
        }
      }
    }
    if(hasNum){
      outputs.add(new Integer(currNum).toString());
    }
    while(!operators.empty()){
      outputs.add(operators.pop().toString());
    }
    return outputs;
  }

  /**
   * given a valid reverse polish notation, calculate the result
   * @param RPN reverse polish notation
   * @return notation result
   */
  public int reversePolishNotationCalculation(String[] RPN){
    Stack<Integer> tempResult = new Stack<Integer>();
    int RPNLen = RPN.length;
    int num1,num2,numCal=0;
    for(int i=0;i<RPNLen;++i){
      if(isInteger(RPN[i])){
        tempResult.push(Integer.parseInt(RPN[i]));
      }else{
        num2 = tempResult.pop();
        num1 = tempResult.pop();
        if(RPN[i].equals("+")){
          numCal = num1 + num2;
        }else if(RPN[i].equals("-")){
          numCal = num1 - num2;
        }else if(RPN[i].equals("*")){
          numCal = num1 * num2;
        }else if(RPN[i].equals("/")){
          numCal = num1 / num2;
        }
        tempResult.push(numCal);
      }
    }
    return tempResult.pop();
  }

  private boolean isInteger(String str) {
    if (str == null) {
      return false;
    }
    int length = str.length();
    if (length == 0) {
      return false;
    }
    int i = 0;
    if (str.charAt(0) == '-') {
      if (length == 1) {
        return false;
      }
      i = 1;
    }
    for (; i < length; i++) {
      char c = str.charAt(i);
      if (c < '0' || c > '9') {
        return false;
      }
    }
    return true;
  }
}
