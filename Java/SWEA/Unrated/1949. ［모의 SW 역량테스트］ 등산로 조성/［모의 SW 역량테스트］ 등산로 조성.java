import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int N, K, max,ans;
	static int[][] map;
	static ArrayList<int[]> start;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > max) {
						max = map[i][j];
						start = new ArrayList<>();
						start.add(new int[] { i, j });
					} else if (map[i][j] == max) {
						start.add(new int[] { i, j });
					}
				}
			}
			ans = 0;
			for (int k = 0; k <= K; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						map[i][j] -= k;
						for (int[] s : start) {
							if(map[s[0]][s[1]] != max) continue;
							dfs(s[0], s[1], 1);
						}
						map[i][j] += k;
					}
				}
			}
			System.out.println("#" + tc+" "+ans);
		}
	}

	private static void dfs(int x, int y, int depth) {
		int nx,ny;
		for (int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if(nx < 0 || nx > N-1 || ny < 0 || ny > N-1 || map[nx][ny] >= map[x][y]) {
				ans = Math.max(ans, depth);
				continue;
			}
			dfs(nx,ny,depth+1);
		}
		return;
	}

}
