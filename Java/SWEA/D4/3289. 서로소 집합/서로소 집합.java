import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int n,m;
	static int c,a,b;
	static int[] parents;
	static String ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			make();
			ans = "";
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				c = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				if(c == 0) {
					union(a,b);
				}else if(c== 1) {
					int pa = find(a);
					int pb = find(b);
					if(pa == pb) ans +="1";
					else ans +="0";
				}
			}
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	public static void make() {
		parents = new int[n+1]; 
		for (int i = 1; i < n+1; i++) {
			parents[i] = i;
		}
	}
	public static int find(int a) {
		if(a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	public static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa == pb) return false;
		parents[pb] = pa;
		return true;
	}

}
