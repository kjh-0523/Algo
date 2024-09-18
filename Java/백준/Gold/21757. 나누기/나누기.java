import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*시간 : 168ms 메모리 : 21588kb*/
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int[] sum = new int[N+1];
		for (int i = 1; i < N+1; i++) {
			sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
		}
		int ans = 0;
		if(sum[N] % 4 != 0) ans = 0;
		else {
			if(sum[N] == 0) {
				int cnt = 0;
				for (int i = 1; i < N+1; i++) {
					if(sum[i] == 0) cnt++;
					ans = (cnt-1) * (cnt-2) * (cnt-3) / 6;
				}
			}else {
				int partSum = sum[N] / 4;
				int[] dp = new int[5];
				dp[0] = 1;
				for (int i = 1; i < N+1; i++) {
					if(sum[i] % partSum == 0) {
						dp[sum[i]/partSum] = dp[sum[i]/partSum] + dp[sum[i]/partSum-1];
					}
				}
				ans = dp[4];
			}
		}
		System.out.println(ans);
	}
}