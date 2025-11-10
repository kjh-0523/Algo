import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        Deque<Integer> listA = new LinkedList<>();
        Deque<Integer> listB = new LinkedList<>();
        for(int i = A.length-1; i > -1; i--){
            listA.add(A[i]);
            listB.add(B[i]);
        }
        
        int answer = 0;
        while(!listA.isEmpty()){
            int curA = listA.pollFirst();
            if(curA >= listB.peekFirst()){
                listB.pollLast();
                continue;
            }
            answer++;
            listB.pollFirst();
        }
        
        return answer;
    }
}