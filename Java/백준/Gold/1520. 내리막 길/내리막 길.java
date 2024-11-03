import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] map;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dp = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		
		// dfs
		int ans = dfs(0,0);
		
		System.out.println(ans);
		
	}
	
	public static int dfs(int x, int y) {
		if(x == N-1 && y == M-1) {
			return 1;
		}
		
		if(dp[x][y] != -1) {
			return dp[x][y];
		}
		
		int nx,ny;
		dp[x][y] = 0;
		for(int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if(nx > -1 && nx < N && ny > -1 && ny < M && map[nx][ny] < map[x][y]) {
				dp[x][y] += dfs(nx, ny);
			}
		}
		return dp[x][y];
	}
}
