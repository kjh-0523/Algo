import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] h, cnt, near;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        h = new int[N + 1];
        cnt = new int[N + 1];
        near = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= N; i++) {
            while (!stack.isEmpty() && h[stack.peek()] <= h[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                cnt[i] += stack.size();
                near[i] = stack.peek();
            }
            stack.push(i);
        }

        stack.clear();
        for (int i = N; i >= 1; i--) {
            while (!stack.isEmpty() && h[stack.peek()] <= h[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                cnt[i] += stack.size();
                if (near[i] == 0 || Math.abs(near[i] - i) > Math.abs(stack.peek() - i)) {
                    near[i] = stack.peek();
                }
            }
            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (cnt[i] == 0) {
                sb.append(0).append("\n");
            } else {
                sb.append(cnt[i]).append(" ").append(near[i]).append("\n");
            }
        }
        System.out.print(sb);
    }
}
