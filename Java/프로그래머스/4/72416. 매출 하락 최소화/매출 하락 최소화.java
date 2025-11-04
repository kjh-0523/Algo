import java.util.*;

class Solution {
    private static int[] Sales;
    private static int[][] dp;
    private static List<Integer>[] nodes;
    
    public int solution(int[] sales, int[][] links) {
        int n = sales.length;
        Sales = sales;
        dp = new int[n+1][2];
        
        nodes = new ArrayList[n+1];
        for(int i = 0; i < n+1; i++){
            nodes[i] = new ArrayList<>();
        }
        
        for(int[] link : links){
            nodes[link[0]].add(link[1]);
        }
        
        dfs(1);
        
        return Math.min(dp[1][1], dp[1][0]);
    }
    
    private static void dfs(int cur){
        dp[cur][1] = Sales[cur-1];
        
        if(nodes[cur].isEmpty()) return;
        
        boolean flag = false;
        int min = 1000000000;
        for(int next : nodes[cur]){
            dfs(next);
            
            if(dp[next][1] > dp[next][0]){
                if(min > dp[next][1] - dp[next][0]){
                    min = dp[next][1] - dp[next][0];
                }
            }else flag = true;
            
            dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
            dp[cur][0] += Math.min(dp[next][0], dp[next][1]);
        }
        
        if(!flag){
            dp[cur][0] += min;
        }
    }
}