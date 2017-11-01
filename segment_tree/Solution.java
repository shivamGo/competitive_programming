package segment_tree;

import java.util.Scanner;

public class Solution {
    public static void main(String[]args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        SegmentTree segmentTree = new SegmentTree(arr, n);
        segmentTree.update(arr, in.nextInt(), in.nextInt());
        System.out.println(segmentTree.getSum(1, 3, n));
        segmentTree.print();
    }
}

class SegmentTree{
    public int[] st;
    public int[] original;
    public int size = 0;
    public SegmentTree(int[] arr, int n){
        this.original = arr;
        int p = (int)Math.ceil(Math.log(n) / Math.log(2));
        size = 2 * ((int)(Math.pow(2, p))) - 1;
        st = new int[size];
        init(arr, n);
    }

    private void init(int[] arr, int n) {
        init(arr, 0, n - 1, 0);
    }

    private int init(int[] arr, int ss, int se, int si){
        if(ss == se){
            st[si] = arr[ss];
            return st[si];
        }

        int mid = mid(ss, se);
        st[si] = init(arr, ss, mid, 2 * si + 1) +
                 init(arr, mid + 1, se, 2 * si + 2);
        return st[si];
    }

    private int mid(int s, int e){
        return s + (e - s) / 2;
    }

    public void print(){
        for(int i = 0; i < original.length; i++){
            System.out.print(original[i] + " ");
        }
    }

    public int getSum(int l, int r, int n){
        return getSum(l, r, 0, n - 1, 0);
    }

    public int getSum(int l, int r, int ss, int se, int si){
        if(ss >= l && se <= r){
            return st[si];
        }

        if(se < l || ss > r){
            return 0;
        }

        int mid = mid(ss, se);
        return getSum(l, r, ss, mid, 2 * si + 1) +
                getSum(l, r, mid + 1, se, 2 * si + 2);
    }

    public void update(int[] arr ,int oldValueIndex, int newValue){
        if(oldValueIndex < 0 || oldValueIndex >= arr.length)
            return;

        int difference = newValue - arr[oldValueIndex];
        arr[oldValueIndex] = newValue;
        update(0, arr.length - 1,0 , oldValueIndex, difference);
    }

    public void update(int ss, int se, int si, int I, int difference){
        if(I < ss || I > se)
            return;

        st[si] += difference;
        if(se != ss) {
            int mid = mid(ss, se);
            update(ss, mid, 2 * si + 1, I, difference);
            update(mid + 1, se, 2 * si + 2, I, difference);
        }
    }
}