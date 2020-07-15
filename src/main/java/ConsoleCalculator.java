import java.util.Scanner;
import java.util.Stack;

public class ConsoleCalculator {

    private static String ExpressionToRPN(String expression) {
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

    private static int RPNToAnswer(String rpn) {
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

                try {
                    if (rpn.charAt(i) == '+') stack.push(b + a);
                    if (rpn.charAt(i) == '-') stack.push(b - a);
                    if (rpn.charAt(i) == '*') stack.push(b * a);
                    if (rpn.charAt(i) == '/') stack.push(b / a);

                } catch (ArithmeticException e) {
                    System.out.println("Ошибка! Деление на ноль.");
                }
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

    private static String convert(String expression) {
        String[] expressionStringArray = expression.split("[+\\-*/()]");

        for (int i = 0; i < expressionStringArray.length; i++) {
            String romanNumber = expressionStringArray[i].trim();
            int arabicNumber = RomanNumeral.romanToArabic(romanNumber);
            if (arabicNumber > 10) throw new RuntimeException("Число не должно превышать 10");
            if (!romanNumber.isEmpty()) expressionStringArray[i] = String.valueOf(arabicNumber);

            expression = expression.replaceFirst(romanNumber, expressionStringArray[i]);
        }
        return expression;
    }

    public static void main(String[] args) {

        System.out.println("Калькулятор умеет работать с римскими или арабскими цифрами");
        System.out.println("Введите выражение одной строкой");

        try (Scanner scanner = new Scanner(System.in)) {
            String expression = scanner.nextLine();

            if (Character.isDigit(expression.toCharArray()[0]) || Character.isDigit(expression.toCharArray()[1])) {
                System.out.println(RPNToAnswer(ExpressionToRPN(expression)));
            } else {
                System.out.println(RomanNumeral.arabicToRoman(RPNToAnswer(ExpressionToRPN(convert(expression)))));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}