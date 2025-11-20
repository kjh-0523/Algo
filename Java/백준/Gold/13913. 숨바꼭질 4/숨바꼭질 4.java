import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int[] time;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (n == k) {
            System.out.println(0);
            System.out.println(n);
        } else {
            bfs(n, k);
            System.out.println(time[k]-1);

            Stack<Integer> stack = new Stack<>();
            stack.push(k);
            int index = k;
            while (index!=n){
                stack.push(parent[index]);
                index = parent[index];
            }
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()){
                sb.append(stack.pop()).append(" ");
            }
            System.out.println(sb);
        }
    }

    public static void bfs(int n, int k) {
        Queue<Integer> q = new LinkedList<>();
        parent = new int[100001];
        time = new int[100001];
        q.offer(n);
        time[n] = 1;

        while (!q.isEmpty()) {
            Integer now = q.poll();

            if (now == k) return;

            if (now * 2 <= 100000 && time[now * 2] == 0) {
                time[now * 2] = time[now] + 1;
                parent[now * 2] = now;
                q.add(now * 2);
            }
            if (now + 1 <= 100000 && time[now + 1] == 0) {
                time[now + 1] = time[now] + 1;
                parent[now + 1] = now;
                q.add(now + 1);
            }
            if (now - 1 >= 0 && time[now - 1] == 0) {
                time[now - 1] = time[now] + 1;
                parent[now - 1] = now;
                q.add(now - 1);
            }
        }
    }
}