import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum RomanNumeral {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100),
    CD(400), D(500), CM(900), M(1000);

    private int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<RomanNumeral> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                .collect(Collectors.toList());
    }

    public static String convertExpressionFromRomanToArabicBeforeCalculation(String expression) {
        String[] expressionStringArray = expression.split("[+\\-*/()]");

        for (int i = 0; i < expressionStringArray.length; i++) {
            String romanNumber = expressionStringArray[i].trim();
            int arabicNumber = new RomanToArabic().apply(romanNumber);
            if (arabicNumber > 10) throw new RuntimeException("Число не должно превышать 10");
            if (!romanNumber.isEmpty()) expressionStringArray[i] = String.valueOf(arabicNumber);

            expression = expression.replaceFirst(romanNumber, expressionStringArray[i]);
        }
        return expression;
    }
}