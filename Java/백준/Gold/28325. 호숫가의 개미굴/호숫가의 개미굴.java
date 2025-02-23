import java.io.*;
import java.util.*;

public class Main {

    public static int N;
    public static long count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        long[] rooms = new long[N];

        for (int i = 0; i < N; i++) {
            rooms[i] = Long.parseLong(st.nextToken());
        }

        Set<Integer> idx = new HashSet<>();
        for (int i = 0; i < N; i++) {
            if (rooms[i] != 0) {
                idx.add(i);
            }
        }

        count = 0;

        if (idx.isEmpty()) {
            System.out.println(N / 2);
            return;
        }

        // 개미가 있는 곳에서 개미 배치하기
        int[] memo = new int[N];
        Arrays.fill(memo, -1); // -1로 초기화

        for (int i = 0; i < N; i++) {
            if (idx.contains(i)) {
                count += rooms[i];
                memo[i] = 0;
            }
        }

        // N == 2일 경우 처리
        if (N == 2) {
            if (idx.size() == 1) {
                count += 1;
            }
            System.out.println(count);
            return;
        }

        int index = 0;
        for (int i = 0; i < N; i++) {
            if (idx.contains(i)) {
                index = i;
                break;
            }
        }

        // 원형으로 돌며 인접한 방에 개미 배치
        for (int i = index; i < N + index; i++) {
            if (memo[i % N] == -1) {
                if (memo[(i - 1 + N) % N] != 1 && memo[(i + 1) % N] != 1) { // 양옆에 개미가 없으면
                    memo[i % N] = 1;
                    count += 1;
                }
            }
        }

        System.out.println(count);
    }
}
