import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, start, result;
	static int[][] map;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int [N][N];
			visited = new boolean [N][N];
			result = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}//입력 끝
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(map[i][j], i,j,1);
				}
			}
			System.out.println("#" + tc + " " + start + " " + result);
		}
	}
	
	public static void dfs(int s, int x, int y, int dis) {
		int nx, ny;
		
		for (int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if( nx < 0 || nx >N-1 || ny < 0 || ny > N-1 || visited[nx][ny] || map[nx][ny] != map[x][y]+1) {
				if( dis > result) {
					result = dis;
					start = s;
				}else if(dis == result) {
					start = Math.min(start,s);
				}
				continue;
			}
			dfs(s,nx,ny,dis+1);
			
		}
	}
}
