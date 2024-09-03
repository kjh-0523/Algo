import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
	static int N, map[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}
			System.out.println("#" + tc + " " + Dijkstra(0, 0, N - 1, N - 1));
		}
	}

	static int Dijkstra(int sx, int sy, int ex, int ey) {
		
		final int INF = Integer.MAX_VALUE;
		boolean[][] visited = new boolean[N][N];
		int[][] minTime = new int[N][N];
		PriorityQueue<int[]> pQueue = new PriorityQueue<>((o1,o2)->o1[2]-o2[2]); // or Integer.compare(o1[2],o2[2])
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minTime[i][j] = INF;
			}
		}
		
		minTime[sx][sy] = 0;
		pQueue.offer(new int[] {sx,sy,minTime[sx][sy]});
		
		while(!pQueue.isEmpty()) {
			int[] stopOver = pQueue.poll(); // step1
			int x = stopOver[0];
			int y = stopOver[1];
			int time = stopOver[2];
			
			if(visited[x][y]) continue;
			visited[x][y] = true;
			if(x == ex && y == ey) return time;
			
			int nx,ny;
			for (int i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if(nx >=0 && nx < N && ny >=0 && ny < N && !visited[nx][ny] && minTime[nx][ny] > time + map[nx][ny]) {
					minTime[nx][ny] = time + map[nx][ny];
					pQueue.offer(new int[] {nx,ny,minTime[nx][ny]});
				}
			}
		}
		return -1;
	}
}
