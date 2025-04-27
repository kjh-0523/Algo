import java.util.*;

class Solution {
    static class Point {
        int x, y, count;

        Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();
        char[][] map = new char[n][m];
        
        for (int i = 0; i < n; i++) {
            map[i] = board[i].toCharArray();
        }
        
        int startX = 0, startY = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'R') {
                    startX = i;
                    startY = j;
                }
            }
        }
        
        boolean[][] visited = new boolean[n][m];
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(startX, startY, 0));
        visited[startX][startY] = true;
        
        while (!q.isEmpty()) {
            Point cur = q.poll();
            
            if (map[cur.x][cur.y] == 'G') {
                return cur.count;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = cur.x;
                int ny = cur.y;
                
                while (true) {
                    int tx = nx + dx[i];
                    int ty = ny + dy[i];
                    
                    if (tx >= 0 && tx < n && ty >= 0 && ty < m && map[tx][ty] != 'D') {
                        nx = tx;
                        ny = ty;
                    } else {
                        break;
                    }
                }
                
                if (visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny, cur.count + 1));
            }
        }
        
        return -1;
    }
}
