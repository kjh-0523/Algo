import java.io.*;
        import java.util.*;

public class Main {
    public static int N;
    public static Stack<Integer> stack;
    public static PriorityQueue<Skyline> pq;
    public static class Skyline implements Comparable<Skyline> {
        public int x, y;
        public Skyline(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Skyline o) {
            return this.x - o.x;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        stack = new Stack<>();
        pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.offer(new Skyline(x, y));
        }
        int ans = 0;
        while(!pq.isEmpty()){
            Skyline cur = pq.poll();
//            System.out.println(cur.x + " " + cur.y);

            if(stack.isEmpty()){
                stack.add(cur.y);
                continue;
            }

            if(cur.y < stack.peek()){
                while(!stack.isEmpty() && stack.peek() > cur.y){
                    stack.pop();
                    ans++;
                }
            }
            if((!stack.isEmpty() && cur.y > stack.peek()) || stack.isEmpty()){
                stack.add(cur.y);
            }
//            System.out.println(stack.toString() + " "+ans);
        }

        while(!stack.isEmpty()){
            if(stack.pop() != 0) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}