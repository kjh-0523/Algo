import java.util.*;
import java.io.*;

class Main
{
    public static int N;
    public static ArrayList<Integer>[] list;
    public static int[] inDegree;
    public static PriorityQueue<Node> q;
    public static int[] time;

    public static class Node implements Comparable<Node>{
        int idx, time;
        public Node(int idx, int time){
            this.idx = idx;
            this.time = time;
        }

        @Override
        public int compareTo(Node o){
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        list = new ArrayList[N+1];
        for(int i = 0; i <= N; i++){
            list[i] = new ArrayList<>();
        }
        inDegree = new int[N+1];
        q = new PriorityQueue<>();
        time = new int[N+1];

        StringTokenizer st = null;
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            time[i] = t;
            int M = Integer.parseInt(st.nextToken());
            inDegree[i] = M;

            for(int j = 0; j < M; j++){
                int b = Integer.parseInt(st.nextToken());
                list[b].add(i);
            }
        }

        int ans = 0;
        for(int i = 1; i < N; i++){
            if(inDegree[i] == 0){
                q.offer(new Node(i,time[i]));
            }
        }

        while(!q.isEmpty()){
            Node cur = q.poll();
            ans = Math.max(ans, cur.time);
//            System.out.println(cur.idx +" " + list[cur.idx].toString());
            for(int next : list[cur.idx]){
                inDegree[next]--;
                if(inDegree[next] == 0){
                    q.offer(new Node(next,time[next] + cur.time));
                }
            }
        }
        System.out.println(ans);
    }
}