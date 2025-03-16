import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int N, K;
    public static Map<Integer, Integer> cnts;
    public static Queue<Integer> sequence;
    public static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cnts = new HashMap<>();
        sequence = new LinkedList<>();
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        int ans = 0;
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            sequence.offer(nums[i]);
            cnts.put(nums[i], cnts.getOrDefault(nums[i], 0) + 1);
            if (cnts.get(nums[i]) > K) {
                ans = Math.max(ans, sequence.size() - 1);
                while (!sequence.isEmpty()) {
                    int cur = sequence.poll();
                    cnts.put(cur, cnts.get(cur) - 1);
                    if (cur == nums[i]) {
                        break;
                    }
                }
            }
        }
        ans = Math.max(ans, sequence.size());
        System.out.println(ans);
    }
}
