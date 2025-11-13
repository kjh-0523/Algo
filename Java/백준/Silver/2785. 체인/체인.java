import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        Deque<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++){
            q.offerLast(nums[i]);
        }

        int ans = 0;
        while(q.size() > 1){
            int num1 = q.pollLast();
            int num2 = q.pollLast();

            q.offerLast(num1 + num2);

            if(q.size() >= 2){
                int num = q.pollFirst();
                if(num > 1){
                    q.offerFirst(num - 1);
                }
            }
            ans++;
        }

        System.out.println(ans);
    }
}