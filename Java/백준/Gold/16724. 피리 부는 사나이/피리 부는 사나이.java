import java.util.*;
import java.io.*;

class Main
{
    public static int N,M, cnt;
    public static int[][] map;
    public static int[][] visited;
    public static int[] dx = {-1,1,0,0};
    public static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                if(line.charAt(j) == 'U') map[i][j] = 0;
                else if(line.charAt(j) == 'D') map[i][j] = 1;
                else if(line.charAt(j) == 'L') map[i][j] = 2;
                else if(line.charAt(j) == 'R') map[i][j] = 3;
            }
        }

        cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(visited[i][j] != 0) continue;
                cnt++;
                int group = dfs(i,j);
                if(group < cnt) cnt--;
            }
        }
//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(visited[i]));
//        }
        System.out.println(cnt);
    }

    public static int dfs(int x, int y) {
        visited[x][y] = cnt;
        int dir = map[x][y];

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(nx < 0 || ny < 0 || nx >= N || ny >= M) return cnt;
        if(visited[nx][ny] != 0) return visited[x][y] = visited[nx][ny];

        visited[x][y] = dfs(nx, ny);
        return visited[x][y];
    }
}