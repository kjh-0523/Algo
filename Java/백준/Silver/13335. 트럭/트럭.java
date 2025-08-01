import java.io.*;
import java.util.*;

public class Main {
    static int N, W, L;
    static int[] trucks;
    static Queue<Integer> bridge;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        trucks = new int[N];
        bridge = new LinkedList<>();
        for (int i = 0; i < W; i++) bridge.add(0);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        int sum = 0;
        int ans = 0;
        while (!bridge.isEmpty()) {
            ans++;

            sum -= bridge.poll();

            if (idx < N) {
                if (sum + trucks[idx] <= L) {
                    bridge.add(trucks[idx]);
                    sum += trucks[idx];
                    idx++;
                } else {
                    bridge.add(0);
                }
            }
        }
        System.out.println(ans);
    }
}
