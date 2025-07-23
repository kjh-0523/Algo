import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] tops;
    public static int[] laser;
    public static Stack<int[]> nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        tops = new int[N+1];
        laser = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; i++) {
            tops[i] = Integer.parseInt(st.nextToken());
        }

        nums = new Stack<>();
        for(int i = N; i > 0; i--){
            nums.push(new int[]{i, tops[i]});
            if(tops[i-1] < nums.peek()[1]) continue;
            while(!nums.isEmpty() && nums.peek()[1] <= tops[i-1]){
                int[] cur = nums.pop();
                laser[cur[0]] = i-1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < N+1; i++){
            sb.append(laser[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}
