package Solution;

import java.util.Scanner;
class Solution {

    public static void main(String[]args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(catalanNumber(n));
    }

    public static int catalanNumber(int n){
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 0; i < n; i++){
            for(int k = 0; k <= i; k++){
                dp[i + 1] += dp[k] * dp[i - k];
            }
        }
        return dp[n];
    }
}