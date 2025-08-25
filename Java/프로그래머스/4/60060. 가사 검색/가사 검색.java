import java.util.*;

class Solution {
    public static Map<Integer, Dictionary> forwardDic;
    public static Map<Integer, Dictionary> backwardDic;
    
    public static class Node{
        HashMap<Character, Node> child;
        int count;
        
        public Node() {
            this.child = new HashMap<>();
            this.count = 0;
        }
    }
    
    public static class Dictionary{
        Node root;
    
        public Dictionary(){
            this.root = new Node();
        }
        
        public void insert(String str){
            Node node = this.root;
            node.count++;
            for(int i = 0; i < str.length(); i++){
                node.child.putIfAbsent(str.charAt(i), new Node());
                node = node.child.get(str.charAt(i));
                node.count++;
            }
        }
        
        int search(String query) {
            Node node = root;
            for (char c : query.toCharArray()) {
                if (c == '?') {
                    return node.count;
                }
                node = node.child.get(c);
                if (node == null) return 0;
            }
            return node.count;
        }
        
    }
    
    public int[] solution(String[] words, String[] queries) {
        forwardDic = new HashMap<>();
        backwardDic = new HashMap<>();
        
        for(String w : words){
            int len = w.length();
            forwardDic.putIfAbsent(len, new Dictionary());
            forwardDic.get(len).insert(w);
            
            backwardDic.putIfAbsent(len, new Dictionary());
            backwardDic.get(len).insert(new StringBuilder(w).reverse().toString());
        }
        
        int[] answer = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            int cnt = 0;
            int len = queries[i].length();
            if(queries[i].charAt(0) == '?'){
                Dictionary dic = backwardDic.get(len);
                if (dic != null) {
                    String revQ = new StringBuilder(queries[i]).reverse().toString();
                    cnt = dic.search(revQ);
                }
            }else{
                Dictionary dic = forwardDic.get(len);
                if (dic != null) {
                    cnt = dic.search(queries[i]);
                }
            }
            
            answer[i] = cnt;
        }
        
        return answer;
    }
    
}