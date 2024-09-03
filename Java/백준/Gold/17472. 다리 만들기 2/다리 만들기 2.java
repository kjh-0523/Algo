import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int[] parents;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static ArrayList<Node> bridge;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < M; j++) {
				map[i][j] = -Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == -1) {
					makeMap(i,j,cnt++);
				}
			}
		}
		bridge = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != 0) {
					for (int k = 0; k < 4; k++) {
						if(i+dx[k] > -1 && i+dx[k] < N && j + dy[k] > -1 && j + dy[k] < M && map[i+dx[k]][j+dy[k]] == 0) {
							makeBridge(i,j,k);
						}
					}
				}
			}
		}//간선 만들기
		
		//크루스칼
		make(cnt-1);
		int ans = 0, con = 0;
		Collections.sort(bridge);
		for (Node b : bridge) {
			if(union(b.from,b.to)) {
				ans += b.weight;
				if(++con == cnt-2) break;
			}
		}
		if(con != cnt-2) System.out.println(-1);
		else System.out.println(ans);
		
	}
	
	public static void makeMap(int x, int y, int cnt) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x,y});
		map[x][y] = cnt;
		int nx,ny;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			for (int i = 0; i < 4; i++) {
				nx = now[0] + dx[i];
				ny = now[1] + dy[i];
				if(nx < 0 || nx > N-1 || ny < 0 || ny > M-1 || map[nx][ny] != -1) continue;
				map[nx][ny] = cnt;
				q.offer(new int[] {nx,ny});
			}
		}
	}
	
	public static void makeBridge(int x, int y, int d) {
		int nx = x,ny = y;
		int len = 0;
		while(true) {
			nx += dx[d];
			ny += dy[d];
			if(nx < 0 || nx > N-1 || ny < 0 || ny > M-1) return;
			if(map[nx][ny] != 0) break;
			len++;
		}
		if(len > 1) {
			bridge.add(new Node(map[x][y],map[nx][ny],len));
		}
	}
	public static void make(int V) {
		parents = new int[V+1];
		for (int i = 1; i < V+1; i++) {
			parents[i] = i;
		}
	}
	
	public static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int ar = find(a);
		int br = find(b);
		if(ar == br) return false;
		parents[br] = ar;
		return true;
	}
	
	public static class Node implements Comparable<Node>{
		int from,to, weight;
		public Node(int from,int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight-o.weight;
		}
	}

}
