import java.io.*;
import java.util.*;

public class Main {
    public static int N, ans;
    public static int[] buildings;
    public static int[] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        buildings = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        memo = new int[N];
        for(int i = 0; i < N-1; i++) {
            int cnt = 0;
            for(int j = i+1; j < N; j++) {
                double incl = calIncl(i, buildings[i], j, buildings[j]);
                double coe = calCoe(incl, i, buildings[i]);
                boolean flag = true;
                int idx = i+1;
                while(idx < j){
                    if(!isValid(incl, coe, idx, buildings[idx])){
                        flag = false;
                        break;
                    }
                    idx++;
                }
                if(flag){
                    memo[i]++;
                    memo[j]++;
                }
            }
        }
        ans = 0;
        for(int i = 0; i < N; i++) {
            ans = Math.max(ans, memo[i]);
        }
        System.out.println(ans);
    }

    public static double calIncl(int x1, int y1, int x2, int y2) {
        return (double)(y2 - y1) / (x2 - x1);
    }

    public static double calCoe(double incl, int x, int y){
        return y - incl * x;
    }

    public static boolean isValid(double incl, double coe, int x, int y){
        double result = incl * x + coe;
		return result > y;
    }
}
