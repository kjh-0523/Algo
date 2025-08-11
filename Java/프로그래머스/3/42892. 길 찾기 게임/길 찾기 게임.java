import java.util.*;

class Solution {
    
    public static class Node{
        int x, y, idx;
        Node left, right;
        
        public Node(int x, int y, int idx){
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        ArrayList<Node> nodes = new ArrayList<>();
        for(int i = 0; i < nodeinfo.length; i++){
            nodes.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i+1));
        }
        
        nodes.sort((o1,o2) -> {
            if(o1.y == o2.y) return o1.x - o2.x;
            return o2.y - o1.y;
        });
            
        Node root = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            insert(root, nodes.get(i));
        }
        
        List<Integer> pre = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        preOrder(root, pre);
        postOrder(root, post);
        
        int[] preList = pre.stream().mapToInt(Integer::intValue).toArray();
        int[] postList = post.stream().mapToInt(Integer::intValue).toArray();
        
        int[][] answer = new int[][] {preList, postList};
        return answer;
    }
    
    public static void insert(Node parent, Node child){
        if(child.x < parent.x){
            if(parent.left == null) parent.left = child;
            else insert(parent.left, child);
        }else{
            if(parent.right == null) parent.right = child;
            else insert(parent.right, child);
        }
    }
    
    public static void preOrder(Node node, List<Integer> pre){
        if(node == null) return;
        pre.add(node.idx);
        preOrder(node.left, pre);
        preOrder(node.right, pre);
    }
    
    public static void postOrder(Node node, List<Integer> post){
        if(node == null) return;
        postOrder(node.left, post);
        postOrder(node.right, post);
        post.add(node.idx);
    }
}