import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*시간 : 108ms 메모리 : 14440kb*/
public class Main {
	static int N,K;
	static boolean[] visited = new boolean[100001];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int ans = bfs(N,K);
		System.out.println(ans);
	}
	static int bfs(int start, int end) {
		PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1,o2) -> o1[1]-o2[1]);
		q.offer(new int[] {start,0});
		
		int cnt = 0;
		visited[start] = true;
		while(!q.isEmpty()) {
			int[] stopOver = q.poll();
			
			if(stopOver[0] == end) {
				cnt = stopOver[1];
				break;
			}
			
			visited[stopOver[0]] = true;
			if(2 * stopOver[0] < 100001 && !visited[2*stopOver[0]]) {
				q.offer(new int[] {2*stopOver[0],stopOver[1]});
			}
			if(stopOver[0]+1 < 100001 && !visited[stopOver[0]+1]) {
				q.offer(new int[] {stopOver[0]+1,stopOver[1]+1});
			}
			if(stopOver[0]-1 > -1 && !visited[stopOver[0]-1]) {
				q.offer(new int[] {stopOver[0]-1,stopOver[1]+1});
			}
		}
		return cnt;
	}

}
