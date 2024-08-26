import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {
	static char[][] map;
	static int N,ans;
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,1,-1,0,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j);
				}
			}//입력 끝 
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == '.') {
						int nx, ny;
						int cnt = 0;
						for (int k = 0; k < 8; k++) {
							nx = i+dx[k];
							ny = j+dy[k];
							if(nx < 0 || nx > N-1 || ny < 0 || ny > N-1) continue;
							if(map[nx][ny] == '*')cnt++;
						}
						map[i][j] = Character.forDigit(cnt, 10);
					}
				}
			}
			ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == '0') {
						bfs(i,j);
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] != '.' && map[i][j] != '*') ans++;
				}
			}
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	static void bfs(int x, int y) {
		ans++;
		Deque<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {x,y});
		map[x][y] = '.';
		int nx,ny;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			x = now[0];
			y = now[1];
			for (int i = 0; i < 8; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if(nx < 0 || nx > N-1 || ny < 0 || ny > N-1)continue;
				if(map[nx][ny] == '0') queue.offer(new int[] {nx,ny});
				map[nx][ny] = '.';
			}
		}
	}
}
