import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N, M;
    public static int[][] map;
    public static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = map[0][0];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i < N - 1) {
                    dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j] + map[i + 1][j]);
                }
                if (j < M - 1) {
                    dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i][j] + map[i][j + 1]);
                }
                if (i < N - 1 && j < M - 1) {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j] + map[i + 1][j + 1]);
                }
            }
        }
        System.out.println(dp[N - 1][M - 1]);
    }
}
