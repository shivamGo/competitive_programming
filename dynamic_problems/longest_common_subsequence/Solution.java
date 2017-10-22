package dynamic_problems.longest_common_subsequence;

import java.util.*;

class Solution{
    public static void main(String[] args){

        Scanner kb = new Scanner(System.in);
        String first = kb.nextLine();
        String sec = kb.nextLine();
        int res = lcs2(first, sec);
        System.out.println(res);
    }

    public static int lcs2(String first, String second){
        int n = first.length();
        int m = second.length();
        int[][] matrix = new int[n + 1][m + 1];
        for(int i = 0; i < matrix.length; i++){
            matrix[i][0] = 0;
        }

        for(int j = 0; j < matrix[0].length; j++){
            matrix[0][j] = 0;
        }

        int lcsLen = 0;
        for(int i = 1; i < matrix.length; i++){
            for(int j = 1; j < matrix[0].length; j++){
                if(first.charAt(i - 1) == second.charAt(j - 1)){
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                }else{
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }
        return matrix[matrix.length - 1][matrix[0].length - 1];
    }
}