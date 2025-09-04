import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int o1, o2;
    public static int[] target;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        o1 = Integer.parseInt(st.nextToken());
        o2 = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        target = new int[M];
        for(int i = 0; i < M; i++){
            target[i] = Integer.parseInt(br.readLine());
        }
        int ans = dfs(0, o1, o2);

        System.out.println(ans);
    }

    public static int dfs(int idx, int open1, int open2){
        if(idx == M) return 0;
        int tmp1 = Math.abs(target[idx] - open1);
        int tmp2 = Math.abs(target[idx] - open2);

        return Math.min(tmp1 + dfs(idx + 1, open2, target[idx]),
            tmp2 + dfs(idx + 1, open1, target[idx]));
    }
}
