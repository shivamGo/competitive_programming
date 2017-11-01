package bit_manipulation.AND_Product_HACK.diff;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-- > 0){
            long A = in.nextLong();
            long B = in.nextLong();

            long bitDiff = A ^ B;

            for(int i = 1; i <= 32; i = i * 2){
                bitDiff |= (bitDiff >> i);
                System.out.println(Long.toString(bitDiff, 2));
            }
            System.out.println(A & (~bitDiff));
        }
    }
}
