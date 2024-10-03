import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static class Node{
		Map<String,Node> childNode = new HashMap<>();
		boolean lastNode;
	}
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Node rootNode = new Node();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int K = Integer.parseInt(st.nextToken());
			String[] strs = new String[K];
			for (int j = 0; j < K; j++) {
				strs[j] = st.nextToken();
			}
			Node node = rootNode;
			for (int j = 0; j < K; j++) {
				node = node.childNode.computeIfAbsent(strs[j], key -> new Node());
			}
			node.lastNode = true;
		}
		print(rootNode, 0);
	}
	
	public static void print(Node cur, int depth) {
		Node node = cur;
		if(!node.lastNode) {
			List<String> list = new ArrayList<String>(node.childNode.keySet());
			Collections.sort(list);
			for (String str : list) {
				for (int i = 0; i < depth; i++) {
					System.out.print("--");
				}
				System.out.println(str);
				print(node.childNode.get(str), depth+1);
			}
		}
	}
}
