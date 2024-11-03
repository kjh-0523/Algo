import java.io.*;
import java.util.*;

public class Main {
	static int input;
	static int ans;
	static StringBuilder sb;
	static class Program{
		int input;
		String commend;
		
		public Program(int input, String commend) {
			this.input = input;
			this.commend = commend;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			input = Integer.parseInt(st.nextToken());
			ans = Integer.parseInt(st.nextToken());
			
			bfs();
		}
        System.out.println(sb);
    }
    
    static void bfs() {
        Deque<Program> deque = new ArrayDeque<>();
        Map<Integer, String> visited = new HashMap<>();
        deque.offer(new Program(input, ""));
        visited.put(input, "");

        while (!deque.isEmpty()) {
            Program cur = deque.poll();
            if (cur.input == ans) {
                sb.append(cur.commend).append("\n");
                return;
            }

            int temp = (cur.input * 2) % 10000;
            if (!visited.containsKey(temp)) {
                visited.put(temp, cur.commend + "D");
                deque.offer(new Program(temp, cur.commend + "D"));
            }

            temp = cur.input - 1 == -1 ? 9999 : cur.input - 1;
            if (!visited.containsKey(temp)) {
                visited.put(temp, cur.commend + "S");
                deque.offer(new Program(temp, cur.commend + "S"));
            }

            temp = (cur.input % 1000) * 10 + (cur.input / 1000);
            if (!visited.containsKey(temp)) {
                visited.put(temp, cur.commend + "L");
                deque.offer(new Program(temp, cur.commend + "L"));
            }

            temp = (cur.input % 10) * 1000 + (cur.input / 10);
            if (!visited.containsKey(temp)) {
                visited.put(temp, cur.commend + "R");
                deque.offer(new Program(temp, cur.commend + "R"));
            }
        }
    }

}