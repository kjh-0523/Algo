import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static boolean[] letter;
    public static boolean[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        letter = new boolean[N];
        ans = new boolean[N];
        for (int i = 0; i < N; i++) {
            letter[i] = Integer.parseInt(st.nextToken()) == 1;
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if(letter[i] != ans[i]){
                for (int j = i; j < i+3; j++) {
                    if(j > N-1) break;
                    ans[j] = !ans[j];
                }
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
