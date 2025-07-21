import java.util.*;

class Solution {
    
    public static int N, M, ans;
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,1,-1};
    public static int[][] map;
    public static Set<state> visited;
    public static pos goalRed;
    public static pos goalBlue;
    public static pos initRed;
    public static pos initBlue;
    
    public static class pos {
        int x, y;
        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o){
            if(this == o) return true;
            if(!(o instanceof pos)) return false;
            pos p = (pos) o;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode(){
            return Objects.hash(x, y);
        }
    }
    
    public static class state {
        int cnt;
        pos red, blue;
        Set<pos> redVisited;
        Set<pos> blueVisited;

        public state(pos red, pos blue, int cnt, Set<pos> redVisited, Set<pos> blueVisited){
            this.cnt = cnt;
            this.red = red;
            this.blue = blue;
            this.redVisited = redVisited;
            this.blueVisited = blueVisited;
        }

        @Override
        public boolean equals(Object o){
            if(this == o) return true;
            if(!(o instanceof state)) return false;
            state s = (state) o;
            return red.equals(s.red) && blue.equals(s.blue)
                && redVisited.equals(s.redVisited) && blueVisited.equals(s.blueVisited);
        }

        @Override
        public int hashCode(){
            return Objects.hash(red, blue, redVisited, blueVisited);
        }
    }
    
    public int solution(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        map = maze;
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(maze[i][j] == 1){
                    initRed = new pos(i,j);
                } else if(maze[i][j] == 2){
                    initBlue = new pos(i,j);
                } else if(maze[i][j] == 3){
                    goalRed = new pos(i,j);
                } else if(maze[i][j] == 4){
                    goalBlue = new pos(i,j);
                }
            }
        }

        visited = new HashSet<>();
        return bfs();
    }
    
    public static int bfs(){
        Queue<state> q = new LinkedList<>();
        
        Set<pos> initRedSet = new HashSet<>();
        Set<pos> initBlueSet = new HashSet<>();
        initRedSet.add(initRed);
        initBlueSet.add(initBlue);

        state start = new state(initRed, initBlue, 0, initRedSet, initBlueSet);
        q.offer(start);
        visited.add(start);
        
        while(!q.isEmpty()){
            state cur = q.poll();
            pos red = cur.red;
            pos blue = cur.blue;
            int cnt = cur.cnt;

            if(compare(red, goalRed) && compare(blue, goalBlue)){
                return cnt;
            }

            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    pos nr = compare(red, goalRed) ? red : new pos(red.x + dx[i], red.y + dy[i]);
                    pos nb = compare(blue, goalBlue) ? blue : new pos(blue.x + dx[j], blue.y + dy[j]);

                    if(check(nr) || check(nb)) continue;
                    if(map[nr.x][nr.y] == 5 || map[nb.x][nb.y] == 5) continue;
                    if(compare(nr, nb)) continue;
                    if(compare(nr, blue) && compare(nb, red)) continue;
                    if(cur.redVisited.contains(nr) || cur.blueVisited.contains(nb)) continue;

                    Set<pos> newRedVisited = new HashSet<>(cur.redVisited);
                    Set<pos> newBlueVisited = new HashSet<>(cur.blueVisited);

                    if (!compare(nr, goalRed)) newRedVisited.add(nr);
                    if (!compare(nb, goalBlue)) newBlueVisited.add(nb);

                    state next = new state(nr, nb, cnt+1, newRedVisited, newBlueVisited);
                    if (visited.contains(next)) continue;

                    visited.add(next);
                    q.offer(next);
                }
            }
        }
        return 0;
    }
    
    public static boolean check(pos p){
        return p.x < 0 || p.x >= N || p.y < 0 || p.y >= M;
    }
    
    public static boolean compare(pos p1, pos p2){
        return p1.x == p2.x && p1.y == p2.y;
    }
}
