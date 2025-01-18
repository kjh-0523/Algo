import java.util.*;
import java.io.*;

class Main
{
    public static int N,M;
    public static LinkedList<Node>[] barn;
    public static int[] dist;

    public static class Node implements Comparable<Node>{
        int next, weight;

        Node(int next, int weight){
            this.next = next;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        barn = new LinkedList[N];
        for(int i = 0; i < N; i++){
            barn[i] = new LinkedList<>();
        }
        dist = new int[N];
        for(int i = 0; i < N; i++){
            Arrays.fill(dist, Integer.MAX_VALUE);
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());

            barn[from].add(new Node(to, weight));
            barn[to].add(new Node(from, weight));
        }

        dijkstra();
        int ans = dist[N-1] != Integer.MAX_VALUE ? dist[N-1] : -1;
        System.out.println(ans);
    }

    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        dist[0] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if (dist[cur.next] < cur.weight) continue;

            for(Node node : barn[cur.next]){
                if(dist[node.next] > node.weight + cur.weight){
                    dist[node.next] = node.weight + cur.weight;
                    pq.add(new Node(node.next, dist[node.next]));
                }
            }
        }
    }
}