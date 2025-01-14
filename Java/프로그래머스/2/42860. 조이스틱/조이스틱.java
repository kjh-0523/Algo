import java.util.*;

class Solution {
    public int solution(String name) {
        
        int N = name.length();
        int[] nameInt = new int[N];
        
        int answer = 0;
        int idx1 = 0;
        for(int i = 0; i < N; i++){
            nameInt[i] = (int)(name.charAt(i) - 'A');
            if(nameInt[i] > 13){
                answer += 26 - nameInt[i];
            }else{
                answer += nameInt[i];
            }
            
            if(nameInt[i] != 0){
                idx1 = i;
            }
        }
        
        int idx2 = 0;
        for(int i = N-1; i > 0; i--){
            if(nameInt[i] != 0){
                idx2 = N - i;
            }
        }
        
        int idx3 = 0;
        int cnt1 = 0;
        int cnt2 = 0;
        for(int i = 0; i <= N/2; i++){
            if(nameInt[i] != 0) cnt1 = i + 1;
        }
        for(int i = N-1; i > N/2; i--){
            if(nameInt[i] != 0) cnt2 = N - i;
        }
        
        if(cnt1 != 0 && cnt2 != 0){
        if(cnt1 * 2 - 2 + cnt2 < cnt2 * 2 - 1 + cnt1){
            idx3 = cnt1 * 2 - 2 + cnt2;
        }else{
            idx3 = cnt2 * 2 - 1 + cnt1;
        }
        System.out.println(cnt1 + " " + cnt2);
        answer += Math.min(Math.min(idx1,idx2), idx3);
        }else{
            answer += Math.min(idx1,idx2);
        }
        
        
        return answer;
    }
}