import java.util.Scanner;

public class CalculatorApp {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Введите выражение одной строкой: ");

            String expression = scanner.nextLine();

            ReversePolishNotation rpnClass = new ReversePolishNotation();
            ArabicRomanConversion conversion = new ArabicRomanConversion();

            if (Character.isDigit(expression.toCharArray()[0]) || Character.isDigit(expression.toCharArray()[1])) {
                String rpnString = rpnClass.expressionToRpn(expression);
                int result = rpnClass.rpnToResult(rpnString);
                System.out.println(result);

            } else {
                String preparedExpression = conversion.prepare(expression);
                String rpnString = rpnClass.expressionToRpn(preparedExpression);
                int result = rpnClass.rpnToResult(rpnString);
                String romanResult = conversion.arabicToRoman(result);
                System.out.println(romanResult);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}