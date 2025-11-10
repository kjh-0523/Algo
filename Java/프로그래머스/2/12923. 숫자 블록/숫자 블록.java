class Solution {
    public int[] solution(long begin, long end) {
        int length = (int) (end - begin + 1);
        int[] answer = new int[length];
        for(int i = 0; i < length; i++){
            long n = begin + i;
            if(n == 1){
                answer[i] = 0;
                continue;
            }
            
            int max = 1;
            for(int j = 2; j < Math.sqrt(n)+1; j++){
                if(n % j == 0){
                    long num = n / j;
                    if(num < 10000001){
                        max = (int) num;
                        break;
                    }
                    if(j < 10000001){
                        max = j;
                    }
                }
            }
            answer[i] = max;
        }
        
        return answer;
    }
}