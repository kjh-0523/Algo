import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, INF = Integer.MAX_VALUE;
	static int[] data;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		data = new int[N + 1];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			data[i] = a;
			data[i + 1] = b;
		}
		dp = new int[N][N];

		for (int i = 2; i < N+1; i++) {
			for (int j = 0; j < N - i + 1; j++) {
				dp[j][j+i-1] = INF;
				for (int k = j; k < j + i - 1; k++) {
					int cal = dp[j][k] + dp[k + 1][j + i - 1] + (data[j] * data[k + 1] * data[j + i]);
					dp[j][j+i-1] = Math.min(dp[j][j+i-1], cal);
				}
			}
		}
		
		System.out.println(dp[0][N-1]);
	}

}