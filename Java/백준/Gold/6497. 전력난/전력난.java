import java.util.*;
import java.io.*;

public class Main {
    public static int m,n;
    public static int[] root;
    public static ArrayList<Node>[] nodes;

    public static class Node implements Comparable<Node>{
        int start, end, weight;

        public Node(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static int find(int a){
        if(root[a] == a) return a;
        return root[a] = find(root[a]);
    }

    public static boolean union(int a, int b){
        int rootB = find(b);
        int rootA = find(a);
        if(rootB == rootA) return false;

        root[rootB] =  rootA;
        return true;
    }
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if (m == 0 && n == 0) break;

            nodes = new ArrayList[m];
            for(int i = 0; i < m; i++){
                nodes[i] = new ArrayList<>();
            }

            root = new int[m];
            for(int i = 0; i < m; i++){
                root[i] = i;
            }

            int total = 0;
            PriorityQueue<Node> pq = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                pq.add(new Node(x, y, z));
                total += z;
            }

            int sum = 0;
            int count = 0;
            while(!pq.isEmpty()){
                Node cur = pq.poll();
                if(union(cur.start, cur.end)){
                    sum += cur.weight;
                    count++;
                }
                if(count == m-1) break;
            }
            sb.append(total - sum).append("\n");
        }
        System.out.println(sb.toString());
    }
}
