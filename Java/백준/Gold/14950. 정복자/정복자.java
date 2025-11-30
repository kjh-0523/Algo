import java.util.*;
import java.io.*;

public class Main {

    public static int N,M,t;
    public static int[] root;
    public static Edge[] edge;

    public static void init(){
        root = new int[N+1];
        for(int i = 1; i < N+1;i++){
            root[i] = i;
        }
        edge = new Edge[M*2];
    }

    public static int find(int a){
        if(root[a] == a) return a;
        return root[a] = find(root[a]);
    }

    public static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;

        root[bRoot] = aRoot;
        return true;
    }

    public static class Edge implements Comparable<Edge>{
        int start, end, weight;

        public Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o){
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        init();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edge[i*2] = new Edge(s,e,w);
            edge[i*2+1] = new Edge(e,s,w);
        }

        Arrays.sort(edge);

        int result = 0;
        int cnt = 0;
        int idx = 0;
        for(Edge e : edge){
            if(union(e.start,e.end)){
                result += e.weight + (t * idx);
                idx++;
                if(++cnt == N-1) break;
            }
        }
        System.out.println(result);
    }
}
