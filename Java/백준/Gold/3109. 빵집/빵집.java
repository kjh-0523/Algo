import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C, cnt;
	static boolean flag;
	static Boolean[][] isSelected;
	static Boolean[][] map;
	static int[] dx = { -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		isSelected = new Boolean[R][C];
		map = new Boolean[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				if (line.charAt(j) == 'x') {
					map[i][j] = true;
				} else
					map[i][j] = false;

			}
		} // 입력 끝
		for (int i = 0; i < R; i++) {
			flag = false;
			dfs(i, 0);
		}

		for (int i = 0; i < R; i++) {
			if (map[i][C - 1])
				cnt++;
		}

		System.out.println(cnt);
	}

	public static void dfs(int x, int y) {
		if (y == C - 1) {
			flag = true;
			return;
		}
		int nx, ny;
		for (int i = 0; i < 3; i++) {
			if (flag) {
				return;
			}
			nx = x + dx[i];
			ny = y + 1;
			if (nx < R && nx > -1 && ny < C && ny > -1 && !map[nx][ny]) {
				map[nx][ny] = true;
				dfs(nx, ny);
			}

		}
	}

}