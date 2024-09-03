import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;
/*시간 : 868ms 메모리 : 313076*/
public class Main {
	static int N, d, k,c;
	static int[] suisi;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		suisi = new int[N];
		for (int i = 0; i < N; i++) {
			suisi[i] = Integer.parseInt(br.readLine());
		}
		int[] eat = new int[d+1];
		eat[c]++;
		int max = 1;
		for (int i = 0; i < k; i++) {
			if(eat[suisi[i]] == 0) max += 1;
			eat[suisi[i]]++;
		}
		int ans = 0;
		for (int i = 0; i < N; i++) {
			eat[suisi[i]]--;
			if(eat[suisi[i]] == 0) max--;
			eat[suisi[(i+k)%N]]++;
			if(eat[suisi[(i+k)%N]] == 1) max++;
			ans = Math.max(ans, max);
		}
		System.out.println(ans);
	}
}
