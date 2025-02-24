import java.util.ArrayList;

public class Convertor {

    public void getResult(ArrayList<String> input) {
        for (String s: input) {
            if (isStringValidInfix(s)) {
                System.out.println(s + " is valid.");
            } else {
                System.out.println(s + " is not-valid.");
            }
        }
    }

    public static ArrayList<String> toStringArrayList(String line) throws ArrayIndexOutOfBoundsException{ // To separate blank space, parenthesis, operand and operator in to each String in the ArrayList.
        ArrayList<String> expression = new ArrayList<>();
        int i = 0;
        while (i<line.length()) {
            char charAtIndex = line.charAt(i);
            // Check for Blank Space
            if (charAtIndex==' ') {
                expression.add(String.valueOf(charAtIndex));
                i++;
            }
            // Check for Parenthesis (Open & Closing)
            else if (isCharOpenParenthesis(charAtIndex) || isCharClosingParenthesis(charAtIndex)) {
                expression.add(String.valueOf(charAtIndex));
                i++;
            }
            // Check for Character Variable
            else if (Character.isLetter(charAtIndex)) {
                expression.add(String.valueOf(charAtIndex));
                i++;
            }
            // Check for Integer Number
            else if (Character.isDigit(charAtIndex)) {
                String integerStr = "";
                while (i<line.length() && Character.isDigit(line.charAt(i))) {
                    integerStr = integerStr + String.valueOf(charAtIndex);
                    i++;
                }
                expression.add(integerStr);
            }
            // Check for an operator (!,+,-,*,/,%,=,<,> and !=,<=,>=, ==)
            else if (isCharSingleOperator(charAtIndex)) {
                if (line.charAt(i+1)=='=') {
                    String operatorStr = String.valueOf(charAtIndex)+String.valueOf(line.charAt(i+1));
                    expression.add(operatorStr);
                    i+=2;
                } else {
                    expression.add(String.valueOf(charAtIndex));
                    i++;
                }
            }
            // Check for a logical operator (||, &&)
            else if (charAtIndex=='|' || charAtIndex=='&') {
                String logicOperatorStr = String.valueOf(charAtIndex)+String.valueOf(line.charAt(i+1));
                expression.add(logicOperatorStr);
                i+=2;
            }
            else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        return expression;
    }

    public static boolean isStringValidInfix(String line) {









        return isStringBalanceParenthesis(line);
    }
    public static boolean isStringBalanceParenthesis(String line) {
        Stack parenthesisStack = new Stack();

        for (char c : line.toCharArray()) {
            if (isCharOpenParenthesis(c)) {
                parenthesisStack.push(c);
            } else if (isCharClosingParenthesis(c)) {
                if (parenthesisStack.isEmpty()) {
                    return false;
                }
                char pop = parenthesisStack.pop();
                if (!isMatchingParenthesisPair(pop,c)) {
                    return false;
                }
            }
        }
        return parenthesisStack.isEmpty();
    }
    public static boolean isMatchingParenthesisPair(char open, char close) {
        return (open=='(' && close==')') ||
                (open=='[' && close==']') ||
                (open=='{' && close=='}');
    }
    public static boolean isCharOpenParenthesis(char c) {
        return (c=='(') || (c=='[') || (c=='{');
    }
    public static boolean isStringOpenParenthesis(String s) {
        return (s.equals("(")) || (s.equals("[")) || (s.equals("{"));
    }
    public static boolean isCharClosingParenthesis(char c) {
        return (c==')') || (c==']') || (c=='}');
    }
    public static boolean isStringClosingParenthesis(String s) {
        return (s.equals(")")) || (s.equals("]")) || (s.equals("}"));
    }
    public static boolean isCharSingleOperator(char c) {
        char[] operators = {'!', '+', '-', '*', '/', '%', '<', '>', '='};
        for (char op : operators) {
            if (c==op) {
                return true;
            }
        }
        return false;
    }
    public static boolean isStringOperator(String s) {
        String[] operators = {"!", "+", "-", "*", "/", "%", "<", ">", "=", "!=", ">=", "<=", "||", "&&"};
        for (String o: operators) {
            if (s.equals(o)) {
                return true;
            }
        }
        return false;
    }

    // For debugging
    public void getToStringArrayListResult (ArrayList<String> input) {
        for (String s: input) {
            ArrayList<String> separatedList = toStringArrayList(s);
            for (String ss : separatedList) {
                System.out.print("(" + ss + "), ");
            }
            System.out.println();
        }
    }
}
