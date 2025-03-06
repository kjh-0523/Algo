import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int R, C, ans;
    public static char[][] map;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();
        }
        ans = 0;
        dfs(0, 0, 0, 0);
        System.out.println(ans == 0 ? 1 : ans);
    }

    public static void dfs(int x, int y, int visited, int cnt) {
        if ((visited & (1 << map[x][y] - 'A')) != 0) {
            ans = Math.max(ans, cnt);
            return;
        }
        visited |= 1 << map[x][y] - 'A';

        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (nx < 0 || nx > R - 1 || ny < 0 || ny > C - 1) {
                continue;
            }
            dfs(nx, ny, visited, cnt + 1);
        }
    }
}