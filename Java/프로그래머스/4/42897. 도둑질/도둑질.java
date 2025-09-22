class Solution {
    public int solution(int[] money) {
        int n = money.length;
        int[] dpF = new int[n]; 
        dpF[0] = money[0];
        dpF[1] = Math.max(money[1], money[0]);
        for(int i = 2; i < n-1; i++) {
            dpF[i] = Math.max(money[i] + dpF[i-2], dpF[i-1]);
        }
        
        int[] dpL = new int[n]; 
        dpL[0] = 0;
        dpL[1] = money[1];
        for(int i = 2; i < n; i++) {
            dpL[i] = Math.max(money[i] + dpL[i-2], dpL[i-1]);
        }
        
        return Math.max(dpL[n-1], dpF[n-2]);
    }
}