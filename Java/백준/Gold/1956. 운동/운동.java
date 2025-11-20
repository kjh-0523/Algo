import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static final int INF = (int)1e9;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[][] node = new int[v+1][v+1];
        for(int i = 1; i < v+1; i++){
            Arrays.fill(node[i], INF);
            node[i][i] = 0;
        }
        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            node[from][to] = weight;
        }

        for(int k = 1; k < v+1; k++){
            for(int i = 1; i < v+1; i++){
                for(int j = 1; j < v+1; j++){
                    node[i][j] = Math.min(node[i][j], node[i][k] + node[k][j]);
                }
            }
        }

        int ans = INF;
        for(int i = 1; i < v+1; i++){ for(int j = 1; j < v+1; j++){ if(i == j) continue; ans = Math.min(ans, node[i][j] + node[j][i]); } }
        System.out.println(ans == INF ? -1 : ans);
    }
}