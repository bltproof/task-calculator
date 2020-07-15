import java.util.Stack;
import java.util.function.Function;

public class RpnToResult implements Function<String, Integer> {

    @Override
    public Integer apply(String rpn) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < rpn.length(); i++) {

            if (rpn.charAt(i) == ' ') continue;

            if (ConsoleCalculator.getPriority(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ' && ConsoleCalculator.getPriority(rpn.charAt(i)) == 0) {
                    sb.append(rpn.charAt(i++));
                    if (i == rpn.length()) break;
                }
                int number = Integer.parseInt(sb.toString());
                if (number > 10) throw new RuntimeException("Число не должно превышать 10");
                stack.push(number);
                sb.delete(0, sb.length());
            }

            if (ConsoleCalculator.getPriority(rpn.charAt(i)) > 1) {
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
}