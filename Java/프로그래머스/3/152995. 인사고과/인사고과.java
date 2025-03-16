import java.util.*;

class Solution {
    public static ArrayList<score> rank;
    public static class score{
        int att, eva, sum;
        
        public score(int att, int eva, int sum){
            this.att = att;
            this.eva = eva;
            this.sum = sum;
        }
    }
    public int solution(int[][] scores) {
        score wonho = new score(scores[0][0], scores[0][1], scores[0][0] + scores[0][1]);
        rank = new ArrayList<>();
        for(int[] s : scores){
            rank.add(new score(s[0],s[1],s[0]+s[1]));
        }
        rank.sort((o1,o2) -> {return o2.sum - o1.sum;});
        
        int size = rank.size();
        int answer = 0;
        for(int i = 0; i < size; i++){
            if(rank.get(i).sum == wonho.sum){
                answer = i+1;
                break;
            }
            if(rank.get(i).att > wonho.att && rank.get(i).eva > wonho.eva){
                answer = -1;
                break;
            }
        }
        if(answer != -1){
            for(int i = 0; i < answer - 2; i++){
                for(int j = i+1; j < answer - 1; j++){
                    if(rank.get(i).sum == rank.get(j).sum) continue;
                    if(rank.get(i).att > rank.get(j).att && rank.get(i).eva > rank.get(j).eva){
                        rank.remove(j);
                        j--;
                        answer--;
                    }
                }
            }   
        }
        return answer;
    }
}