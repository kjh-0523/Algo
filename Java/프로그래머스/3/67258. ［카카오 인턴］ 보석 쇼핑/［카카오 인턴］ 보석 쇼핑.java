import java.util.*;

class Solution {
    Set<String> gemSet;
    Map<String, Integer> gemMap;
    
    
    public int[] solution(String[] gems) {
        int n = gems.length;
        gemSet = new HashSet<>();
        for(String g : gems){
            gemSet.add(g);
        }
        int size = gemSet.size();
        
        gemMap = new HashMap<>();
        int start = 0;
        int end = 0;
        int bestStart = 0;
        int bestEnd = n-1;
        while (start < n && end < n) {
            if(gemMap.isEmpty()){
                gemMap.put(gems[start], 1);
            }
            
            if (gemMap.size() < size) {
                end++;
                if (end == n) break;
                gemMap.put(gems[end], gemMap.getOrDefault(gems[end], 0) + 1);
            } else {
                if ((end - start) < (bestEnd - bestStart)) {
                    bestStart = start;
                    bestEnd = end;
                }
                if (gemMap.get(gems[start]) == 1) {
                    gemMap.remove(gems[start]);
                } else {
                    gemMap.put(gems[start], gemMap.get(gems[start]) - 1);
                }
                start++;
                
                if (start > end && start < n) {
                    end = start;
                    gemMap.put(gems[start], 1);
                }
            }
        }

        return new int[]{bestStart + 1, bestEnd + 1};
    }
}