import java.util.Stack;

public class ReversePolishNotation {

    public String expressionToRpn(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);

            int priority = getPriority(token);

            if (priority == 0) result.append(token);
            if (priority == 1) stack.push(token);

            if (priority > 1) {
                result.append(' ');

                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority) {
                        result.append(stack.pop());
                        result.append(' ');
                    } else break;
                }
                stack.push(token);
            }

            if (priority == -1) {
                result.append(' ');

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

    public int rpnToResult(String rpn) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < rpn.length(); i++) {

            if (rpn.charAt(i) == ' ') continue;

            if (getPriority(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                    sb.append(rpn.charAt(i++));
                    if (i == rpn.length()) break;
                }
                int number = Integer.parseInt(sb.toString());
                if (number > 10) throw new RuntimeException("Число не должно превышать 10");
                stack.push(number);
                sb.delete(0, sb.length());
            }

            if (getPriority(rpn.charAt(i)) > 1) {
                int a = stack.pop(), b = stack.pop();
                char operator = rpn.charAt(i);

                try {
                    switch (operator) {
                        case '+':
                            stack.push(b + a);
                            break;
                        case '-':
                            stack.push(b - a);
                            break;
                        case '*':
                            stack.push(b * a);
                            break;
                        case '/':
                            stack.push(b / a);
                            break;
                    }

                } catch (ArithmeticException e) {
                    System.out.println("Ошибка деления на ноль!");
                }
            }
        }
        return stack.pop();
    }

    private int getPriority(char token) {
        if (token == '*' || token == '/') return 3;
        else if (token == '+' || token == '-') return 2;
        else if (token == '(') return 1;
        else if (token == ')') return -1;
        return 0;
    }
}