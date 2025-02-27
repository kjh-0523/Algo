import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


public class Main {
    public static int N;
    public static PriorityQueue<Node> pq;
    public static int[] root;

    public static class Node implements Comparable<Node> {
        int from, to, weight;

        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static int find(int a) {
        if (root[a] == a) {
            return a;
        }
        return root[a] = find(root[a]);
    }

    public static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return false;
        }
        root[rootB] = rootA;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>();
        int sum = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = line.charAt(j);
                if (c == '0') {
                    continue;
                } else if (c < 'a') {
                    pq.offer(new Node(i, j, c - 'A' + 27));
                    sum += c - 'A' + 27;
                } else {
                    pq.offer(new Node(i, j, c - 'a' + 1));
                    sum += c - 'a' + 1;
                }
            }
        }

        root = new int[N];
        for (int i = 0; i < N; i++) {
            root[i] = i;
        }

        int cnt = 0;
        int min = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (union(cur.from, cur.to)) {
                cnt++;
                min += cur.weight;
            }
            if (cnt == N - 1) {
                break;
            }
        }
        int ans = cnt != N - 1 ? -1 : sum - min;
        System.out.println(ans);
    }
}
