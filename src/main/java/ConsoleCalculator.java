import java.util.Scanner;
import java.util.Stack;

public class ConsoleCalculator {
    private Scanner scanner = new Scanner(System.in);

    public static String ExpressionToRPN(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<Character>();

        int priority;
        for (int i = 0; i < expression.length(); i++) {
            priority = getPriority(expression.charAt(i));
            if (priority == 0) result.append(expression.charAt(i));
            if (priority == 1) stack.push(expression.charAt(i));

            if (priority > 1) {
                result.append(" ");

                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority) result.append(stack.pop());
                    else break;
                }
                stack.push(expression.charAt(i));
            }

            if (priority == -1) {
                result.append(" ");

                while (getPriority(stack.peek()) != 1) {
                    result.append(stack.pop());
                }
                stack.pop();
            }
        }
        while (!stack.empty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    public static int RPNToAnswer(String rpn) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') continue;
            if (getPriority(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                    sb.append(rpn.charAt(i++));
                    if (i == rpn.length()) break;
                }
                stack.push(Integer.parseInt(sb.toString()));
                sb.delete(0, sb.length());
            }
            if (getPriority(rpn.charAt(i)) > 1) {
                int a = stack.pop(), b = stack.pop();

                if (rpn.charAt(i) == '+') stack.push(b + a);
                if (rpn.charAt(i) == '-') stack.push(b - a);
                if (rpn.charAt(i) == '*') stack.push(b * a);
                if (rpn.charAt(i) == '/') stack.push(b / a);
            }
        }

        return stack.pop();
    }

    private static int getPriority(char token) {
        if (token == '*' || token == '/') return 3;
        else if (token == '+' || token == '-') return 2;
        else if (token == '(') return 1;
        else if (token == ')') return -1;
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(RPNToAnswer(ExpressionToRPN("(2 + 2) * 2")));
    }
}