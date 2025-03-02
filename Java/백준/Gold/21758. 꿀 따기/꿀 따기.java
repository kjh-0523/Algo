import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] sum;
    public static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        sum = new int[N];
        map = new int[N];
        map[0] = Integer.parseInt(st.nextToken());
        sum[0] = map[0];
        for (int i = 1; i < N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1] + map[i];
        }
        int ans = 0;
        for(int i = 1; i < N-1; i++){
            int sum1 = (sum[N-1] - map[0] - map[i])
                     + (sum[N-1] - sum[i]);

            int sum2 = (sum[N-1] - map[i] - map[N-1])
                     + (sum[i] - map[i]);

            int sum3 = (sum[i] - map[0])
                     + (sum[N-2] - sum[i-1]);
            ans = Math.max(ans, Math.max(sum1, Math.max(sum2, sum3)));
        }
        System.out.println(ans);
    }
}
