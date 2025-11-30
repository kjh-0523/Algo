import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static int N;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            arr[i] = line.toCharArray();
        }

        System.out.println(aaa(0, 0, N));
    }

    static boolean check(int x, int y, int size) {
        char start = arr[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != start) {
                    return false;
                }
            }
        }
        return true;
    }

    static String aaa(int x, int y, int size) {
        if (size == 1) {
            return String.valueOf(arr[x][y]);
        }

        if (check(x, y, size)) {
            return String.valueOf(arr[x][y]);
        }

        int newSize = size / 2;
        String p1 = aaa(x, y, newSize);
        String p2 = aaa(x, y + newSize, newSize);
        String p3 = aaa(x + newSize, y, newSize);
        String p4 = aaa(x + newSize, y + newSize, newSize);

        return "(" + p1 + p2 + p3 + p4 + ")";
    }
}
