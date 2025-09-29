import java.util.*;

class Solution {
    public static int[] dx = {-1,1,0,0};
    public static int[] dy = {0,0,-1,1};
    static int answer = Integer.MAX_VALUE;

    public int solution(int[][] board, int r, int c) {
        Map<Integer, List<int[]>> cards = new HashMap<>();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(board[i][j] == 0) continue;
                int num = board[i][j];
                cards.putIfAbsent(num, new ArrayList<>());
                cards.get(num).add(new int[]{i, j});
            }
        }

        List<Integer> nums = new ArrayList<>(cards.keySet());
        boolean[] used = new boolean[nums.size()];

        dfs(board, r, c, 0, nums, used, cards);

        return answer;
    }

    static void dfs(int[][] board, int r, int c, int count,
                    List<Integer> nums, boolean[] used,
                    Map<Integer, List<int[]>> cards) {
        boolean done = true;
        for(boolean b : used) if(!b) done = false;
        if(done) {
            answer = Math.min(answer, count);
            return;
        }

        for(int i = 0; i < nums.size(); i++){
            if(used[i]) continue;

            int num = nums.get(i);
            List<int[]> pos = cards.get(num);
            int[] card1 = pos.get(0);
            int[] card2 = pos.get(1);

            int d1 = bfs(board, r, c, card1[0], card1[1])
                   + bfs(board, card1[0], card1[1], card2[0], card2[1])
                   + 2;

            int d2 = bfs(board, r, c, card2[0], card2[1])
                   + bfs(board, card2[0], card2[1], card1[0], card1[1])
                   + 2;

            int[][] newBoard = copyBoard(board);

            newBoard[card1[0]][card1[1]] = 0;
            newBoard[card2[0]][card2[1]] = 0;

            used[i] = true;

            dfs(newBoard, card2[0], card2[1], count + d1, nums, used, cards);
            dfs(newBoard, card1[0], card1[1], count + d2, nums, used, cards);

            used[i] = false;
        }
    }

    static int bfs(int[][] board, int sx, int sy, int tx, int ty) {
        boolean[][] visited = new boolean[4][4];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], cnt = cur[2];

            if (x == tx && y == ty) return cnt;

            // 방향키 이동
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, cnt + 1});
                }
            }

            // Ctrl 이동
            for (int d = 0; d < 4; d++) {
                int nx = x, ny = y;
                while (true) {
                    int tx2 = nx + dx[d];
                    int ty2 = ny + dy[d];
                    if (!(0 <= tx2 && tx2 < 4 && 0 <= ty2 && ty2 < 4)) break;
                    nx = tx2;
                    ny = ty2;
                    if (board[nx][ny] != 0) break;
                }
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, cnt + 1});
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    static int[][] copyBoard(int[][] board){
        int[][] newBoard = new int[4][4];
        for(int i = 0; i < 4; i++){
            newBoard[i] = board[i].clone();
        }
        return newBoard;
    }
}
