import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 받기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()); 

        int[][] stars = new int[K][2];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Integer.parseInt(st.nextToken());
            stars[i][1] = Integer.parseInt(st.nextToken());
        }

        int maxCount = 0;

        for (int[] star1 : stars) {
            for (int[] star2 : stars) {
                int x1 = star1[0];
                int y1 = star2[1];

                int count = 0;
                for (int[] star : stars) {
                    int x = star[0], y = star[1];
                    if (x1 <= x && x <= x1 + L && y1 <= y && y <= y1 + L) {
                        count++;
                    }
                }
                maxCount = Math.max(maxCount, count);
            }
        }

        System.out.println(K - maxCount);
    }
}
