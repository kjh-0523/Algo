import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] tallerCount = new int[N]; // 입력 받은 왼쪽에 있는 큰 사람 수
        int[] result = new int[N]; // 최종 줄을 선 결과

        // 입력 받기
        for (int i = 0; i < N; i++) {
            tallerCount[i] = sc.nextInt();
        }

        // 사람을 1부터 N까지 배치
        for (int i = 0; i < N; i++) {
            int count = tallerCount[i]; // 왼쪽에 있어야 하는 큰 사람 수
            for (int j = 0; j < N; j++) {
                if (count == 0 && result[j] == 0) { 
                    result[j] = i + 1; // i+1 키인 사람을 해당 자리에 배치
                    break;
                }
                if (result[j] == 0) { 
                    count--; // 빈 자리일 경우만 count 감소
                }
            }
        }

        // 결과 출력
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
