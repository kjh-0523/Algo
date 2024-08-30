import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static LinkedList<Toy>[] toys;
	static List<Integer> basicToy = new ArrayList<Integer>();
	static int[][] dp;

	static class Toy {
		int to, weight;

		public Toy(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		toys = new LinkedList[N + 1];
		for (int i = 1; i < N+1; i++) {
			toys[i] = new LinkedList<>();
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			toys[from].add(new Toy(to, weight));
		}
		dp = new int[N + 1][N + 1];
		// 입력 끝
		// 기본 장난감 찾기
		for (int i = 1; i < N + 1; i++) {
			if (toys[i].size() == 0) {
				dp[i][i] = 1;
				dp[i][0] = 1;
				basicToy.add(i);
			}
		}
		dfs(N);
		for(int bt : basicToy) {
			System.out.println(bt +" "+dp[N][bt]);
		}
//		for (int i = 1; i < N+1; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
	}

	public static void dfs(int cur) {
		for (Toy toy : toys[cur]) {
			if (dp[toy.to][0] == 0) {
				dfs(toy.to);
			}
			if (dp[toy.to][0] == 1) {
				dp[cur][toy.to] += dp[toy.to][toy.to] * toy.weight;
			}
			if(dp[toy.to][0] == 2) {
				for (int bt : basicToy) {
					dp[cur][bt] += dp[toy.to][bt]*toy.weight;
				}
			}
		}
		dp[cur][0] = 2;
//		for (int i = 1; i < N+1; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
//		System.out.println();
		return;
	}

}
