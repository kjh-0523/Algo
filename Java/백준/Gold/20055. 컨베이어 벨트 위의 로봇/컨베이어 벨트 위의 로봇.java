import java.io.*;
import java.util.*;

public class Main {
    public static int N, K, cntDur;
    public static int[] belt;
    public static boolean[] isFull;
    public static Queue<Integer> robots;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        belt = new int[2 * N];
        isFull = new boolean[N];
        for(int i = 0; i < 2 * N; i++){
            int d = Integer.parseInt(st.nextToken());
            belt[i] = d;
        }

        robots = new LinkedList<>();
        cntDur = 0;
        int stage = 0;
        while(cntDur < K){
            moveBelt();

            moveRobot();

            if(belt[0] > 0 && !isFull[0]){
                robots.add(0);
                belt[0]--;
                isFull[0] = true;
                if(belt[0] == 0){
                    cntDur++;
                }
            }

            stage++;
        }
        System.out.println(stage);
    }

    public static void moveBelt(){
        int temp = belt[2*N-1];
        for(int i = 2*N-2; i > -1; i--){
            belt[i+1] = belt[i];
        }
        belt[0] = temp;

        if(robots.isEmpty()) return;
        Queue<Integer> temps = new LinkedList<>();
        if(robots.peek() == N-2){
            isFull[robots.poll()] = false;
        }

        while(!robots.isEmpty()){
            int cur = robots.poll();
            temps.add(cur+1);
            isFull[cur] = false;
            isFull[cur+1] = true;
        }
        robots = temps;
    }

    public static void moveRobot(){
        if(robots.isEmpty()) return;
        Queue<Integer> temps = new LinkedList<>();

        if(robots.peek() == N-2 && !isFull[N-1] && belt[N-1] > 0){
            belt[N-1]--;
            if(belt[N-1] == 0) cntDur++;
            isFull[N-2] = false;
            robots.poll();
        }

        while(!robots.isEmpty()){
            int cur = robots.poll();

            if(isFull[cur+1] || belt[cur+1] < 1) {
                temps.add(cur);
                continue;
            }

            belt[cur+1]--;
            if(belt[cur+1] == 0) cntDur++;
            isFull[cur] = false;
            isFull[cur+1] = true;
            temps.add(cur+1);
        }

        robots = temps;
    }
}
