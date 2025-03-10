import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N, S;
    public static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        S = Integer.parseInt(br.readLine());

        for (int i = 0; i < N - 1 && S > 0; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < N && j - i <= S; j++) {
                if (nums[j] > nums[maxIdx]) {
                    maxIdx = j;
                }
            }

            for (int j = maxIdx; j > i; j--) {
                swap(j, j - 1);
            }
            S -= (maxIdx - i);
        }

        for (int i = 0; i < N; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static void swap(int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
