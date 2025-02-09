import java.util.*;

class Solution {
    public static final int MOD = 10007;
    public int solution(int n, int[] tops) {
        int[] dp = new int[2 * n + 1];
        dp[0] = 1;
        if(tops[0] == 1){
            dp[1] = 3;
        }else{
            dp[1] = 2;
        }
        dp[2] = dp[0] + dp[1];
        for(int i = 3; i < 2 * n + 1; i++){
            if(i % 2 == 0){
                dp[i] = (dp[i-1] + dp[i-2])%MOD;
            }else{
                if(tops[i/2] == 1){
                    dp[i] = (dp[i-1] * 2 + dp[i-2])%MOD;
                }else{
                    dp[i] = (dp[i-1] + dp[i-2])%MOD;
                }
            }
        }
        return dp[2 * n]%MOD;
    }
}