class Solution {
    public int solution(int n) {
        final int MOD = 1_000_000_007;

        long[] dp  = new long[n + 1]; // 전체 경우의 수
        long[] dp4 = new long[n + 1]; // 4 + 3k 유형 누적합 (각 항이 2*dp[i-4])
        long[] dp5 = new long[n + 1]; // 5 + 3k 유형 누적합 (각 항이 2*dp[i-5])
        long[] dp6 = new long[n + 1]; // 6 + 3k 유형 누적합 (각 항이 4*dp[i-6])

        dp[0] = 1L;

        for (int i = 1; i <= n; i++) {
            if (i - 1 >= 0) dp[i] = (dp[i] + dp[i - 1]) % MOD;
            if (i - 2 >= 0) dp[i] = (dp[i] + (2L * dp[i - 2]) % MOD) % MOD;
            if (i - 3 >= 0) dp[i] = (dp[i] + (5L * dp[i - 3]) % MOD) % MOD;

            if (i - 4 >= 0) {
                long add = (2L * dp[i - 4]) % MOD;
                dp4[i] = (dp4[i - 3] + add) % MOD;
                dp[i] = (dp[i] + dp4[i]) % MOD;
            }

            if (i - 5 >= 0) {
                long add = (2L * dp[i - 5]) % MOD;
                dp5[i] = (dp5[i - 3] + add) % MOD;
                dp[i] = (dp[i] + dp5[i]) % MOD;
            }

            if (i - 6 >= 0) {
                long add = (4L * dp[i - 6]) % MOD;
                dp6[i] = (dp6[i - 3] + add) % MOD;
                dp[i] = (dp[i] + dp6[i]) % MOD;
            }
        }

        return (int) dp[n];
    }
}
