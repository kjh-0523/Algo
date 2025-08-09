import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, H;
    public static boolean[][] ladders;
    public static List<int[]> candidates;
    public static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladders = new boolean[N+1][H+1];
        for(int i = 1; i < M+1; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            ladders[n][h] = true;
        }

        candidates = new ArrayList<>();
        for(int h = 1; h < H+1; h++) {
            for(int n = 1; n < N; n++) {
                if(!ladders[n][h] && !ladders[n-1][h] && !ladders[n+1][h]) {
                    candidates.add(new int[]{n, h});
                }
            }
        }
        ans = 4;
        dfs(0,0);

        System.out.println(ans > 3 ? -1 : ans);
    }

    public static void dfs(int idx, int sum){
        if(sum >= ans) return;
        if(validationLadder()) {
            ans = sum;
            return;
        }
        if(sum == 3 || idx == candidates.size()){
            return;
        }

            for (int i = idx; i < candidates.size(); i++) {
                int n = candidates.get(i)[0];
                int h = candidates.get(i)[1];
                if (ladders[n][h])
                    continue;
                if (ladders[n + 1][h])
                    continue;
                if (ladders[n - 1][h])
                    continue;
                ladders[n][h] = true;
                dfs(idx + 1, sum + 1);
                ladders[n][h] = false;
            }

    }

    public static boolean validationLadder() {
        for(int i = 1; i < N+1; i++){
            int pos = i;
            for(int j = 1; j < H+1; j++){
                if(ladders[pos-1][j]) pos--;
                else if(ladders[pos][j]) pos++;
            }
            if(pos != i) return false;
        }
        return true;
    }
}
