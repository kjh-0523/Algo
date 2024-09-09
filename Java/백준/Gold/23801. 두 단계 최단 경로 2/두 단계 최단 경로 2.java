import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*시간 : 424ms 메모리 : 65992kb*/
public class Main {
	static int N, M;
	static long[] distance1;
	static ArrayList<Edge>[] road;
	static final long INF = Long.MAX_VALUE;

	static class Edge implements Comparable<Edge> {
		int to;
		long weight;

		public Edge(int to, long weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		road = new ArrayList[N+1];
		distance1 = new long[N+1];

		for (int i = 1; i <= N; i++) {
			road[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			road[from].add(new Edge(to, weight));
			road[to].add(new Edge(from, weight));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int P = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		int[] nums = new int[P];
		for (int i = 0; i < P; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		long[] ans1 = new long[P];
		long[] ans2 = new long[P];
		Dijkstra(start, end);
		for (int i = 0; i < P; i++) {
			ans1[i] = distance1[nums[i]];
		}
		Dijkstra(end, start);
		for (int i = 0; i < P; i++) {
			ans2[i] = distance1[nums[i]];
		}
		long ans = INF;
		for (int i = 0; i < P; i++) {
			if(ans1[i] == INF || ans2[i] == INF) continue;
			else ans = Math.min(ans, ans1[i] + ans2[i]);
		}
		System.out.println(ans == INF ? -1 : ans);
	}

	static void Dijkstra(int start, int end) {
		PriorityQueue<Edge> q = new PriorityQueue<>();
		Arrays.fill(distance1, INF);
		distance1[start] = 0;
		q.offer(new Edge(start, 0));

		while (!q.isEmpty()) {
			Edge cur = q.poll();

			if (cur.weight > distance1[cur.to])
				continue;
//			if (cur.to == end)
//				return;
			for (Edge next : road[cur.to]) {
				if (distance1[next.to] > distance1[cur.to] + next.weight) {
					distance1[next.to] = distance1[cur.to] + next.weight;
					q.offer(new Edge(next.to, distance1[next.to]));
				}
			}
		}
	}
}
