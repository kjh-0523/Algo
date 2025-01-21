import java.util.*;
import java.io.*;

public class Main {
    public static int N,M,x,y;
    public static int[][] map;
    public static int[][] visit;
    public static int[] dx = {-1,1,0,0};
    public static int[] dy = {0,0,1,-1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new int[N][M];
        map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    x = i;
                    y = j;
                }
            }
        }
        bfs();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 1 && visit[i][j] == 0){
                    sb.append(-1+" ");
                }
                else sb.append(visit[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x,y});

        while (!q.isEmpty()){
            int[] cur = q.poll();
            int nx,ny;
            for(int i = 0; i < 4; i++){
                nx =cur[0] + dx[i];
                ny = cur[1] + dy[i];
                if(nx < 0 || nx > N-1 || ny < 0 || ny > M-1 || map[nx][ny] != 1 || visit[nx][ny] != 0) continue;
                q.offer(new int[] {nx,ny});
                visit[nx][ny] = visit[cur[0]][cur[1]] + 1;
            }
        }
    }
}
