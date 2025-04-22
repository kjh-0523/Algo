import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int N, M;
    public static int[] route;
    public static boolean[] check;
    public static List<Integer>[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        tree = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int now = Integer.parseInt(st.nextToken());
                if (now == 1) {
                    tree[i + 1].add(j + 1);
                }
            }
        }

        route = new int[M];
        check = new boolean[M];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.offer(route[0]);
        visited[route[0]] = true;
        check[0] = true;
        int idx = 1;

        while (!q.isEmpty() && idx < M) {
            int cur = q.poll();
            if (cur == route[idx]) {
                q.clear();
                q.offer(cur);
                visited = new boolean[N + 1];
                visited[cur] = true;
                check[idx] = true;
                idx += 1;
                continue;
            }
            for (int next : tree[cur]) {
                if (visited[next]) {
                    continue;
                }
                q.offer(next);
                visited[next] = true;
            }
        }

        boolean ans = true;
        for (int i = 0; i < M; i++) {
            if (!check[i]) {
                ans = false;
                break;
            }
        }
        System.out.println(ans ? "YES" : "NO");
    }
}
