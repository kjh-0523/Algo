import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long[] sum = new long[N + 1]; // 부분합 배열
        long totalSum = 0; // 전체 합
        
        // 입력값을 받고 부분합 계산
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
        }

        totalSum = sum[N]; // 전체 합은 sum[N]에 저장됨
        
        // 전체 합이 4로 나누어지지 않으면 답은 0
        if (totalSum % 4 != 0) {
            System.out.println(0);
            return;
        }
        
        long partSum = totalSum / 4; // 각 구간의 합
        long[] count = new long[4]; // 각 구간에 해당하는 개수
        
        long ans = 0; // 정답 변수
        long countPart1 = 0; // 첫 번째 구간에 해당하는 합을 저장

        // 부분합을 계산하면서 가능한 경우의 수 카운팅
        for (int i = 1; i < N; i++) { // 마지막 원소는 제외
            if (sum[i] == 3 * partSum) {
                ans += count[2]; // sum[i] == 3*partSum인 구간의 경우 두 번째 구간까지 완료된 모든 경우의 수를 더함
            }
            if (sum[i] == 2 * partSum) {
                count[2] += countPart1; // sum[i] == 2*partSum인 구간에 대해 첫 번째 구간이 몇 번 있었는지 기록
            }
            if (sum[i] == partSum) {
                countPart1++; // sum[i] == partSum인 경우의 수 카운트
            }
        }
        
        System.out.println(ans); // 결과 출력
    }
}
