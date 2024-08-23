import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static int[][][] visited;
	static int K, X,Y;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static int[] hx = {-1,-2,-2,-1,1,2,2,1};
	static int[] hy = {-2,-1,1,2,2,1,-1,-2};
	static int[][] map;
	static Deque<int[]> queue;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		map = new int[X][Y];
		visited = new int[X][Y][K+1];
		for (int i = 0; i < X; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < Y; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}// 입력 끝
		queue = new ArrayDeque<int[]>();
		queue.add(new int[] {0,0,0});
		visited[0][0][0] = 0;
		int x,y,nx,ny, h;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			x = now[0];
			y = now[1];
			h = now[2];
			if(x == X-1 && y == Y-1) {
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if(nx > -1 && nx < X && ny > -1 && ny < Y && map[nx][ny] == 0 && visited[nx][ny][h] == 0) {
					visited[nx][ny][h] = visited[x][y][h] + 1;
					queue.offer(new int[] {nx,ny,h});
				}
				
			}
			if(h < K) {
				for (int i = 0; i < 8; i++) {
					nx = x + hx[i];
					ny = y + hy[i];
					if(nx > -1 && nx < X && ny > -1 && ny < Y && map[nx][ny] == 0 && visited[nx][ny][h+1] == 0) {
						visited[nx][ny][h+1] = visited[x][y][h] + 1;
						queue.offer(new int[] {nx,ny,h+1});
					}
					
				}
			}
		}//bfs  종료
		int ans = Integer.MAX_VALUE;
		for (int i = K; i > -1; i--) {
			if(visited[X-1][Y-1][i] != 0) {
				ans = Math.min(ans,visited[X-1][Y-1][i]);
			}
		}
		if (ans == Integer.MAX_VALUE) ans = -1;
		if (X == 1 && Y == 1) ans = 0;
		
		System.out.println(ans);
	}

}
