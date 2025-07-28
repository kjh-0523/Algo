import java.util.*;

class Solution {
    public static int N, ans;
    public static final int priceCorner = 500;
    public static final int priceStraight = 100;
    public static int[][] map;
    public static int[][][] visited;
    public static int[] dx = {0,1,0,-1};
    public static int[] dy = {1,0,-1,0};

    public static class State {
        int x, y, price, dir;
        public State(int x, int y, int price, int dir){
            this.x = x;
            this.y = y;
            this.price = price;
            this.dir = dir;
        }
    }

    public int solution(int[][] board) {
        N = board.length;
        map = board;
        visited = new int[N][N][4];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                Arrays.fill(visited[i][j], (int) 1e7);
            }
        }

        Queue<State> q = new LinkedList<>();

        q.offer(new State(0, 0, 0, -1));
        ans = (int) 1e7;
        while (!q.isEmpty()) {
            State cur = q.poll();

            if (cur.x == N - 1 && cur.y == N - 1) {
                ans = Math.min(ans, cur.price);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1) continue;

                int nextPrice = cur.price;
                if (cur.dir == -1 || cur.dir == i) {
                    nextPrice += priceStraight;
                } else {
                    nextPrice += priceCorner + priceStraight;
                }

                if (visited[nx][ny][i] > nextPrice) {
                    visited[nx][ny][i] = nextPrice;
                    q.offer(new State(nx, ny, nextPrice, i));
                }
            }
        }

        return ans;
    }
}
