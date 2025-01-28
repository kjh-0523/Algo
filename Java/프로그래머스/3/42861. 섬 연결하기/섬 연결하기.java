import java.util.*;

class Solution {
    public static boolean[] visited;
    public static int[] list;
    public static PriorityQueue<Node> pq;
    public static class Node implements Comparable<Node>{
        int start,end,weight;
        
        public Node(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }
    
    public static int find(int a){
        if(list[a] == a) return a;
        return list[a] = find(list[a]);
    }
    
    public static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        
        if(aRoot == bRoot) return false;
        
        list[bRoot] = aRoot;
        return true;
    }
    public int solution(int n, int[][] costs) {
        visited = new boolean[n];
        list = new int[n];
        for(int i = 0; i < n; i++){
            list[i] = i;
        }
        pq = new PriorityQueue<>();
        for(int[] cost : costs){
            pq.offer(new Node(cost[0], cost[1], cost[2]));
        }
        int answer = 0;
        int cnt = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(union(cur.start, cur.end)){
                answer += cur.weight;
                cnt++;
            }
            if(cnt == n-1) break;
        }
        
        return answer;
    }
}