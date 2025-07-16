import java.util.*;
import java.io.*;

public class Main {
    public static int N, M, ans;
    public static char[][] map;
    public static int[][][] visited;
    public static ArrayList<int[]> razer;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[M][N];
        razer = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'C'){
                    razer.add(new int[]{i, j});
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        visited = new int[M][N][4];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(visited[i][j], 10001);
            }
        }

        for (int i = 0; i < 4; i++) {
            int nx = razer.get(0)[0] + dx[i];
            int ny = razer.get(0)[1] + dy[i];
            if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
            if(map[nx][ny] == '*') continue;
            visited[nx][ny][i] = 0;
            q.offer(new int[]{nx, ny, i, 0});
        }

        ans = 10001;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], dir = cur[2], cnt = cur[3];

            if(x == razer.get(1)[0] && y == razer.get(1)[1]){
                ans = Math.min(ans, cnt);
            }

            for(int i = 0; i < 4; i++){
                if(i == (dir + 2) % 4) continue;
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if(map[nx][ny] == '*') continue;

                int nextCnt = (i == dir) ? cnt : cnt + 1;
                if(visited[nx][ny][i] > nextCnt){
                    visited[nx][ny][i] = nextCnt;
                    q.offer(new int[]{nx, ny, i, nextCnt});
                }
            }
        }

        System.out.println(ans);
    }
}
