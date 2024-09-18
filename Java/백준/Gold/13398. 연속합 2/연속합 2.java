import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			nums.add(Integer.parseInt(st.nextToken()));
		}
		int[][] dp = new int[2][N];
		dp[0][0] = nums.get(0);
		dp[1][0] = nums.get(0);
		int max = nums.get(0);
		for (int i = 1; i < N; i++) {
			dp[0][i] = Math.max(dp[0][i-1]+nums.get(i), nums.get(i));
			dp[1][i] = Math.max(dp[0][i-1],dp[1][i-1] + nums.get(i));
			max = Math.max(max, Math.max(dp[0][i], dp[1][i]));
		}
		System.out.println(max);
	}
}