package dynamic_problems.power_set.string;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = Integer.parseInt(in.nextLine());
        while(t-- > 0){
            int n = Integer.parseInt(in.nextLine());
            char[] pat = in.nextLine().toCharArray();
            List<String> sets = new ArrayList<>();
            solve(pat, sets);
            Collections.sort(sets);
            for(String p : sets){
                System.out.print(p + " ");
            }
            System.out.println();
        }
    }

    public static void solve(char[] chs, List<String> treeSet){
        int max = 1 << chs.length;
        for(int i = 1; i < max; i++){
            String e = setElement(chs, i);
            treeSet.add(e);
        }
    }

    private static String setElement(char[] chs, int i) {
        String p = "";
        int idx = 0;
        for(int k = i; k > 0; k = k >> 1){
            if((k & 1) == 1){
                p += chs[idx];
            }
            idx++;
        }
        return p;
    }
}
