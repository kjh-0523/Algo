import java.util.*;
import java.io.*;

class Main
{
    public static int N;
    public static int[] menu;
    public static long[] pow;
    public static final int DIV = 1000000007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        menu = new int[N];
        for(int i = 0; i < N; i++){
            menu[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(menu);
        pow = new long[N];
        pow[0] = 1;
        for(int i = 1; i < N; i++){
            pow[i] = pow[i-1]*2 % DIV;
        }
        long ans = 0;
        for(int i = 0; i < N; i++){
            ans += menu[i] * (pow[i] - pow[N-1-i] + DIV) % DIV;
        }
        System.out.println(ans % DIV);
    }
}