import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*시간 : 108ms 메모리 : 14440kb*/
public class Main {
	static int N,K;
	static int[] graph = new int[100001];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Arrays.fill(graph, Integer.MAX_VALUE);
		Dijkstra(N,K);
		System.out.println(graph[K]);
	}
	static void Dijkstra(int start, int end) {
		PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1,o2) -> o1[1]-o2[1]);
		graph[start] = 0;
		q.offer(new int[] {start,graph[start]});
		
		while(!q.isEmpty()) {
			int[] stopOver = q.poll();
			
			if(stopOver[1] > graph[stopOver[0]]) continue;
			if(stopOver[0] == end) {
				return;
			}
			
			if(2*stopOver[0] < 100001 && graph[2*stopOver[0]] > stopOver[1]) {
				graph[2 *stopOver[0]] = stopOver[1];
				q.offer(new int[] {2*stopOver[0],stopOver[1]});
			}
			if(stopOver[0]-1 > -1 && graph[stopOver[0]-1] > stopOver[1] + 1) {
				graph[stopOver[0]-1] = stopOver[1] + 1;
				q.offer(new int[] {stopOver[0]-1,stopOver[1]+1});
			}
			if(stopOver[0]+1 < 100001 && graph[stopOver[0]+1] > stopOver[1] + 1) {
				graph[stopOver[0]+1] = stopOver[1] + 1;
				q.offer(new int[] {stopOver[0]+1,stopOver[1]+1});
			}
			
		}
	}

}
