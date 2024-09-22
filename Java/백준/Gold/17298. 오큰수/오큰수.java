import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/*시간 : 184ms 메모리 : 23616kb*/
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < N; i++) {
			if(!stack.isEmpty()) {
				while(!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
					nums[stack.pop()] = nums[i];
				}
			}
			stack.push(i);
		}
		while(!stack.isEmpty()) {
			nums[stack.pop()] = -1;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			sb.append(nums[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
}
