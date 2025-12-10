import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    static int n, m, k;
    static int[] pos;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        pos = new int[k];
        for(int i = 0; i < k; i++){
            pos[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = n;
        String ans = "";
        while(left <= right){
            int mid = (left + right) / 2;

            String res = check(mid);
            if(res.isEmpty()){
                right = mid-1;
            }else{
                left = mid+1;
                ans = res;
            }
        }

        System.out.println(ans);
    }

    static String check(int dis){
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        sb.append(1);
        int before = pos[0];

        for(int i = 1; i < k; i++){
            if(cnt == m){
                sb.append(0);
            } else if(pos[i] - before >= dis) {
                sb.append(1);
                cnt++;
                before = pos[i];
            } else{
                sb.append(0);
            }
        }
        if(cnt == m){
            return sb.toString();
        }
        return "";
    }
}