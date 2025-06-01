class Solution {
    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;
        
        int[][] life = new int[N+1][M+1];
        
        for(int[] s : skill) {
            int type = s[0];
            int x1 = s[1];
            int y1 = s[2];
            int x2 = s[3];
            int y2 = s[4];
            int degree = s[5];
            if(type == 1) degree *= -1;
            
            life[x1][y1] += degree;
            life[x1][y2 + 1] -= degree;
            life[x2+1][y1] -= degree;
            life[x2+1][y2+1] += degree;
        }
        
        for(int i = 0; i < N+1; i++){
            for(int j = 1; j < M+1; j++){
                life[i][j] += life[i][j-1];
            }
        }
        
        for(int j = 0; j < M+1; j++){
            for(int i = 1; i < N+1; i++){
                life[i][j] += life[i-1][j];
            }
        }
        
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(board[i][j] + life[i][j] <= 0) continue;
                cnt++;
            }
        }
        return cnt;
    }
}