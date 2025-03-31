import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int Good;
	static int N;
	static long[] nums;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		for (int i = 0; i < N; i++) {
			int left = 0;
			int right = N-1;
			while(left < right) {
				if(left == i) {
					left++;
					continue;
				}
				if(right == i) {
					right--;
					continue;
				}
				if(nums[i] == nums[left] + nums[right]) {
					Good++;
					break;
				}else if(nums[i] > nums[left] + nums[right]) {
					left++;
				}else if(nums[i] < nums[left] + nums[right]) {
					right--;
				}
				
				
			}
			
		}
		System.out.println(Good);
	}

}