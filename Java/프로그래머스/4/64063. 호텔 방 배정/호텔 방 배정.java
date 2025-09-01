import java.util.*;

class Solution {
    public static Map<Long, Long> empty;
    public long[] solution(long k, long[] room_number) {
        int n = room_number.length;
        empty = new HashMap<>();
        long[] answer = new long[n];
        
        for(int i = 0; i < n; i++){
            answer[i] = find(room_number[i]);
        }
        
        return answer;
    }
    
    public static long find(Long number){
        if(!empty.containsKey(number)){
            empty.put(number, number+1);
            return number;
        }
        
        long emptyNumber = find(empty.get(number));
        empty.put(number, emptyNumber);
        return emptyNumber;
    }
}