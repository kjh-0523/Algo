import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String number = br.readLine().trim();

        StringBuilder stack = new StringBuilder();

        for (int i = 0; i < number.length(); i++) {
            char cur = number.charAt(i);
            while (K > 0 && stack.length() > 0 && stack.charAt(stack.length() - 1) < cur) {
                stack.deleteCharAt(stack.length() - 1);
                K--;
            }
            stack.append(cur);
        }

        if (K > 0) {
            stack.setLength(stack.length() - K);
        }

        System.out.println(stack.toString());
    }
}
