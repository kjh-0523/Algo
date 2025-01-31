import java.util.*;
import java.io.*;

public class Main {
    public static int N,M;
    public static boolean[] broken;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        if(M > 0) {
            st = new StringTokenizer(br.readLine(), " ");
        }
        broken = new boolean[10];
        for(int i = 0; i < M; i++){
            int b = Integer.parseInt(st.nextToken());
            broken[b] = true;
        }
        int cntPlus = 0;
        int cur = N;
        while(cur < 1000000){
            if(cur == 100) break;
            if(cur != 1000000 && check(cur)){
                cntPlus += cntDigit(cur);
                break;
            }
            cur++;
            cntPlus++;
        }
        cur = N;
        int cntMin = 0;
        while(cur >= -1){
            if(cur == 100) break;
            if(cur != -1 && check(cur)){
                cntMin += cntDigit(cur);
                break;
            }
            cur--;
            cntMin++;
        }
        if(Math.abs(N - 100) < cntMin) cntMin = Math.abs(N - 100);
        if(cur > -1){
            System.out.println(Math.min(cntPlus,cntMin));
        }else{
            System.out.println(cntPlus);
        }
    }

    public static boolean check(int cur){
        int c = -1;
        if(cur > 99999){
            c = (cur%1000000)/ 100000;
            if(broken[c]) return false;
        }
        if(cur > 9999){
            c = (cur%100000) / 10000;
            if(broken[c]) return false;
        }
        if(cur > 999){
            c = (cur%10000) / 1000;
            if(broken[c]) return false;
        }
        if(cur > 99){
            c = (cur%1000) / 100;
            if(broken[c]) return false;
        }
        if(cur > 9){
            c = (cur%100) / 10;
            if(broken[c]) return false;
        }
        c = (cur%10);
        return !broken[c];
    }

    public static int cntDigit(int cur){
        if(cur > 99999) return 6;
        else if(cur > 9999) return 5;
        else if(cur > 999) return 4;
        else if(cur > 99) return 3;
        else if(cur > 9)return 2;
        else return 1;
    }
}
