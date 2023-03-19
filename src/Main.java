import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите выражение: ");
        String exp = scanner.nextLine();
        //Определяем арифметическое действие:

        int actionIndex = -1;
        int arf = 0;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i;

            }
        }

        if (actionIndex == -1) {
            throw new Exception("Некорректное выражение");
        }

        String[] data = exp.split(regexActions[actionIndex]);
        if (isRoman(data[0]) == isRoman(data[1])) {
            int a, b;
            boolean isRoman = isRoman(data[0]);
            if (isRoman) {
                a = romanToArabic(data[0]);
                b = romanToArabic(data[1]);
            } else {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
                if (a < 0 || a > 10 || b < 0 || b > 10) {
                    throw new Exception("Значине должно быть от 1 до 10 включительно");
                }
            }
            int result;
            switch (actions[actionIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    result = a / b;
                    break;
            }
            if (isRoman) {
                if (result > 0) {
                    System.out.println(arabicToRoman(result));
                } else {
                    throw new Exception("в римской системе нет отрицательных чисел");
                }
            } else {
                System.out.println(result);
            }
        } else {
            throw new Exception("Числа должны быть в одном формате");
        }
    }

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        int i = 0;
        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }
        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public static boolean isRoman(String input) {
        String romanNumeral = input.toUpperCase();
        boolean result = false;
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        int i = 0;
        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result = romanNumeral.startsWith(symbol.name());
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }
        return result;
    }

}
