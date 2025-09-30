import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] sum = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            sum[i] = Integer.parseInt(br.readLine()) + sum[i - 1];
        }

        int[][] dp = new int[n + 1][m + 1];
        Arrays.fill(dp[0], -32768 * 101);
        dp[0][0] = 0;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = i; k > 0; k--) {
                    if (k - 2 >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[k - 2][j - 1] + sum[i] - sum[k - 1]);
                    } else if (k == 1 && j == 1) {
                        dp[i][j] = Math.max(dp[i][j], sum[i]);
                    }
                }
            }
        }

        System.out.println(dp[n][m]);
    }

}