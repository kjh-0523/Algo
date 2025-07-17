import java.util.*;
import java.io.*;

public class Main {
    public static int N, M;
    public static Set<Integer> knowTruth;
    public static ArrayList<Integer>[] party;
    public static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        knowTruth = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++){
            int k = Integer.parseInt(st.nextToken());
            knowTruth.add(k);
        }

        party = new ArrayList[M];
        for(int i = 0; i < M; i++){
            party[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            for(int j = 0; j < m; j++){
                party[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        graph = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            for(int j = 0; j < party[i].size()-1; j++){
                int to = party[i].get(j);
                for(int k = j+1; k < party[i].size(); k++){
                    int from = party[i].get(k);
                    if(graph[to].contains(from)) continue;
                    graph[to].add(from);
                    graph[from].add(to);
                }
            }
        }

        int cnt = 0;
        top :
        for(ArrayList<Integer> p : party){
            for(int member : p){
                if(!bfs(member)) continue top;
            }
            cnt++;
        }
        System.out.println(cnt);
    }

    public static boolean bfs(int m){
        Queue<Integer> q = new LinkedList<>();
        q.offer(m);
        Set<Integer> visited = new HashSet<>();
        visited.add(m);

        while(!q.isEmpty()){
            int cur = q.poll();
            if(knowTruth.contains(cur)) return false;
            for(int next : graph[cur]){
                if(visited.contains(next)) continue;
                visited.add(next);
                q.offer(next);
            }
        }
        return true;
    }
}
