import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] nums;
    public static ArrayList<Integer> dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(br.readLine());
        }

        dp = new ArrayList<>();
        for(int i = 0; i < N; i++){
            if(!dp.isEmpty() && nums[i] <= dp.get(dp.size() - 1)){
                int pos = binarySearch(0, dp.size()-1, nums[i]);
                dp.set(pos, nums[i]);
                continue;
            }
            dp.add(nums[i]);
        }

        System.out.println(N - dp.size());

    }

    public static int binarySearch(int start, int end, int element){
        while(start < end){
            int mid = (start + end) / 2;
            if(element > dp.get(mid)) start = mid + 1;
            else end = mid;
        }
        return end;
    }
}
