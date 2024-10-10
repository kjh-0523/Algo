import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
	
	static int[][] map;
	static int N;
	static int K;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#"+tc+" ");
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int ans = 0;
			for (int i = 0; i < N; i++) {
				if(slope(i,-1, new boolean[N])) ans++;
			}
			for (int i = 0; i < N; i++) {
				if(slope(-1,i, new boolean[N])) ans++;
			}
			sb.append(ans + "\n");
		}
		System.out.println(sb.toString());
	}
	
	static boolean slope(int x, int y, boolean[] slope) {
		if(y == -1) {
			for (int i = 1; i < N; i++) {
				if(Math.abs(map[x][i] - map[x][i-1]) > 1) return false;
				if(map[x][i] > map[x][i-1]) {
					for (int j = 0; j < K; j++) {
						if(i-j <= 0 || map[x][i-1] != map[x][i-j-1] || slope[i-j-1]) {
							return false;
						}
						slope[i-j-1] = true;
					}
				}else if(map[x][i] < map[x][i-1]) {
					for (int j = 0; j < K; j++) {
						if(i+j >= N || map[x][i] != map[x][i+j] || slope[i+j]) {
							return false;
						}
						slope[i+j] = true;
					}
				}
			}
		}else if(x == -1) {
			for (int i = 1; i < N; i++) {
				if(Math.abs(map[i][y] - map[i-1][y]) > 1) return false;
				if(map[i][y] > map[i-1][y]) {
					for (int j = 0; j < K; j++) {
						if(i-j <= 0 || map[i-1][y] != map[i-j-1][y] || slope[i-j-1]) {
							return false;
						}
						slope[i-j-1] = true;
					}
				}else if(map[i][y] < map[i-1][y]) {
					for (int j = 0; j < K; j++) {
						if(i+j >= N || map[i][y] != map[i+j][y] || slope[i+j]) {
							return false;
						}
						slope[i+j] = true;
					}
				}
		}
	}
		return true;
	}
}
