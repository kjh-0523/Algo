import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] dp = new int[41];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i < N + 1; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int ans = 1;

        int beforeSeat = 0;
        for(int i = 0; i < M; i++){
            int temp = Integer.parseInt(br.readLine());
            ans *= dp[temp - beforeSeat - 1];
            beforeSeat = temp;
        }

        ans *= dp[N - beforeSeat];

        System.out.println(ans);
    }
}
