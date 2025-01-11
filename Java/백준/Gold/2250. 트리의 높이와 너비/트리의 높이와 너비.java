import java.util.*;
import java.io.*;

class Main
{
    public static int N, no;
    public static Node[] nodes;
    public static Map<Integer, Integer> min;
    public static Map<Integer, Integer> max;

    public static class Node{
        int left, right;

        public Node(int left, int right){
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nodes = new Node[N+1];
        min = new HashMap<>();
        max = new HashMap<>();

        StringTokenizer st = null;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            nodes[n] = new Node(l,r);
        }
        Set<Integer> nodeSet = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            nodeSet.add(i);  // 모든 노드를 추가
        }

        for (int i = 1; i <= N; i++) {
            Node node = nodes[i];
            if (node.left != -1) nodeSet.remove(node.left);  // 왼쪽 자식 제거
            if (node.right != -1) nodeSet.remove(node.right);  // 오른쪽 자식 제거
        }

        int root = nodeSet.iterator().next();

        no = 1;
        dfs(root,1);

        int MAX = min.size();
        int ans = 0;
        int level = 0;
        for(int i = 1; i < MAX+1; i++){
            if(ans < max.get(i) - min.get(i) + 1) {
                ans = max.get(i) - min.get(i) + 1;
                level = i;
            }
        }

        System.out.println(level +" "+ans);
    }

    public static void dfs(int idx, int level){

        if(nodes[idx].left != -1){
            dfs(nodes[idx].left, level+1);
        }

        min.put(level, Math.min(min.getOrDefault(level, no), no));
        max.put(level, Math.max(max.getOrDefault(level, no), no));
//        System.out.println(no+" "+idx);
        no++;

        if(nodes[idx].right != -1){
            dfs(nodes[idx].right,  level+1);
        }

    }
}