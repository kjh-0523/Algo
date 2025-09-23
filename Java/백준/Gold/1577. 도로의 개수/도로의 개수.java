import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static long[][] dp;
    public static boolean[][] hor;
    public static boolean[][] ber;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new long[N + 1][M + 1];
        hor = new boolean[N][M + 1];
        ber = new boolean[N + 1][M];

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            if (y1 == y2) {
                hor[Math.min(x1, x2)][y1] = true;
            } else {
                ber[x1][Math.min(y1, y2)] = true;
            }
        }

        for (int i = 1; i <= N; i++) {
            if (hor[i-1][0]) break;

            dp[i][0] = 1L;
        }
        for (int i = 1; i <= M; i++) {
            if (ber[0][i-1]) break;

            dp[0][i] = 1L;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];

                if (hor[i-1][j]) {
                    dp[i][j] -= dp[i-1][j];
                }

                if (ber[i][j-1]) {
                    dp[i][j] -= dp[i][j-1];
                }
            }
        }

        System.out.println(dp[N][M]);
    }
}
