import java.util.*;

class Solution {
    public static int K, N, ans;
    public static int[][] re;
    
    public static class Participant implements Comparable<Participant>{
        int start, end;
        public Participant(int start, int end){
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Participant o){
            return this.end - o.end;
        }
        
    }
    public int solution(int k, int n, int[][] reqs) {
        K = k;
        N = n;
        re = reqs;
        
        ans = Integer.MAX_VALUE;
        dfs(0, N - K, new int[K]);
        return ans;
    }
    
    public static void dfs(int idx, int remain, int[] cnt){
        if(idx == K-1){
            cnt[idx] = remain + 1;
            ans = Math.min(ans, couns(cnt));
            return;
        }
        
        for(int i = 0; i < remain+1; i++){
            cnt[idx] = i+1;
            dfs(idx + 1, remain - i, cnt);
        }
    }
    
    public static int couns(int[] cnt){
        PriorityQueue<Participant>[] pq = new PriorityQueue[K];
        for(int i = 0; i < K; i++){
            pq[i] = new PriorityQueue<>();
        }
        
        int time = 0;
        for(int[] r : re){
            int s = r[0];
            int e = s + r[1];
            int t = r[2] - 1;
            
            if(pq[t].isEmpty()){
                pq[t].offer(new Participant(s, e));
            }else{
                if(pq[t].size() >= cnt[t]){
                    Participant cur = pq[t].poll();
                    if(cur.end > s){
                        int wait = cur.end - s;
                        time += wait;
                        s += wait;
                        e += wait;
                        if(time >= ans) return time;
                    }
                }
                pq[t].offer(new Participant(s,e));
            }
        }
        return time;
    }
    
}