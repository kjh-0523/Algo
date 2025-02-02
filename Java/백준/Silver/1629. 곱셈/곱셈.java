import java.util.*;
import java.io.*;

class Main
{
    public static int a,b,c;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        long ans = pow(b);

        System.out.println(ans);
    }

    public static long pow(int multiple){
        if(multiple == 1){
            return a % c;
        }

        long temp = pow(multiple / 2);

        if(multiple % 2 == 1){
            return (temp * temp % c) * a % c;
        }
        return temp * temp % c;
    }
}