/**
 * author: Nattikorn Sae-sue, 672115014
 * contact: nattikorn_s@cmu.ac.th
 * files: InfixToPostfixMain.java, Convertor.java, Stack.java, and Node.java
 */

import java.util.ArrayList;

public class Convertor {

    public void getResult(ArrayList<String> input) {
        for (int i=0; i<input.size(); i++) {
            String cleanInfix = input.get(i).replace(" ", "");
            System.out.println("Expression " + (i+1) + ":");
            System.out.println("Infix Expression: " + cleanInfix);
            if (isStringValidInfix(cleanInfix)) {
                System.out.println("Is a valid infix expression.");
                System.out.println("Postfix Expression: " + toPostfix(cleanInfix));
            } else {
                System.out.println("Is an invalid infix expression.");
            }
            System.out.println();
        }
    }

    public static ArrayList<String> toStringArrayListToken(String line) throws ArrayIndexOutOfBoundsException { // To separate blank space, parenthesis, operand and operator in to each String in the ArrayList.
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

    public static String toPostfix(String infix) {
        ArrayList<String> tokens = toStringArrayListToken(infix);
        StringBuilder postfix = new StringBuilder();
        Stack stack = new Stack();

        for (String token : tokens) {
            if (token.equals(" ")) {
                continue;
            }
            if (isStringOpenParenthesis(token)) {
                stack.push(token);
            }
            if (isStringClosingParenthesis(token)) {
                while (!stack.isEmpty() && !isStringOpenParenthesis(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.pop(); // Remove the `(` in the stack
            }
            if (isStringOperand(token)) {
                postfix.append(token);
            }
            if (isStringOperator(token)) {
                if (token.equals("-") && (postfix.isEmpty() || (tokens.indexOf(token) > 0 && (isStringOpenParenthesis(tokens.get(tokens.indexOf(token) - 1)) || isStringOperator(tokens.get(tokens.indexOf(token) - 1)))))) {
                    // unary minus treat differently
                    stack.push("~"); // Using ~ to represent unary minus
                }
                else {
                    while (!stack.isEmpty() && !isStringOpenParenthesis(stack.peek()) && hasHigherOrEqualPrecedence(stack.peek(), token)) {
                        postfix.append(stack.pop());
                    }
                    stack.push(token);
                }
            }
        }
        // Pop remaining data in stack.
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }
    private static int getPrecedence(String operator) {
        switch (operator) {
            case "=":
                return 1;
            case "||":
                return 2;
            case "&&":
                return 3;
            case "==":
            case "!=":
                return 8;
            case "<":
            case "<=":
            case ">":
            case ">=":
                return 9;
            case "+":
            case "-":
                return 11;
            case "*":
            case "^":
            case "/":
            case "%":
                return 12;
            case "~":
            case "!":
                return 13;
            default:
                return -1;
        }
    }
    private static boolean hasHigherOrEqualPrecedence(String op1, String op2) {
        return getPrecedence(op1) >= getPrecedence(op2);
    }

    public static boolean isStringValidInfix(String line) {
        if (!isStringBalanceParenthesis(line)) {
            return false;
        }

        try {
            ArrayList<String> tokens = toStringArrayListToken(line.trim()); // Ignore the whitespace.
            if (tokens.isEmpty()) {
                return true; // Empty token is valid.
            }
            boolean expectedOperand = true;
            boolean expectedOperator = false;
            boolean previousWasOperator = false; // Handle negative operand.
            for (int i=0; i<tokens.size(); i++) {
                String token = tokens.get(i);

                // Skip spaces
                if (token.equals(" ")) {
                    continue;
                }

                if (isStringOpenParenthesis(token)) { // Before the '(' must be an Operator or beginning of expression. After must be an Operand.
                    if (!expectedOperand) {
                        return false;
                    }
                    expectedOperand = true;
                    expectedOperator = false;
                    previousWasOperator = false;
                    continue;
                }
                if (isStringClosingParenthesis(token)) { // Before the ')' must be an Operand. After must be an Operator.
                    if (expectedOperand) {
                        return false;
                    }
                    expectedOperand = false;
                    expectedOperator = true;
                    previousWasOperator = false;
                    continue;
                }
                if (isStringOperand(token)) {
                    if (!expectedOperand) {
                        return false;
                    }
                    expectedOperand = false;
                    expectedOperator = true;
                    previousWasOperator = false;
                    continue;
                }
                if (isStringSubtraction(token) && (i==0 || isStringOpenParenthesis(tokens.get(i-1)) || isStringOperator(tokens.get(i-1)))) {
                    // Negative Numbers Handle
                    expectedOperand = true;
                    expectedOperator = false;
                    previousWasOperator = true;
                    continue;
                }
                if (isStringOperator(token)) {
                    if (!expectedOperator) {
                        return false;
                    }
                    expectedOperand = true;
                    expectedOperator = false;
                    previousWasOperator = true;
                    continue;
                }
                return false;
            }
            return !expectedOperand; // Valid Expression ends with Operand or Closing parenthesis

        } catch (Exception e) {
            return false;
        }
    }
    private static boolean isStringBalanceParenthesis(String line) {
        Stack parenthesisStack = new Stack();

        for (char c : line.toCharArray()) {
            if (isCharOpenParenthesis(c)) {
                parenthesisStack.push(String.valueOf(c));
            } else if (isCharClosingParenthesis(c)) {
                if (parenthesisStack.isEmpty()) {
                    return false;
                }
                char pop = parenthesisStack.pop().charAt(0);
                if (!isMatchingParenthesisPair(pop,c)) {
                    return false;
                }
            }
        }
        return parenthesisStack.isEmpty();
    }
    private static boolean isMatchingParenthesisPair(char open, char close) {
        return (open=='(' && close==')') ||
                (open=='[' && close==']') ||
                (open=='{' && close=='}');
    }
    private static boolean isCharOpenParenthesis(char c) {
        return (c=='(') || (c=='[') || (c=='{');
    }
    private static boolean isStringOpenParenthesis(String s) {
        return (s.equals("(")) || (s.equals("[")) || (s.equals("{"));
    }
    private static boolean isCharClosingParenthesis(char c) {
        return (c==')') || (c==']') || (c=='}');
    }
    private static boolean isStringClosingParenthesis(String s) {
        return (s.equals(")")) || (s.equals("]")) || (s.equals("}"));
    }
    private static boolean isCharSingleOperator(char c) {
        char[] operators = {'!', '+', '-', '*', '/', '%', '<', '>', '='};
        for (char op : operators) {
            if (c==op) {
                return true;
            }
        }
        return false;
    }
    private static boolean isStringOperator(String s) {
        String[] operators = {"!", "+", "-", "*", "/", "%", "<", ">", "==", "=", "!=", ">=", "<=", "||", "&&"};
        for (String o: operators) {
            if (s.equals(o)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isStringOperand(String s) {
        return Character.isLetter(s.charAt(0)) || Character.isDigit(s.charAt(0));
    }
    private static boolean isStringSubtraction(String s) {
        return s.equals("-");
    }
}
