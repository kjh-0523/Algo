import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		final int MOD = (int) 1e9 + 3;

		int[][] dp = new int[N + 1][K + 1];
		for (int i = 1; i <= N; i++) {
			dp[i][1] = i;
			dp[i][0] = 1;
		}
		for (int i = 2; i <= N; i++) {
			for (int j = 2; j <= K; j++) {
				dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
			}
		}
		System.out.println((dp[N - 1][K] + dp[N - 3][K - 1])%MOD);
	}

}
