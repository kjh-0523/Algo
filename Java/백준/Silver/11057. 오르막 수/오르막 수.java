import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static int N;
    public static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[10];
        for (int i = 0; i < 10; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < 10; j++) {
                dp[j] = (dp[j] + dp[j - 1]) % 10007;
            }
        }

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = (sum + dp[i]) % 10007;
        }
        System.out.println(sum % 10007);
    }
}
