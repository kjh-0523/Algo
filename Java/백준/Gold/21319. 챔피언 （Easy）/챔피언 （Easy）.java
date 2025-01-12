import java.util.*;
import java.io.*;

class Main
{
    public static int N;
    public static int sIdx = Integer.MAX_VALUE;
    public static int[] input;
    public static ArrayList<Player> players = new ArrayList<>();

    public static class Player{
        int init, power;

        public Player(int init, int power){
            this.init = init;
            this.power = power;
        }
    }

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        input = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < N; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        for(int i= 1; i <=N; i++){
            if(i>1 && input[i-2] == input[i-1]) continue;
            players.add(new Player(i, input[i-1]));
        }

        StringBuilder sb = new StringBuilder();

        if(N == 1){
            sb.append("1");
            System.out.println(sb.toString());
        }else if(!canWin(players.get(players.size()-1)) || players.size() == 1){
            sb.append("-1");
            System.out.println(sb.toString());
        } else{
            int left = 1;
            int right = players.size()-1;

            while(left < right){
                int mid = (left+right)/2;
                if(canWin(players.get(mid))) right = mid;
                else left = mid+1;
            }
            for(Player p : players){
                if(p.init < sIdx) continue;
                sb.append(p.init).append(" ");
            }
            System.out.println(sb.toString());
        }

    }

    public static boolean canWin(Player player){
        for(Player p : players){
            if(p.init <= player.init) continue;
            if(p.power >= player.power + p.init-2) return false;
        }
        sIdx = Math.min(sIdx, player.init);
        return true;
    }
}