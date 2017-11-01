package graph.hackerrank.Journey_to_the_Moon;


import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception{
        DisjointSet disjointSet = new DisjointSet();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int groups = in.nextInt();
        for(int i = 0; i < n; i++){
            disjointSet.makeSet(i);
        }

        for(int i = 0; i < groups; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            disjointSet.union(u, v);
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            int countryCode = disjointSet.findSet(i);
            if(map.containsKey(countryCode)){
                map.put(countryCode, map.get(countryCode) + 1);
            }else{
                map.put(countryCode, 1);
            }
        }

        Integer[] list = new Integer[n];
        map.values().toArray(list);
        long combinations = 0L;
        for(int i = 0; i < list.length - 1; i++){
            for(int j = i + 1; j < list.length; j++){
                combinations += list[i] * list[j];
            }
        }
        System.out.println(combinations);
    }
}

class DisjointSet{
    public Map<Integer, Node> dset = new HashMap<>();
    public void makeSet(int id){
        Node n = new Node(id, 0);
        dset.put(id, n);
    }

    public int findSet(int id){
        return findSet(dset.get(id)).id;
    }


    public Node findSet(Node node){
        Node parent = node.parent;
        if(node == parent)
            return parent;

        node.parent = findSet(parent);
        return node.parent;
    }

    public void union(int id1, int id2){
        Node p1 = findSet(dset.get(id1));
        Node p2 = findSet(dset.get(id2));
        if(p1.id == p2.id){
            return;
        }
        if(p1.rank >= p2.rank){
            p1.rank = p1.rank == p2.rank ? p1.rank + 1 : p1.rank;
            p2.parent = p1;
        }else{
            p1.parent = p2;
        }
    }
}

class Node{
    int id;
    int rank;
    Node parent;
    public Node(int id, int rank){
        this.id = id;
        this.rank = rank;
        parent = this;
    }
}
