import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, K;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] coins = new int[N];
		for (int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(coins);
		int[] dp = new int[K+1];
		Arrays.fill(dp, K+1);
		dp[0] = 0;
		for (int i = 0; i < N; i++) {
			for (int j = coins[i]; j < K+1; j++) {
				dp[j] = Math.min(dp[j], dp[j-coins[i]]+1);
			}
		}
		if(dp[K] == K+1) System.out.println(-1);
		else System.out.println(dp[K]);
	}
}