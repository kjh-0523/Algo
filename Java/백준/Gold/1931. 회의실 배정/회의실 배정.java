import java.io.*;
import java.util.*;

public class Main {
    public static int N;

    public static class Meeting implements Comparable<Meeting>{
        int start, end;
        public Meeting (int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o1){
            if (this.end == o1.end) return this.start - o1.start;
            return this.end - o1.end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Meeting> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.add(new Meeting(s, e));
        }

        int endTime = 0;
        int cnt = 0;
        while(!pq.isEmpty()){
            Meeting cur = pq.poll();
            if(cur.start >= endTime){
                endTime = cur.end;
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
