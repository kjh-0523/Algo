import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int vertex, weight;
		Node next;

		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	static int[] minDistance;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken()) - 1;
//		int end = Integer.parseInt(st.nextToken());

		Node[] adjList = new Node[V];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		Dijkstra(adjList, start);
		for (int i = 0; i < V; i++) {
			if (minDistance[i] == Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(minDistance[i]);
		}

	}

	static int Dijkstra(Node[] adjList, int start) {
		final int V = adjList.length;
		minDistance = new int[V];
		boolean[] visited = new boolean[V];
		final int INF = Integer.MAX_VALUE;

		Arrays.fill(minDistance, INF);
		minDistance[start] = 0;

		for (int i = 0; i < V; i++) {
			// step 1 : 미방문 정점 중 시작점에서 가장 가까운 정점 선택
			int min = INF;
			int stopOver = -1;
			for (int j = 0; j < V; j++) {
				if (!visited[j] && minDistance[j] < min) {
					min = minDistance[j];
					stopOver = j;
				}
			}
//			if(stopOver == end) break;
			if (stopOver == -1)
				break;
			visited[stopOver] = true;

			// step2 : 선택된 정점을 경유해서 미방문 인접한 정점으로의 최소비용을 갱싱할 수 있는지 체크
			for (Node next = adjList[stopOver]; next != null; next = next.next) {
				if (!visited[next.vertex] && minDistance[next.vertex] > min + next.weight) {
					minDistance[next.vertex] = min + next.weight;
				}
			}
		}
		return 1;
	}
}
