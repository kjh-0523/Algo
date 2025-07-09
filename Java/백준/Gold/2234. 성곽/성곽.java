import java.util.*;
import java.io.*;

public class Main {
    public static int N,M, ans1, ans2, ans3;
    public static ArrayList<Integer> size;
    public static Set<Integer>[] graph;
    public static int[][] wall;
    public static int[][] map;
    public static int[] dx = {0, -1, 0, 1};
    public static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        wall = new int[N][N];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                wall[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 0;
        ans2 = 0;
        size = new ArrayList();
        size.add(0);
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 0){
                    num++;
                    bfs(num, i, j);
                }
            }
        }
        ans1 = num;

        graph = new HashSet[num+1];
        for(int i = 1; i < num+1; i++){
            graph[i] = new HashSet<>();
        }
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                makeGraph(i,j);
            }
        }

        ans3 = 0;
        for(int i = 1; i < num + 1; i++){
            for(int next : graph[i]){
                ans3 = Math.max(ans3, size.get(i) + size.get(next));
            }
        }
        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println(ans3);

    }

    public static void bfs(int num, int x, int y){
        Queue<int[]> q= new LinkedList<>();
        q.offer(new int[]{x, y});
        map[x][y] = num;
        int cnt = 0;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            cnt++;
            for(int i = 0; i < 4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx < 0 || ny < 0 || nx >= M || ny >= N || map[nx][ny] != 0) continue;
                if((wall[cur[0]][cur[1]] & (1 << i)) > 0) continue;
                map[nx][ny] = num;
                q.offer(new int[]{nx,ny});
            }
        }
        ans2 = Math.max(cnt, ans2);
        size.add(cnt);
    }

    public static void makeGraph(int x, int y){
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
            if(map[nx][ny] == map[x][y]) continue;
            graph[map[x][y]].add(map[nx][ny]);
        }
    }
}
