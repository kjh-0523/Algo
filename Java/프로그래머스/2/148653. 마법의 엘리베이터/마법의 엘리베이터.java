class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(storey != 0){
            int remain = storey % 10;
            if(remain > 5){
                answer += 10 - remain;
                storey /= 10;
                storey++;
            }else if(remain < 5){
                answer += remain;
                storey /= 10;
            }else{
                if ((storey / 10) % 10 >= 5) {
                    answer += 10 - remain;
                    storey /= 10;
                    storey++;
                } else {
                    answer += remain;
                    storey /= 10;
                }
            }
        }
        
        return answer;
    }
}