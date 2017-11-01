package dynamic_problems.lego_blocks;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        int M_max = 1000;
        long MOD = 1000000007;
        long[] allWaysRow = new long[M_max+1];

        allWaysRow[0] = 1;
        allWaysRow[1] = 1; // 1 way to create 1x1 wall
        allWaysRow[2] = 2; // 2 ways to create 1x2 wall
        allWaysRow[3] = 4; // 4 ways to create 1x3 wall
        allWaysRow[4] = 8; // 8 ways to create 1x4 wall

        for(int i = 5; i <= M_max; i++){
            allWaysRow[i] = (allWaysRow[i-1] + allWaysRow[i-2] + allWaysRow[i-3] + allWaysRow[i-4]) % MOD;
        }

        Scanner sc = new Scanner(System.in);
        // N->height, M-> width
        int T = sc.nextInt(), N, M;
        for(int i=0; i<T; i++){
            N = sc.nextInt();
            M = sc.nextInt();

            long[] allWays = new long[M_max+1];
            for(int j=0; j<=M; j++){
                allWays[j] = modPower(allWaysRow[j], N, MOD);
            }

            long[] goodWays = new long[M+1];
            goodWays[0] = 0;
            goodWays[1] = 1;
            for(int j=2; j<=M; j++){
                goodWays[j] = allWays[j];
                long temp = 0;
                for(int k=1; k<j; k++){
                    temp = (temp + (goodWays[k]*allWays[j-k])%MOD )%MOD;
                }
                goodWays[j] = (MOD+goodWays[j]-temp)%MOD;
            }

            System.out.println(goodWays[M]);
        }
    }

    static long modPower(long n, int P, long mod){
        long retVal = 1;
        for(int i=0; i<P; i++){
            retVal = (retVal*n)%mod;
        }
        return retVal;
    }
}