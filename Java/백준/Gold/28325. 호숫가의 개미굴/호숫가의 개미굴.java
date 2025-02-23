import java.io.*;
import java.util.*;

public class Main {

    public static int N;
    public static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] rooms = new int[N];

        for (int i = 0; i < N; i++) {
            rooms[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> idx = new HashSet<>();
        for (int i = 0; i < N; i++) {
            if (rooms[i] != 0) {
                idx.add(i);
            }
        }

        if (idx.isEmpty()) {
            System.out.println(N / 2);
        } else {
            int[] memo = new int[N];
            count = 0;
            for (int i = 0; i < N; i++) {
                if (idx.contains(i)) {
                    count += rooms[i];
                    memo[i] = -1;
                }
            }
            if (N == 2) {
                if (idx.size() == 1) {
                    count += 1;
                }
            } else {
                int index = 0;
                for (int i = 0; i < N; i++) {
                    if (idx.contains(i)) {
                        index = i;
                        break;
                    }
                }
                for (int i = index; i < N + index; i++) {
                    if (memo[i % N] == 0) {
                        if (memo[(i - 1 + N) % N] != 1 && memo[(i + 1) % N] != 1) {
                            memo[i % N] = 1;
                            count += 1;
                        }
                    }
                }
            }
            System.out.println(count);
        }
    }
}
