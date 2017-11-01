package dynamic_problems.Burst_Balloons_leetcode;

import java.util.Scanner;

class Solution {
    public static void main(String[]args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = in.nextInt();
        }

        int res = new Solution().maxCoins(nums);
        System.out.println(res);
    }

    public int maxCoins(int[] a) {
        int[] nums = new int[a.length + 2];
        nums[0] = nums[nums.length - 1] = 1;
        for(int i = 1; i < nums.length - 1; i++){
            if(a[i - 1] > 0){
                nums[i] = a[i - 1];
            }
        }
        int n = nums.length;

        int[][] dp = new int[n][n];
        for(int k = 2; k < n; k++){
            for(int l = 0; l < n - k; l++){
                int r = l + k;
                for(int j = l + 1; j < r; j++){
                    dp[l][r] = Math.max(dp[l][r], nums[l] * nums[j] * nums[r] + dp[l][j] + dp[j][r]);
                }
            }
        }
        print(dp);
        return dp[0][dp.length - 1];
    }

    public void print(int[][] mat){
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
