import java.util.Arrays;
import java.util.Collections;

class Solution {
    public long solution(int n, int[] works) {
        int size = works.length;
        double average = Arrays.stream(works).average().orElse(0);
        Arrays.sort(works);
        for(int i = 0; i < size / 2; i++){
            int temp = works[i];
            works[i] = works[size - i - 1];
            works[size - i - 1] = temp;
        }
        int idx = 0;
        int max = works[0];
        while(n != 0){
            if(works[idx] == max){
                n--;
                works[idx]--;
            }
            idx++;
            if(idx > size - 1){
                idx = idx % size;
                max = works[0];
                if(max == 0) break;
            }

        }
        long answer = 0;
        for(int i = 0; i < size; i++){
            answer += Math.pow(works[i],2);
        }

        return answer;
    }
}