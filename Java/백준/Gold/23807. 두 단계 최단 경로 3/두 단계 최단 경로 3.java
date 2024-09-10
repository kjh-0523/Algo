import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*시간 : 1056ms 메모리 : 161040kb*/
public class Main {
	static int N, M,P, start, end;
	static long ans;
	static long[] distance1;
	static ArrayList<Edge>[] road;
	static final long INF = Long.MAX_VALUE;
	static long[][] table;
	static int[] nums;
	static int[] perm;

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
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		P = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		nums = new int[P+2];
		nums[0] = start;
		for (int i = 1; i < P+1; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		nums[P+1] = end;
		
		table = new long[P+2][N+1];
		Dijkstra(start,end);
		for (int i = 0; i < P+2; i++) {
			table[0][nums[i]] = distance1[nums[i]];
		}
		Dijkstra(end, start);
		for (int i = 0; i < P+2; i++) {
			table[P+1][nums[i]] = distance1[nums[i]];
		}
		for (int i = 1; i < P+1; i++) {
			Dijkstra(nums[i],end);
			for (int j = 0; j < P+2; j++) {
				if(nums[i] == nums[j]) continue;
				table[i][nums[j]] = distance1[nums[j]];
			}
		}
//		for (int i = 0; i < P+2; i++) {
//			System.out.println(Arrays.toString(table[i]));
//		}
		perm = new int[3];
		ans = INF;
		perm(0,0);
		
		System.out.println(ans == INF ? -1 : ans);
		
	}
	
	static void perm(int depth, int flag) {
		if(depth == 3) {
			long sum = 0;
			int idx = 0;
			for (int i = 0; i < 3; i++) {
				if(table[idx][nums[perm[i]]] == INF) return;
				sum+= table[idx][nums[perm[i]]];
				idx = perm[i];
			}
			if(table[P+1][nums[idx]] == INF) return;
			sum += table[P+1][nums[idx]];
//			System.out.println(idx);
//			if(sum == 4102) System.out.println(Arrays.toString(perm));
			ans = Math.min(ans, sum);
			return;
		}
		
		for (int i = 0; i < P; i++) {
			if((flag & 1 << i) != 0) continue;
			perm[depth] = i+1;
			perm(depth+1, flag | 1 << i);
		}
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

			for (Edge next : road[cur.to]) {
				if (distance1[next.to] > distance1[cur.to] + next.weight) {
					distance1[next.to] = distance1[cur.to] + next.weight;
					q.offer(new Edge(next.to, distance1[next.to]));
				}
			}
		}
	}
}
