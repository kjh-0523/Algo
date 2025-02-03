import java.util.*;
import java.io.*;

public class Main {

    public static int N,M,V;
    public static ArrayList<Integer>[] graph;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i = 0; i < N+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine()," ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }
        for(int i = 1; i < N+1; i++){
            graph[i].sort(Comparator.comparingInt(o -> o));
        }
        dfs(V,new boolean[N+1]);
        sb.append("\n");
        bfs(V);
        System.out.println(sb.toString());
    }

    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur +" ");
            for(int next : graph[cur]){
                if(visited[next]) continue;
                visited[next] = true;
                q.offer(next);
            }
        }
    }

    public static void dfs(int cur, boolean[] visited){
        if(visited[cur]) return;

        sb.append(cur +" ");
        visited[cur] = true;
        for(int next : graph[cur]){
            dfs(next, visited);
        }
    }
}
