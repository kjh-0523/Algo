import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*시간 : 168ms 메모리 : 18660kb*/
public class Main {
	static int N,M,X;
	static int[] distance1;
	static int[] distance2;
	static ArrayList<int[]>[] road1;
	static ArrayList<int[]>[] road2;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		distance1 = new int[N+1];
		distance2 = new int[N+1];
		Arrays.fill(distance1, Integer.MAX_VALUE);
		Arrays.fill(distance2, Integer.MAX_VALUE);
		road1 = new ArrayList[N+1];
		road2 = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			road1[i] = new ArrayList<int[]>();
		}
		for (int i = 1; i <= N; i++) {
			road2[i] = new ArrayList<int[]>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			road1[from].add(new int[] {to,weight});
			road2[to].add(new int[] {from,weight});
		}
		Dijkstra1(X);
		Dijkstra2(X);
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			ans = Math.max(ans, distance1[i]+distance2[i]);
		}
		System.out.println(ans);
	}
	static void Dijkstra1(int start) {
		PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1,o2) -> o1[1]-o2[1]);
		distance1[start] = 0;
		q.offer(new int[] {start,distance1[start]});
		
		while(!q.isEmpty()) {
			int[] stopOver = q.poll();
			
			if(stopOver[1] > distance1[stopOver[0]]) continue;
			for (int i = 0; i < road1[stopOver[0]].size(); i++) {
				if(distance1[road1[stopOver[0]].get(i)[0]] > stopOver[1] + road1[stopOver[0]].get(i)[1]) {
					distance1[road1[stopOver[0]].get(i)[0]] = stopOver[1] + road1[stopOver[0]].get(i)[1];
					q.offer(new int[] {road1[stopOver[0]].get(i)[0],distance1[road1[stopOver[0]].get(i)[0]]});
				}
			}
			
			
		}
	}
	static void Dijkstra2(int start) {
		PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1,o2) -> o1[1]-o2[1]);
		distance2[start] = 0;
		q.offer(new int[] {start,distance2[start]});
		
		while(!q.isEmpty()) {
			int[] stopOver = q.poll();
			
			if(stopOver[1] > distance2[stopOver[0]]) continue;
			for (int i = 0; i < road2[stopOver[0]].size(); i++) {
				if(distance2[road2[stopOver[0]].get(i)[0]] > stopOver[1] + road2[stopOver[0]].get(i)[1]) {
					distance2[road2[stopOver[0]].get(i)[0]] = stopOver[1] + road2[stopOver[0]].get(i)[1];
					q.offer(new int[] {road2[stopOver[0]].get(i)[0],distance2[road2[stopOver[0]].get(i)[0]]});
				}
			}
			
			
		}
	}

}
