import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] parents;
	static int N;
	static ArrayList<double[]> stars;

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
			return (int) (this.weight - o.weight);
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

	public static double calDist(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stars = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			stars.add(new double[] { Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()) });
		}
		Edge[] edges = new Edge[N * (N - 1) / 2];
		int idx = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				double w = calDist(stars.get(i)[0], stars.get(i)[1], stars.get(j)[0], stars.get(j)[1]);
				edges[idx++] = new Edge(i, j, w);
			}
		}
		Arrays.sort(edges);
		makeSet();
		int cnt = 0;
		double ans = 0;
		for (Edge edge : edges) {
			if (union(edge.start, edge.end)) {
				cnt++;
				ans += edge.weight;
				if (cnt == N - 1)
					break;
			}
		}
		System.out.printf("%.2f",ans);
	}
}