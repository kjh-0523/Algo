import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int ML = Integer.parseInt(st.nextToken());
        int MK = Integer.parseInt(st.nextToken());

        int C = Integer.parseInt(br.readLine());

        int[] zombies = new int[L];
        for (int i = 0; i < L; i++) {
            zombies[i] = Integer.parseInt(br.readLine());
        }

        Queue<Integer> gunQueue = new LinkedList<>();
        int damageSum = MK;
        boolean survived = true;

        for (int time = 0; time < L; time++) {
            if (gunQueue.size() == ML) {
                damageSum -= gunQueue.poll();
            }

            if (zombies[time] <= damageSum) {
                gunQueue.offer(MK);
                damageSum += MK;
            } else {
                if (C > 0) {
                    C--;
                    gunQueue.offer(0);
                } else {
                    survived = false;
                    break;
                }
            }
        }

        System.out.println(survived ? "YES" : "NO");
    }
}
