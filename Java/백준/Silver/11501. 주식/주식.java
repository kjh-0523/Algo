import java.util.*;
import java.io.*;

public class Main {
    public static int T;
    public static int N;
    public static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc = 0; tc < T; tc++){
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            nums = new int[N];

            for(int i = 0; i < N; i++){
                nums[i] = Integer.parseInt(st.nextToken());
            }

            int max = 0;
            long ans = 0;
            for(int i = N-1; i > -1; i--){
                if(nums[i] >= max){
                    max = nums[i];
                }else{
                    ans -= nums[i];
                    ans += max;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }
}