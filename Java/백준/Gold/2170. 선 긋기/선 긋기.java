import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static PriorityQueue<line> lines;
    public static class line implements Comparable<line>{
        int start, end;

        public line(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(line o) {
            if (this.start == o.start)
                return o.end - this.end;
            return this.start - o.start;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        lines = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            lines.offer(new line(s,e));
        }

        int sum = 0;
        int s = -1000000001;
        int e = -1000000001;
        while(!lines.isEmpty()){
            line cur = lines.poll();

            if(s == cur.start) continue;

            if(e < cur.start){
                sum += e - s;
                s = cur.start;
                e = cur.end;
            }else{
                e = Math.max(e, cur.end);
            }
        }
        sum += e - s;

        System.out.println(sum);
    }
}