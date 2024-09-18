import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int[] sum = new int[N+1];
		for (int i = 1; i < N+1; i++) {
			sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
		}
		long ans = 0;
		if(sum[N] % 4 != 0) ans = 0;
		else {
			if(sum[N] == 0) {
				int cnt = 0;
				for (int i = 1; i < N+1; i++) {
					if(sum[i] == 0) cnt++;
					ans = (cnt-1) * (cnt-2) * (cnt-3) / 6;
				}
			}else {
				long partSum = sum[N] / 4;
				long[] dp = new long[5];
				dp[0] = 1;
				for (int i = 1; i < N+1; i++) {
					int idx = (int) (sum[i]/partSum);
					if(idx >0 && idx < 5 && sum[i] % partSum == 0) {
						dp[idx] = dp[idx] + dp[idx-1];
					}
				}
				ans = dp[4];
			}
		}
		System.out.println(ans);
	}
}