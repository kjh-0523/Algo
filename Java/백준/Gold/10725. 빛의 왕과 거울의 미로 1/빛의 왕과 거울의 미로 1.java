import java.util.*;
import java.io.*;

public class Main {
    public static int N,M,X,Y;
    public static long cnt;
    public static int[] start;
    public static int[] dest;
    public static int[][] dx = {{-1,0,1,0},{0,-1,0,1},{0,1,0,-1}};
    public static int[][] dy = {{0,1,0,-1},{1,0,-1,0},{-1,0,1,0}};
    public static int[][] ddir = {{0,1,2,3},{1,0,3,2},{3,2,1,0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        // start 지점 계산
        if(X >= 1 && X < M+1) start = new int[] {-1,X-1,2};
        else if(X >= M+1 && X < M+N+1) start = new int[] {X-1-M,-1,1};
        else if(X >= M+N+1 && X < M+N+N+1) start = new int[] {X-1-(M+N),M,3};
        else start = new int[] {N,X-1-(M+N+N),0};

        //destination 지점 계산
        if(Y >= 1 && Y < M+1) dest = new int[] {-1,Y-1};
        else if(Y >= M+1 && Y < M+N+1) dest = new int[] {Y-1-M,-1};
        else if(Y >= M+N+1 && Y < M+N+N+1) dest = new int[] {Y-1-(M+N),M};
        else dest = new int[] {N,Y-1-(M+N+N)};

        // map 입력
        char[][] map = new char[N][M];
        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = line.charAt(j);
            }
        }
        cnt = 0;
        dfs(map, start[0] + dx[0][start[2]], start[1] + dy[0][start[2]], start[2], new boolean[N][M]);
        System.out.println(cnt%10007);
    }

    public static void dfs(char[][] map, int x, int y, int dir, boolean[][] visited){
        // 도착지에 도달한 경우
        if(x == dest[0] && y == dest[1]){
            cnt += count(map, visited)%10007;
//            for(int i = 0; i < N; i++){
//                System.out.println(Arrays.toString(map[i]));
//            }
            return;
        }
        //백트레킹
        if(x < 0 || x > N-1 || y < 0 || y > M-1){
            return;
        }
        visited[x][y] = true;
        //map이 ?인 경우
        if(map[x][y] == '?'){
            map[x][y] = '.';
            dfs(map, x + dx[0][dir], y + dy[0][dir], ddir[0][dir], visited);
            map[x][y] = '/';
            dfs(map, x + dx[1][dir], y + dy[1][dir], ddir[1][dir], visited);
            map[x][y] = '\\';
            dfs(map, x + dx[2][dir], y + dy[2][dir], ddir[2][dir], visited);
            map[x][y] = '?';
        }else if(map[x][y] == '.'){ //map이 .,\,/인 경우
            dfs(map, x + dx[0][dir], y + dy[0][dir], ddir[0][dir], visited);
        }else if(map[x][y] == '/'){
            dfs(map, x + dx[1][dir], y + dy[1][dir], ddir[1][dir], visited);
        }else{
            dfs(map, x + dx[2][dir], y + dy[2][dir], ddir[2][dir], visited);
        }
        visited[x][y] = false;
    }

    public static long count(char[][] map, boolean[][] visited){
        long cnt = 1;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(!visited[i][j]){
                    if(map[i][j] == '?'){
                        cnt*=3;
                    }
                }
            }
        }
        return cnt;
    }
}
