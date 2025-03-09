import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();  // 숫자의 개수 입력
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            if (isPrime(num)) {
                count++;
            }
        }
        
        System.out.println(count);
        scanner.close();
    }

    public static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
