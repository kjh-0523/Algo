import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] score;
    public static int[][] dpMax, dpMin;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        score = new int[N][3];
        dpMax = new int[N][3];
        dpMin = new int[N][3];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            score[i][0] = Integer.parseInt(st.nextToken());
            score[i][1] = Integer.parseInt(st.nextToken());
            score[i][2] = Integer.parseInt(st.nextToken());
        }

        dpMax[0][0] = score[0][0];
        dpMax[0][1] = score[0][1];
        dpMax[0][2] = score[0][2];
        dpMin[0][0] = score[0][0];
        dpMin[0][1] = score[0][1];
        dpMin[0][2] = score[0][2];
        for(int i = 1; i < N; i++){
            dpMax[i][0] = Math.max(dpMax[i-1][0], dpMax[i-1][1]) + score[i][0];
            dpMax[i][1] = Math.max(dpMax[i-1][0], Math.max(dpMax[i-1][1], dpMax[i-1][2]))+score[i][1];
            dpMax[i][2] = Math.max(dpMax[i-1][1], dpMax[i-1][2]) + score[i][2];

            dpMin[i][0] = Math.min(dpMin[i-1][0], dpMin[i-1][1]) + score[i][0];
            dpMin[i][1] = Math.min(dpMin[i-1][0], Math.min(dpMin[i-1][1], dpMin[i-1][2]))+score[i][1];
            dpMin[i][2] = Math.min(dpMin[i-1][1], dpMin[i-1][2]) + score[i][2];
        }

        int max = Math.max(dpMax[N-1][0], Math.max(dpMax[N-1][1], dpMax[N-1][2]));
        int min = Math.min(dpMin[N-1][0], Math.min(dpMin[N-1][1], dpMin[N-1][2]));
        System.out.println(max + " " + min);
    }
}
