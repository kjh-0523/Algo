import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static PriorityQueue<Problem> pq;

    public static class Problem implements Comparable<Problem>{
        int no, deadLine, cup;
        public Problem(int no, int deadLine, int cup){
            this.no = no;
            this.deadLine = deadLine;
            this.cup = cup;
        }
        @Override
        public int compareTo(Problem o) {
            if(this.deadLine == o.deadLine) return o.cup - this.cup;
            return deadLine - o.deadLine;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Problem(i, d, c));
        }

        PriorityQueue<Integer> problem = new PriorityQueue<>();
        while(!pq.isEmpty()){
            Problem p = pq.poll();
            if(problem.size() < p.deadLine){
                problem.offer(p.cup);
            }else{
                if(problem.peek() < p.cup){
                    problem.poll();
                    problem.offer(p.cup);
                }
            }
        }

        int ans = 0;
        for(int p : problem){
            ans += p;
        }
        System.out.println(ans);
    }
}
