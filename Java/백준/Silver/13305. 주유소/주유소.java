import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());

        int[] dist = new int[n-1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n-1; i++){
            dist[i] = Integer.parseInt(st.nextToken());
        }

        int[] price = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            price[i] = Integer.parseInt(st.nextToken());
        }

        long p = price[0];
        long sum = dist[0];
        long ans = 0;
        for(int i = 1; i < n-1; i++){
            if(price[i] < p){
                ans += p * sum;
                p = price[i];
                sum = dist[i];
            }else{
                sum += dist[i];
            }
        }

        ans += p * sum;
        System.out.println(ans);
    }
}