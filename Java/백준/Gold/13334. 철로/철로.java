import java.util.*;
import java.io.*;

public class Main {

    public static int N, D;
    public static PriorityQueue<Node> homeOffice;
    public static PriorityQueue<Node> road;

    public static class Node {
        int left, right;
        Node(int left, int right){
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        homeOffice = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.right == o2.right) return o1.left - o2.left;
                return o1.right - o2.right;
            }
        });
        road = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2){
                if (o1.left == o2.left) return o1.right - o2.right;
                return o1.left - o2.left;
            }
        });
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine()," ");
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            if(h > o) homeOffice.offer(new Node(o,h));
            else homeOffice.offer(new Node(h,o));
        }
        D = Integer.parseInt(br.readLine());

        int cnt = 0;
        while(!homeOffice.isEmpty()){
            Node cur = homeOffice.poll();
            //거리 길이가 철로 길이 안에 들어가는지 확인
            if((cur.right - cur.left) > D) continue;
            int end = cur.right;
            int start = end - D;

            road.add(cur);

            while (!road.isEmpty() && road.peek().left < start) {
                road.poll();
            }
            // road의 사이즈가 cnt 보다 크면 갱신
            cnt = Math.max(cnt, road.size());
        }
        System.out.println(cnt);
    }
}
