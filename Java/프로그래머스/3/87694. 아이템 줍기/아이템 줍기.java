import java.util.*;

class Solution {
    
    public static int[] dx = {-1,0,1,0};
    public static int[] dy = {0,-1,0,1};
    public static int[][] map;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[101][101];
        for(int[] rec : rectangle){
            for(int i = rec[0] * 2; i <= rec[2] * 2; i++){
                for(int j = rec[1] * 2; j <= rec[3] * 2; j++){
                    if(i == rec[0] * 2|| i == rec[2] * 2|| j == rec[1] * 2|| j == rec[3]* 2){
                        if(map[i][j] == 2){
                            continue;
                        }
                        map[i][j] = 1;
                    }else {
                        map[i][j] = 2;                    
                    }
                }
            }
        }
        int answer = bfs(characterX* 2, characterY* 2, itemX* 2, itemY* 2);
        return answer;
    }
    
    public static int bfs(int characterX, int characterY, int itemX, int itemY){
        Queue<int[]> q = new LinkedList<>();
        int[][] visited = new int[101][101];
        int nx,ny;
        visited[characterX][characterY] = 1;
        for(int i = 0; i < 4; i++){
            nx = characterX + dx[i];
            ny = characterY + dy[i];
            if(nx < 0 || nx > 100 || ny < 0 || ny > 100 || map[nx][ny] != 1){
                    continue;
                }
            q.offer(new int[] {nx,ny,i});
            visited[nx][ny] = 1;
        }
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0] == itemX && cur[1] == itemY){
                break;
            }
            
            int nnx,nny, dir;
            for(int i = 0; i < 4; i++){
                dir = (cur[2] + i)%4;
                nnx = cur[0] + dx[dir];
                nny = cur[1] + dy[dir];
                if(nnx < 0 || nnx > 100 || nny < 0 || nny > 100){
                    continue;
                }
                if(map[nnx][nny] == 1 && visited[nnx][nny] == 0){
                    visited[nnx][nny] = visited[cur[0]][cur[1]] + 1;
                    q.offer(new int[] {nnx,nny, dir});
                    break;
                }
            }
        }
        return visited[itemX][itemY]/2;
    }
}