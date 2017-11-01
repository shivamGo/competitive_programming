package graph.hackerrank.the_story_of_a_tree;

import java.util.*;

public class Solution {
    public static Vertex[] vertices;
    public static boolean[] isVisited;
    public static int[] levels;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-- > 0){
            int n = in.nextInt();
            int e = n -1;
            vertices = new Vertex[n + 1];
            isVisited = new boolean[n + 1];
            levels = new int[n + 1];
            for(int i = 1; i <= n; i++){
                vertices[i] = new Vertex();
            }

            for(int i = 0; i < e; i++){
                int u = in.nextInt();
                int v = in.nextInt();
                vertices[u].adj.add(v);
                vertices[v].adj.add(u);
            }

            int guess = in.nextInt();
            int minScore = in.nextInt();
            List<Edge> guesses = new ArrayList<>();
            for(int i = 0; i < guess; i++){
                guesses.add(new Edge(in.nextInt(), in.nextInt()));
            }

            int probility = 0;
            for(int u = 1; u <= n; u++){
                bfs(u);
                boolean allRight = true;
                for(Edge edge : guesses){
                    if(levels[edge.u] > levels[edge.v]){
                        allRight = false;
                        break;
                    }
                }
                if(allRight)
                    probility++;

                Arrays.fill(isVisited, false);
                Arrays.fill(levels, 0);
            }

            int hcf = gcd(probility, n);
            System.out.println((probility / hcf) + "/" + (n / hcf));
        }
    }

    public static int gcd(int a, int b){
        //int a = n < p ? n : p;
        //int b = a == n ? p : n;
        while(a != 0){
            int t = a;
            a = b % a;
            b = t;
        }
        return a + b;
    }

    public static void bfs(int s){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        isVisited[s] = true;
        levels[s] = 0;
        while (!queue.isEmpty()){
            int u = queue.remove();
            for(int v : vertices[u].adj){
                if(!isVisited[v]){
                    isVisited[v] = true;
                    levels[v] = levels[u] + 1;
                    queue.add(v);
                }
            }
        }
    }
}

class Vertex{
    List<Integer> adj = new LinkedList<>();
}

class Edge{
    int u, v;
    public Edge(int u, int v){
        this.u = u;
        this.v = v;
    }
}
