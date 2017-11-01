package dynamic_problems.catalan_number;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    public static void main(String[]args){
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int res = new Solution().catalanNumber(n);
        System.out.println(res);
    }

    public int catalanNumber(int n){
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j <= i; j++){
                dp[i + 1] += dp[j] * dp[i - j];
            }
        }
        return dp[n];
    }
}
