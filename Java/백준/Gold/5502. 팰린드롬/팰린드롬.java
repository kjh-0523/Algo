import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String pal, revPal;
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	pal = br.readLine();
    	StringBuilder sb = new StringBuilder(pal);
    	revPal = sb.reverse().toString();
    	
    	int diff = lcs();
    	
    	System.out.println(N - diff);
    }
    
    public static int lcs() {
    	int l1 = pal.length();
    	int[][] dp = new int[l1+1][l1+1];
    	
    	for (int i = 1; i < l1+1; i++) {
			for (int j = 1; j < l1+1; j++) {
				if(pal.charAt(i-1) == revPal.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				}else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
    	
    	return dp[l1][l1];
    }
}
