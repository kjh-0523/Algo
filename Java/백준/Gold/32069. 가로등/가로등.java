import java.io.*;
        import java.util.*;

public class Main {
    public static long L,N,K;
    public static Set<Long> visited;
    public static Queue<Node> q;
    public static int[] dx = {-1,1};

    public static class Node{
        public long x, dis;
        public Node(long x, long dis){
            this.x = x;
            this.dis = dis;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        visited = new HashSet<>();
        q = new LinkedList<>();

        L = Long.parseLong(st.nextToken());
        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++){
            long light = Long.parseLong(st.nextToken());
            q.offer(new Node(light, 0));
            visited.add(light);
        }
        int cnt = 0;
        while(!q.isEmpty() && cnt++ < K){
            Node cur = q.poll();
            sb.append(cur.dis + "\n");
            long nx;
            for(int i = 0; i < 2; i++){
                nx = cur.x + dx[i];
                if(nx < 0 || nx > L-1 || visited.contains(nx))continue;
                visited.add(nx);
                q.offer(new Node(nx, cur.dis + 1));
            }
        }
        System.out.println(sb.toString());
    }
}