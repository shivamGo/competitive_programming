package dynamic_problems.xor_and_sum;
import java.io.*;
import java.util.*;

public class Solution {
    private static Reader in = new Reader();
    private static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    private static final int mod = 1000000007;

    private static void solve() throws Exception {
        byte[] A = toByteArray(in.nextLine().toCharArray());
        byte[] B = toByteArray(in.nextLine().toCharArray());
        final int max = 314159;

        reverseByteArray(A);
        reverseByteArray(B);

        A = extendArray(A, B.length);
        B = extendArray(B, A.length);

        long sum = 0L;
        long pow = 1L;
        int oneCount = 0;
        int n = A.length;

        //*--1--*-------2-----*--3--*
        int i = 0;
        for(i = 0; i < n; i++){
            oneCount += B[i];
            int mul = A[i] == 0 ? oneCount : max - oneCount + 1;
            sum = (sum + mul * pow) % mod;
            pow = (pow << 1L) % mod;
        }

        while(i++ < max){
            sum = (sum + (pow * oneCount)) % mod;
            pow = (pow << 1L) % mod;
        }

        for(int j = 0; j < n; j++){
            sum = (sum + (pow * oneCount)) % mod;
            pow = (pow << 1L) % mod;
            oneCount -= B[j];
        }
        System.out.println(sum);
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

    public static void main(String[] args) throws Exception {
        solve();
        out.flush();
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

        String nextLine() throws IOException{
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
}