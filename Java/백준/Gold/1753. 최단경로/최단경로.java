import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int vertex, weight;
		Node next;
		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	static int[] distance;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine()," ");
		int start = Integer.parseInt(st.nextToken())-1;
//		int end = Integer.parseInt(st.nextToken());
		
		Node[] adjList = new Node[V];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		Dijkstra(adjList, start);
		for (int i = 0; i < V; i++) {
			if(distance[i] == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(distance[i]);
		}
		
	}
	
	static int Dijkstra(Node[] adjList, int start) {
		int V = adjList.length;
		int INF = Integer.MAX_VALUE;
		PriorityQueue<int[]> q = new PriorityQueue<>((o1,o2)-> o1[1] - o2[1]);
		distance = new int[V];
		Arrays.fill(distance, INF);
		
		distance[start] = 0;
		q.offer(new int[] {start,distance[start]});
		
		while(!q.isEmpty()) {
			int[] stopOver = q.poll();
			int cur = stopOver[0];
			int dis = stopOver[1];
			
//			if(cur == end) break;
			if(distance[cur] < dis) continue;
			
			for (Node next = adjList[cur]; next != null; next = next.next) {
				if(distance[next.vertex] > dis + next.weight) {
					distance[next.vertex] = dis + next.weight;
					q.offer(new int[] {next.vertex, distance[next.vertex]});
				}
			}
		}
		return 1;
	}
}
