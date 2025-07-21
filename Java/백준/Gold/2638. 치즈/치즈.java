import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] map;
    public static int[][] info;
    public static Set<Integer> air;
    public static int[] dx = {-1,1,0,0};
    public static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        info = new int[N][M];
        air = new HashSet<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 1;
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 1 || info[i][j] != 0) continue;
                if(markingAir(num, i,j)){
                    air.add(num);
                }
                num++;
            }
        }

        int cnt = 0;
        while(checkCheese()){
            melting();
            reMarking();
            cnt++;
        }
        System.out.println(cnt);
    }

    public static boolean checkCheese(){
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 1) return true;
            }
        }
        return false;
    }

    public static boolean markingAir(int num, int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        info[x][y] = num;
        boolean flag = false;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int i = 0; i < 4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx < 0 || nx > N-1 || ny < 0 || ny > M-1){
                    flag = true;
                    continue;
                }
                if(map[nx][ny] == 1 || info[nx][ny] > 0) continue;
                q.offer(new int[]{nx, ny});
                info[nx][ny] = num;
            }
        }
        return flag;
    }

    public static void melting(){
        ArrayList<int[]> melted = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 1){
                    int cnt = 0;
                    for(int k = 0; k < 4; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(air.contains(info[nx][ny])) cnt++;
                    }
                    if(cnt > 1) melted.add(new int[]{i, j});
                }
            }
        }

        for(int[] m : melted){
            map[m[0]][m[1]] = 0;
        }
    }

    public static void reMarking(){
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0 && info[i][j] == 0){
                    info[i][j] = 1;
                }
                if(map[i][j] == 0 && info[i][j] > 1 && !air.contains(info[i][j])){
                    for(int k = 0; k < 4; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(air.contains(info[nx][ny]) || (map[nx][ny] == 0 && info[nx][ny] == 0)) {
                            air.add(info[i][j]);
                        }
                    }
                }
            }
        }
    }
}
