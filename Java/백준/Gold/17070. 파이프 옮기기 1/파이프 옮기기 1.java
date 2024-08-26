import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
/*시간 : 760ms 메모리 : 397384kb*/
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
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Deque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0,1,0});
		int nx,ny,x,y,s;
		int ans = 0;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			x = now[0];
			y = now[1];
			s = now[2];
			for (int i = 0; i < 3; i++) {
				if(Math.abs(s - i)>=2) continue;
				nx = x + dx[i];
				ny = y + dy[i];
				if(nx > -1 && nx < N && ny > -1 && ny < N && map[nx][ny] != 1) {
					if(i == 1 && (map[nx-1][ny] == 1 || map[nx][ny-1] == 1)) continue;
					if(nx == N-1 && ny == N-1) {
						ans++;
						continue;
					}
					queue.offer(new int[] {nx,ny,i});
				}
			}
		}
		System.out.println(ans);
	}
}
