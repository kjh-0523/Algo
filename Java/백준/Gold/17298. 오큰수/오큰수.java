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
		int[] ans = new int[N];
		ans[N-1] = -1;
		stack.push(nums[N-1]);
		for (int i = N-2; i > -1; i--) {
			while(!stack.isEmpty()) {
				if(stack.peek() > nums[i]) {
					ans[i] = stack.peek();
					stack.push(nums[i]);
					break;
				}
				stack.pop();
			}
			if(stack.isEmpty()) {
				ans[i] = -1;
				stack.push(nums[i]);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(ans[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
}
