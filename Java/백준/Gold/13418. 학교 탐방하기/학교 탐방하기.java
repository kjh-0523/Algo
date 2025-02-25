import java.io.*;
import java.util.*;

public class Main {

    public static int N,M;
    public static int[] root;
    public static PriorityQueue<Node> pq;
    public static PriorityQueue<Node> rpq;
    public static class Node implements Comparable<Node>{
        int from, to;
        boolean isDownhill;

        public Node(int from, int to, int is){
            this.from = from;
            this.to = to;
            isDownhill = is == 1;
        }

        @Override
        public int compareTo(Node o){
            if(o.isDownhill) return 1;
            return -1;
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
        N = Integer.parseInt(st.nextToken())+1;
        M = Integer.parseInt(st.nextToken())+1;
        root = new int[N];
        for(int i = 0; i < N; i++){
            root[i] = i;
        }
        pq = new PriorityQueue<>();
        rpq = new PriorityQueue<>(
                (o1, o2) -> {
                    if(o1.isDownhill && !o2.isDownhill) return 1;
                    else return -1;
                }
        );

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine()," ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int is = Integer.parseInt(st.nextToken());
            pq.offer(new Node(from,to,is));
            rpq.offer(new Node(from,to,is));
        }

        int minCnt = 0;
        int cnt = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(union(cur.from, cur.to)){
                if(!cur.isDownhill){
                    minCnt++;
                    cnt++;
                }
                if(cnt == N-1) break;
            }
        }

        for(int i = 0; i < N; i++){
            root[i] = i;
        }

        int maxCnt = 0;
        cnt = 0;
        while(!rpq.isEmpty()){
            Node cur = rpq.poll();

            if(union(cur.from, cur.to)){
                if(!cur.isDownhill){
                    maxCnt++;
                    cnt++;
                }
                if(cnt == N-1) break;
            }
        }
        int ans = (int) (Math.pow(maxCnt,2) - Math.pow(minCnt,2));
        System.out.println(ans);
    }
}
