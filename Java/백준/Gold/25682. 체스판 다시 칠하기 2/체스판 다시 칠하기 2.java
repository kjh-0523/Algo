import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*시간 : 644ms 메모리 : 127264kb*/
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N,M,K;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N+1][M+1];
		boolean flag = true;
		for (int i = 1; i < N+1; i++) {
			String line = br.readLine();
			for (int j = 1; j < M+1; j++) {
				if(flag && line.charAt(j-1) == 'W') {
					dp[i][j] = 1;
				}else if(!flag && line.charAt(j-1) == 'B') {
					dp[i][j] = 1;
				}
				flag = !flag;
			}
			if(M % 2 == 0) flag = !flag;
		}
		
		for (int i = 1; i < N+1; i++) {
			for (int j = 2; j < M+1; j++) {
				dp[i][j] += dp[i][j-1];
			}
		}
		for (int i = 1; i < M+1; i++) {
			for (int j = 2; j < N+1; j++) {
				dp[j][i] += dp[j-1][i];
			}
		}
		int min = Integer.MAX_VALUE;
		int max = 0;
		 for (int i = 1; i < N-K+2; i++) {
			for (int j = 1; j < M-K+2; j++) {
				int sum = dp[i+K-1][j+K-1] - dp[i+K-1][j-1] - dp[i-1][j+K-1] + dp[i-1][j-1];
				
				min = Math.min(min, sum);
				max = Math.max(max, sum);
			}
		}
		 int ans = 0;
		 ans = Math.min(min, K*K - max);
		 System.out.println(ans);
	}
}