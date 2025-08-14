import java.io.*;
import java.util.*;

public class Main {
    public static int H, W, C;
    public static int[] shots;
    public static char[][] map;
    public static ArrayList<cluster> clusters;
    public static boolean[][] visited;
    public static int[] dx = {-1,1,0,0};
    public static int[] dy = {0,0,1,-1};

    public static class pos implements Comparable<pos>{
        int x, y;
        public pos(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(pos o){
            if(this.x == o.x) return this.y - o.y;
            return this.x - o.x;
        }
    }

    public static class cluster{
        PriorityQueue<pos> cluster;
        boolean flag;

        public cluster(){
            cluster = new PriorityQueue<>();
            flag = true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[H+1][W];
        for(int i = H; i > 0; i--){
            String line = br.readLine();
            for(int j = 0; j < W; j++){
                map[i][j] = line.charAt(j);
            }
        }

        C = Integer.parseInt(br.readLine());
        shots = new int[C];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++){
            shots[i] = Integer.parseInt(st.nextToken());
        }

        boolean isLeft = true;
        for(int i = 0; i < C; i++){
            shotSpear(shots[i], isLeft);
            isLeft = !isLeft;

            checkCluster();
        }


        StringBuilder sb = new StringBuilder();
        for(int i = H; i > 0; i--){
            for(int j = 0; j < W; j++){
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void shotSpear(int height, boolean isLeft){
        if(isLeft){
            for(int i = 0; i < W; i++){
                if(map[height][i] == 'x'){
                    map[height][i] = '.';
                    return;
                }
            }
        }else{
            for(int i = W-1; i > -1; i--){
                if(map[height][i] == 'x'){
                    map[height][i] = '.';
                    return;
                }
            }
        }
    }

    public static void checkCluster() {
        visited = new boolean[H + 1][W];
        clusters = new ArrayList<>();
        int idx = 0;
        for (int i = H; i > 0; i--) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == '.' || visited[i][j])
                    continue;
                clusters.add(new cluster());
                clusters.get(idx).flag = bfs(idx, i, j);
                idx++;
            }
        }

        for(int i = 0; i < clusters.size(); i++){
            cluster cur = clusters.get(i);
            if(cur.flag) continue;
            setCluster(i);
        }

        for(int i = 0; i < clusters.size(); i++){
            cluster cur = clusters.get(i);
            if(cur.flag) {
                dropCluster(i);
                setCluster(i);
            }
        }
    }

    public static boolean bfs(int idx, int x, int y) {
        Queue<pos> q = new LinkedList<>();
        q.offer(new pos(x, y));
        visited[x][y] = true;

        boolean flag = true;
        while(!q.isEmpty()){
            pos cur = q.poll();
            map[cur.x][cur.y] = '.';
            clusters.get(idx).cluster.offer(cur);

            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 1) {
                    flag = false;
                    continue;
                }
                if(nx > H || ny < 0 || ny > W-1 || map[nx][ny] == '.' || visited[nx][ny]) continue;
                q.offer(new pos(nx, ny));
                visited[nx][ny] = true;
            }
        }

        // StringBuilder sb = new StringBuilder();
        // for(int i = H; i > 0; i--){
        //     for(int j = 0; j < W; j++){
        //         sb.append(map[i][j]);
        //     }
        //     sb.append("\n");
        // }
        // System.out.println(sb.toString());

        return flag;
    }

    public static void dropCluster(int idx){

        while(true){
            ArrayList<pos> temp = new ArrayList<>();
            for(pos c : clusters.get(idx).cluster){
                int nx = c.x - 1;
                if(map[nx][c.y] == 'x' || nx < 1){
                    return;
                }
                temp.add(new pos(nx, c.y));
            }
            // for (int i = 0; i < temp.size(); i++){
            //     System.out.println(temp.get(i).x + " " + temp.get(i).y);
            // }
            // System.out.println("-------");
            clusters.get(idx).cluster = new PriorityQueue<>(temp);
        }
    }

    public static void setCluster(int idx){
        for(pos c : clusters.get(idx).cluster){
            map[c.x][c.y] = 'x';
        }
    }
}
