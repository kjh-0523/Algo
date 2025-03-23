class Solution {
    public long solution(int[] sequence) {
        int N = sequence.length;
        
        int[] pulse = new int[N];
        int[] rPulse = new int[N];
        for(int i = 0; i < N; i++){
            if(i % 2 == 0){
                pulse[i] = sequence[i];
                rPulse[i] = sequence[i] * -1;
            }else{
                pulse[i] = sequence[i] * -1;
                rPulse[i] = sequence[i];
            }
        }
        
        long ans = 0;

        long maxSum = pulse[0];
        long currentSum = pulse[0];

        for (int i = 1; i < N; i++) {
            currentSum = Math.max(pulse[i], currentSum + pulse[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        
        long rMaxSum = rPulse[0];
        long rCurrentSum = rPulse[0];

        for (int i = 1; i < N; i++) {
            rCurrentSum = Math.max(rPulse[i], rCurrentSum + rPulse[i]);
            rMaxSum = Math.max(rMaxSum, rCurrentSum);
        }
        
        ans = Math.max(maxSum, rMaxSum);
        return ans;
    }
}