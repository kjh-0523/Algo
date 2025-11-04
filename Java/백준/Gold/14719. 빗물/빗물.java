import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[h][w];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < w; i++){
            int cur = Integer.parseInt(st.nextToken());

            for(int j = 0; j < cur; j++){
                map[j][i] = true;
            }
        }

        int sum = 0;
        for(int i = 0; i < h; i++){
            int left = -1;
            for(int j = 0; j < w; j++){
                if(map[i][j]){
                    if(left != -1){
                        sum += j - left - 1;
                    }
                    left = j;
                }
            }
        }

        System.out.println(sum);
    }
}