import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/* 메모리 : 49404kb, 시간 :684ms */
public class Main {
	public static class shark {
		int speed;
		int dir;
		int size;

		public shark(int speed, int dir, int size) {
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}

		public void SetDir(int dir) {
			this.dir = dir;
		}

	}

	static int R, C, M;
	static int[] dx = { 0, -1, 1, 0 };
	static int[] dy = { -1, 0, 0, 1 };// 0왼, 1위,2아래,3오
	static shark[][] map;
	static int manY, cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new shark[R + 1][C + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) % 4;
			int z = Integer.parseInt(st.nextToken());
			map[x][y] = new shark(s, d, z);
		}
		catchShark();
		System.out.println(cnt);
	}

	public static void catchShark() {
		for (int i = 1; i < C+1; i++) {
//			for (int j = 0; j < R+1; j++) {
//				for (int k = 0; k < C+1; k++) {
//					if(map[j][k] != null) System.out.print(map[j][k].dir + " ");
//					else System.out.print("5 ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			for (int j = 1; j < R + 1; j++) {
				if (map[j][i] != null) {
					cnt += map[j][i].size;
					map[j][i] = null;
					break;
				}
			}
			moveShark();
		}
	}

public static void moveShark() {
    shark[][] newMap = new shark[R + 1][C + 1];
    for (int i = 1; i < R + 1; i++) {
        for (int j = 1; j < C + 1; j++) {
            if (map[i][j] != null) {
                int nx = 0, ny = 0;
                if(map[i][j].dir == 0) {
                	int s = map[i][j].speed %(2*C -2);
                	nx = i;
                	if(j - s > 0) {
                		ny = j-s;
                	}else if(j-s > -(C-1)) {
                		ny = 2-(j-s);
                		map[i][j].SetDir(3-map[i][j].dir);
                	}else {
                		ny = (2*C-2)+j-s;
                	}
                }else if(map[i][j].dir == 1) {
                	int s = map[i][j].speed %(2*R -2);
                	ny = j;
                	if(i - s > 0) {
                		nx = i-s;
                	}else if(i-s > -(R-1)) {
                		nx = 2-(i-s);
                		map[i][j].SetDir(3-map[i][j].dir);
                	}else {
                		nx = (2*R-2)+i-s;
                	}
                }else if(map[i][j].dir == 2) {
                	int s = map[i][j].speed %(2*R -2);
                	ny = j;
                	if(i + s <= R) {
                		nx = i+s;
                	}else if(i+s < 2*R) {
                		nx = 2*R - i-s;
                		map[i][j].SetDir(3-map[i][j].dir);
                	}else {
                		nx = i+s-(2*R-2);
                	}
                }else if(map[i][j].dir == 3) {
                	int s = map[i][j].speed %(2*C -2);
                	nx = i;
                	if(j + s <= C) {
                		ny = j+s;
                	}else if(j+s < 2*C) {
                		ny = 2*C - j-s;
                		map[i][j].SetDir(3-map[i][j].dir);
                	}else {
                		ny = j+s-(2*C-2);
                	}
                }
                if (newMap[nx][ny] != null) {
                    if (newMap[nx][ny].size < map[i][j].size) {
                        newMap[nx][ny] = map[i][j];
                    }
                }else {
                    newMap[nx][ny] = map[i][j];
                }
            }
        }
    }
//    for (int k = 0; k < R; k++) {
//    	System.out.println(Arrays.toString(newMap[k]));
//    }
    map = newMap;
}
}