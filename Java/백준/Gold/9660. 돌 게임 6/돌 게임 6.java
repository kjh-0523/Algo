import java.io.*;
import java.util.*;

public class Main {
    public static long N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine())-1;

        String[] ans = {"SK", "CY", "SK", "SK", "SK", "SK", "CY"};
        int idx = (int) (N % 7);

        System.out.println(ans[idx]);
    }
}
