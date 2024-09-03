import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N,M,house,ans,maxPrice, map[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			house = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) house++;
				}
			}
			int K = 1;
			ans = 0;
			maxPrice = 0;
			while(true) {
				int cost = (K*K) + (K-1) * (K-1);
				if(cost > house * M) break;
				calCost(K,cost);
				K++;
			}
			System.out.println("#" + tc+" "+ans);
		}
		
	}

	private static void calCost(int K,int cost) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int houseCnt = searchHouse(i,j,K);
				int price =  M * houseCnt - cost;
				if(price >= 0 ) {
					ans = Math.max(ans, houseCnt);
				}
				
			}
		}
	}

	private static int searchHouse(int x, int y, int K) {
		int cnt = 0;
		for (int i = -(K-1); i <K ; i++) {
			int idx = Math.abs(i);
			for (int j = -(K-1)+idx; j < K-idx; j++) {
				int nx = x + i;
				int ny = y + j;
				if(nx < 0 || nx > N-1 || ny < 0 || ny > N-1) continue;
				if(map[nx][ny] == 1) {
					cnt++;
				}
			}
		}
//		System.out.println(cnt);
		return cnt;
	}

}
