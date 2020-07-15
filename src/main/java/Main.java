import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Калькулятор умеет работать с римскими или арабскими цифрами");
        System.out.println("Введите выражение одной строкой");

        try (Scanner scanner = new Scanner(System.in)) {
            String expression = scanner.nextLine();

            ConsoleCalculator calculator = new ConsoleCalculator();

            if (Character.isDigit(expression.toCharArray()[0]) || Character.isDigit(expression.toCharArray()[1])) {
                String rpn = calculator.expressionToRPN(expression);
                int result = calculator.rpnToResult(rpn);
                System.out.println(result);

            } else {
                String convertedExpression = RomanNumeral.convertExpressionFromRomanianToArabicBeforeCalculation(expression);
                String rpn = calculator.expressionToRPN(convertedExpression);
                int result = calculator.rpnToResult(rpn);
                String romanResult = RomanNumeral.arabicToRoman(result);
                System.out.println(romanResult);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}