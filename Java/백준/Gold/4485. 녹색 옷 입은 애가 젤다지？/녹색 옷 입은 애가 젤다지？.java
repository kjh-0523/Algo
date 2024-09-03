import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*시간 : 192ms 메모리 : 19176kb*/
public class Main {
	static int N, map[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 0;
		while(true) {
			tc++;
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append("Problem ").append(tc).append(": ").append(Dijkstra(0, 0, N - 1, N - 1)).append("\n");
		}
		System.out.println(sb);
	}

	static int Dijkstra(int sx, int sy, int ex, int ey) {
		
		final int INF = Integer.MAX_VALUE;
		int[][] minTime = new int[N][N];
		PriorityQueue<int[]> pQueue = new PriorityQueue<>((o1,o2)->o1[2]-o2[2]); // or Integer.compare(o1[2],o2[2])
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minTime[i][j] = INF;
			}
		}
		
		minTime[sx][sy] = map[0][0];
		pQueue.offer(new int[] {sx,sy,minTime[sx][sy]});
		
		while(!pQueue.isEmpty()) {
			int[] stopOver = pQueue.poll(); // step1
			int x = stopOver[0];
			int y = stopOver[1];
			int time = stopOver[2];
			
			if(x == ex && y == ey) {
				return time;
			}
			if(minTime[x][y] < time) continue;
			int nx,ny;
			for (int i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if(nx >=0 && nx < N && ny >=0 && ny < N  && minTime[nx][ny] > time + map[nx][ny]) {
					minTime[nx][ny] = time + map[nx][ny];
					pQueue.offer(new int[] {nx,ny,minTime[nx][ny]});
				}
			}
		}
		return -1;
	}
}
