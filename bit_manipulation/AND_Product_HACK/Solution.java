package bit_manipulation.AND_Product_HACK;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);
        int t = in.nextInt();
        while(t-- > 0){
            long a = in.nextLong();
            long b = in.nextLong();

            long res = 0L;
            boolean isAllOne = true;
            for(long i = 0; i < 64; i++){
                isAllOne = true;
                for(long n = a; n <= b; n++){
                    if((n & (1 << i)) == 0){
                        isAllOne = false;
                        break;
                    }
                }
                if(isAllOne)
                    res |= i;
            }
            System.out.println(res);
        }
    }
}
