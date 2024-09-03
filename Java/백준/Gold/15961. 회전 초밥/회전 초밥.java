import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

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
		HashMap<Integer, Integer> eat = new HashMap<>();
		eat.put(c, 1);
		for (int i = 0; i < k; i++) {
			eat.put(suisi[i], eat.getOrDefault(suisi[i], 0)+1);
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			if(eat.get(suisi[i]) == 1) eat.remove(suisi[i]);
			else eat.put(suisi[i], eat.get(suisi[i])-1);
			eat.put(suisi[(i+k)%N], eat.getOrDefault(suisi[(i+k)%N], 0)+1);
			max = Math.max(max, eat.size());
		}
		System.out.println(max);
	}

}
