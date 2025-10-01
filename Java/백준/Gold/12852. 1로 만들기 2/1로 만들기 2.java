import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] dp = new int[n+1];
        int[] prev = new int[n+1];

        Arrays.fill(dp, 1000000);
        dp[n] = 0;
        prev[n] = n+1;
        for (int i = n; i > 1; i--) {
            if(i % 3 == 0 && dp[i/3] > dp[i] + 1){
                dp[i/3] = dp[i] + 1;
                prev[i/3] = i;
            }
            if(i % 2 == 0 && dp[i/2] > dp[i] + 1){
                dp[i/2] = dp[i] + 1;
                prev[i/2] = i;
            }
            if(dp[i-1] > dp[i] + 1){
                dp[i-1] = dp[i] + 1;
                prev[i-1] = i;
            }
        }

        System.out.println(dp[1]);
        int idx = 1;
        List<Integer> ans = new ArrayList<>();
        while(idx < n+1){
            ans.add(idx);
            idx = prev[idx];
        }

        StringBuilder sb = new StringBuilder();
        for(int i = ans.size()-1; i >= 0; i--){
            sb.append(ans.get(i)).append(" ");
        }
        System.out.println(sb);
    }
}