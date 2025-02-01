import java.util.*;
import java.io.*;

class Main
{
    public static int N, E;
    public static int u, v;
    public static ArrayList<Node>[] node;
    public static int MAX_VALUE = Integer.MAX_VALUE;

    public static class Node implements Comparable<Node>{
        int next, weight;

        public Node(int next, int weight){
            this.next = next;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        node = new ArrayList[N];
        for(int i = 0; i < N; i++){
            node[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken())-1;
            int t = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            node[f].add(new Node(t, w));
            node[t].add(new Node(f, w));
        }
        st = new StringTokenizer(br.readLine());
        u = Integer.parseInt(st.nextToken())-1;
        v = Integer.parseInt(st.nextToken())-1;
        int ans = 0;

        int[] dist0 = dijkstra(0);
        int[] distU = dijkstra(u);
        int[] distV = dijkstra(v);

        int ans1 = 0;
        if(dist0[u] == MAX_VALUE || distU[v] == MAX_VALUE || distV[N-1] == MAX_VALUE){
            ans1 = -1;
        }else{
            ans1 = dist0[u] + distU[v] + distV[N-1];
        }
        int ans2 = 0;
        if(dist0[v] == MAX_VALUE || distV[u] == MAX_VALUE || distV[N-1] == MAX_VALUE){
            ans2 = -1;
        }else{
            ans2 = dist0[v] + distV[u] + distU[N-1];
        }

        if(ans1 == -1 && ans2 == -1){
            ans = -1;
        }else if(ans1 == -1){
            ans = ans2;
        }else if(ans2 == -1){
            ans = ans1;
        }else{
            ans = Math.min(ans1, ans2);
        }
        System.out.println(ans);
    }

    public static int[] dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N];
        Arrays.fill(dist, MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(dist[cur.next] < cur.weight) continue;

            for(Node next: node[cur.next]){
                if(dist[next.next] > cur.weight + next.weight){
                    dist[next.next] = cur.weight + next.weight;
                    pq.offer(new Node(next.next, dist[next.next]));
                }
            }
        }
        return dist;
    }
}