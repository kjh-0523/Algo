import java.util.*;
import java.io.*;

public class Main{
	
	static int N;
	static PriorityQueue<Node> pq;
	
	public static class Node implements Comparable<Node>{
		int deadLine, weight;
		
		public Node(int deadLine, int weight) {
			this.deadLine = deadLine;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			if(o.deadLine == this.deadLine) return o.weight - this.weight;
			return this.deadLine - o.deadLine;
		}


	}
	
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        
        StringTokenizer st = null;
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine()," ");
        	int d = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	pq.offer(new Node(d,w));
        }
        
        PriorityQueue<Integer> problem = new PriorityQueue<>();
        while(!pq.isEmpty()) {
        	Node cur = pq.poll();
        	if(problem.size() < cur.deadLine) {
        		problem.offer(cur.weight);
        	}else if(problem.size() >= cur.deadLine) {
        		if(problem.peek() < cur.weight) {
        			problem.poll();
        			problem.add(cur.weight);
        		}
        	}
        }
        
        int ans = 0;
        for(int p : problem) {
        	ans += p;
        }
        System.out.println(ans);
    }
}