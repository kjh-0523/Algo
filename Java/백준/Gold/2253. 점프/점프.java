import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static boolean[] small;
    public static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        small = new boolean[N+1];
        dp = new int[N+1][150];
        for (int i = 0; i < M; i++) {
            small[Integer.parseInt(br.readLine())] = true;
        }

        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], 10000);
        }
        dp[1][0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 150; j++) {
                if (dp[i][j] == 10000) continue;
                for (int k = -1; k <= 1; k++) {
                    int nj = j + k;
                    int ni = i + nj;
                    if (nj > 0 && nj < 150 && ni <= N && (ni == N || !small[ni])) {
                        dp[ni][nj] = Math.min(dp[ni][nj], dp[i][j] + 1);
                    }
                }
            }
        }

        int answer = 10000;
        for (int j = 0; j < 150; j++) {
            answer = Math.min(answer, dp[N][j]);
        }

        System.out.println(answer == 10000 ? -1 : answer);
    }
}
