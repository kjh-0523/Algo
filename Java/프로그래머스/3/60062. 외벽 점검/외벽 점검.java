import java.util.*;

class Solution {
    public static int m, k;
    public static int[] diff;
    public static ArrayList<int[]> perm = new ArrayList<>();
    public int solution(int n, int[] weak, int[] dist) {
        m = weak.length;
        k = dist.length;
        diff = new int[m];
        for(int i = 0; i < m-1; i++){
            diff[i] =  weak[i+1]- weak[i];
        }
        diff[m-1] = weak[0] + n - weak[m-1];
        Arrays.sort(dist);
        
        
        boolean[] Visited = new boolean[k];
        for (int i = 1; i < k+1; i++){
            perm(new int[i], i, 0, Visited, dist);
        }

        for (int[] p : perm){
            for (int i = 0; i < m; i++) {  
                if (isValid(i, p)) {
                    return p.length;
                }
            }
        }

        return -1;
    }

    private static void perm(int[] p, int limit, int depth, boolean[] visited, int[] dist) {
        if (limit == depth) {
            perm.add(p.clone());
            return;
        }

        for (int i = k-1; i > k - limit - 1; i--) {
            if (!visited[i]) {
                visited[i] = true;
                p[depth] = dist[i];
                perm(p, limit, depth+1, visited, dist);
                visited[i] = false;
            }
        }
    }

    private static boolean isValid(int i, int[] p) {            
        int cnt = 0;
        for (int d : p) {
            while (d - diff[i] >= 0) {
                d -= diff[i];
                i = (i+1)%m;
                cnt++;
            }
            i = (i+1)%m;
            cnt++;

            if (cnt == m)
                return true;
        }

        return false;
    }
}