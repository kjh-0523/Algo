import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int ans = 0;
            int N = Integer.parseInt(br.readLine());
            int[] cnt = new int[N];
            char[] bomb = new char[N];

            String line = br.readLine();
            for (int i = 0; i < N; i++) {
                cnt[i] = Integer.parseInt(String.valueOf(line.charAt(i)));
            }

            line = br.readLine();
            for (int i = 0; i < N; i++) {
                bomb[i] = line.charAt(i);
            }

            for (int i = 0; i < N; i++) {
                if (bomb[i] == '*') {
                    for (int j = -1; j < 2; j++) {
                        if (i + j < 0 || i + j > N - 1) {
                            continue;
                        }
                        cnt[i + j]--;
                    }
                    bomb[i] = 'X';
                    ans++;
                }
            }

            for (int i = 0; i < N; i++) {
                if (cnt[i] > 0) {
                    if (i > 1 && cnt[i - 1] == 0) {
                        continue;
                    }
                    if (i < N - 1 && cnt[i + 1] == 0) {
                        continue;
                    }
                    for (int j = -1; j < 2; j++) {
                        if (i + j < 0 || i + j > N - 1) {
                            continue;
                        }
                        cnt[i + j]--;
                    }
                    bomb[i] = 'X';
                    ans++;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }
}
