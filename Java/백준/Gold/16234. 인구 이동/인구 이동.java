import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int days = 0;
        while (true) {
            visited = new boolean[N][N];
            boolean moved = false;

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!visited[r][c]) {
                        List<int[]> union = bfs(r, c);
                        if (union.size() > 1) {
                            redistribute(union);
                            moved = true;
                        }
                    }
                }
            }

            if (!moved) break;
            days++;
        }

        System.out.println(days);
    }

    static List<int[]> bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> union = new ArrayList<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;
        union.add(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0], cc = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if (visited[nr][nc]) continue;

                int diff = Math.abs(map[cr][cc] - map[nr][nc]);
                if (diff >= L && diff <= R) {
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                    union.add(new int[]{nr, nc});
                }
            }
        }

        return union;
    }

    static void redistribute(List<int[]> union) {
        int sum = 0;
        for (int[] pos : union) {
            sum += map[pos[0]][pos[1]];
        }
        int newPop = sum / union.size();
        for (int[] pos : union) {
            map[pos[0]][pos[1]] = newPop;
        }
    }
}
