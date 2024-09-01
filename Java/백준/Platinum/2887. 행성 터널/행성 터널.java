import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
/* 시간 : 120ms 메모리 : 14752kb*/
public class Main {

	static int[] parents;
	static int N;
	static ArrayList<Star> stars;

	static class Edge implements Comparable<Edge> {
		int start, end;
		int weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static class Star{
		int num,x,y,z;
		public Star(int num,int x, int y, int z) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		@Override
		public String toString() {
			return "Star [x=" + x + ", y=" + y + ", z=" + z + "]";
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

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stars = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			stars.add(new Star(i,Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		ArrayList<Edge> edges = new ArrayList<>();
        stars.sort((o1, o2) -> o1.x-o2.x);
        for (int i = 0; i < N - 1; i++) {
            int w = Math.abs(stars.get(i).x - stars.get(i + 1).x);
            edges.add(new Edge(stars.get(i).num, stars.get(i+1).num, w));
        }

        stars.sort((o1, o2) -> o1.y-o2.y);
        for (int i = 0; i < N - 1; i++) {
            int weight = Math.abs(stars.get(i).y - stars.get(i + 1).y);
            edges.add(new Edge(stars.get(i).num, stars.get(i+1).num, weight));
        }

        stars.sort((o1, o2) -> o1.z-o2.z);
        for (int i = 0; i < N - 1; i++) {
            int weight = Math.abs(stars.get(i).z - stars.get(i + 1).z);
            edges.add(new Edge(stars.get(i).num, stars.get(i+1).num, weight));
        }
		Collections.sort(edges);
		makeSet();
		int cnt = 0;
		int ans = 0;
		for (Edge edge : edges) {
			if (union(edge.start, edge.end)) {
				cnt++;
				ans += edge.weight;
				if (cnt == N - 1) {
				}
			}
		}
		System.out.println(ans);
	}
}