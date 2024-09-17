import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/*시간 : 92ms 메모리 : 11872kb*/
public class Main {
	static Long N, P, Q;
	static HashMap<Long, Long> memo;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Long.parseLong(st.nextToken());
		P = Long.parseLong(st.nextToken());
		Q = Long.parseLong(st.nextToken());
		
		memo = new HashMap<Long, Long>();
		memo.put(0l, 1l);
		System.out.println(dp(N));
		
	}
	
	public static long dp(long l) {
		if(memo.get(l) != null) return memo.get(l);
		long cal = dp(l/P) + dp(l/Q);
		memo.put(l, cal);
		return cal;
	}
}