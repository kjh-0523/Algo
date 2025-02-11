import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[] dp = new boolean[N+1];
        if(N>=1) dp[1] = true;
        if(N>=3) dp[3] = true;

        for(int i = 2; i <=N; i++){
            if ((i - 1 >= 0 && !dp[i - 1]) || (i - 3 >= 0 && !dp[i - 3])) {
                dp[i] = true;
            }
        }

        if(dp[N]) System.out.println("SK");
        else System.out.println("CY");
    }

}