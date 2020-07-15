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

        System.out.println("Калькулятор умеет работать с римскими или арабскими цифрами");
        System.out.println("Введите выражение одной строкой");

        try (Scanner scanner = new Scanner(System.in)) {
            String expression = scanner.nextLine();

            if (Character.isDigit(expression.toCharArray()[0]) || Character.isDigit(expression.toCharArray()[1])) {
                String rpn = new ExpressionToRpn().apply(expression);
                int result = new RpnToResult().apply(rpn);
                System.out.println(result);

            } else {
                String convertedExpression = RomanNumeral.convertExpressionFromRomanToArabicBeforeCalculation(expression);
                String rpn = new ExpressionToRpn().apply(convertedExpression);
                int result = new RpnToResult().apply(rpn);
                String romanResult = new ArabicToRoman().apply(result);
                System.out.println(romanResult);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}