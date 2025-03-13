import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static int N, E;
    public static boolean ans;
    public static char[][] map;
    public static ArrayList<coor> teacher;
    public static ArrayList<coor> empty;
    public static coor[] obstacles;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};

    public static class coor {
        int x, y;

        public coor(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        teacher = new ArrayList<>();
        empty = new ArrayList<>();
        obstacles = new coor[3];

        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j * 2);
                if (map[i][j] == 'T') {
                    teacher.add(new coor(i, j));
                } else if (map[i][j] == 'X') {
                    empty.add(new coor(i, j));
                }
            }
        }

        E = empty.size();
        ans = false;

        dfs(0, 0);
        System.out.println(ans ? "YES" : "NO");

    }

    public static void dfs(int cnt, int idx) {
        if (cnt == 3) {
            char[][] newMap = new char[N][N];
            for (int i = 0; i < N; i++) {
                System.arraycopy(map[i], 0, newMap[i], 0, N);
            }
            for (coor o : obstacles) {
                newMap[o.x][o.y] = 'O';
            }
            if (surv(newMap)) {
                ans = true;
            }
            return;
        }

        for (int i = idx; i < E; i++) {
            obstacles[cnt] = empty.get(i);
            dfs(cnt + 1, i + 1);
            if (ans) {
                break;
            }
        }
    }

    public static boolean surv(char[][] newMap) {
        for (coor t : teacher) {
            int nx, ny;
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < N; j++) {
                    nx = t.x + dx[i] * j;
                    ny = t.y + dy[i] * j;
                    if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) {
                        continue;
                    }
                    if (newMap[nx][ny] == 'O') {
                        break;
                    }
                    if (newMap[nx][ny] == 'S') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
