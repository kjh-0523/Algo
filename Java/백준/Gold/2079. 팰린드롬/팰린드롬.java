import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/*시간 : ms 메모리 : kb*/
public class Main {
    static String pal;
    static int len;
    static boolean[][] check;
    static final int MAX = (int) 1e7;
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	pal = br.readLine();
    	len = pal.length();
    	check = new boolean[len+1][len+1];
    	
    	checkPal();
    	
    	int[] dp = new int[len+1];
    	
    	for (int i = 1; i < len+1; i++) {
    		dp[i] = MAX;
			for (int j = 1; j < i+1; j++) {
				if(check[j][i]) dp[i] = Math.min(dp[i], dp[j-1]+1);
			}
		}
    	System.out.println(dp[len]);
    }
    
    static void checkPal() {
    	for (int i = 1; i < len+1; i++) {
			check[i][i] = true;
		}
    	
    	for (int i = 1; i < len; i++) {
			if(pal.charAt(i) == pal.charAt(i-1)) {
				check[i][i+1] = true;
			}
		}
    	
    	for (int i = 2; i < len; i++) {
			for (int j = 1; j < len - i + 1; j++) {
				if(pal.charAt(j-1) == pal.charAt(j+i-1) && check[j+1][j+i-1]) {
					check[j][j+i] = true;
				}
			}
		}
    }

}
