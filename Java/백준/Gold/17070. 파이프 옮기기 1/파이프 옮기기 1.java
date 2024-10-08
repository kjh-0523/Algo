import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int N;
	static int[][][] dp = new int[20][20][3];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[1][2][0] = 1;
		int ans = dp();
		System.out.println(ans);
	}

	public static int dp() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == 1) 
					continue;
				dp[i][j][0] += dp[i][j - 1][0] + dp[i][j - 1][2];
				dp[i][j][1] += dp[i - 1][j][1] + dp[i - 1][j][2];
				if (map[i - 1][j] == 0 && map[i][j - 1] == 0) {
					dp[i][j][2] += dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
				}
			}
		}
		return dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
	}
}
