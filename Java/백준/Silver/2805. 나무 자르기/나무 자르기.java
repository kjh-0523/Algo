import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Long> nums = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            nums.add(Long.parseLong(st.nextToken()));
        }
        nums.sort(Collections.reverseOrder());

        long left = 0;
        long right = 1000000000;
        while(left <= right){
            long mid = (left + right) / 2;
            long sum = 0;

            for(long num : nums){
                if(num <= mid) break;
                sum += num - mid;
            }

            if(sum >= m){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }

        System.out.println(right);
    }
}