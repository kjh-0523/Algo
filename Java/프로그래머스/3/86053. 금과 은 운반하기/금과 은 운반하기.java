class Solution {
    public static int A, B, N;
    public static int[] gold, silver, weight, time;

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        A = a;
        B = b;
        N = g.length;
        gold = g;
        silver = s;
        weight = w;
        time = t;

        long start = 0;
        long end = (long) 1e14 * 4;
        long answer = end;

        while (start <= end) {
            long mid = (start + end) / 2;

            if (checkSum(mid)) {
                answer = mid;
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return answer;
    }

    public static boolean checkSum(long at) {
        long total = 0;
        long goldSum = 0;
        long silverSum = 0;

        for (int i = 0; i < N; i++) {
            long cnt = at / (time[i] * 2);
            if (at % (time[i] * 2) >= time[i]) cnt++;

            long max = weight[i] * cnt;

            total += Math.min(max, gold[i] + silver[i]);
            goldSum += Math.min(max, gold[i]);
            silverSum += Math.min(max, silver[i]);
        }

        return total >= A + B && goldSum >= A && silverSum >= B;
    }
}
