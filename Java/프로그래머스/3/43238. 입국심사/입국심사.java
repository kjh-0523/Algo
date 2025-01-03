class Solution {
    public long solution(int n, int[] times) {
        Long MAX = 1000000000000000000L;
        Long left = 0L;
        Long right = MAX;
        
        Long mid = 0L;
        long answer = 0L;
        
        while(left<=right){
            mid = (left + right)/2;
            
            Long sum = 0L;
            for(int t : times){
                sum += mid / t;
            }
            
            if(sum >= n){
                answer = mid;
                right = mid - 1;
            }else if( sum < n){
                left = mid + 1;
            }
        }
        return answer;
    }
}