import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        String line = br.readLine();
        int n = line.length();

        int cnt = 0;
        for(int i = 0; i < n; i++){
            if(line.charAt(i) == 'a') cnt++;
        }

        String line2 = line + line;
        int cur = 0;
        for(int i = 0; i < cnt; i++){
            if(line2.charAt(i) == 'a') cur++;
        }

        int ans = cnt - cur;
        for(int i = 1; i < n; i++){
            if(line2.charAt(i-1) == 'a') cur--;
            if(line2.charAt(i+cnt-1) == 'a') cur++;

            ans = Math.min(ans, cnt - cur);
            if(ans == 0) break;
        }

        System.out.println(ans);
    }
}