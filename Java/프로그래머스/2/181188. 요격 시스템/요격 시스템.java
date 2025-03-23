import java.util.*;

class Solution {

    public static class Missile implements Comparable<Missile>{
        int s,e;
        
        public Missile(int s, int e){
            this.s = s;
            this.e = e;
        }
        
        @Override
        public int compareTo(Missile o){
            return o.e - this.e;
        }
    }
    public int solution(int[][] targets) {
        
        PriorityQueue<Missile> missiles = new PriorityQueue<>();
        int N = targets.length;
        
        for(int i = 0 ; i < N; i++){
            missiles.offer(new Missile(targets[i][0], targets[i][1]));
        }
        
        int answer = 1;
        Missile first = missiles.poll();
        int start = first.s;
        int end = first.e;
        while(!missiles.isEmpty()){
            Missile cur = missiles.poll();
            if(cur.e <= start){
                start = cur.s;
                end = cur.e;
                answer++;
                continue;
            }
            
            start = Math.max(start, cur.s);
            end = Math.min(end, cur.e);
        }
        return answer;
    }
}