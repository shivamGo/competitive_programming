package graph.hackerrank.dijkstra_algrithm;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
    private static int[][] graph;
    private static boolean[] inSet;
    private static int[] distance;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while(T-- > 0){
            int n = in.nextInt();
            int e = in.nextInt();
            graph = new int[n + 1][n + 1];
            distance = new int[n + 1];
            inSet = new boolean[n + 1];
            Arrays.fill(distance, -1);
            for(int i = 0; i < e; i++){
                int u = in.nextInt();
                int v = in.nextInt();
                int w = in.nextInt();
                if(graph[u][v] == 0){
                    graph[u][v] = w;
                    graph[v][u] = w;
                }else if(graph[u][v] > w){
                    graph[u][v] = w;
                    graph[v][u] = w;
                }
            }
            int s = in.nextInt();
            dijkstra(s);
            for(int i = 1; i <= n; i++){
                if(i != s){
                    System.out.print(distance[i] + " ");
                }
            }
            System.out.println();
        }
    }

    private static void dijkstra(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));
        distance[s] = 0;

        while (!pq.isEmpty()){
            Node node = pq.poll();
            int u = node.id;
            inSet[u] = true;
            for(int v = 1; v < graph[0].length; v++){
                if(graph[u][v] != 0){
                    if(!inSet[v]){
                        if(distance[v] == -1){
                            distance[v] = distance[u] + graph[u][v];
                            pq.offer(new Node(v, distance[v]));
                        }
                        if(distance[v] > distance[u] + graph[u][v]){
                            pq.remove(new Node(v, distance[v]));
                            distance[v] = distance[u] + graph[u][v];
                            pq.offer(new Node(v, distance[v]));
                        }
                    }
                }
            }
        }
    }
}

class Node implements Comparable<Node>{
    int id;
    int w;
    public Node(int id, int w){
        this.id = id;
        this.w = w;
    }



    public boolean equals(Object o){
        Node node = (Node)o;
        if(node.id == id && node.w == w){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int compareTo(Node node) {
        return this.w - node.w;
    }
}
