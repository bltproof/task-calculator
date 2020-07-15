import java.util.*;
import java.util.regex.Pattern;

public class ConsoleCalculator {
    private String expression;

    public ConsoleCalculator(String expression) {
        this.expression = expression;
    }

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
        //(?!10$)([0-9]{2})
        //[2-9[1-9]]{2,}
        String str = "2*3+(44 / 2)";
        Pattern pattern = Pattern.compile("[2-9[1-9]]{2,}");

        if (pattern.matcher(str).matches()) {
            throw new RuntimeException("Ошибка! Цифры только от 1 до 10!");
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
                stack.push(Integer.parseInt(sb.toString()));
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

    private static String convert(Scanner scanner) {

    }

    public static void main(String[] args) {

        System.out.println("Калькулятор умеет работать с римскими или арабскими цифрами");
        System.out.println("Введите выражение одной строкой");

        try (Scanner scanner = new Scanner(System.in)) {
            String str = scanner.nextLine();
            String[] array = str.split("[+\\-*/()]");

            for (int i = 0; i < array.length; i++) {
                String romanLetter = array[i].trim();
                if (!romanLetter.isEmpty()) array[i] = String.valueOf(RomanNumeral.romanToArabic(romanLetter));
                str = str.replace(romanLetter, array[i]);
            }

            System.out.println(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}