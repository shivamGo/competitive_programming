package bit_manipulation.AND_Product_HACK.optimal;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int test = in.nextInt();
        while(test-- > 0){
            long a = in.nextLong();
            long b = in.nextLong();

            long ans = 0;
            for(int i = 0; i < 32; i++){
                long k = (a + (1L << i)) >> i;
                long p = (k << i) - 1;
                System.out.println((i + 1) + " k " + k + " p " + p + " k bin: " + Long.toString(k, 2) + " p bin: " + Long.toString(p, 2));
                if(p >= b){
                    if((k & 1) == 0)
                        ans |= 1L << i;
                }
            }
            System.out.println(ans);
        }
    }
}
