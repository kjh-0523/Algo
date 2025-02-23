import java.util.*;
import java.io.*;

public class Main {
    public static int N;
    public static int[] rooms;
    public static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        rooms = new int[N];
        dp = new int[N][2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            rooms[i] = Integer.parseInt(st.nextToken());
        }
        dp[0][0] = rooms[0];
        dp[0][1] = 1;

        dp[1][0] = Math.max(dp[0][0], dp[0][1]) + rooms[1];
        dp[1][1] = dp[0][0] + 1;

        for (int i = 2; i < N-1; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]) + rooms[i];
            dp[i][1] = dp[i - 1][0] + 1;
        }
        if(dp[0][0] >= dp[0][1]) {
            dp[N-1][0] = Math.max(dp[N - 2][0], dp[N - 2][1]) + rooms[N-1];
            dp[N-1][1] = dp[N - 2][0] + 1;
        }else{
            dp[N-1][0] = Math.max(dp[N - 2][0], dp[N - 2][1]) + rooms[N-1];
        }
        System.out.println(Math.max(dp[N - 1][0], dp[N - 1][1]));
    }
}