import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int[] time;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        String balls = br.readLine();
        int cntB = 0;
        int cntR = 0;
        for(int i=0; i<n; i++){
            if(balls.charAt(i) == 'B') cntB++;
            else cntR++;
        }

        int rightCnt = 0;
        int idx = n-1;
        while(idx >= 0 && balls.charAt(idx) == balls.charAt(n-1)){
            rightCnt++;
            idx--;
        }

        int leftCnt = 0;
        idx = 0;
        while(idx <= n-1 && balls.charAt(idx) == balls.charAt(0)){
            leftCnt++;
            idx++;
        }

        if(balls.charAt(n-1) == 'B') rightCnt = Math.min(cntB - rightCnt, cntR);
        else rightCnt = Math.min(cntR - rightCnt, cntB);

        if(balls.charAt(0) == 'B') leftCnt = Math.min(cntB - leftCnt, cntR);
        else leftCnt = Math.min(cntR - leftCnt, cntB);

        System.out.println(Math.min(rightCnt, leftCnt));
    }
}