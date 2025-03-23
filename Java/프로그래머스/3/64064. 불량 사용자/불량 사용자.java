import java.util.*;

class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        int N = user_id.length;
        int M = banned_id.length;
        ArrayList<String>[] bannedList = new ArrayList[M];

        for(int i = 0; i < M; i++){
            bannedList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            int len = banned_id[i].length();
            for(String id : user_id){
                if(id.length() != len) continue;
                boolean flag = false;
                for(int j = 0; j < len; j++){
                    if(banned_id[i].charAt(j) == '*') continue;
                    if(banned_id[i].charAt(j) != id.charAt(j)){
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    bannedList[i].add(id);
                }
            }
        }

        int[] root = new int[M];
        for(int i = 0; i < M; i++){
            root[i] = i;
        }
        Set<Set<String>> uniqueCombinations = new HashSet<>();
        findCombinations(bannedList, 0, new HashSet<>(), uniqueCombinations);

        return uniqueCombinations.size();
    }
    
    private void findCombinations(ArrayList<String>[] bannedList, int index, Set<String> current, Set<Set<String>> uniqueCombinations) {
        if (index == bannedList.length) {
            uniqueCombinations.add(new HashSet<>(current));
            return;
        }

        for (String id : bannedList[index]) {
            if (!current.contains(id)) {
                current.add(id);
                findCombinations(bannedList, index + 1, current, uniqueCombinations);
                current.remove(id);
            }
        }
    }
}