import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
	static StringBuilder sb;
	static int[][] gear;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			gear = new int[4][8];
			int K = Integer.parseInt(br.readLine());
			//기어 값 입력
			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < 8; j++) {
					gear[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				int idx = Integer.parseInt(st.nextToken())-1;
				int dir = Integer.parseInt(st.nextToken());
				int[] dirs = new int[4];
				dirs[idx] = dir;
				// 방향 정하기
				for (int j = 1; j < 4; j++) {
					int r = idx+j;
					int l = idx-j;
					if(r > 0 && r < 4) {
						dirs[r] = dirs[r-1] * setDir(r-1, 1);
					}
					if(l > -1 && l < 3) {
						dirs[l] = dirs[l+1] * setDir(l+1, -1);
					}
				}
				
				//방향에 따라 회전
				for (int j = 0; j < 4; j++) {
					if(dirs[j] == 0) continue;
					Rotate(dirs[j], j);
				}
//				for (int j = 0; j < 4; j++) {
//					System.out.println(Arrays.toString(gear[j]));
//				}
//				System.out.println();
			}
			//발간 화살표 위치 점수 계산
			int sum = 0;
			for (int i = 0; i < 4; i++) {
				if(gear[i][0] == 1) {
					sum |= 1<<i;
				}
			}
			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static int setDir(int idx, int dir) {
		if(dir > 0) {
			if(gear[idx][2] != gear[idx+1][6]) {
				return -1;
			}else {
				return 0;
			}
		}else {
			if(gear[idx][6] != gear[idx-1][2]) {
				return -1;
			}else {
				return 0;
			}
		}
	}
	
	static void Rotate(int dir, int idx) {
		if(dir == 1) {
			int temp = gear[idx][7];
			for (int i = 6; i > -1; i--) {
				gear[idx][i+1] = gear[idx][i];
			}
			gear[idx][0] = temp;
		}else {
			int temp = gear[idx][0];
			for (int i = 1; i < 8; i++) {
				gear[idx][i-1] = gear[idx][i];
			}
			gear[idx][7] = temp;
		}
	}
}

