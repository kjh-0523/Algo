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

        dp = new int[N+1];
        Arrays.fill(dp, (int)1e9);
        dp[1] = 0;
        for(int i = 1; i < N; i++){
            if(i+1 < N+1) dp[i+1] = Math.min(dp[i] + 1, dp[i+1]);
            if(i * 2 < N+1) dp[i * 2] = Math.min(dp[i] + 1, dp[i * 2]);
            if(i * 3 < N+1) dp[i * 3] = Math.min(dp[i] + 1, dp[i*3]);
        }

        System.out.println(dp[N]);
    }
}
