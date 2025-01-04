import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static char[][] board;
	static int rx, ry, bx, by, hx, hy;
	static int ans;
	static int[][][][] visited;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static List<int[]> cor = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new char[n][m];
		visited = new int[n][m][n][m];
		String line;
		for (int i = 0; i < n; i++) {
			line = br.readLine();
			for (int j = 0; j < m; j++) {
				board[i][j] = line.charAt(j);
				if (board[i][j] == 'R') {
					rx = i;
					ry = j;
				} else if (board[i][j] == 'B') {
					bx = i;
					by = j;
				} else if (board[i][j] == 'O') {
					hx = i;
					hy = j;
				}
			}
		}
		
		int cnt = 1;
		cor.add(new int[] {rx,ry,bx,by,cnt});
		int nrx,nry,nbx,nby;
		boolean redIn, blueIn;
		visited[rx][ry][bx][by] = 1;
		
		outer : while(!cor.isEmpty()) {
			rx = cor.get(0)[0];
			ry = cor.get(0)[1];
			bx = cor.get(0)[2];
			by = cor.get(0)[3];
			cnt = cor.get(0)[4];
			cor.remove(0);
			
			if(cnt > 10) {
				ans = -1;
				break;
			}
			for (int i = 0; i < 4; i++) {
				redIn = false;
				blueIn = false;
				nrx = rx;
				nry = ry;
				while(board[nrx + dx[i]][nry + dy[i]] != '#') {
					nrx += dx[i];
					nry += dy[i];
					
					if(board[nrx][nry] == 'O') {
						redIn = true;
						break;
					}
				}
				
				nbx = bx;
				nby = by;
				while(board[nbx + dx[i]][nby + dy[i]] != '#') {
					nbx += dx[i];
					nby += dy[i];
					
					if(board[nbx][nby] == 'O') {
						blueIn = true;
						break;
					}
				}
				
				if(blueIn) {
					continue;
				}
				
				if(redIn && !blueIn) {
					ans = cnt;
					break outer;
				}
				
				if(nrx == nbx && nry == nby) {
					if(i == 0) {
						if(rx > bx) nbx-=dx[i];
						else nrx-=dx[i];
					}else if(i == 1) {
						if(rx < bx) nbx-=dx[i];
						else nrx-=dx[i];
					}else if(i == 2) {
						if(ry < by) nby -= dy[i];
						else nry -= dy[i];
					}else if(i == 3) {
						if(ry > by) nby -= dy[i];
						else nry -= dy[i];
					}
				}
				
				if(visited[nrx][nry][nbx][nby] == 0) {
					visited[nrx][nry][nbx][nby] = 1;
					cor.add(new int[] {nrx,nry,nbx,nby,cnt+1});
				}
			}
		}
		if(ans == 0) ans = -1;
		System.out.println(ans);

	}
}