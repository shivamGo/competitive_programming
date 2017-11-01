package bit_manipulation.xor_sequence.optimal_solution;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-- > 0){
            int bitbudget = in.nextInt();
            byte[] A = in.nextLine().getBytes();
            byte[] B = in.nextLine().getBytes();
            byte[] C = in.nextLine().getBytes();

            reverse(A); format(A);
            reverse(B); format(B);
            reverse(C); format(C);

            int maxLen = Math.max(Math.max(A.length, B.length), C.length);
            A = pad(A, maxLen);
            B = pad(B, maxLen);
            C = pad(C, maxLen);

            for(int i = 0; i < maxLen; i++){
                for(byte j = 1; j < 16; j += j){
                    if((C[i] & j) == 0){
                        if((A[i] & j) != 0){
                            A[i] = (byte)(A[i] ^ j);
                            bitbudget--;
                        }

                        if((B[i] & j) != 0){
                            B[i] = (byte) (B[i] ^ j);
                            bitbudget--;
                        }
                    }else{
                        if(((A[i] | B[i]) & j) == 0){
                            B[i] = (byte) (B[i] ^ j);
                            bitbudget--;
                        }
                    }
                }
            }

            //maximize B`
            for(int i = maxLen - 1; i >= 0; i--){
              for(byte j = 8; j > 0; j /= 2){
                if((A[i] & B[i] & j) != 0){
                    if(bitbudget == 0)
                        break;

                    A[i] = (byte)(A[i] ^ j);
                    bitbudget--;
                }

                if(((A[i] & j) == j) && (B[i] & j) == 0){
                    if(bitbudget > 1){
                        bitbudget -= 2;
                        A[i] = (byte)(A[i] ^ j);
                        B[i] = (byte)(B[i] ^ j);
                    }
                }
              }
              if(bitbudget == 0){
                  break;
              }
            }

            if(bitbudget < 0){
                System.out.println("-1");
            }else{

                reverse(A); reverse(B);
                A = trim(A); B = trim(B);
                unformat(A); unformat(B);
                System.out.println(new String(A));
                System.out.println(new String(B));
            }
        }
    }

    static byte[] trim(byte[] bytes){
        int dead =0;
        for(int i=0; i<bytes.length;i++){
            if(0 == bytes[i]){
                dead++;
            }else{
                break;
            }
        }
        if(dead==bytes.length){dead--;}
        byte[] bytes2 = new byte[bytes.length-dead];
        for(int i=dead; i<bytes.length;i++){
            bytes2[i-dead] = bytes[i];
        }
        return bytes2;
    }

    static byte[] pad(byte[] bytes, int length){
        byte bytes2[] = new byte[length];
        for(int i = 0; i<bytes.length;i++){
            bytes2[i] = bytes[i];
        }
        for(int i = bytes.length; i<length;i++){
            bytes2[i] = 0;
        }
        return bytes2;
    }

    static void format(byte[] b){
        for(int i = 0; i<b.length;i++){
            b[i] -= 48;
            if(10<b[i]){b[i]-=7;}
        }
    }

    static void unformat(byte[] b){
        for(int i = 0; i<b.length;i++){
            if(9<b[i]){b[i]+=7;}
            b[i] += 48;
        }
    }

    static void reverse(byte[] ch){
        int len = ch.length;
        for(int i = 0; i<(len/2);i++){
            byte tmp = ch[i];
            ch[i] = ch[len-1-i];
            ch[len-1-i] = tmp;
        }
    }
}
