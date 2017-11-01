package dynamic_problems.stock_maximize;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-- > 0){
            int n = in.nextInt();
            int[] prices = new int[n];
            for(int i = 0; i < n; i++){
                prices[i] = in.nextInt();
            }

            long res = solve(prices);
            System.out.println(res);
        }
    }

    private static long solve(int[] prices) {
        long profit = 0L;
        long max = Integer.MIN_VALUE;
        for(int i = prices.length - 1; i >= 0; i--){
            if(max < prices[i]){
                max = prices[i];
            }
            profit = profit + (max - prices[i]);
        }
        return profit;
    }
}
