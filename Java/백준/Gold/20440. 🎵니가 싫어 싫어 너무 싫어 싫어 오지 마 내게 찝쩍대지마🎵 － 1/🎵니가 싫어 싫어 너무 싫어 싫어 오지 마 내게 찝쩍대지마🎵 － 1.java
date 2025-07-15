 import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<int[]> events = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int in = Integer.parseInt(st.nextToken());
            int out = Integer.parseInt(st.nextToken());
            events.add(new int[]{in, 1});  // 모기 입장
            events.add(new int[]{out, -1}); // 모기 퇴장
        }

        // 시간 오름차순, 입장이 퇴장보다 먼저
        events.sort((a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        int cur = 0;
        int max = 0;
        int maxStart = 0;
        int maxEnd = 0;
        boolean tracking = false; // 최대 상태 지속 중 여부

        for (int i = 0; i < events.size(); i++) {
            int time = events.get(i)[0];
            int delta = events.get(i)[1];

            cur += delta;
            if(time == maxEnd){
                tracking = true;
            }
            if (cur > max) {
                max = cur;
                maxStart = time;
                tracking = true;
            }else if (cur == max-1 && tracking) {
                // max 유지 종료
                maxEnd = time;
                tracking = false;
            }
        }

        System.out.println(max);
        System.out.println(maxStart + " " + maxEnd);
    }
}
