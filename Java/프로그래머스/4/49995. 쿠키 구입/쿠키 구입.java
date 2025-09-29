class Solution {
    public int solution(int[] cookie) {
        int N = cookie.length;
        
        int ans = 0;
        for(int i = N-1; i > 0; i--){
            int sum1 = 0;
            
            for(int j = i; j > 0; j--){
                sum1 += cookie[j];
                if(sum1 <= ans) continue;
                int sum2 = 0;
                for(int k = j-1; k > -1; k--){
                    sum2 += cookie[k];
                    if(sum2 >= sum1) break;
                }
                if(sum1 == sum2){
                    ans = Math.max(sum1, ans);    
                }
            }
        }
        return ans;
    }
}