import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N, K;
    public static int[] status;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        status = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            status[i] = Integer.parseInt(st.nextToken());
        }

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            switch (gender) {
                case 1:
                    doBoy(num);
                    break;
                case 2:
                    doGirl(num);
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            sb.append(status[i]).append(" ");
            if (i % 20 == 0) {
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static void doGirl(int num) {
        status[num] = status[num] == 1 ? 0 : 1;
        for (int i = 1; i < N; i++) {
            if (num + i > N || num - i < 1 || status[num + i] != status[num - i]) {
                break;
            }
            status[num + i] = status[num + i] == 1 ? 0 : 1;
            status[num - i] = status[num + i];
        }
    }

    private static void doBoy(int num) {
        int mul = 1;
        while (num * mul < N + 1) {
            status[num * mul] = status[num * mul] == 1 ? 0 : 1;
            mul++;
        }
    }
}
