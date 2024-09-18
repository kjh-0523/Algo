import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] jewels = new int[100001];
		int[] sum = new int[100001];
		int[] dp = new int[100001];
		for (int i = 1; i < N+1; i++) {
			jewels[i] = Integer.parseInt(br.readLine());
			sum[i] = sum[i-1]+jewels[i];
		}
		int ans = sum[M];
		dp[M] = sum[M];
		for (int i = M+1; i < N+1; i++) {
			dp[i] = Math.max(dp[i-1]+jewels[i], sum[i]-sum[i-M]);
			if(ans < dp[i]) ans = dp[i];
		}
//		System.out.println(Arrays.toString(dp));
		if(ans < 0) ans = 0;
		System.out.println(ans);
	}
}