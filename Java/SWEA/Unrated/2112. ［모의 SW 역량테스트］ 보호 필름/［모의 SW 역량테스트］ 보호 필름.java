import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int D, W, K;
	static int[][] board;
	static int[] A;
	static int[] B;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			board = new int[D][W];

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			A = new int[W];
			B = new int[W];
			for (int i = 0; i < W; i++) {
				A[i] = 0;
				B[i] = 1;
			}
			// 입력 끝
			result = Integer.MAX_VALUE;
			subset(0,0);
			System.out.println("#" + tc+" "+result);

		}
	}
	
	public static void subset(int depth, int cnt) {
		if(cnt >= result) return;
		if(depth == D) {
			if(check()) {
				result = cnt;
			}
			return;
		}
		int[] temp = Arrays.copyOf(board[depth], W);
		subset(depth+1, cnt);
		
		board[depth] = A;
		subset(depth+1, cnt+1);
		
		board[depth] = B;
		subset(depth+1, cnt+1);
		
		board[depth] = temp;
	}
	
	public static boolean check() {
		top : for (int i = 0; i < W; i++) {
			int cntA = 0, cntB = 0;
			for (int j = 0; j <D ; j++) {
				if(board[j][i] == 1) {
					cntB++;
					cntA = 0;
				}else {
					cntA++;
					cntB = 0;
				}
				if(cntA >= K || cntB>= K) {
					continue top;
				}
			}
			return false;
		}
	return true;
	}

}
