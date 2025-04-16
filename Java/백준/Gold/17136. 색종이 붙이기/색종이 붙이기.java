import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static int ans;
    public static int[][] map = new int[10][10];
    public static List<int[]> target;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        target = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    target.add(new int[]{i, j});
                }
            }
        }

        ans = Integer.MAX_VALUE;
        dfs(0, 0, new int[6], new boolean[10][10]);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

    }

    public static void dfs(int cnt, int idx, int[] paper, boolean[][] visited) {
        if (cnt > ans) {
            return;
        }
        if (idx == target.size()) {
            ans = Math.min(ans, cnt);
            return;
        }

        int x = target.get(idx)[0];
        int y = target.get(idx)[1];
        if (visited[x][y]) {
            dfs(cnt, idx + 1, paper, visited);
        }
        boolean[][] temp = new boolean[10][10];
        for (int i = 0; i < 10; i++) {
            System.arraycopy(visited[i], 0, temp[i], 0, 10);
        }

        for (int i = 5; i > 0; i--) {
            boolean flag = true;
            top:
            for (int nx = x; nx < x + i; nx++) {
                for (int ny = y; ny < y + i; ny++) {
                    if (nx < 0 || nx > 9 || ny < 0 || ny > 9 || map[nx][ny] == 0 || visited[nx][ny]) {
                        flag = false;
                        break top;
                    }
                    visited[nx][ny] = true;
                }
            }
            if (flag) {
                paper[i]++;
                if (paper[i] < 6) {
                    dfs(cnt + 1, idx + 1, paper, visited);
                }
                paper[i]--;
            }
            for (int j = 0; j < 10; j++) {
                System.arraycopy(temp[j], 0, visited[j], 0, 10);
            }
        }
    }
}
