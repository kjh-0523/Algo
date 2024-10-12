import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int W;
	static int H;
	static int[][] arr;
	static int[] nums;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0,0,-1, 1};
	static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case<=T; test_case++) {
			sb.append("#"+test_case+" ");
			ans = (int)1e9;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			arr = new int[H][W];
			nums = new int[N];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			perm(0);
			
			sb.append(ans+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static void perm(int depth) {
		if(depth== N) {
			drop();
			return;
		}
		
		for(int i=0; i<W; i++) {
			nums[depth]= i;
			perm(depth+1);
		}
	}
	
	static void drop() {
	    int[][] carr = new int[H][W];
	    for (int i = 0; i < H; i++) {
	        carr[i] = Arrays.copyOf(arr[i], W);
	    }

	    top:
	    for (int i = 0; i < N; i++) {
	        int idx = 0;
	        while (idx < H && carr[idx][nums[i]] == 0) {
	            idx++;
	        }
	        if (idx == H) {
	            continue top;
	        }

	        Queue<Coo> q = new ArrayDeque<>();
	        q.add(new Coo(idx, nums[i], carr[idx][nums[i]]));
	        carr[idx][nums[i]] = 0;

	        while (!q.isEmpty()) {
	            Coo now = q.poll();

	            for (int j = 0; j < 4; j++) {
	                for (int k = 1; k < now.power; k++) {
	                    int nx = now.x + dx[j] * k;
	                    int ny = now.y + dy[j] * k;

	                    if (nx < 0 || ny < 0 || nx >= H || ny >= W || carr[nx][ny] == 0) {
	                        continue;
	                    }
	                    if (carr[nx][ny] > 1) {
	                        q.add(new Coo(nx, ny, carr[nx][ny]));
	                    }
	                    carr[nx][ny] = 0;
	                }
	            }
	        }

	        for (int col = 0; col < W; col++) {
	            int row = H - 1;
	            for (int j = H - 1; j >= 0; j--) {
	                if (carr[j][col] != 0) {
	                    carr[row--][col] = carr[j][col];
	                }
	            }
	            while (row >= 0) {
	                carr[row--][col] = 0;
	            }
	        }
	    }

	    int cnt = 0;
	    for (int i = 0; i < H; i++) {
	        for (int j = 0; j < W; j++) {
	            if (carr[i][j] != 0) {
	                cnt++;
	            }
	        }
	    }
	    ans = Math.min(ans, cnt);
	}

	
	
	static class Coo{
		int x;
		int y;
		int power;
		public Coo(int x, int y, int power) {
			super();
			this.x = x;
			this.y = y;
			this.power = power;
			
		}
		
	}

}
