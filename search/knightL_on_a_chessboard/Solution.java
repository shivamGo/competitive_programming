package search.knightL_on_a_chessboard;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class Solution {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[][] output = new int[n][n];
        for(int i = 1; i < n; i++){
            for(int j = 1; j < n; j++){
                int fToIntm = bfs(0, 0, n - 1, n - 1, n);
            }
        }

    }


    private static final int[] rows = {-1,-2,-2,-1,1,2,2,1};
    private static final int[] cols = {-2,-1,1,2,2,1,-1,-2};

    private static int bfs(int sr, int sc, int dr, int dc, int n){
        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(sr, sc, 0));
        int dis = 0;
        boolean[][] isVisited = new boolean[n][n];
        isVisited[sr][sc] = true;

        while (!queue.isEmpty()){
            Cell cell = queue.remove();
            if(cell.r == dr && cell.c == dc){
                return cell.dist;
            }else{
                for(int i = 0; i < 8; i++){
                    int newRow = cell.r + rows[i];
                    int newCol = cell.c + cols[i];
                    if(isValid(isVisited, newRow, newCol, n)){
                        queue.offer(new Cell(newRow, newCol, cell.dist + 1));
                    }
                }
            }
        }
        return -1;
    }

    private static boolean isValid(boolean[][] isVisited, int newRow, int newCol, int n){
        if(newRow < 0 || newCol < 0 || newRow >= n || newCol >= n || isVisited[newRow][newCol]){
            return false;
        }else{
            return true;
        }
    }
}

class Cell{
    int r,  c;
    int dist;
    public Cell(int r, int c, int dist){
        this.r = r;
        this.c = c;
        this.dist = dist;
    }
}
