class Solution {
    
    public int[] solution(int target) {
        int[][] dp = new int[target+1][2];
        for(int i = 0; i < target+1; i++){
            dp[i] = new int[]{100000, -1};
        }
        
        dp[0][0] = 0;
        dp[0][1] = 0;
        
        for(int i = 0; i < target + 1; i++){
            for(int s = 1; s < 21; s++){
                if(i + s > target) break;
                
                if(dp[i+s][0] > dp[i][0] + 1){
                    dp[i+s][0] = dp[i][0] + 1;
                    dp[i+s][1] = dp[i][1] + 1;
                }else if(dp[i+s][0] == dp[i][0] + 1 && dp[i+s][1] < dp[i][1] + 1){
                    dp[i+s][1] = dp[i][1] + 1;
                }
            }
            
            for(int d = 2; d < 41; d += 2){
                if(i + d > target) break;
                
                if(dp[i+d][0] > dp[i][0] + 1){
                    dp[i+d][0] = dp[i][0] + 1;
                    dp[i+d][1] = dp[i][1];
                }else if(dp[i+d][0] == dp[i][0] + 1 && dp[i+d][1] < dp[i][1]){
                    dp[i+d][1] = dp[i][1];
                }
            }
            
            for(int t = 3; t < 61; t += 3){
                if(i + t > target) break;
            
                if(dp[i+t][0] > dp[i][0] + 1){
                    dp[i+t][0] = dp[i][0] + 1;
                    dp[i+t][1] = dp[i][1];
                }else if(dp[i+t][0] == dp[i][0] + 1 && dp[i+t][1] < dp[i][1]){
                    dp[i+t][1] = dp[i][1];
                }
            }
            
            int b = 50;
            if(i+b <= target){
                if(dp[i+b][0] > dp[i][0] + 1){
                    dp[i+b][0] = dp[i][0] + 1;
                    dp[i+b][1] = dp[i][1] + 1;
                }else if(dp[i+b][0] == dp[i][0] + 1 && dp[i+b][1] < dp[i][1] + 1){
                    dp[i+b][1] = dp[i][1]+1;
                }
            }
        }
        
        return dp[target];
    }
}