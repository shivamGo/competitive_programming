package string.the_grid_search_hack;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    private static Reader in = new Reader();
    private static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    private static final int mod = 1000000007;

    private static void solve() throws Exception {
        int test = in.nextInt();
        while(test-- > 0){
            int R = in.nextInt();
            int C = in.nextInt();

            String[] mat = new String[R];
            for(int i = 0; i < R; i++){
                mat[i] = in.nextLine();
            }

            int patR = in.nextInt();
            int patC = in.nextInt();

            String[] pat = new String[patR];
            for(int i = 0; i < patR; i++){
                pat[i] = in.nextLine();

            }

            boolean isFind = false;
            outer:
            for(int i = 0; i < mat.length - pat.length + 1; i++){
                for(int j = 0; j < mat[0].length() - pat[0].length() + 1; j++){
                    if(find(pat, mat, i, j)){
                        isFind = true;
                        break outer;
                    }
                }
            }
            System.out.println(isFind ? "YES" : "NO");
        }
    }

    private static boolean find(String[] pat, String[] mat, int r, int c) {
        for(int i = 0; i < pat.length; i++){
            for(int j = 0; j < pat[0].length(); j++){
                if(pat[i].charAt(j) != mat[r + i].charAt(c + j)){
                    return false;
                }
            }
        }
        return true;
    }


    private static boolean search(String[] pat, String[] mat) {
        for(int i = 0; i < pat.length - mat.length + 1; i++){
            Pattern p = Pattern.compile(mat[0]);
            Matcher m = p.matcher(pat[i]);
            int s = -1;
            while(m.find()){
                s = m.start();
                System.out.println(s);
            }
            if(s != -1){
                if(search(pat, mat, i, s)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean search(String[] pat, String[] mat, int i, int s) {
        for(int r = 1; r < mat.length; r++){
            for(int c = 0; c < mat[0].length(); c++){
                if(pat[i + r].charAt(s + c) != mat[r].charAt(c)){
                    System.out.println(r + " " + c);
                    return false;
                }
            }
        }
        return true;
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