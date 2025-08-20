import java.io.*;
import java.util.*;

public class Main {
    public static int N, K;
    public static int[][] map;
    public static Deque<Piece>[][] pieces;
    public static ArrayList<pos> piecePos;
    public static int[] dx = {0,0,-1,1};
    public static int[] dy = {1,-1,0,0};

    public static class Piece{
        int idx, dir;
        public Piece(int idx, int dir){
            this.idx = idx;
            this.dir = dir;
        }
    }

    public static class pos{
        int x, y;
        public pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pieces = new Deque[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                pieces[i][j] = new LinkedList<>();;
            }
        }

        piecePos = new ArrayList<>();

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken())-1;

            pieces[x][y].offer(new Piece(i, dir));
            piecePos.add(new pos(x,y));
        }

        int cnt = 1;
        // for(pos p : piecePos){
        //     System.out.println(p.x + " " + p.y + " " + pieces[p.x][p.y].peekFirst().dir);
        // }

        top:
        while(cnt < 1001){
            for(int i = 0; i < K; i++){
                int x = piecePos.get(i).x;
                int y = piecePos.get(i).y;

                if(pieces[x][y].isEmpty()) {
                    System.out.println("something wrong");
                    continue;
                }
                if(pieces[x][y].peekFirst().idx != i) continue;

				int dir = pieces[x][y].peekFirst().dir;
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(nx < 0 || nx > N-1 || ny < 0 || ny > N-1 || map[nx][ny] == 2){
                    if(dir == 0 || dir == 1){
                        dir = dir == 0 ? 1 : 0;
                    }else if(dir == 2 || dir == 3){
                        dir = dir == 2 ? 3 : 2;
                    }
                    nx = x + dx[dir];
                    ny = y + dy[dir];
                    pieces[x][y].peekFirst().dir = dir;

                    if(nx < 0 || nx > N-1 || ny < 0 || ny > N-1 || map[nx][ny] == 2){
                        continue;
                    }
                }

                if(map[nx][ny] == 1){
                    while(!pieces[x][y].isEmpty()){
                        Piece cur = pieces[x][y].pollLast();
                        piecePos.set(cur.idx, new pos(nx, ny));
                        pieces[nx][ny].offer(cur);
                    }
                }else{
                    while(!pieces[x][y].isEmpty()){
                        Piece cur = pieces[x][y].pollFirst();
                        piecePos.set(cur.idx, new pos(nx, ny));
                        pieces[nx][ny].offer(cur);
                    }
                }

                if(pieces[nx][ny].size() > 3) break top;
            }
            // System.out.println(cnt + "----------------");
            // for(pos p : piecePos){
            //     System.out.println(p.x + " " + p.y + " " + pieces[p.x][p.y].peekFirst().dir);
            // }
            cnt++;
        }
        System.out.println(cnt > 1000 ? -1 : cnt);
    }
}
