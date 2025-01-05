import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main
{
    public static int N,M,K;
    public static boolean[][] map;
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,1,-1};
    public static int answer;

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            map[r][c] = true;
        }

        answer = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j]){
                    bfs(i,j);
                }
            }
        }

        System.out.println(answer);
    }

    public static void bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x,y});
        map[x][y] = false;
        int cnt = 1;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int nx, ny;

            for(int i = 0; i < 4; i++){
                nx = cur[0] + dx[i];
                ny = cur[1] + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == false) continue;
                map[nx][ny] = false;
                q.add(new int[]{nx, ny});
                cnt++;
            }
        }

        answer = Math.max(cnt,answer);
    }
}