import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*시간 : 176ms 메모리 : 18584kb*/
public class Main {
	static int N,M,X,Y,Z;
	static long[] distance1;
	static ArrayList<Edge>[] road1;
	static class Edge implements Comparable<Edge>{
		int to;
		long weight;

		public Edge(int to, long weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Main.Edge o) {
			return (int) (this.weight - o.weight);
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		distance1 = new long[N+1];
		road1 = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			road1[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			road1[from].add(new Edge(to,weight));
		}
		st = new StringTokenizer(br.readLine()," ");
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		Z = Integer.parseInt(st.nextToken());
		long ans1 = 0,ans2 = 0, ans3 = 0;
		Dijkstra1(X,Y);
		ans1 = distance1[Y]== Long.MAX_VALUE ? -1 : distance1[Y];
		Dijkstra1(Y, Z);
		ans2 += distance1[Z]== Long.MAX_VALUE ? -1 : distance1[Z];
		if(ans2 == -1 || ans1 == -1) ans1 = -1;
		else ans1 = ans1 + ans2;
		Dijkstra2(X,Z);
		ans3 = distance1[Z] == Long.MAX_VALUE ? -1 : distance1[Z];
		System.out.println(ans1 + " " + ans3);
	}
	static void Dijkstra1(int start, int end) {
		PriorityQueue<Edge> q = new PriorityQueue<>();
		Arrays.fill(distance1, Long.MAX_VALUE);
		distance1[start] = 0;
		q.offer(new Edge(start,distance1[start]));
		
		while(!q.isEmpty()) {
			Edge stopOver = q.poll();
			
//			if(stopOver.to == end) return;
			if(stopOver.weight > distance1[stopOver.to]) continue;
			for (int i = 0; i < road1[stopOver.to].size(); i++) {
				if(distance1[road1[stopOver.to].get(i).to] > stopOver.weight + road1[stopOver.to].get(i).weight) {
					distance1[road1[stopOver.to].get(i).to] = stopOver.weight + road1[stopOver.to].get(i).weight;
					q.offer(new Edge (road1[stopOver.to].get(i).to,distance1[road1[stopOver.to].get(i).to]));
				}
			}
		}
	}
	
	static void Dijkstra2(int start, int end) {
		PriorityQueue<Edge> q = new PriorityQueue<>();
		Arrays.fill(distance1, Long.MAX_VALUE);
		distance1[start] = 0;
//		distance1[Y] = 0;
		q.offer(new Edge(start,distance1[start]));
		
		while(!q.isEmpty()) {
			Edge stopOver = q.poll();
			
//			if(stopOver.to == Y) continue;
//			if(stopOver.to == end) return;
			if(stopOver.weight > distance1[stopOver.to]) continue;
			for (int i = 0; i < road1[stopOver.to].size(); i++) {
				if(road1[stopOver.to].get(i).to == Y) continue;
				if(distance1[road1[stopOver.to].get(i).to] > stopOver.weight + road1[stopOver.to].get(i).weight) {
					distance1[road1[stopOver.to].get(i).to] = stopOver.weight + road1[stopOver.to].get(i).weight;
					q.offer(new Edge (road1[stopOver.to].get(i).to,distance1[road1[stopOver.to].get(i).to]));
				}
			}
		}
	}

}
