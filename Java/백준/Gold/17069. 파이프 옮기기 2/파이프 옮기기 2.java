import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*시간 : 104ms 메모리 : 14320kb*/
public class Main {
	static int N;
	static int[][] map;
	static long[][][] dp = new long[35][35][3];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[1][2][0] = 1;
		for (int i = 1; i < N+1; i++) {
			for (int j = 2; j < N+1; j++) {
				if(map[i][j] == 1) continue;
				dp[i][j][0] += dp[i][j-1][0]+dp[i][j-1][2];
				dp[i][j][1] += dp[i-1][j][1]+dp[i-1][j][2];
				if(map[i-1][j] != 1 && map[i][j-1] != 1) {
					dp[i][j][2] += dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
			}
		}
		long ans = 0;
		for (int i = 0; i < 3; i++) {
			ans += dp[N][N][i];
		}
		System.out.println(ans);
		
	}


}
