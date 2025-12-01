import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        int left = 0;
        int right = 100000000;
        while(left < right){
            int mid = (left + right) / 2;
            
            long cnt = cntWork(mid, cores); 
            
            if(cnt >= n){
                right = mid;
            }else{
                left = mid+1;   
            }
        }
        
        long job = cntWork(right, cores); 
        int time = right;
        while(true){
            for(int i = cores.length - 1; i >= 0; i--){
                if(time % cores[i] == 0){
                    job--;
                    if(job == n - 1){
                        return i + 1;
                    }
                }
            } 
            time--;
        }
    }
    
    public static long cntWork(int time, int[] cores){
        long cnt = cores.length;
        for(int core : cores){
            cnt += time / core;
        }
        return cnt;
    }
}