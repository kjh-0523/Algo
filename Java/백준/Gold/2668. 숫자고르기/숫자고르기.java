import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] nums;
    public static List<Integer> ans;
    public static int[] state; // 0: 미방문, 1: 방문중, 2: 사이클X, 3: 사이클O

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new int[N+1];
        for(int i = 1; i < N+1; i++){
            nums[i] = Integer.parseInt(br.readLine());
        }

        state = new int[N+1];
        ans = new ArrayList<>();

        for(int i = 1; i < N+1; i++){
            if(state[i] == 0){
                dfs(i, new ArrayList<>());
            }
        }

        Collections.sort(ans);
        System.out.println(ans.size());
        for(int a : ans){
            System.out.println(a);
        }

    }

    static void dfs(int cur, List<Integer> path) {
        state[cur] = 1;
        path.add(cur);

        int next = nums[cur];
        if (state[next] == 0) {
            dfs(next, path);
        } else if (state[next] == 1) {
            int idx = path.indexOf(next);
            for (int i = idx; i < path.size(); i++) {
                ans.add(path.get(i));
                state[path.get(i)] = 3;
            }
        }

        if (state[cur] != 3) {
            state[cur] = 2;
        }
        path.remove(path.size() - 1);
    }
}
