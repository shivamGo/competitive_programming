package dynamic_problems.longest_palindromic_subsequence;

import com.sun.org.apache.xml.internal.resolver.readers.SAXCatalogParser;

import java.util.Scanner;

class Solution {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String pat = kb.nextLine();
        int len = new Solution().longestPalindromeSubseq(pat);
        System.out.println(len);
    }
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        for(int j = 0; j < dp[0].length; j++){
            dp[dp.length - 1][j] = 0;
        }

        for(int i = 0; i < dp.length; i++){
            dp[i][0] = 0;
        }

        for(int i = n - 1; i >= 0; i--){
            for(int j = 1; j < dp[0].length; j++){
                if(s.charAt(i) == s.charAt(j - 1)){
                    dp[i][j] = dp[i + 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][dp.length - 1];
    }

    public static void printMatrix(int[][] mat){
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println("");
        }
    }
}