import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, sx, sy, size = 2, ans = 0, food = 0;
    static int[][] board;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {-1, 1, 0, 0};
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    sx = i;
                    sy = j;
                    board[i][j] = 0;
                }
            }
        }

        while (true) {
            int[] result = find(BFS());
            if (result == null) {
                System.out.println(ans);
                break;
            } else {
                sx = result[0];
                sy = result[1];
                ans += result[2];
                board[sx][sy] = 0;
                food++;

                if (food == size) {
                    food = 0;
                    size++;
                }
            }
        }
    }

    static int[][] BFS() {
        Queue<int[]> queue = new LinkedList<>();
        int[][] visit = new int[N][N];
        for (int i = 0; i < N; i++) {
			Arrays.fill(visit[i], -1);
		}

        queue.add(new int[]{sx, sy});
        visit[sx][sy] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (visit[nx][ny] == -1 && board[nx][ny] <= size) {
                        queue.add(new int[]{nx, ny});
                        visit[nx][ny] = visit[x][y] + 1;
                    }
                }
            }
        }
        return visit;
    }

    static int[] find(int[][] visit) {
        int minD = INF, x = 0, y = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visit[i][j] != -1 && board[i][j] > 0 && board[i][j] < size) {
                    if (visit[i][j] < minD) {
                        minD = visit[i][j];
                        x = i;
                        y = j;
                    }
                }
            }
        }

        if (minD == INF) return null;
        return new int[]{x, y, minD};
    }
}
