import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int T, N;
    public static int[][] sticker;
    public static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            sticker = new int[2][N];
            dp = new int[3][N];

            StringTokenizer st = null;
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dp[0][0] = sticker[0][0];
            dp[1][0] = sticker[1][0];
            for (int i = 1; i < N; i++) {
                dp[0][i] = Math.max(dp[1][i - 1], dp[2][i - 1]) + sticker[0][i];
                dp[1][i] = Math.max(dp[0][i - 1], dp[2][i - 1]) + sticker[1][i];
                dp[2][i] = Math.max(dp[0][i - 1], Math.max(dp[1][i - 1], dp[2][i - 1]));
            }
            int max = Math.max(dp[0][N - 1], Math.max(dp[1][N - 1], dp[2][N - 1]));
            sb.append(max).append("\n");
        }
        System.out.println(sb.toString());
    }
}
