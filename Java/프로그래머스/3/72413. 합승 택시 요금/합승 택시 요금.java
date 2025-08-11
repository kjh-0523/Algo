import java.util.*;

class Solution {
    public static final int INF = 20000001;
    public static int N;
    public static int[][] dist;
    public static ArrayList<int[]>[] nodes;
    
    public static class pos implements Comparable<pos>{
        int x, w;
        
        public pos(int x, int w){
            this.x = x;
            this.w = w;
        }
        
        @Override
        public int compareTo(pos o){
            return this.w - o.w;
        }
    }
    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        nodes = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++){
            nodes[i] = new ArrayList<>();
        }
        
        for(int[] f : fares){
            int from = f[0];
            int to = f[1];
            int w = f[2];
            nodes[from].add(new int[]{to,w});
            nodes[to].add(new int[]{from,w});
        }
        
        dist = new int[3][N+1];
        
        dist[0] = Dijkstra(s, new int[N+1]);
        // System.out.println(Arrays.toString(dist[0]));
        
        dist[1] = Dijkstra(a, new int[N+1]);
        
        dist[2] = Dijkstra(b, new int[N+1]);
        
        int answer = INF;
        for(int i = 1; i < N+1; i++){
            int total = dist[0][i] + dist[1][i] + dist[2][i];
            answer = Math.min(answer, total);
        }
        
        return answer;
    }
    
    public static int[] Dijkstra(int start, int[] temp){
        PriorityQueue<pos> pq = new PriorityQueue<>();
        pq.offer(new pos(start,0));
        Arrays.fill(temp, INF);
        temp[start] = 0;
        
        while(!pq.isEmpty()){
            pos cur = pq.poll();
            int x = cur.x;
            int w = cur.w;
            
            if (temp[x] < w) continue;
            
            for(int[] next : nodes[x]){
                int nx = next[0];
                int nw = next[1];
                if(temp[nx] > w + nw){
                    temp[nx] = w + nw;
                    pq.offer(new pos(nx, w + nw));
                }
            }
        }
        
        return temp;
    }
}