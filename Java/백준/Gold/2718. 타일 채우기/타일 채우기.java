import java.io.BufferedReader;
import java.io.InputStreamReader;
/*시간 : ms 메모리 : kb*/
public class Main {

	static int N;
	static long[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			dp = new long[5][N+2];
			dp[0][1] = 1;
			for (int i = 2; i < N+2; i++) {
				 dp[0][i] = dp[0][i-2] + dp[0][i-1] + dp[1][i-1] +dp[2][i-1] + dp[3][i-1];
				 dp[1][i] = dp[0][i-1] + dp[3][i-1];
				 dp[2][i] = dp[0][i-1] + dp[4][i-1];
				 dp[3][i] = dp[0][i-1] + dp[1][i-1];
				 dp[4][i] = dp[2][i-1];
			}
			System.out.println(dp[0][N+1]);
		}
	}
}