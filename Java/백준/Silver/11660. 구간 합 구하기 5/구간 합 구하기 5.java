import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*시간 : 88ms 메모리 : 12668kb*/
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] sum = new int[N * N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N+1; j++) {
				sum[N*i +j] = Integer.parseInt(st.nextToken()) + sum[N*i+j-1];
			}
		}
			
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken())-1;
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken())-1;
			int y2 = Integer.parseInt(st.nextToken());
			int ans = 0;
			for (int j = x1; j < x2+1; j++) {
				ans += sum[N*j + y2] - sum[N*j+y1-1];
			}
			System.out.println(ans);
		}
		
	}
}