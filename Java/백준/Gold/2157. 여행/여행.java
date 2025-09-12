import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        final int NEG = -1;
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 0; i <= N; i++) Arrays.fill(dp[i], NEG);
        
        dp[1][1] = 0;

        List<int[]>[] routes = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) routes[i] = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a >= b) continue;
            routes[a].add(new int[]{b, c});
        }

        for (int cnt = 1; cnt <= M - 1; cnt++) {
            for (int city = 1; city <= N; city++) {
                if (dp[city][cnt] == NEG) continue;
                for (int[] r : routes[city]) {
                    int nxt = r[0];
                    int cost = r[1];
                    dp[nxt][cnt + 1] = Math.max(dp[nxt][cnt + 1], dp[city][cnt] + cost);
                }
            }
        }

        int ans = 0;
        for (int cnt = 1; cnt <= M; cnt++) {
            if (dp[N][cnt] != NEG) ans = Math.max(ans, dp[N][cnt]);
        }
        System.out.println(ans);
    }
}
