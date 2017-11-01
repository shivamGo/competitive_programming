package dynamic_problems.combination_sum_4_leetcode;

import java.util.Scanner;

class Solution {

    public static void main(String[]args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        int res = new Solution().maxCoins(arr);
        System.out.println(res);
    }

    public int maxCoins(int[] a) {
        int n = a.length + 2;
        int[] nums = new int[n];
        nums[0] = nums[n - 1] = 1;
        for(int i = 0; i < a.length; i++){
            nums[i + 1] = a[i];
        }

        int[][] dp = new int[n][n];
        for(int l = 2; l < n; l++){
            for(int i = 0; i < n - l ;i++){
                int j = i + l;
                for(int k = i + 1; k < j; k++){
                    dp[i][j] = Math.max(dp[i][j], nums[i] * nums[k] * nums[j] + dp[i][k] + dp[k][j]);
                }

            }
        }
        return dp[0][dp.length - 1];
    }
}