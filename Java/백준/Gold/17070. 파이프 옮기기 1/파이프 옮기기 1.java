import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0,1,1};
	static int[] dy = {1,1,0};
	static int[][] map;
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = -Integer.parseInt(st.nextToken());
			}
		}
		dfs(0,1,0);
		int ans= 0;
		if(map[N-1][N-1] == -1) ans = 0;
		else ans = map[N-1][N-1];
		System.out.println(ans);
	}
	public static void dfs(int x, int y, int d) {
		int nx, ny;
		for (int i = 0; i < 3; i++) {
			if(Math.abs(d - i) >= 2) continue;
			nx = x + dx[i];
			ny = y + dy[i];
			if(nx > N-1 || ny > N-1 || map[nx][ny] == -1) continue;
			if(i == 1 && (map[nx-1][ny] == -1 || map[nx][ny-1] == -1))continue;
			map[nx][ny]++;
			dfs(nx,ny,i);
		}
	}
}
