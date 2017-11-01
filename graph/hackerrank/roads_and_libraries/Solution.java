package graph.hackerrank.roads_and_libraries;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.*;

public class Solution{
    static Vertex[] graph;
    static boolean isVisited[];
    public static void main(String[]args){
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-- > 0){
            int V = in.nextInt();
            int E = in.nextInt();
            long libCost = in.nextLong();
            long roadCost = in.nextLong();
            graph = new Vertex[V + 1];
            isVisited = new boolean[V + 1];
            for(int i = 1; i <= V; i++){
                graph[i] = new Vertex();
            }

            for(int i = 1; i <= E; i++){
                int u = in.nextInt();
                int v = in.nextInt();
                graph[u].adj.add(v);
                graph[v].adj.add(u);
            }
            int necessaryLibCount = 0;
            for(int i = 1; i <= V; i++){
                if(!isVisited[i]){
                    necessaryLibCount++;
                    dfs(i);
                }
            }
            System.out.println(necessaryLibCount * libCost + (V - necessaryLibCount) * Math.min(libCost, roadCost));
        }
    }

    private static void dfs(int s){
        isVisited[s] = true;
        for(int v : graph[s].adj){
            if(!isVisited[v]){
                dfs(v);
            }
        }
    }
}

class Vertex{
    public List<Integer> adj = new LinkedList<>();
}