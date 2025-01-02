import java.util.*;
class Solution {
    public Deque<Integer> stack;
    public int[] solution(int[] prices) {
        stack = new LinkedList<>();
        int N = prices.length;
        int[] answer = new int[N];
        
        for(int i = 0; i < N; i++){
            if(!stack.isEmpty()){
                while(!stack.isEmpty() && prices[stack.peekLast()] > prices[i]){
                    for(int idx: stack){
                        answer[idx]++;
                    }
                    stack.pollLast();
                }
                    stack.offer(i);
                
            }else{
                stack.offer(i);
            }
        }
        int cnt = 0;
        while(!stack.isEmpty()){
            int idx = stack.pollLast();
            answer[idx] += cnt;
            cnt++;
        }
        
        return answer;
    }
}