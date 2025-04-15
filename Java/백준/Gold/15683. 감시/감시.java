import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static int N, M;
    public static int ans;
    public static int[][] map;
    public static List<int[]> cctvs;
    public static int[] dx = {0, -1, 0, 1};
    public static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cctvs = new ArrayList<>();

        map = new int[N][M];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    if (map[i][j] != 6) {
                        cctvs.add(new int[]{i, j});
                    }
                    cnt++;
                }
            }
        }
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, newMap[i], 0, M);
        }
        ans = 0;
        dfs(cnt, 0, newMap);
        System.out.println((N * M) - ans);
    }

    public static void dfs(int cnt, int cur, int[][] newMap) {
        if (cur == cctvs.size()) {
            ans = Math.max(ans, cnt);
//            System.out.println(ans);
            return;
        }

        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(newMap[i], 0, temp[i], 0, M);
        }
        int x = cctvs.get(cur)[0];
        int y = cctvs.get(cur)[1];
        int type = map[x][y];
        switch (type) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    int checkCnt = check(newMap, x, y, i);
                    dfs(cnt + checkCnt, cur + 1, newMap);
                    for (int j = 0; j < N; j++) {
                        System.arraycopy(temp[j], 0, newMap[j], 0, M);
                    }
                }
                break;
            case 2:
                for (int i = 0; i < 2; i++) {
                    int checkCnt = 0;
                    checkCnt += check(newMap, x, y, i);
                    checkCnt += check(newMap, x, y, i + 2);
                    dfs(cnt + checkCnt, cur + 1, newMap);
                    for (int j = 0; j < N; j++) {
                        System.arraycopy(temp[j], 0, newMap[j], 0, M);
                    }
                }
                break;
            case 3:
                for (int i = 0; i < 4; i++) {
                    int checkCnt = 0;
                    checkCnt += check(newMap, x, y, i);
                    checkCnt += check(newMap, x, y, (i + 1) % 4);
                    dfs(cnt + checkCnt, cur + 1, newMap);
                    for (int j = 0; j < N; j++) {
                        System.arraycopy(temp[j], 0, newMap[j], 0, M);
                    }
                }
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    int checkCnt = 0;
                    checkCnt += check(newMap, x, y, i);
                    checkCnt += check(newMap, x, y, (i + 1) % 4);
                    checkCnt += check(newMap, x, y, (i + 2) % 4);
                    dfs(cnt + checkCnt, cur + 1, newMap);
                    for (int j = 0; j < N; j++) {
                        System.arraycopy(temp[j], 0, newMap[j], 0, M);
                    }
                }
                break;
            case 5:
                int checkCnt = 0;
                checkCnt += check(newMap, x, y, 0);
                checkCnt += check(newMap, x, y, 1);
                checkCnt += check(newMap, x, y, 2);
                checkCnt += check(newMap, x, y, 3);
                dfs(cnt + checkCnt, cur + 1, newMap);
                for (int j = 0; j < N; j++) {
                    System.arraycopy(temp[j], 0, newMap[j], 0, M);
                }
                break;
        }
    }

    public static int check(int[][] newMap, int x, int y, int dir) {
        int cnt = 0;
        while (true) {
            x += dx[dir];
            y += dy[dir];
            if (x < 0 || x > N - 1 || y < 0 || y > M - 1) {
                break;
            }
            if (newMap[x][y] == 6) {
                break;
            }
            if (newMap[x][y] != 0) {
                continue;
            }
            newMap[x][y] = -1;
            cnt++;
        }

        return cnt;
    }
}
