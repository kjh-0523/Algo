import java.util.*;

public class Solution {
    public long solution(int[][] land, int P, int Q) {
        int n = land.length;
        int size = n * n;

        int[] arr = new int[size];
        int idx = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[idx++] = land[i][j];
            }
        }

        Arrays.sort(arr);

        long[] prefix = new long[size + 1];
        for (int i = 0; i < size; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }

        long answer = Long.MAX_VALUE;

        // 4️⃣ 같은 높이는 한 번만 계산
        for (int i = 0; i < size; i++) {
            // 같은 높이 스킵
            if (i > 0 && arr[i] == arr[i - 1]) continue;

            int h = arr[i];

            long leftCount = i;
            long leftSum = prefix[i];

            long rightCount = size - i;
            long rightSum = prefix[size] - prefix[i];

            long buildCost = (h * leftCount - leftSum) * P;
            long cutCost = (rightSum - h * rightCount) * Q;

            answer = Math.min(answer, buildCost + cutCost);
        }

        return answer;
    }
}
