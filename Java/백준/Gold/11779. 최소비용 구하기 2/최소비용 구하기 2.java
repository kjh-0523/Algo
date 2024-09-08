import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static long[] distance1;
	static ArrayList<Edge>[] road1;
	static int[] path;

	static class Edge implements Comparable<Edge> {
		int to;
		long weight;

		public Edge(int to, long weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Main.Edge o) {
			return Long.compare(this.weight, o.weight);
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		distance1 = new long[N + 1];
		path = new int[N + 1];
		road1 = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			road1[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			road1[from].add(new Edge(to, weight));
		}
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		Dijkstra(start, end);
		ArrayList<Integer> result = new ArrayList<Integer>();
		int cur = end;
		while(cur != 0) {
			result.add(0,cur);
			cur = path[cur];
		}
		System.out.println(distance1[end]);
		System.out.println(result.size());
		for (int r : result) {
			System.out.print(r + " ");
		}
	}

	static void Dijkstra(int start, int end) {
		PriorityQueue<Edge> q = new PriorityQueue<>();
		Arrays.fill(distance1, Long.MAX_VALUE);
		distance1[start] = 0;
		q.offer(new Edge(start, distance1[start]));

		while (!q.isEmpty()) {
			Edge stopOver = q.poll();
			if (stopOver.weight > distance1[stopOver.to])
				continue;
			if(stopOver.to == end) return;
			for (int i = 0; i < road1[stopOver.to].size(); i++) {
				if (distance1[road1[stopOver.to].get(i).to] > stopOver.weight + road1[stopOver.to].get(i).weight) {
					distance1[road1[stopOver.to].get(i).to] = stopOver.weight + road1[stopOver.to].get(i).weight;
					path[road1[stopOver.to].get(i).to] = stopOver.to;
					q.offer(new Edge(road1[stopOver.to].get(i).to, distance1[road1[stopOver.to].get(i).to]));
				}
			}
		}
	}
}
