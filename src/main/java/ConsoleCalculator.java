import java.util.Scanner;

public class ConsoleCalculator {

    public static int getPriority(char token) {
        if (token == '*' || token == '/') return 3;
        else if (token == '+' || token == '-') return 2;
        else if (token == '(') return 1;
        else if (token == ')') return -1;
        return 0;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите выражение одной строкой: ");
            String expression = scanner.nextLine();
            ExpressionToRpn expressionToRpn = new ExpressionToRpn();
            RpnToResult rpnToResult = new RpnToResult();

            if (Character.isDigit(expression.toCharArray()[0]) || Character.isDigit(expression.toCharArray()[1])) {
                String rpn = expressionToRpn.apply(expression);
                int result = rpnToResult.apply(rpn);
                System.out.println(result);

            } else {
                String convertedExpression = RomanNumeral.convertExpressionFromRomanToArabicBeforeCalculation(expression);
                String rpn = expressionToRpn.apply(convertedExpression);
                int result = rpnToResult.apply(rpn);
                String romanResult = new ArabicToRoman().apply(result);
                System.out.println(romanResult);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}