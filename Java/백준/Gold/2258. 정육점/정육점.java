import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> {
            if(o1[1] == o2[1]) return o2[0] - o1[0];
            return o1[1] - o2[1];
        });
        int sum = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            sum += w;
            pq.offer(new int[] {w, p});
        }

        if(sum < M){
            System.out.println(-1);
            return;
        }

        int ans = Integer.MAX_VALUE;
        int weight = 0;
        int prevPrice = -1;
        int count = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            weight += cur[0];
            if (cur[1] == prevPrice) {
                count++;
            } else {
                count = 1;
                prevPrice = cur[1];
            }

            if (weight >= M) {
                ans = Math.min(ans, cur[1] * count);
            }
        }

        System.out.println(ans);
    }
}
