import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, cnt;
    public static ArrayList<int[]>[][] button;
    public static boolean[][] on;
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,-1,1};

    public static class pos{
        int x, y;

        public pos(int x, int y){
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        button = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                button[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            button[x][y].add(new int[]{a,b});
        }

        bfs();

        cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(on[i][j]){
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    public static void bfs() {
        on = new boolean[N][N];
        on[0][0] = true;

        for (int[] b : button[0][0]) {
            if (!on[b[0]][b[1]]) {
                on[b[0]][b[1]] = true;
            }
        }

        while (true) {
            boolean changed = false;
            boolean[][] visited = new boolean[N][N];
            visited[0][0] = true;
            Queue<pos> q = new LinkedList<>();
            q.add(new pos(0, 0));

            while (!q.isEmpty()) {
                pos cur = q.poll();
                int x = cur.x;
                int y = cur.y;

                for (int[] b : button[x][y]) {
                    int a = b[0], b_ = b[1];
                    if (!on[a][b_]) {
                        on[a][b_] = true;
                        changed = true;
                    }
                }

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || nx > N-1 || ny < 0 || ny > N-1) continue;
                    if (!on[nx][ny] || visited[nx][ny]) continue;

                    visited[nx][ny] = true;
                    q.add(new pos(nx, ny));
                }
            }

            if (!changed) break;
        }
    }
}
