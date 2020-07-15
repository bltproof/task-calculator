import java.util.Stack;
import java.util.function.Function;

public class ExpressionToRpn implements Function<String, String> {

    @Override
    public String apply(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);

            int priority = ConsoleCalculator.getPriority(token);

            if (priority == 0) result.append(token);
            if (priority == 1) stack.push(token);

            if (priority > 1) {
                result.append(' ');

                while (!stack.empty()) {
                    if (ConsoleCalculator.getPriority(stack.peek()) >= priority) {
                        result.append(stack.pop());
                        result.append(' ');
                    } else break;
                }
                stack.push(token);
            }

            if (priority == -1) {
                result.append(' ');

                while (ConsoleCalculator.getPriority(stack.peek()) != 1) {
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
}