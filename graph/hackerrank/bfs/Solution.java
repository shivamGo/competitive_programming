package graph.hackerrank.bfs;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static Vertex[] vertices;
    static boolean[] isVisited;
    static int[] distance;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        while(q-- > 0){
            int n = in.nextInt();
            int e = in.nextInt();
            vertices = new Vertex[n + 1];
            isVisited = new boolean[n + 1];
            distance = new int[n + 1];
            for(int i = 1; i <= n; i++){
                vertices[i] = new Vertex();
            }

            for(int i = 0; i < e; i++){
                int u = in.nextInt();
                int v = in.nextInt();
                vertices[u].adj.add(v);
                vertices[v].adj.add(u);
            }
            int s = in.nextInt();
            Arrays.fill(distance, -1);
            bfs(s);
            for(int i = 1; i <= n; i++){
                if(i != s){
                    System.out.print(distance[i] + " ");
                }
            }
            System.out.println();
        }

        in.close();
    }

    private static void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        isVisited[s] = true;
        distance[s] = 0;
        while (!queue.isEmpty()){
            int u = queue.remove();
            for(int v : vertices[u].adj){
                if(!isVisited[v]){
                    isVisited[v] = true;
                    distance[v] = distance[u] + 6;
                    queue.offer(v);
                }
            }
        }
    }
}

class Vertex{
    List<Integer> adj = new ArrayList<>();
}
