import java.util.ArrayList;

public class Main {

    // build a program that can convert hex-dex and dec-hex
    // A12 = (10 * 16^2) + (1 * 16^1) + (2 * 16^0)

    public static void main(String[] args) {
        int a = HexToDecimal.hexToDecimal("A12");
        System.out.println(a);

        System.out.println(DecimalToHex.decimalToHex(a));
    }

    static class HexToDecimal {

        private static int hexToDecimalDigit(char c) {
            return switch (c) {
                case 'A' -> 10;
                case 'B' -> 11;
                case 'C' -> 12;
                case 'D' -> 13;
                case 'E' -> 14;
                case 'F' -> 15;
                default -> Integer.parseInt(Character.toString(c));
            };
        }

        private static int[] hexStringToDigits(String hex_num) {
            hex_num = hex_num.toUpperCase();
            hex_num = hex_num.replaceAll("[ ]", "");
            int[] digits = new int[hex_num.length()];

            for (int i = 0; i < digits.length; i++) {
                digits[i] = hexToDecimalDigit(hex_num.charAt(i));
            }

            return digits;
        }

        public static int hexToDecimal(String hex_num) {
            int[] digits = hexStringToDigits(hex_num);
            int total = 0;

            int index = digits.length-1;
            int pow = 0;

            while (index >= 0) {
                total += (digits[index] * (int)Math.pow(16, pow));
                index--;
                pow++;
            }

            return total;
        }

    }

    static class DecimalToHex {
        // Divide by 16 and take remainder continuously until remainder is 0
        // 2578 / 16 = 161 R 2 --> 161 / 16 = 160 R 1 --> 10 / 16 = 0 R 10 => 2, 1, A = A12

        private static String decimalToHexDigit(int digit) {
            if (digit < 0 || digit > 15) {
                System.err.println("Hex digit must be constrained by 0 - 15");
                return null; // null character (not 0)
            }
            return switch (digit) {
                case 0 -> "0";
                case 10 -> "A";
                case 11 -> "B";
                case 12 -> "C";
                case 13 -> "D";
                case 14 -> "E";
                case 15 -> "F";
                default -> Integer.toString(digit);
            };
        }

        private static final ArrayList<Integer> remainders = new ArrayList<>();
        private static ArrayList<Integer> getHexDigits(int dec_num) {
            if (dec_num == 0) return remainders;

            remainders.add(dec_num % 16);

            dec_num /= 16;
            return getHexDigits(dec_num);
        }

        public static String decimalToHex(int dec_num) {
            StringBuilder hex = new StringBuilder();
            getHexDigits(dec_num);
            for (int i = remainders.size()-1; i >= 0; i--) {
                hex.append(decimalToHexDigit(remainders.get(i)));
            }
            return hex.toString();
        }

    }

}
