import java.io.*;
import java.util.*;

public class Main {
    static int N, ans;
    static PriorityQueue<Integer> pq;
    static PriorityQueue<int[]> nums;
    static boolean[] visited = new boolean[10001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>((o1,o2)->{ 
        	return o1 - o2;
        });
        nums = new PriorityQueue<>((o1,o2) ->{
        	return o1[1] - o2[1];
        });
        ans = 0;
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine()," ");
        	int p = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());
        	nums.add(new int[] {p,d});
		}
        
        while(!nums.isEmpty()) {
        	int[] cur = nums.poll();
        	if(cur[1] <= pq.size()) {
        		if(pq.peek() < cur[0]) {
        			pq.poll();
        			pq.offer(cur[0]);
        		}
        	}else {
        		pq.offer(cur[0]);
        	}
        }
        while(!pq.isEmpty()) {
        	ans += pq.poll();
        }
        System.out.println(ans);
    }
}