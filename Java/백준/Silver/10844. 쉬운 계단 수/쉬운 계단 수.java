import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int MOD = 1000000000;
		long dp[][] = new long[10][N + 1];
		for (int i = 0; i < 10; i++) {
			dp[i][1] = 1;
		}
		for (int i = 2; i < N + 1; i++) {
			dp[0][i] = dp[1][i-1]% MOD;
			dp[9][i] = dp[8][i-1]% MOD;
			for (int j = 1; j < 9; j++) {
				dp[j][i] = (dp[j-1][i-1] % MOD + dp[j+1][i-1]% MOD)% MOD;
			}
		}
		long ans = 0;
		for (int i = 1; i < 10; i++) {
			ans =(ans + dp[i][N]) % MOD;
		}
		System.out.println(ans);
	}
}
