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
        Queue<Program> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(new Program(input, ""));
        visited.add(input);

        while (!q.isEmpty()) {
            Program cur = q.poll();
            if (cur.input == ans) {
                sb.append(cur.commend).append("\n");
                return;
            }

            int temp = (cur.input * 2) % 10000;
            if (!visited.contains(temp)) {
                q.offer(new Program(temp, cur.commend + "D"));
                visited.add(temp);
            }

            temp = cur.input - 1;
            if (temp == -1) temp = 9999;
            if (!visited.contains(temp)) {
                q.offer(new Program(temp, cur.commend + "S"));
                visited.add(temp);
            }

            temp = (cur.input % 1000) * 10 + (cur.input / 1000);
            if (!visited.contains(temp)) {
                q.offer(new Program(temp, cur.commend + "L"));
                visited.add(temp);
            }

            temp = (cur.input % 10) * 1000 + (cur.input / 10);
            if (!visited.contains(temp)) {
                q.offer(new Program(temp, cur.commend + "R"));
                visited.add(temp);
            }
        }
    }
}