package dynamic_problems.Maximum_Sum_of_3_Non_Overlapping_Subarrays_leetcode.optimal;

import java.util.Scanner;

public class Solution {
    public static void main(String[]args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        int k = in.nextInt();
        int[] res = solve(arr, k);
        System.out.println(res[0] + " " + res[1] + " " + res[2]);
    }

    public static int[] solve(int[] nums, int k){
        int[] window = new int[nums.length - k + 1];
        int sum = 0;

        for(int i = 0; i < nums.length; i++){
            sum += nums[i];

            if(i >= k){
                sum -= nums[i - k];
            }
            if(i >= k - 1){
                window[i - k + 1] = sum;
            }
        }

        int[] left = new int[window.length];
        int b = 0;
        for(int i = 0; i < left.length; i++){
            if(window[i] > window[b]){
                b = i;
            }
            left[i] = b;
        }

        int[] right = new int[window.length];
        b = window.length - 1;
        for(int i = right.length - 1; i > -1; i--){
            if(window[i] > window[b]){
                b = i;
            }
            right[i] = b;
        }

        int[] ans = {-1, -1, -1};
        for(int i = k; i < window.length  - k; i++){
            int s = left[i - k];
            int e = right[i + k];
            if(ans[0] == -1 || window[ans[0]] + window[ans[1]] + window[ans[2]] < window[s] + window[i] + window[e]){
                ans[0] = s;
                ans[1] = i;
                ans[2] = e;
            }
        }
        return ans;
    }

    public static void print(int[] n){
        for(int i = 0; i < n.length; i++){
            System.out.print(n[i] + " ");
        }
        System.out.println();
    }
}
