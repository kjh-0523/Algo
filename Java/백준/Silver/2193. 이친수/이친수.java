import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static int N;
    public static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new long[N][2];
        dp[0][0] = 0;
        dp[0][1] = 1;

        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][1] + dp[i - 1][0];
            dp[i][1] = dp[i - 1][0];
//            System.out.println(i + 1 + " : " + dp[i][0] + " " + dp[i][1]);
        }

        System.out.println(dp[N - 1][0] + dp[N - 1][1]);

    }
}
