import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String result = calc(input);
        System.out.println(result);
    }
    public static String calc(String input) {
        String[] tokens = input.split(" ");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Некорректный ввод");
        }

        int a;
        int b;
        try {
            a = Integer.parseInt(tokens[0]);
            b = Integer.parseInt(tokens[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некорректные числа");
        }


        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10");
        }

        int result;

        switch (tokens[1]) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Некорректная операция");
        }

        if (isRomanNumeral(input)) {
            if (result <= 0) {
                throw new IllegalArgumentException("Результат не может быть отрицательным или нулевым при работе с римскими числами");
            }
            return toRomanNumeral(result);
        } else {
            return String.valueOf(result);
        }
    }

    private static boolean isRomanNumeral(String input) {
        return input.matches(".*[IVX].*");
    }

    private static String toRomanNumeral(int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Некорректное число для преобразования в римскую систему");
        }

        String[] romanNumerals = {
                "M",
                "CM",
                "D",
                "CD",
                "C",
                "XC",
                "L",
                "XL",
                "X",
                "IX",
                "V",
                "IV",
                "I"
        };

        int[] values = {
                1000,
                900,
                500,
                400,
                100,
                90,
                50,
                40,
                10,
                9,
                5,
                4,
                1
        };

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (number > 0) {
            if (number - values[i] >= 0) {
                sb.append(romanNumerals[i]);
                number -= values[i];
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}


