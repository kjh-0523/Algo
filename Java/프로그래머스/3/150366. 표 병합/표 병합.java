import java.util.*;

class Solution {
    static final int SIZE = 51;
    static int[] parent = new int[SIZE * SIZE];
    static String[] value = new String[SIZE * SIZE];
    
    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();
        
        for (int i = 0; i < SIZE * SIZE; i++) {
            parent[i] = i;
        }
        
        for (String cmd : commands) {
            String[] parts = cmd.split(" ");
            String action = parts[0];
            
            if (action.equals("UPDATE")) {
                if (parts.length == 4) {
                    int r = Integer.parseInt(parts[1]);
                    int c = Integer.parseInt(parts[2]);
                    String v = parts[3];
                    int idx = toIndex(r, c);
                    int root = find(idx);
                    value[root] = v;
                } else if (parts.length == 3) {
                    String v1 = parts[1];
                    String v2 = parts[2];
                    for (int i = 0; i < SIZE * SIZE; i++) {
                        if (value[find(i)] != null && value[find(i)].equals(v1)) {
                            value[find(i)] = v2;
                        }
                    }
                }
            } else if (action.equals("MERGE")) {
                int r1 = Integer.parseInt(parts[1]);
                int c1 = Integer.parseInt(parts[2]);
                int r2 = Integer.parseInt(parts[3]);
                int c2 = Integer.parseInt(parts[4]);
                
                int idx1 = toIndex(r1, c1);
                int idx2 = toIndex(r2, c2);
                
                union(idx1, idx2);
            } else if (action.equals("UNMERGE")) {
                int r = Integer.parseInt(parts[1]);
                int c = Integer.parseInt(parts[2]);
                int idx = toIndex(r, c);
                int root = find(idx);
                String original = value[root];
                
                List<Integer> group = new ArrayList<>();
                for (int i = 0; i < SIZE * SIZE; i++) {
                    if (find(i) == root) {
                        group.add(i);
                    }
                }
                
                for (int i : group) {
                    parent[i] = i;
                    value[i] = null;
                }
                
                value[idx] = original;
            } else if (action.equals("PRINT")) {
                int r = Integer.parseInt(parts[1]);
                int c = Integer.parseInt(parts[2]);
                int idx = toIndex(r, c);
                String v = value[find(idx)];
                if (v == null) {
                    answer.add("EMPTY");
                } else {
                    answer.add(v);
                }
            }
        }
        
        return answer.toArray(new String[0]);
    }
    
    private int toIndex(int r, int c) {
        return (r - 1) * 50 + (c - 1);
    }
    
    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX == rootY) return;
        
        parent[rootY] = rootX;
        
        if (value[rootX] == null && value[rootY] != null) {
            value[rootX] = value[rootY];
        }
    }
}
