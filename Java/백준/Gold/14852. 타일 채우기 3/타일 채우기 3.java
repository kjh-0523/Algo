import java.io.BufferedReader;
import java.io.InputStreamReader;

/*시간 : 168ms 메모리 : 14244kb*/
public class Main {

	static int N;
	static long[][] dp;
	static int INF = 1000000007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		dp = new long[2][1000001];
		dp[0][0] = 1;
		dp[0][1] = 2;
		dp[0][2] = 7;
		for (int i = 3; i < N + 1; i++) {
			dp[1][i] = (dp[1][i - 1] + dp[0][i - 3]) % INF;
			dp[0][i] = (2 * dp[0][i - 1] + 3 * dp[0][i - 2] + 2 * dp[1][i]) % INF;
		}
		System.out.println(dp[0][N]);
	}

}