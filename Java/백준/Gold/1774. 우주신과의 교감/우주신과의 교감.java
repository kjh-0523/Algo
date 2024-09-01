import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] parents;
	static int N,M;
	static ArrayList<int[]> Gods;

	static class Edge implements Comparable<Edge> {
		int start, end;
		double weight;

		public Edge(int start, int end, double weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			double c = this.weight - o.weight;
			if(c > 0) return 1;
			else return -1;
		}
	}

	public static void makeSet() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

	public static int findSet(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = findSet(parents[a]);
	}

	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;
		parents[bRoot] = aRoot;
		return true;
	}

	public static double calDist(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Gods = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			Gods.add(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
		}
		Edge[] edges = new Edge[N * (N - 1) / 2];
		int idx = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				double w = calDist(Gods.get(i)[0], Gods.get(i)[1], Gods.get(j)[0], Gods.get(j)[1]);
				edges[idx++] = new Edge(i, j, w);
			}
		}
		Arrays.sort(edges);
		makeSet();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			union(a,b);
		}
		int cnt = 0;
		double ans = 0;
		for (Edge edge : edges) {
			if (union(edge.start, edge.end)) {
				cnt++;
				ans += edge.weight;
				if (cnt == N - 1) {
					break;
				}
					
			}
		}
		System.out.printf("%.2f",ans);
	}
}