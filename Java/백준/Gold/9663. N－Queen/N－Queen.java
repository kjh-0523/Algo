import java.util.Scanner;

public class Main {
	static int N, col[],ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		col = new int[N+1];
		
		setQueens(1);
		System.out.println(ans);
	}
	
	static void setQueens(int rowNo) {
		if(!isAvailable(rowNo-1)) return;
		
		if(rowNo > N) {
			// 무조건 답
			ans++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			col[rowNo] = i;
			setQueens(rowNo+1);
		}
	}
	
	private static boolean isAvailable(int rowNo) {
		
		for (int i = 1; i < rowNo; i++) {
			if(col[rowNo] == col[i] || rowNo - i == Math.abs(col[rowNo] - col[i])) return false;
		}
		
		return true;
	}
}
