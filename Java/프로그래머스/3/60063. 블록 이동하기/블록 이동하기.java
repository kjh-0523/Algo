import java.util.*;

class Solution {
    public static int N;
    public static int[][] board;
    public static int[] dx = {0,1,0,-1};
    public static int[] dy = {1,0,-1,0};
    
    public int solution(int[][] b) {
        N = b.length;
        board = b;
        int answer = bfs();
        return answer;
    }
    
    public static int bfs(){
        Queue<int[]> q = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][N][N][N];
        q.offer(new int[]{0,0,0,1,0});
        visited[0][0][0][1] = true;
        
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x1 = cur[0], y1 = cur[1], x2 = cur[2], y2 = cur[3], cnt = cur[4];
            
            if((x1==N-1 && y1==N-1) || (x2==N-1 && y2==N-1)) return cnt;
            
            for(int i = 0; i < 4; i++) {
                int nx1 = x1 + dx[i];
                int ny1 = y1 + dy[i];
                int nx2 = x2 + dx[i];
                int ny2 = y2 + dy[i];
                
                if(inRange(nx1,ny1,N) && inRange(nx2,ny2,N)
                  && board[nx1][ny1]==0 && board[nx2][ny2]==0
                  && !visited[nx1][ny1][nx2][ny2]) {
                    visited[nx1][ny1][nx2][ny2]=true;
                    visited[nx2][ny2][nx1][ny1]=true;
                    q.offer(new int[]{nx1,ny1,nx2,ny2,cnt+1});
                }
            }
            
            // 회전
            if(x1==x2) { // 가로
                for(int d = -1; d < 2; d+=2) {
                    if(inRange(x1+d,y1,N) && inRange(x2+d,y2,N)
                      && board[x1+d][y1]==0 && board[x2+d][y2]==0) {
                        if(!visited[x1][y1][x1+d][y1]) {
                            visited[x1][y1][x1+d][y1]=true;
                            visited[x1+d][y1][x1][y1]=true;
                            q.offer(new int[]{x1,y1,x1+d,y1,cnt+1});
                        }
                        if(!visited[x2][y2][x2+d][y2]) {
                            visited[x2][y2][x2+d][y2]=true;
                            visited[x2+d][y2][x2][y2]=true;
                            q.offer(new int[]{x2,y2,x2+d,y2,cnt+1});
                        }
                    }
                }
            } else { // 세로
                for(int d = -1; d < 2; d+=2) {
                    if(inRange(x1,y1+d,N) && inRange(x2,y2+d,N)
                      && board[x1][y1+d]==0 && board[x2][y2+d]==0) {
                        if(!visited[x1][y1][x1][y1+d]) {
                            visited[x1][y1][x1][y1+d]=true;
                            visited[x1][y1+d][x1][y1]=true;
                            q.offer(new int[]{x1,y1,x1,y1+d,cnt+1});
                        }
                        if(!visited[x2][y2][x2][y2+d]) {
                            visited[x2][y2][x2][y2+d]=true;
                            visited[x2][y2+d][x2][y2]=true;
                            q.offer(new int[]{x2,y2,x2,y2+d,cnt+1});
                        }
                    }
                }
            }
        }
        return -1;
    }
    
    public static boolean inRange(int x, int y, int N) {
        return x>-1 && y>-1 && x<N && y<N;
    }
}