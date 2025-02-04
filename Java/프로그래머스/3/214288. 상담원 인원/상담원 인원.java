import java.util.*;

class Solution {
    public static int answer;
    public static int k,n;
    public static int[][] reqs;
    public static class Participant implements Comparable<Participant>{
        int start, end;
        public Participant(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Participant o1){
            // if(this.end == o1.end) return this.start - this.start;
            return this.end - o1.end;
        }
    }
    public int solution(int K, int N, int[][] Reqs) {
        k = K;
        n = N;
        reqs = Reqs;
        
        answer = Integer.MAX_VALUE;
        dfs(n - k, 0, new int[k]);
        return answer;
    }
    
    public static void dfs(int remain, int idx, int[] cnt){
        if(idx == k-1){
            cnt[idx] = remain+1;
            answer = Math.min(answer,couns(cnt));
            // System.out.println(Arrays.toString(cnt) + answer);
            return;
        }
        
        for (int i = 0; i <= remain; i++) {
            cnt[idx] = i+1;
            dfs(remain - i, idx + 1, cnt);
        }
        
    }
    
    public static int couns(int[] cnt){
        PriorityQueue<Participant>[] q = new PriorityQueue[k];
        for(int i = 0; i < k; i++){
            q[i] = new PriorityQueue<>();
        }
        
        int time = 0;
        for(int[] req : reqs){
            int s = req[0];
            int e = s + req[1];
            int t = req[2]-1;
            
            if(q[t].isEmpty()){
                q[t].offer(new Participant(s,e));
                continue;
            }
            
            if(q[t].size() >= cnt[t]){
                Participant cur = q[t].poll();
                if(cur.end > s){
                    int remain = cur.end - s;
                    time += remain;
                    s += remain;
                    e += remain;
                    if(time >= answer) return time;
                }
            }
            q[t].offer(new Participant(s,e));
        }
        return time;
    }
}