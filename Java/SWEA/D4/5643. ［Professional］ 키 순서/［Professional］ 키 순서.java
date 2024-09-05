import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*시간 : 155ms 메모리 : 29,192kb*/
public class Solution {

	static int N, M;
	static ArrayList<Integer>[] from;
	static ArrayList<Integer>[] to;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			from = new ArrayList[N + 1];
			to = new ArrayList[N + 1];
			for (int i = 1; i < N + 1; i++) {
				from[i] = new ArrayList<>();
				to[i] = new ArrayList<>();
			}
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int f = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				from[f].add(t);
				to[t].add(f);
			}
			int ans = 0;
			for (int i = 1; i < N + 1; i++) {
				int up = bfsU(i);
				int down = bfsD(i);
				if(up+down == N-1) ans++;
			}
			System.out.println("#" + tc + " " + ans);
		}
	}

	private static int bfsU(int start) {
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		visited[start] = true;
		q.offer(start);
		int cnt = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < from[cur].size(); i++) {
				if(visited[from[cur].get(i)])continue;
				visited[from[cur].get(i)] = true;
				cnt++;
				q.offer(from[cur].get(i));
			}
		}
		
		return cnt;
	}
	private static int bfsD(int start) {
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		visited[start] = true;
		q.offer(start);
		int cnt = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < to[cur].size(); i++) {
				if(visited[to[cur].get(i)])continue;
				visited[to[cur].get(i)] = true;
				cnt++;
				q.offer(to[cur].get(i));
			}
		}
		
		return cnt;
	}

}
