import java.util.*;

class Solution {
    public static int[] answer;
    public int[] solution(int n, int s) {
        if(n > s) return new int[] {-1};
        answer = new int[n];
        int num = s/n;
        int remain = s % n;
        Arrays.fill(answer, num);
        for(int i = n-1; i > n-1 - remain; i--){
            answer[i]++;
        }
        return answer;
    }
    
}