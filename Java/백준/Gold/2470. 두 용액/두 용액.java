import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] liquid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        liquid = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for (int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(liquid);

        int left = 0;
        int right = N-1;
        int sum = (int) 2e9;
        int[] ans = new int[2];
        while(left < right) {
            if(Math.abs(liquid[left] + liquid[right]) < sum) {
                sum = Math.abs(liquid[left] + liquid[right]);
                ans[0] = liquid[left];
                ans[1] = liquid[right];
            }else{
                if(liquid[left] + liquid[right] < 0) left++;
                else right--;
            }
            if(sum == 0) break;
        }
        System.out.println(ans[0] + " " + ans[1]);
    }
}
