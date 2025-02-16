import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] checker = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            checker[i] = line.toCharArray();
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                ans = Math.min(ans, paint(checker, i, j));
            }
        }

        System.out.println(ans);
    }

    public static int paint(char[][] board, int startX, int startY) {
        int whiteStart = 0; // W로 시작하는 경우
        int blackStart = 0; // B로 시작하는 경우

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char current = board[startX + i][startY + j];
                if ((i + j) % 2 == 0) {
                    if (current != 'W') whiteStart++;
                    if (current != 'B') blackStart++;
                } else {
                    if (current != 'B') whiteStart++;
                    if (current != 'W') blackStart++;
                }
            }
        }

        return Math.min(whiteStart, blackStart);
    }
}
