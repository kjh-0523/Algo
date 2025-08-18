import java.util.*;

class Solution {
    public int solution(int[] a) {
        
        int leftMin = a[0];
        Set<Integer> left = new HashSet<>();
        for(int i = 1; i < a.length; i++){
            if(leftMin > a[i]){
                leftMin = a[i];
                continue;
            }
            left.add(a[i]);
        }
        
        int rightMin = a[a.length-1];
        Set<Integer> right = new HashSet<>();
        for(int i = a.length-2; i > -1; i--){
            if(rightMin > a[i]){
                rightMin = a[i];
                continue;
            }
            right.add(a[i]);
        }
        
        int cnt = 0;
        for(int num : left){
            if(right.contains(num)) cnt++;
        }
        
        
        int answer = a.length - cnt;
        return answer;
    }
}