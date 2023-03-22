import java.util.List;
import java.util.Scanner;

public class Main {
    private static final char[] actions = {'+', '-', '/', '*'};
    private static final String[] regexActions = {"\\+", "-", "/", "\\*"};
    private static final int MAX_VALUE = 10;
    private static final int MIN_VALUE = 1;

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scanner.nextLine();

        System.out.println(calc(exp));
    }

    public static String calc(String input) throws Exception {
        int operand = -1;
        int operands = 0;


        for (int i = 0; i < actions.length; i++) {
            for (int j = 0; j < input.length(); j++) {
                if (operands > 1) {
                    throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                } else if (input.charAt(j) == actions[i]) {
                    operand = i;
                    operands++;
                }
            }
        }

        if (operand == -1) throw new Exception("строка не является математической операцией");

        String[] data = input.split(regexActions[operand]);
        if (FormatRomanNumber.isRoman(data[0]) == FormatRomanNumber.isRoman(data[1])) {
            int a, b;
            boolean isRoman = FormatRomanNumber.isRoman(data[0]);
            if (isRoman) {
                a = FormatRomanNumber.romanToArabic(data[0]);
                b = FormatRomanNumber.romanToArabic(data[1]);
            } else {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }

            if (a < MIN_VALUE || a > MAX_VALUE || b < MIN_VALUE || b > MAX_VALUE) {
                throw new Exception("Значине должно быть от 1 до 10 включительно");
            }

            int result;
            switch (actions[operand]) {
                case '+':
                    result = a + b;
                    break;
                case '-':
                    result = a - b;
                    break;
                case '*':
                    result = a * b;
                    break;
                default:
                    result = a / b;
                    break;
            }
            if (isRoman) {
                if (result > 0) {
                    return FormatRomanNumber.arabicToRoman(result);
                } else {
                    throw new Exception("в римской системе диапазон возможных значений от 1 до 3999");
                }
            } else {
                return Integer.toString(result);
            }
        } else {
            throw new Exception("используются одновременно разные системы счисления");
        }
    }
}
