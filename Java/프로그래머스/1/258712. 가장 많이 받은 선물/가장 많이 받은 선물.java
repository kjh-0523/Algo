import java.util.*;
class Solution {
    public static int[] nextGift;
    public static int[][] gift;
    public static int[] score;
    public int solution(String[] friends, String[] gifts) {
        int N = friends.length;
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i++){
            map.put(friends[i],i);
        }
        nextGift = new int[N];
        gift = new int[N][N];
        score = new int[N];
        
        for(int i = 0; i < gifts.length; i++){
            StringTokenizer st = new StringTokenizer(gifts[i], " ");
            int give = map.get(st.nextToken());
            int take = map.get(st.nextToken());
            gift[give][take]++;
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                score[i] += gift[i][j] - gift[j][i];
            }
        }
        
        for(int i = 0; i < N; i++){
            for(int j = i+1; j < N; j++){
                if(gift[i][j] > gift[j][i]){
                    nextGift[i]++;
                }else if(gift[i][j] < gift[j][i]){
                    nextGift[j]++;
                }else{
                    if(score[i] > score[j]) nextGift[i]++;
                    else if(score[i] < score[j]) nextGift[j]++;
                }
            }
        }
        int answer = 0;
        // for(int i = 0; i < N; i++){
        //     System.out.println(Arrays.toString(gift[i]));
        // }
        // System.out.println(Arrays.toString(score));
        // System.out.println(Arrays.toString(nextGift));
        for(int i = 0; i < N; i++){
            answer = Math.max(answer, nextGift[i]);
        }
        return answer;
    }
}