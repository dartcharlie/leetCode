import java.util.Stack;

public class StringProblem {
  public boolean backspaceCompare(String S, String T) {
    String sp = processed(S);
    String tp = processed(T);
    return sp.equals(tp);
  }

  private String processed(String s) {
    Stack<Character> process = new Stack<>();
    for(int i=0;i<s.length();++i) {
      if(s.charAt(i) == '#') {
        if(!process.isEmpty())
          process.pop();
      } else {
        process.push(s.charAt(i));
      }
    }
    StringBuilder sb = new StringBuilder();
    for(char c:process) {
      sb.append(c);
    }
    return sb.toString();
  }
}
