package search;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        int e = 0, o = 0;
        for(int i = 0; i < arr.length; i++){
            if((arr[i] & 1) > 0){
                o++;
            }else{
                e++;
            }
        }

        int mod = 1000000007;
        if(o > 0){
            System.out.println((pow(2, n - 1, mod) - 1 + mod) % mod);
        }else{
            System.out.println((pow(2, n, mod) - 1 + mod) % mod);
        }
    }


    //mod power
    public static long pow(long a, long n, long mod)
    {
        long ret = 1;
        int x = 63-Long.numberOfLeadingZeros(n);
        for( ;x >= 0 ;x--){
            ret = ret * ret % mod;
            if(n << 63 - x < 0)
                ret = ret * a % mod;
        }
        return ret;
    }
}