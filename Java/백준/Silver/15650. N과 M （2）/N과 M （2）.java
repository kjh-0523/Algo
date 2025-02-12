import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[] arr;
    public static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        visited = new boolean[N+1];
        dfs(1,0);
    }
    public static void dfs(int idx, int cnt) {
        if (cnt == M) {
            for(int i = 0; i < M; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = idx; i < N+1; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            arr[cnt] = i;
            dfs(i+1, cnt+1);
            visited[i] = false;
        }
    }
}