import java.util.*;
import java.io.*;

public class Main {

    public static int N,M;
    public static int[][] map;
    public static int[][] visited;
    public static ArrayList<int[]> shark;
    public static int[] dx = {-1,-1,-1,0,1,1,1,0};
    public static int[] dy = {-1,0,1,1,1,0,-1,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];
        for(int i = 0; i < N; i++){
            Arrays.fill(visited[i], 3000);
        }
        shark = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    shark.add(new int[]{i,j});
                }
            }
        }
        int ans = 0;
        for(int[] s : shark){
            visited[s[0]][s[1]] = 0;
            bfs(s);
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                ans = Math.max(ans, visited[i][j]);
            }
        }
        System.out.println(ans);
    }

    public static void bfs(int[] start){
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        while(!q.isEmpty()){
            int[] cur = q.poll();

            int nx,ny;
            for(int i = 0; i < 8; i++){
                nx = cur[0] + dx[i];
                ny = cur[1] + dy[i];
                if(nx < 0 || nx > N-1 || ny < 0 || ny > M-1 || map[nx][ny] == 1) continue;
                if(visited[nx][ny] <= visited[cur[0]][cur[1]] + 1) continue;
                visited[nx][ny] = visited[cur[0]][cur[1]] + 1;
                q.offer(new int[] {nx,ny});

            }
        }
    }
}