import java.util.*;

class Solution {
    
    public static class Node implements Comparable<Node>{
        int x, y, cost;
        
        public Node(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
    
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,-1,1};
    
    public int solution(int[][] land, int height) {
        int n = land.length;
        
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(0,0,0));
        boolean[][] visited = new boolean[n][n];
        
        int answer = 0;
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            if(visited[cur.x][cur.y]) continue;
            
            visited[cur.x][cur.y] = true;
            answer += cur.cost;
            
            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if(nx < 0 || nx > n-1 || ny < 0 || ny > n-1 || visited[nx][ny]) continue;
                
                int cost = Math.abs(land[nx][ny] - land[cur.x][cur.y]);
                
                if(cost > height){
                    q.offer(new Node(nx,ny, cost));
                }else{
                    q.offer(new Node(nx,ny, 0));
                }
            }
        }
        
        return answer;
    }
}