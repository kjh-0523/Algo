import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int N, M, C, ans;
	static int[][] map;
	static int[] honey;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // input
			ans = 0;
			honey = new int[2];
			comb(0, 0);
			System.out.println("#" + tc + " " +ans);
		}
	}

	public static void comb(int depth, int start) {
		if (depth == 2) {
//			System.out.println(1);
			calMoney();
			return;
		}
		for (int i = start; i < N * N; i++) {
			if ((i % N) + M - 1 < N) {
				honey[depth] = i;
				comb(depth + 1, i + M);
			}
		}
	}

	public static void calMoney() {
		int x1 = honey[0] / N;
		int y1 = honey[0] % N;
		int x2 = honey[1] / N;
		int y2 = honey[1] % N;
//		System.out.println(x1 + " "+ y1+ " " + x2+" "+y2);
		int[] nums1 = new int[M];
		int[] nums2 = new int[M];
		for (int i = 0; i < 1 << M; i++) {
			int len1 = 0;
			int len2 = 0;
			for (int k = 0; k < M; k++) {
				if ((i & 1 << k) != 0) {
					nums1[len1++] = map[x1][y1 + k];
				}
			}
			for (int j = 0; j < 1 << M; j++) {
				len2 = 0;
				for (int k = 0; k < M; k++) {
					if ((j & 1 << k) != 0) {
						nums2[len2++] = map[x2][y2 + k];
					}
				}
				int sum1 = 0, sum2 = 0;
				for (int j1 = 0; j1 < len1; j1++) {
					sum1 += nums1[j1];
				}
				for (int j1 = 0; j1 < len2; j1++) {
					sum2 += nums2[j1];
				}
				if (sum1 <= C && sum2 <= C) {
					int price = 0;
					for (int j1 = 0; j1 < len1; j1++) {
						price+=nums1[j1] * nums1[j1];
					}
					for (int j1 = 0; j1 < len2; j1++) {
						price+=nums2[j1] * nums2[j1];
					}
//				if(ans < price) {
//					System.out.println();
//					System.out.println(price);
//					System.out.println(Arrays.toString(nums1)+ x1+" "+y1);
//					System.out.println(Arrays.toString(nums2) + x2+" "+y2);
//					System.out.println();
//			}
					ans = Math.max(ans, price);
				}
			}
			// 값 비교
		}//부분집합
	}
}