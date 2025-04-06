import java.util.*;

class Solution {
    
    public static Deque<Integer> q;
    
    public int solution(int[] players, int m, int k) {
        int T = players.length;
        q = new ArrayDeque<>();
        int time = 0;
        int answer = 0;
        while(time < T){
            while(!q.isEmpty() && q.peek() <= time){
                q.poll();
            }
            
            if(players[time] / m > q.size()){
                int server = q.size();
                for(int i = 0; i < (players[time] / m)- server; i++){
                    q.offer(time + k);
                    answer++;
                }
            }
            time++;
        }
        return answer;
    }
}