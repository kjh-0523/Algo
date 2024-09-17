import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N,K;
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ArrayList<int[]> items = new ArrayList<>();
		int[] dp = new int[K+1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			items.add(new int[] {w,v});
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = K; j >= items.get(i)[0]; j--) {
				dp[j] = Math.max(dp[j], dp[j-items.get(i)[0]]+items.get(i)[1]);
				}
			}
		System.out.println(dp[K]);
		}
}