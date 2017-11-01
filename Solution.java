
import java.util.*;
import java.io.*;

class Heap
{
    private int size;
    private int currentIndex;
    private int[] num;

    public Heap(int size){
        this.size = size;
        num = new int[size];
        currentIndex = 0;
    }

    public void insert(int val){
        if(currentIndex >= this.size){
            System.out.println("Sorry Heap full!!");
        }else{
            num[currentIndex++] = val;
        }
    }

    public void buildMinHeap(){
        for(int i = this.size / 2 - 1; i >= 0; i--){
            minHeapify(this.size, i);
        }
    }

    public void minHeapify(int n, int i){
        int l = i * 2 + 1;
        int r = i * 2 + 2;
        int min;
        if(l < n && num[i] > num[l])
            min = l;
        else
            min = i;

        if(r < n && num[r] < num[min])
            min = r;

        if(min  != i){
            swap(i, min);
            minHeapify(n, min);
        }
    }

    public int extractMin(){
        int val = this.num[0];
        this.num[0] = this.num[this.size - 1];
        this.size--;
        minHeapify(this.size, 0);
        return val;
    }

    public void swap(int x, int y){
        int temp = num[x];
        num[x] = num[y];
        num[y] = temp;
    }

    public void print(){
        for(int i = 0; i < num.length; i++){
            System.out.print(num[i] + " ");
        }
        System.out.println();
    }
}

public class Solution{

    public static void main(String[]ar){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Heap heap = new Heap(n);

        for(int i = 0; i < n; i++){
            heap.insert(scanner.nextInt());
        }
        heap.buildMinHeap();
        heap.print();
    }
}