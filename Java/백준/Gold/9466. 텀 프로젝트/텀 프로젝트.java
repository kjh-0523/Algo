import java.util.*;
import java.io.*;

public class Main {

    public static int N;
    public static int[] choose;
    public static int cnt;
    public static boolean[] visited;
    public static boolean[] circle;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        for(int tc = 0; tc < T; tc++){
            N = Integer.parseInt(br.readLine());
            cnt = 0;
            choose = new int[N];
            visited = new boolean[N];
            circle = new boolean[N];
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < N; i++){
                choose[i] = Integer.parseInt(st.nextToken())-1;
                if(choose[i] == i) {
                    circle[i] = true;
                    cnt++;
                }
            }

            for(int i = 0; i < N; i++){
                if(circle[i]) continue;
                dfs(i);
            }
            int ans = N - cnt;
            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int n){
        if(visited[n]){
            circle[n] = true;
            cnt++;
        }else{
            visited[n] = true;
        }

        if(!circle[choose[n]]){
            dfs(choose[n]);
        }

        visited[n] = false;
        circle[n] = true;
    }
}