import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static int N;
    public static int[][] map;
    public static boolean[][] visited;
    public static PriorityQueue<Integer> ans;
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        ans = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || map[i][j] == 0) {
                    continue;
                }
                ans.add(bfs(i, j));
            }
        }
        System.out.println(ans.size());
        while (!ans.isEmpty()) {
            System.out.println(ans.poll());
        }
    }

    public static int bfs(int x, int y) {
        int cnt = 1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int nx, ny;
            for (int i = 0; i < 4; i++) {
                nx = cur[0] + dx[i];
                ny = cur[1] + dy[i];

                if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1 || map[nx][ny] == 0 || visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                cnt++;
                q.offer(new int[]{nx, ny});
            }
        }
        return cnt;
    }
}
