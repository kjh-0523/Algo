import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[][] points;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        points = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[1][0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int k = 0; k <= K; k++) {
                if (dp[i][k] == Integer.MAX_VALUE) continue;

                for (int j = i + 1; j <= N; j++) {
                    int jump = j - i - 1;
                    int nextK = k + jump;
                    if (nextK > K) continue;
                    int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                    dp[j][nextK] = Math.min(dp[j][nextK], dp[i][k] + dist);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int k = 0; k <= K; k++) {
            ans = Math.min(ans, dp[N][k]);
        }
        System.out.println(ans);
    }
}
