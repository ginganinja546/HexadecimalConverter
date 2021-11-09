import java.util.ArrayList;

public class Main {

    // build a program that can convert hex-dex and dec-hex
    // A12 = (10 * 16^2) + (1 * 16^1) + (2 * 16^0)

    public static void main(String[] args) {
        //System.out.println(HexToDecimal.hexToDecimal("A12"));

        //System.out.println(DecimalToHex.decimalToHexDigit(1));
        System.out.println(DecimalToHex.decimalToHex(514252));
    }

    class HexToDecimal {

        private static int hexToDecimalDigit(char c) {
            switch (c) {
                case 'A':
                    return 10;
                case 'B':
                    return 11;
                case 'C':
                    return 12;
                case 'D':
                    return 13;
                case 'E':
                    return 14;
                case 'F':
                    return 15;
                default:
                    return Integer.parseInt(Character.toString(c));
            }
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

    class DecimalToHex {
        // Divide by 16 and take remainder continuously until remainder is 0
        // 2578 / 16 = 161 R 2 --> 161 / 16 = 160 R 1 --> 10 / 16 = 0 R 10 => 2, 1, A = A12

        private static String decimalToHexDigit(int digit) {
            if (digit < 0 || digit > 15) {
                System.err.println("Hex digit must be constrained by 0 - 15");
                return null; // null character (not 0)
            }
            switch(digit) {
                case 0:
                    return "0";
                case 10:
                    return "A";
                case 11:
                    return "B";
                case 12:
                    return "C";
                case 13:
                    return "D";
                case 14:
                    return "E";
                case 15:
                    return "F";
                default:
                    return Integer.toString(digit);
            }
        }

        private static ArrayList<Integer> remainders = new ArrayList<>();
        private static ArrayList<Integer> getHexDigits(int dec_num) {
            if (dec_num == 0) return remainders;

            remainders.add(dec_num % 16);

            dec_num /= 16;
            return getHexDigits(dec_num);
        }

        public static String decimalToHex(int dec_num) {
            String hex = "";
            getHexDigits(dec_num);
            for (int i = remainders.size()-1; i >= 0; i--) {
                hex += decimalToHexDigit(remainders.get(i));
            }
            return hex;
        }

    }

}
