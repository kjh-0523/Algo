import java.util.*;
import java.io.*;

public class Main {
    public static int N,K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(dp(N,K));
    }

    public static int dp(int n, int k){
        if(k == 0 || n == k) return 1;
        return dp(n-1, k) + dp(n-1, k-1);
    }
}
