import java.util.*;

class Solution {
    static List<Integer>[] tree;
    static boolean[] visited;
    static int answer = 0;

    public int solution(int n, int[][] lighthouse) {
        tree = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] edge : lighthouse) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }

        dfs(1);

        return answer;
    }

    private int dfs(int node) {
        visited[node] = true;
        boolean needLight = false;

        for (int next : tree[node]) {
            if (!visited[next]) {
                int child = dfs(next);
                if (child == 0) {
                    needLight = true;
                }
            }
        }

        if (needLight) {
            answer++;
            return 1;
        }

        return 0;
    }
}
