package greedy_algorithms;

import java.util.*;

public class Solution {
    static Map<Character, String> codeMap = new HashMap<>();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<Character, Integer> map = new HashMap<>();

        int t = Integer.parseInt(in.nextLine());
        while(t-- > 0){
            String p = in.nextLine();
            StringTokenizer st = new StringTokenizer(in.nextLine());
            int i = 0;
            while (st.hasMoreTokens()){
                int val = Integer.parseInt(st.nextToken());
                map.put(p.charAt(i++), val);
            }

            List<String> list = new Solution().huffmanEcoding(map);
            for(char key : codeMap.keySet()){
                System.out.println(key + " " + codeMap.get(key));
            }
            System.out.println();

            Node root = new Solution().huffmanEcodingAux(map);
            String s = in.nextLine();
            new Solution().decode(s, root);
            System.out.println();
        }
    }

    public List<String> huffmanEcoding(Map<Character, Integer> freTable){
        Node root = huffmanEcodingAux(freTable);
        List<String> codeList = new ArrayList<>();
        preorder(root, codeList, "");
        return codeList;
    }

    public void preorder(Node root, List<String> codeList, String code){
        if(root != null){
            if(root.id != '$'){
                codeList.add(code);
                codeMap.put(root.id, code);
                //return //optimal
            }
            preorder(root.left, codeList, code + "0");
            preorder(root.right, codeList, code + "1");
        }
    }

    public  Node huffmanEcodingAux(Map<Character, Integer> freTable){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        initPriorityQueue(pq, freTable);
        while (pq.size() > 1){
            Node a = pq.poll();
            Node b = pq.poll();
            Node hmn = new Node('$', a.f + b.f);
            hmn.left = a;
            hmn.right = b;
            pq.offer(hmn);
        }
        return pq.poll();
    }

    public void decode(String pat ,Node root)
    {
        StringBuilder stringBuilder = new StringBuilder();
        Node t = root;
        for(int i = 0; i < pat.length(); i++){
            t = pat.charAt(i) == '0' ? t.left :t.right;
            if(t.left == null && t.right == null){
                stringBuilder.append(t.id);
                t = root;
            }
        }
        System.out.println(stringBuilder.toString());
    }

    public void initPriorityQueue(PriorityQueue<Node> pq, Map<Character, Integer> freTable){
        for(char key : freTable.keySet()){
            pq.offer(new Node(key, freTable.get(key)));
        }
    }
}

class Node implements Comparable<Node>{
    public char id;
    public Node left;
    public Node right;
    public int f;
    public Node(){
    }

    public Node(char id, int f){
        this.id = id;
        this.f = f;
    }

    @Override
    public int compareTo(Node node) {
        return this.f - node.f;
    }
}
