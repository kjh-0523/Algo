import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n+1][m+1];
        int[] nums = new int[n+1];
        for(int i = 1; i < n+1; i++){
            nums[i] = Integer.parseInt(br.readLine());
        }
        nums[0] = 1;
        for(int i = 1; i < n+1; i++){
            if(nums[i] == 1) dp[i][0] = dp[i-1][0] + 1;
            else dp[i][0] = dp[i-1][0];
        }

        for(int i = 1; i < m+1; i++){
            int pos = i % 2 == 1 ? 2 : 1;
            for(int j = i; j < n+1; j++){
                if(nums[j] == pos && nums[j-1] != pos){
                    dp[j][i] = Math.max(dp[j-1][i-1] + 1, dp[j-1][i]+1);
                }else if(nums[j] == pos && nums[j-1] == pos){
                    dp[j][i] = dp[j-1][i] + 1;
                }else{
                    dp[j][i] = dp[j-1][i];
                }
            }
        }
        // for(int i = 0; i < n+1; i++){
        //     System.out.println(Arrays.toString(dp[i]));
        // }
        int ans = 0;
        for(int i = 0; i < m+1; i++){
            ans = Math.max(ans, dp[n][i]);
        }
        System.out.println(ans);
    }
}