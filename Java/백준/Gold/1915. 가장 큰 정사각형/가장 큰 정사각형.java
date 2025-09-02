import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N][M];

        int max = 0;
        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < M; j++){
                dp[i][j] = line.charAt(j) - '0';
                if(i > 0 && j > 0){
                    if(dp[i][j] > 0 && dp[i-1][j] > 0 && dp[i][j-1] > 0 && dp[i-1][j-1] > 0){
                        dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                    }
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        System.out.println(max * max);
    }
}
