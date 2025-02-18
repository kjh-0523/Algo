import java.util.*;
import java.io.*;

public class Main {
    public static int N;
    public static ArrayList<Integer> nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            nums.add(Integer.parseInt(br.readLine()));
        }
        nums.sort((o1,o2) -> o1 - o2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(nums.get(i)).append("\n");
        }
        System.out.println(sb.toString());
    }
}