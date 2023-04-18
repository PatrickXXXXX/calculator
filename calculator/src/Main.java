import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите выражение:");
        Scanner scannedExpression = new Scanner(System.in);
        String input = scannedExpression.nextLine();
        String result = calc(input);
        System.out.println(result);
    }
    public static String calc(String input) {
        return Main.decomposeExpressionIntoThreeParts(input);
    }
    public static String decomposeExpressionIntoThreeParts (String expression) {
        String firstNumber = "";
        String secondNumber = "";
        String actionSign = "";
        boolean switchFromOneNumberToAnother = false;
        for (int i = expression.length() - 1; i >= 0; i--) {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-' ||
                    expression.charAt(i) == '*' || expression.charAt(i) == '/') {
                actionSign = expression.charAt(i) + actionSign;
                switchFromOneNumberToAnother = true;
            } else {
                if (switchFromOneNumberToAnother) {
                    firstNumber = expression.charAt(i) + firstNumber;
                } else {
                    secondNumber = expression.charAt(i) + secondNumber;
                }
            }
        }
        return Main.identifyNumbers(firstNumber, secondNumber, actionSign);
    }
    static String identifyNumbers (String firstNumber, String secondNumber, String actionSign) {
        boolean roman1 = false;
        boolean roman2 = false;
        String[] arrayOfRomaNumbers =
                {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                        "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                        "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XIX", "XXX",
                        "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                        "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                        "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                        "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                        "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXXX",
                        "XXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                        "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C",};
        for (int i = 0; i <= arrayOfRomaNumbers.length - 1; i++) {
            if (firstNumber.equals(arrayOfRomaNumbers[i])) {
                firstNumber = Integer.toString(i + 1);
                roman1 = true;
            }
            if (secondNumber.equals(arrayOfRomaNumbers[i])) {
                secondNumber = Integer.toString(i + 1);
                roman2 = true;
            }
        }
        if (roman1 && roman2 || !roman1 && !roman2) {
            if (Integer.parseInt(firstNumber) > 10 || Integer.parseInt(firstNumber) < 1 ||
                    Integer.parseInt(secondNumber) > 10 || Integer.parseInt(firstNumber) < 1) {
                return "Числа должны быть от 1 до 10";
            } else if (roman1 && roman2) {
                int x = Integer.parseInt(Main.getResultOfExpression(firstNumber, secondNumber, actionSign));
                return arrayOfRomaNumbers[x - 1];
            } else if (!roman1 && !roman2) {
                return Main.getResultOfExpression(firstNumber, secondNumber, actionSign);
            }
        }
        return Main.getResultOfExpression("", "", "");
    }
    static String getResultOfExpression (String firstNumber, String secondNumber, String actionSign) {
        int a = Integer.parseInt(firstNumber);
        int b = Integer.parseInt(secondNumber);
        if (actionSign.equals("+")) {
            return Integer.toString(a + b);
        } else if (actionSign.equals("-")) {
            return Integer.toString(a - b);
        } else if (actionSign.equals("*")) {
            return Integer.toString(a * b);
        } else {
            return Integer.toString(a / b);
        }
    }
}