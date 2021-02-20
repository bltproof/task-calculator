import java.util.Scanner;

public class CalculatorApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите выражение одной строкой: ");

            String expression = scanner.nextLine();

            ReversePolishNotation notation = new ReversePolishNotation();
            ArabicRomanConversion conversion = new ArabicRomanConversion();

            if (Character.isDigit(expression.toCharArray()[0]) || Character.isDigit(expression.toCharArray()[1])) {
                String rpnString = notation.expressionToRpn(expression);
                int result = notation.rpnToResult(rpnString);
                System.out.println(result);

            } else {
                String preparedExpression = conversion.prepare(expression);
                String rpnString = notation.expressionToRpn(preparedExpression);
                int result = notation.rpnToResult(rpnString);
                String romanResult = conversion.toRoman(result);
                System.out.println(romanResult);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}