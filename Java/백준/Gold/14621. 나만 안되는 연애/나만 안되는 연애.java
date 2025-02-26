import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static char[] school;
    public static PriorityQueue<Node> nodes;
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

    public static int find(int a){
        if(root[a] == a) return a;
        return root[a] = find(root[a]);
    }

    public static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return false;
        root[rootB] = rootA;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        school = new char[N];
        String line = br.readLine();
        for(int i = 0; i < N; i++){
            school[i] = line.charAt(i*2);
        }

        root = new int[N];
        for(int i = 0; i < N; i++){
            root[i] = i;
        }

        nodes = new PriorityQueue<>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine()," ");
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());

            if(school[from] != school[to]){
                nodes.add(new Node(from, to, weight));
            }
        }

        int cnt = 0;
        int ans = 0;
        while(!nodes.isEmpty()){
            Node cur = nodes.poll();

            if(union(cur.from, cur.to)){
                cnt++;
                ans += cur.weight;
            }
            if(cnt == N-1) break;
        }
        if(cnt != N-1) ans = -1;
        System.out.println(ans);
    }
}
