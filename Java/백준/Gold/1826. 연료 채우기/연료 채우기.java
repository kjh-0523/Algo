import java.util.*;
import java.io.*;

public class Main {
    public static int N, L ,P;
    public static PriorityQueue<Gas> gas;

    public static class Gas implements Comparable<Gas>{
        int location, amount;

        Gas(int location, int amount){
            this.location = location;
            this.amount = amount;
        }

        @Override
        public int compareTo(Gas o) {
            if(this.location == o.location) return o.amount - this.amount;
            return this.location - o.location;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = null;
        gas = new PriorityQueue<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            gas.offer(new Gas(l,a));
        }

        st= new StringTokenizer(br.readLine());
        int dest = Integer.parseInt(st.nextToken());
        int nowAmount = Integer.parseInt(st.nextToken());
        int cnt = 0;
        PriorityQueue<Gas> select = new PriorityQueue<>(new Comparator<Gas>() {
            @Override
            public int compare(Gas o1, Gas o2) {
                return o2.amount - o1.amount;
            }
        });
        while(!gas.isEmpty() || !select.isEmpty()){
            while(!gas.isEmpty() && gas.peek().location <= nowAmount){
                select.offer(gas.poll());
            }

            if(select.isEmpty()){
                break;
            }

            if(nowAmount < dest){
                cnt++;
                nowAmount += select.poll().amount;
            }else{
                break;
            }
        }
        int ans = nowAmount < dest ? -1 : cnt;
        System.out.println(ans);
    }
}