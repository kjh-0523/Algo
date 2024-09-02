import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static long ans;
	public static int[][] weight;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		weight = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				weight[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = Integer.MAX_VALUE;
		dfs(0,new boolean[N],0,0);
		System.out.println(ans);
		
	}
	public static void dfs(int v, boolean[] visited,int w, int cnt) {
		if(w > ans) {
			return;
		}
		if(cnt == N-1) {
			if(weight[v][0] == 0) return;
			if(w+weight[v][0] < ans) {
				ans = w+weight[v][0];
			}
			return;
		}
		visited[v] = true;
		for (int i = 0; i < N; i++) {
			if(visited[i] || weight[v][i] == 0) continue;
			dfs(i,visited,w+weight[v][i],cnt+1);
		}
		visited[v] = false;
	}
}
