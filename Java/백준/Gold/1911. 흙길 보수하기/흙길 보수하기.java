import java.io.*;
import java.util.*;

public class Main {
    public static int N, L;

    public static class Puddle implements Comparable<Puddle>{
        int start, end;
        public Puddle(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Puddle o){
            return this.start - o.start;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        PriorityQueue<Puddle> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.offer(new Puddle(s, e));
        }

        int start = -1;
        int end = -1;
        int cnt = 0;
        while(!pq.isEmpty()){
            Puddle cur = pq.poll();

            if(end < cur.start){
                start = cur.start;
                int diff = (cur.end - cur.start) / L;
                if((cur.end - cur.start) % L != 0) diff++;
                cnt += diff;
                end = start + diff * L;
            }else if(end < cur.end){
                int diff = (cur.end - end) / L;
                if((cur.end - end) % L != 0) diff++;
                cnt+= diff;
                end += diff * L;
            }
        }

        System.out.println(cnt);
    }
}
