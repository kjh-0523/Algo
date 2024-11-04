import java.io.*;
import java.util.*;

public class Main {
	static int N, blackAns, whiteAns;
	static boolean[][] initMap;
	static boolean[] digit1, digit2;
	static List<int[]> blaskBishop;
	static List<int[]> whiteBishop;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		initMap = new boolean[N][N];
		digit1 = new boolean[2 * N];
		digit2 = new boolean[2 * N];
		blaskBishop = new ArrayList<>();
		whiteBishop = new ArrayList<>();
		
		StringTokenizer st = null;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j < N; j++) {
				int input = Integer.parseInt(st.nextToken());
				if(input == 1) {
					if((i+j) %2 == 0) {
						blaskBishop.add(new int[] {i,j});
					}else {
						whiteBishop.add(new int[] {i,j});
					}
				}else {
					initMap[i][j] = true;
				}
			}
		}
		blackAns = 0;
		whiteAns = 0;
		search(blaskBishop, 0, 0, true);
		search(whiteBishop, 0, 0, false);
		
		System.out.println(blackAns + whiteAns);
	}

	public static void search(List<int[]> bishop, int idx, int cnt, boolean isBlack) {
		if(idx == bishop.size()) {
			if(isBlack) blackAns = Math.max(blackAns, cnt);
			else whiteAns = Math.max(whiteAns, cnt);
			return;
		}
		if(cnt + bishop.size() - idx - 1 < (isBlack? blackAns : whiteAns)) {
			return;
		}
		
		//퀸 선택
		int x = bishop.get(idx)[0];
		int y = bishop.get(idx)[1];
		if(!initMap[x][y] && !digit1[x+y] && !digit2[x-y+N-1]) {
			digit1[x+y] = true;
			digit2[x-y+N-1] = true;
			
			search(bishop, idx + 1, cnt +1, isBlack);
			
			digit1[x+y] = false;
			digit2[x-y+N-1] = false;
		}
		
		
		//퀸 선택 X
		search(bishop, idx+1, cnt, isBlack);
	}
}
