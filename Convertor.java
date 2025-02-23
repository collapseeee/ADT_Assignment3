import java.util.ArrayList;

public class Convertor {

    public void getResult(ArrayList<String> input) {
        for (String s: input) {
            if (isValidInfix(s)) {
                System.out.println(s + " is valid.");
            } else {
                System.out.println(s + " is not-valid.");
            }
        }
    }

    public static boolean isValidInfix(String line) {
        Stack parenthesisStack = new Stack();
        // Perform parenthesis check
        String input = line.trim();
        for (int i=0; i<input.length(); i++) {
            if (input.charAt(i)=='[' || input.charAt(i)=='{' || input.charAt(i)=='(') {
                parenthesisStack.push(input.charAt(i));
            } else if (input.charAt(i)==']' || input.charAt(i)=='}' || input.charAt(i)==')') {
                try {
                    parenthesisStack.pop();
                } catch (RuntimeException e) {
                    return false;
                }
            }
        }
        if (parenthesisStack.isEmpty()) {
            return true;
        } else {
            return false;
        }

        // Perform operand pair check
    }

    public static boolean isOperator(String c) {
        String[] operatorList = {"!", "+", "-", "*", "/", "%", "<", "<=", ">", ">=", "==", "!=", "&&", "||", "="};
        for (int i = 0; i<operatorList.length; i++) {
            if (c.equals(operatorList[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInteger(String c) {
        try {
            int num = Integer.parseInt(c);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isOperand(String c) {
        return c.length()==1;
    }
}
