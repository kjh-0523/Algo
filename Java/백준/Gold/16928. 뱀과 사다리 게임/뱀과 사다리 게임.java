import java.io.*;
import java.util.*;

public class Main {
	static int N, M, ans;
	static int[] map = new int[101];
	static int[] visited = new int[101];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			map[s] = e;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			map[s] = e;
		}
		Arrays.fill(visited, (int) 1e7);

		bfs();

		System.out.println(visited[100]);
	}

	public static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		visited[1] = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(cur == 100) {
				return;
			}
			if(map[cur] > 0) {
				if(visited[map[cur]] > visited[cur]) {
					visited[map[cur]] = visited[cur];
					q.offer(map[cur]);
				}
			}else {
				for (int i = 1; i < 7; i++) {
					if(cur + i < 101 && visited[cur+i] > visited[cur] + 1) {
						visited[cur + i] = visited[cur] + 1;
						q.offer(cur + i);
					}
				} 
			}
					
		}
		
		
	}
}
