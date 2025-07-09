import java.util.*;
import java.io.*;

public class Main {
    public static int R,C, cnt;
    public static char[][] map;
    public static Arduino jong;
    public static ArrayList<Arduino> crazy;
    public static int[] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    public static int[] dy = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static class Arduino {
        int x, y;
        public Arduino(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        crazy = new ArrayList<>();
        for(int i = 0 ; i < R; i++){
            String line = br.readLine();
            for(int j = 0; j < C; j++){
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'I'){
                    jong = new Arduino(i,j);
                }else if(map[i][j] == 'R'){
                    crazy.add(new Arduino(i,j));
                }
            }
        }

        String move = br.readLine();
        boolean flag = true;
        cnt = 0;
        for(int i = 0 ; i < move.length(); i++){
            int m = Integer.parseInt(move.substring(i,i+1));
            flag = moveJongsoo(m);
            cnt++;
            if(!flag){
                break;
            }
        }


        if(flag){
            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < R; i++){
                for(int j = 0; j < C; j++){
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());
        }else{
            System.out.println("kraj "+ cnt);
        }

    }

    public static boolean moveJongsoo(int m){
        // 종수 이동
        int jx = jong.x;
        int jy = jong.y;
        jong.x += dx[m];
        jong.y += dy[m];
        if(map[jong.x][jong.y] == 'R'){
            return false;
        }else{
            map[jx][jy] = '.';
            map[jong.x][jong.y] = 'I';
        }

        crazy.sort(Comparator.comparingInt(c -> calDistance(jong.x, jong.y, c.x, c.y)));
        
        // 미친 아두이노 이동
        Set<int[]> destroy = new HashSet<>();
        for(Arduino c : crazy){
            int cx = c.x;
            int cy = c.y;
            int dir = 0;
            int dis = 10000;
            for(int i = 1; i < 10; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                int newDis = calDistance(jong.x, jong.y, nx, ny);
                if(newDis < dis){
                    dir = i;
                    dis = newDis;
                }
            }
            c.x += dx[dir];
            c.y += dy[dir];
            if(map[c.x][c.y] == 'I'){
                return false;
            }else if(map[c.x][c.y] == 'R'){
                destroy.add(new int[]{c.x,c.y});
                map[cx][cy] = '.';
            }else{
                map[cx][cy] = '.';
                map[c.x][c.y] = 'R';
            }
        }

        // 같은 위치 아두이노 삭제
        if(!destroy.isEmpty()){
            Set<Arduino> remove = new HashSet<>();
            for(int[] d : destroy){
                map[d[0]][d[1]] = '.';
                for(Arduino c : crazy){
                    if(d[0] == c.x && d[1] == c.y){
                        remove.add(c);
                    }
                }
            }
            if(!remove.isEmpty()){
                crazy.removeAll(remove);
            }
        }
        return true;
    }

    public static int calDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
