import java.util.*;
import java.io.*;

public class Main {

    public static int[][] dx = {{0,0,0,1},
                                {0,1,0,0},
                                {0,0,0,-1},
                                {0,-1,0,0},
                                {-1,0,1,0},
                                {0,0,0,0}};
    public static int[][] dy = {{1,0,0,0},
                                {-1,0,0,0},
                                {0,0,1,0},
                                {0,0,-1,0},
                                {0,0,0,0},
                                {0,1,0,-1}};
    public static int[][] dir = {{1,-1,-1,2},
                                 {3,2,-1,-1},
                                 {-1,-1,1,0},
                                 {-1,0,3,-1},
                                 {0,-1,2,-1},
                                 {-1,1,-1,3}};

    public static int N, K;
    public static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = bfs();
        if(K == 1){
            ans = (int)1e7;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    int flag = map[i][j];
                    for(int k = 0; k < 6; k++){
                        if(flag == k) continue;
                        map[i][j] = k;
                        int cnt = bfs();
//                        System.out.println(cnt);
                        if(cnt > 0){
                            ans = Math.min(cnt,ans);
                        }
                    }
                    map[i][j] = flag;
                }
            }
        }

        ans = ans == (int) 1e7? -1 : ans;
        System.out.println(ans);

    }

    public static int bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0, 1, 1});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int tile = map[cur[0]][cur[1]];

            int nx = cur[0] + dx[tile][cur[2]];
            int ny = cur[1] + dy[tile][cur[2]];
            int ndir = dir[tile][cur[2]];

            if(nx == N-1 && ny == N) return cur[3];
            if(nx < 0 || nx > N-1 || ny < 0 || ny > N-1 || ndir == -1) continue;

            q.offer(new int[] {nx,ny,ndir,cur[3]+1});
        }

        return -1;
    }
}