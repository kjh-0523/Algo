import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static long[] distance1;
    static boolean[][] removedEdges;
    static ArrayList<Edge>[] road; 
    static ArrayList<Integer>[] path;

    static class Edge implements Comparable<Edge> {
        int to;
        long weight;

        public Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            road = new ArrayList[N];
            path = new ArrayList[N];
            removedEdges = new boolean[N][N];
            distance1 = new long[N];

            for (int i = 0; i < N; i++) {
                road[i] = new ArrayList<>();
                path[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                road[from].add(new Edge(to, weight));
            }

            Dijkstra(start);

            if (distance1[end] == Long.MAX_VALUE) {
                System.out.println(-1);
                continue;
            }

            bfs(end, start);

            Dijkstra(start);

            System.out.println(distance1[end] == Long.MAX_VALUE ? -1 : distance1[end]);
        }
    }

    static void Dijkstra(int start) {
        PriorityQueue<Edge> q = new PriorityQueue<>();
        Arrays.fill(distance1, Long.MAX_VALUE);
        distance1[start] = 0;
        q.offer(new Edge(start, 0));

        while (!q.isEmpty()) {
            Edge current = q.poll();

            if (current.weight > distance1[current.to]) continue;

            for (Edge next : road[current.to]) {
                if (removedEdges[current.to][next.to]) continue;

                if (distance1[next.to] > distance1[current.to] + next.weight) {
                    distance1[next.to] = distance1[current.to] + next.weight;
                    path[next.to].clear();
                    path[next.to].add(current.to);
                    q.offer(new Edge(next.to, distance1[next.to]));
                } else if (distance1[next.to] == distance1[current.to] + next.weight) {
                    path[next.to].add(current.to);
                }
            }
        }
    }

    static void bfs(int end, int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N];
        q.add(end);
        visited[end] = true;

        while (!q.isEmpty()) {
            int current = q.poll();

            if (current == start) continue;

            for (int prevNode : path[current]) {
                if (!visited[prevNode]) {
                    q.add(prevNode);
                    visited[prevNode] = true;
                }
                removedEdges[prevNode][current] = true;
            }
        }
    }
}
