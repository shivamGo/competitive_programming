package contests;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    private static Reader in = new Reader();
    private static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    private static void solve() throws Exception {

    }

    public static void main(String[] args) throws Exception {
        solve();
        out.flush();
    }

    //Inverse Multiplication Modulo
    public static int pow(int n, int a){
        return new Double(Math.pow(n, a)).intValue();
    }

    public static int modInverse(int a, int m)
    {
        int m0 = m, t, q;
        int x0 = 0, x1 = 1;

        if (m == 1)
            return 0;

        while (a > 1)
        {
            q = a / m;
            t = m;
            m = a % m; a = t;
            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }

        if (x1 < 0)
            x1 += m0;

        return x1;
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