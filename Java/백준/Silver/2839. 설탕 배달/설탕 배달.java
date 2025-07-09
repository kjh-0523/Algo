import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = -1;
        dp[2] = -1;
        dp[3] = 1;
        if(N > 3) dp[4] = -1;
        for(int i = 5; i <= N; i++) {
            if(i % 5 == 0) dp[i] = dp[i-5] + 1;
            else if(i % 3 == 0) dp[i] = dp[i-3] + 1;
            else {
                int num1 = dp[i-3];
                int num2 = dp[i-5];
                if(num1 == -1 && num2 == -1) dp[i] = -1;
                else if(num1 == -1) dp[i] = dp[i-5];
                else if(num2 == -1) dp[i] = dp[i-3];
                else dp[i] = Math.min(num1, num2) + 1;
            }
        }
        System.out.println(dp[N]);
    }
}
