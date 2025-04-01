import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }
        scanner.close();

        if (N == 1) {
            System.out.println("A");
            return;
        }
        
        if (N == 2) {
            if (arr[0] == arr[1]) {
                System.out.println(arr[0]);
            } else {
                System.out.println("A");
            }
            return;
        }
        
        Integer a = null, b = null;
        boolean valid = true;
        
        if (arr[1] - arr[0] != 0) {
            a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
            b = arr[1] - arr[0] * a;
        } else {
            a = 0;
            b = arr[1];
        }

        for (int i = 1; i < N; i++) {
            if (arr[i] != arr[i - 1] * a + b) {
                valid = false;
                break;
            }
        }

        if (!valid) {
            System.out.println("B");
        } else {
            System.out.println(arr[N - 1] * a + b);
        }
    }
}