import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static boolean flag;
    public static int[][] sudo;
    public static ArrayList<int[]> zeros;
    public static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        sudo = new int[9][9];
        zeros = new ArrayList<>();
        N = 0;
        for(int i = 0; i < 9; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++){
                sudo[i][j] = Integer.parseInt(st.nextToken());
                if(sudo[i][j] == 0) {
                    zeros.add(new int[]{i, j});
                    N++;
                }
            }
        }

        sb = new StringBuilder();
        flag = false;
        dfs(0);
        System.out.println(sb.toString());
    }

    public static void dfs(int idx){
        if(flag){
            return;
        }
        if(idx == N){
            flag = true;
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    sb.append(sudo[i][j]).append(" ");
                }
                sb.append("\n");
            }
            return;
        }

        int x = zeros.get(idx)[0];
        int y = zeros.get(idx)[1];
        for(int i = 1; i < 10; i++){
            sudo[x][y] = i;
            if(check19(x) && check91(y) && check33(x,y)){
                dfs(idx+1);
            }
            sudo[x][y] = 0;
        }
    }

    public static boolean check19(int idx){
        boolean[] visited = new boolean[10];
        for(int i = 0; i < 9; i++){
            if(sudo[idx][i] == 0){
                continue;
            }
            if(visited[sudo[idx][i]]){
                return false;
            }else{
                visited[sudo[idx][i]] = true;
            }
        }
        return true;
    }

    public static boolean check91(int idx){
        boolean[] visited = new boolean[10];
        for(int i = 0; i < 9; i++){
            if(sudo[i][idx] == 0){
                continue;
            }
            if(visited[sudo[i][idx]]){
                return false;
            }else{
                visited[sudo[i][idx]] = true;
            }
        }
        return true;
    }

    public static boolean check33(int x, int y){
        boolean[] visited = new boolean[10];
        int ix = 3 * (x/3);
        int iy = 3 * (y/3);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(sudo[ix+i][iy+j] == 0){
                    continue;
                }
                if(visited[sudo[ix + i][iy + j]]){
                    return false;
                }else{
                    visited[sudo[ix + i][iy + j]] = true;
                }
            }
        }
        return true;
    }
}
