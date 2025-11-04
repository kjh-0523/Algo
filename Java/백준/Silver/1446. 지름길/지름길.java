import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<int[]>> nodes = new ArrayList<>();
        for(int i = 0; i < m; i++){
            nodes.add(new ArrayList<>());
            nodes.get(i).add(new int[]{i+1, 1});
        }
        nodes.add(new ArrayList<>());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if(t <= m) {
                nodes.get(f).add(new int[] {t, w});
            }
        }

        int[] dist = new int[m+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[] {0,0});
        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            if(cur[1] < dist[cur[0]]){
                dist[cur[0]] = cur[1];
            }

            for(int[] next : nodes.get(cur[0])){
                if(dist[cur[0]] + next[1] < dist[next[0]]){
                    pq.offer(new int[] {next[0], dist[cur[0]] + next[1]});
                }
            }
        }

        System.out.println(dist[m]);
    }
}