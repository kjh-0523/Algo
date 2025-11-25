import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int[][] gears = new int[4][8];
        for(int i = 0; i < 4; i++) {
            String line = br.readLine();
            for(int j = 0; j < 8; j++){
                gears[i][j] = line.charAt(j) - '0';
            }
        }

        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            // 회전 방향 결정
            int[] direction = new int[4];
            direction[num] = dir;
            for(int j = 1; j < 4; j++){
                if(num + j < 4){
                    if(gears[num + j][6] != gears[num + j - 1][2]){
                        direction[num + j] = -direction[num + j - 1];
                    }
                }

                if(num - j > -1){
                    if(gears[num - j][2] != gears[num - j + 1][6]){
                        direction[num - j] = -direction[num - j + 1];
                    }
                }
            }

            for(int j = 0; j < 4; j++){
                rotate(gears[j], direction[j]);
            }
        }

        int ans = 0;
        for(int i = 0; i < 4; i++){
            ans += (int)(gears[i][0] * Math.pow(2, i));
        }
        System.out.println(ans);
    }

    public static void rotate(int[] gear, int dir){
        if(dir == 0) return;
        if(dir == 1){
            int temp = gear[7];
            for(int i = 7; i > 0; i--){
                gear[i] = gear[i - 1];
            }
            gear[0] = temp;
        }else{
            int temp = gear[0];
            for(int i = 0; i < 7; i++){
                gear[i] = gear[i + 1];
            }
            gear[7] = temp;
        }
    }
}