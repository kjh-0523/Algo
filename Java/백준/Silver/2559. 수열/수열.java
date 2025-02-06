import java.io.*;
import java.util.*;

class Main {
    public static int N, K;
    public static int[] nums;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            nums[i] = num;
        }

        int sum = 0;
        for(int i = 0; i < K; i++){
            sum += nums[i];
        }

        int ans = sum;
        for(int i = K; i < N; i++){
            sum += nums[i] - nums[i-K];
            ans = Math.max(ans, sum);
        }

        System.out.println(ans);
    }

}