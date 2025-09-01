class Solution {
    public static int[] dx = {0,-1,0,1,0};
    public static int[] dy = {0,0,1,0,-1};
    public int solution(int[][] clockHands) {
        int n = clockHands.length;
        int[] topSpin = new int[n];
        int answer = n * n * 4;
        
        top:
        while(topSpin[n-1] != 4){
            boolean flag = true;
            int sum = 0;
            int[][] clock = new int[n][n];
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    clock[x][y] = clockHands[x][y];
                }
            }
            
            in:
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    int spin = 0;
                    if(i == 0){
                        spin = topSpin[j];
                    }else{
                        spin = (4 - clock[i-1][j]) % 4;
                    }
                    if(spin == 0) continue;
                    
                    for(int k = 0; k < 5; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(nx < 0 || nx > n-1 || ny < 0 || ny > n-1) continue;
                        clock[nx][ny] = (clock[nx][ny] + spin) % 4;
                    }
                    sum += spin;
                    if(sum >= answer) {
                        flag = false;
                        break in;
                    }
                }
            }
            
            for(int i = 0; i < n; i++){
                topSpin[i]++;
                if(i != n-1 && topSpin[i] == 4){
                    topSpin[i] %= 4;
                    continue;
                }
                break;
            }
            
            if(flag){
                for(int i = 0; i < n; i++){
                    if(clock[n-1][i] != 0) continue top;
                }
                answer = Math.min(answer, sum);
            }
        }
        
        
        return answer;
    }
}