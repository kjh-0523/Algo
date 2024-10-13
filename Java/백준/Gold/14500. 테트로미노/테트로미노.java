import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int N, M, ans;
    static int[][] map;
    static int[][][] tetromino = {
        {{0,0},{0,1},{0,2},{0,3}},  // 1 모양
        {{0,0},{1,0},{2,0},{3,0}},  // 1 모양
        {{0,0},{1,0},{0,1},{1,1}},  // ㅁ 모양
        {{0,0},{1,0},{2,0},{2,1}},  // L 모양
        {{0,0},{1,0},{2,0},{2,-1}},  // L 모양
        {{0,0},{1,0},{2,0},{0,1}},  // L 모양
        {{0,0},{1,0},{2,0},{0,-1}},  // L 모양
        {{0,0},{0,1},{0,2},{1,2}},  // L 모양
        {{0,0},{0,1},{0,2},{-1,2}},  // L 모양
        {{0,0},{0,1},{0,2},{1,0}},  // L 모양
        {{0,0},{0,1},{0,2},{-1,0}},  // L 모양
        {{0,0},{1,0},{1,1},{2,1}},  // z 모양
        {{0,0},{1,0},{1,-1},{2,-1}}, // z 모양
        {{0,0},{0,1},{-1,1},{-1,2}}, // z 모양
        {{0,0},{0,1},{1,1},{1,2}}, // z 모양
        {{0,0},{-1,0},{0,1},{0,-1}},  // T 모양
        {{0,0},{1,0},{0,-1},{0,1}}, // T 모양
        {{0,0},{-1,0},{1,0},{0,1}},  // T 모양
        {{0,0},{-1,0},{1,0},{0,-1}},  // T 모양
    };
    
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int[][] shape : tetromino) {
                    calculateMaxSum(i, j, shape);
                }
            }
        }
        
        System.out.println(ans);
    }
    
    public static void calculateMaxSum(int x, int y, int[][] shape) {
        int sum = 0;
        for (int[] s : shape) {
            int nx = x + s[0];
            int ny = y + s[1];
            if (nx < 0 || ny < 0 || nx > N-1 || ny > M-1) {
                return;
            }
            sum += map[nx][ny];
        }
        ans = Math.max(ans, sum);
    }
}
