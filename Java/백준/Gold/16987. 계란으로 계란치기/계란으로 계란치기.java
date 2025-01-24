import java.util.*;
import java.io.*;

public class Main {

    public static int N;
    public static int cnt;
    public static ArrayList<Egg> eggs;

    public static class Egg{
        int durability, weight;

        public Egg(int durability, int weight){
            this.durability = durability;
            this.weight = weight;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        eggs = new ArrayList<>();
        StringTokenizer st = null;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            eggs.add(new Egg(d,w));
        }
        cnt = 0;

        dfs(0,0);

        System.out.println(cnt);
    }

    public static void dfs(int idx,int crashCnt){
        if(idx == N || crashCnt == N-1){
            cnt = Math.max(cnt,crashCnt);
            return;
        }

        if(eggs.get(idx).durability < 1){
            dfs(idx+1,crashCnt);
            return;
        }
        int tempCnt = crashCnt;
        for(int i = 0; i < N; i++){
            if(i == idx) continue;
            if(eggs.get(i).durability < 1) continue;

            eggs.get(i).durability -= eggs.get(idx).weight;
            eggs.get(idx).durability -= eggs.get(i).weight;

            if(eggs.get(i).durability < 1) crashCnt++;
            if(eggs.get(idx).durability < 1) crashCnt++;

            dfs(idx+1, crashCnt);

            crashCnt = tempCnt;

            eggs.get(i).durability += eggs.get(idx).weight;
            eggs.get(idx).durability += eggs.get(i).weight;
        }

    }
}
