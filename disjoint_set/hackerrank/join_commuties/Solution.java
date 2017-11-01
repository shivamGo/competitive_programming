package disjoint_set.hackerrank.join_commuties;

import org.omg.PortableInterceptor.INACTIVE;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(in.nextLine());
        DisjointSet disjointSet = new DisjointSet();
        int M = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= M; i++){
            disjointSet.makeSet(i);
        }

        int q = Integer.parseInt(st.nextToken());
        while(q-- > 0){
            st = new StringTokenizer(in.nextLine());
            String type = st.nextToken();
            if(type.equals("Q")){
                int c = Integer.parseInt(st.nextToken());
                Node n = disjointSet.findSet(c);
                System.out.println(n.members);
            }else{
                int  I = Integer.parseInt(st.nextToken());
                int J = Integer.parseInt(st.nextToken());
                disjointSet.union(I, J);
            }
        }
    }


}

class DisjointSet{
    Map<Integer, Node> map = new HashMap<>();
    public void makeSet(int id){
        Node n = new Node(id, 0);
        n.members = 1;
        map.put(id, n);
    }

    public Node findSet(int id){
        return findSet(map.get(id));
    }

    public Node findSet(Node n){
        Node parent = n.parent;
        if(n == parent){
            return parent;
        }

        n.parent = findSet(parent);
        return n.parent;
    }

    public void union(int n1, int n2){
        Node p1 = findSet(map.get(n1));
        Node p2 = findSet(map.get(n2));
        if(p1 == p2)
            return;

        if(p1.rank >= p2.rank){
            p1.rank = p1.rank == p2.rank ? p1.rank + 1 : p1.rank;
            p2.parent = p1;
            p1.members += p2.members;
        }else{
            p2.members += p1.members;
            p1.parent = p2;
        }
    }
}

class Node{
    int  id;
    int  members;
    Node parent;
    int rank;
    public Node(int id, int rank){
        this.id = id;
        this.parent = this;
        this.rank = 0;
    }
}




