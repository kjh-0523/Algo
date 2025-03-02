import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>((o1,o2) ->{
            if(o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.add(new int[]{x, y});
        }

        StringBuilder ans = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] coord = pq.poll();
            ans.append(coord[0]).append(" ").append(coord[1]).append("\n");
        }
        System.out.println(ans);
    }
}
