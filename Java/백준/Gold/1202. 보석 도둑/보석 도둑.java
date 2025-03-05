import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[N];
        int[] bags = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(size, weight);
        }

        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewels, Comparator.comparingInt(o -> o.size));

        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        long ans = 0;
        int j = 0;
        for (int i = 0; i < K; i++) {
            while (j < N && jewels[j].size <= bags[i]) {
                pq.offer(jewels[j].weight);
                j++;
            }
            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }

        System.out.println(ans);
    }

    static class Jewel {
        int size, weight;
        public Jewel(int size, int weight) {
            this.size = size;
            this.weight = weight;
        }
    }
}
