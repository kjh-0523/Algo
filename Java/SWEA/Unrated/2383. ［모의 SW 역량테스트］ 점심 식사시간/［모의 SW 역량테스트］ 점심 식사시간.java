import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
 
public class Solution {
    static int N;
    static List<int[]> stairs;
    static List<int[]> man;
    static int ans;
    static List<List<Integer>> stairInfo;
 
    static int calcTime(List<Integer> arrivalTimes, int k) {
        Collections.sort(arrivalTimes);
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int arrivalTime : arrivalTimes) {
            if (queue.size() >= 3) {
                int front = queue.poll();
                int max = Math.max(arrivalTime, front + k);
                queue.add(max);
            }
            else {
                queue.add(arrivalTime);
            }
        }
        if (!queue.isEmpty()) return queue.peekLast() + k + 1;
        else return 0;
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; ++tc) {
            stairs = new ArrayList<>();
            man = new ArrayList<>();
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; ++i) {
            	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; ++j) {
                    int b = Integer.parseInt(st.nextToken());
                    if (b == 1) {
                        man.add(new int[]{i, j});
                    }
                    else if (b >=  2) {
                        stairs.add(new int[]{b, i, j});
                    }
                }
            }
 
            ans = Integer.MAX_VALUE;
            for (int i = 0; i < (1 << man.size()); ++i) {
                stairInfo = new ArrayList<>();
                stairInfo.add(new ArrayList<>());
                stairInfo.add(new ArrayList<>());
 
                for (int j = 0; j < man.size(); j++) {
                    int stairNum  = 0;
                    if((i & (1 << j)) == 0) stairNum =  0;
                    else stairNum = 1;
                    stairInfo.get(stairNum).add(Math.abs(man.get(j)[0] - stairs.get(stairNum)[1]) + Math.abs(man.get(j)[1] - stairs.get(stairNum)[2]));
                }
                int maxTime = 0;
                for (int j = 0; j < 2; j++) {
                    maxTime = Math.max(maxTime, calcTime(stairInfo.get(j), stairs.get(j)[0]));
                }
                ans = Math.min(ans, maxTime);
            }
            System.out.println("#"+tc+" "+ans);
        }
    }
}