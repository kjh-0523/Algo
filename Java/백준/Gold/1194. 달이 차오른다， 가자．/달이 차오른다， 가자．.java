import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
/* 시간 : 484ms 메모리 : 64452*/
public class Main {
	
	static boolean[][][] visited;
	static int X,Y, sX,sY;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static char[][] map;
	static Deque<int[]> queue;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		map = new char[X][Y];
		visited = new boolean[X][Y][64];
		for (int i = 0; i < X; i++) {
			String line = br.readLine();
			for (int j = 0; j < Y; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == '0') {
					map[i][j] = '.';
					sX = i;
					sY = j;
				}
			}
		}//입력 끝
		queue = new ArrayDeque<>();
		queue.offer(new int[] {sX,sY,0,0});
		int x,y,nx,ny,k,cnt;
		int ans = 0;
		top : while(!queue.isEmpty()) {
			int[] now = queue.poll();
			x = now[0];
			y = now[1];
			k = now[2];
			cnt = now[3];
			for (int i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if(nx > -1 && nx < X && ny > -1 && ny < Y && !visited[nx][ny][k]) {
					if(map[nx][ny] == '1') {
						ans = cnt+1;
						break top;
					}//탈출 끝
					if(map[nx][ny] == '.') {
						visited[nx][ny][k] = true;
						queue.offer(new int[] {nx,ny,k,cnt+1});
					}else if(-1 < (map[nx][ny] - 'A') && (map[nx][ny] - 'A') < 6) {
						if((k & (1 << map[nx][ny] - 'A')) != 0) {
							visited[nx][ny][k] = true;
							queue.offer(new int[] {nx,ny,k,cnt+1});
						}else continue;
					}else if(-1 < (map[nx][ny] - 'a') && (map[nx][ny] - 'a') < 6){
						visited[nx][ny][k] = true;
						queue.offer(new int[] {nx,ny,k | (1 << map[nx][ny] - 'a'),cnt+1});
					}
				}
			}
		}//bfs 끝
		
		if (ans == 0) ans = -1;
		System.out.println(ans);
	}

}
