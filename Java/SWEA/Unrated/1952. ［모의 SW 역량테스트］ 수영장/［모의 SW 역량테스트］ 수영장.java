import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] price;
	static int[] monthPlan;
	static int ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			//입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			price = new int[4];
			monthPlan = new int[12];
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 12; i++) {
				monthPlan[i] = Integer.parseInt(st.nextToken());
			}
			//입력 끝
			
			ans = price[3];
			subset(0,0);
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	public static void subset(int month, int sum) {
		if(sum >= ans) return;
		if(month > 11) {
			if(ans > sum) ans = sum;
			return;
		}
		
		int thisMonth = 0;
		//1일권 사용
		thisMonth = monthPlan[month] * price[0];
		subset(month+1, sum+thisMonth);
		//한달권 사용
		thisMonth = price[1];
		subset(month+1, sum+thisMonth);
		//세달권 사용
		thisMonth = price[2];
		subset(month+3, sum+thisMonth);
	}

}
