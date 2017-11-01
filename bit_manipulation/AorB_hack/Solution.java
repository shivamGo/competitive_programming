package bit_manipulation.AorB_hack;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Solution {
    static Map<Character, String> digitToHex = new HashMap<>();
    static Map<String, Character> binToHex = new HashMap<>();

    static{
        digitToHex.put('0', "0000");
        digitToHex.put('1', "0001");
        digitToHex.put('2', "0010");
        digitToHex.put('3', "0011");
        digitToHex.put('4', "0100");
        digitToHex.put('5', "0101");
        digitToHex.put('6', "0110");
        digitToHex.put('7', "0111");
        digitToHex.put('8', "1000");
        digitToHex.put('9', "1001");
        digitToHex.put('A', "1010");
        digitToHex.put('B', "1011");
        digitToHex.put('C', "1100");
        digitToHex.put('D', "1101");
        digitToHex.put('E', "1110");
        digitToHex.put('F', "1111");

        binToHex.put("0000", '0');
        binToHex.put("0001", '1');
        binToHex.put("0010", '2');
        binToHex.put("0011", '3');
        binToHex.put("0100", '4');
        binToHex.put("0101", '5');
        binToHex.put("0110", '6');
        binToHex.put("0111", '7');
        binToHex.put("1000", '8');
        binToHex.put("1001", '9');
        binToHex.put("1010", 'A');
        binToHex.put("1011", 'B');
        binToHex.put("1100", 'C');
        binToHex.put("1101", 'D');
        binToHex.put("1110", 'E');
        binToHex.put("1111", 'F');
    }

    static int k;
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int t = Integer.parseInt(kb.nextLine());
        while(t-- > 0){
            k = Integer.parseInt(kb.nextLine());
            byte[] A = toByteArray(convertHexToBin4Bit(kb.nextLine()).toCharArray());
            byte[] B = toByteArray(convertHexToBin4Bit(kb.nextLine()).toCharArray());
            byte[] C = toByteArray(convertHexToBin4Bit(kb.nextLine()).toCharArray());
            A = extendArray(A, C.length);
            B = extendArray(B, C.length);

            AorB(A, B, C);
            if(k >= 0){
                minimize(A, B, C);
                String te = convertBinToHex(A);
                String newA = trimStartingZero(te);
                String newB = trimStartingZero(convertBinToHex(B));
                System.out.println(newA);
                System.out.println(newB);
            }else{
                System.out.println("-1");
            }
        }
    }

    private static void minimize(byte[] a, byte[] b, byte[] c) {
        for(int i = 0; i < c.length; i++){
            if(c[i] == 1){
                if(a[i] == 1 && b[i] == 0){
                    if(k >= 2){
                        a[i] = 0;
                        b[i] = 1;
                        k = k - 2;
                    }
                }else if(a[i] == 1 && b[i] == 1){
                    if(k >= 1){
                        a[i] = 0;
                    }
                }else if(k <= 0){
                    return;
                }
            }
        }
    }

    private static String trimStartingZero(String s) {
        int i = 0;
        for(i = 0; i < s.length(); i++){
            if(s.charAt(i) - '0' != 0){
                break;
            }
        }
        return s.substring(i);
    }

    private static void AorB(byte[] a, byte[] b, byte[] c) {
        for(int i = 0; i < c.length; i++){
            if(c[i] == 1){
                if(a[i] == 0 && b[i] == 0 && k > 0){
                    b[i] = 1;
                    k--;
                }
            }else{
                if(a[i] == 1 && b[i] == 1){
                    a[i] = 0;
                    b[i] = 0;
                    k = k - 2;
                }else if(a[i] == 1 && b[i] == 0){
                    k--;
                    a[i] = 0;
                }else if(a[i] == 0 && b[i] == 1){
                    k--;
                    b[i] = 0;
                }
            }
        }
    }

    public static String convertHexToBin4Bit(String A){
        String res = "";
        for(int i = 0; i < A.length(); i++){
            res += digitToHex.get(A.charAt(i));
        }
        return res;
    }

    public static String convertBinToHex(byte[] arr){
        String temp = "";
        String res = "";
        for(int i = 0; i < arr.length; i+=4){
            temp = "";
            for(int j = i; j < i + 4; j++){
                temp += arr[j];
            }
            res += binToHex.get(temp);
        }
        return res;
    }



    public static void reverseByteArray(final byte[] arr){
        for(int i = 0, j = arr.length - 1; i < j; i++, j--){
            byte temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static byte[] toByteArray(final char[] arr){
        final int n = arr.length;
        final byte[] byteArr = new byte[n];
        for(int i = 0; i < n; i++){
            byteArr[i] = (byte) (arr[i] - '0');
        }
        return byteArr;
    }

    public static byte[] extendArray(byte[] arr, int n){
        return arr.length >= n ? arr : Arrays.copyOf(arr, n);
    }

    public static int[] extendArray(int[] arr, int n){
        return arr.length >= n ? arr : Arrays.copyOf(arr, n);
    }

    private static boolean isSpaceChar(int c) {
        return !(c >= 33 && c <= 126);
    }

    public static int pow(int n, int a){
        return new Double(Math.pow(n, a)).intValue();
    }

    public static void printArray(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }

    public static void printMatrix(int[][] mat){
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println("");
        }
    }

    private static class Reader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;
        Reader() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = new StringTokenizer("");
        }

        String nextLine() throws IOException {
            return reader.readLine();
        }

        String next() throws IOException {
            while (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException{
            return Long.parseLong(next());
        }

        double nextDouble() throws IOException{
            return Double.parseDouble(next());
        }
    }

    public static String hexToBinary(String s){
        return new BigInteger(s, 16).toString(2);
    }
}
