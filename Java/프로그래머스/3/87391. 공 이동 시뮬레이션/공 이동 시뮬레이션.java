class Solution {
    
    public static int[] dx = {0,0,-1,1};
    public static int[] dy = {-1,1,0,0};
    public long solution(int n, int m, int x, int y, int[][] queries) {
        int N = queries.length;
        long sx = x;
        long sy = y;
        long ex = x;
        long ey = y;
        
        for(int i = N-1; i > -1; i--){
            int command = queries[i][0];
            int move = queries[i][1];
            
            if(command == 0){
                if(sy == 0){
                    ey = ey - (move * dy[command]) < m ? ey - (move * dy[command]) : m-1;
                }else{
                    sy = sy - (move * dy[command]);
                    ey = ey - (move * dy[command]) < m ? ey - (move * dy[command]) : m-1;
                    if(sy > m-1) return 0;
                }
            }else if(command == 1){
                if(ey == m-1){
                    sy = sy - (move * dy[command]) > -1 ? sy - (move * dy[command]) : 0;  
                }else{
                    ey = ey - (move * dy[command]);
                    sy = sy - (move * dy[command]) > -1 ? sy - (move * dy[command]) : 0;
                    if(ey < 0) return 0;
                }
            }else if(command == 2){
                if(sx == 0){
                    ex = ex - (move * dx[command]) < n ? ex - (move * dx[command]) : n-1;
                }else{
                    sx = sx - (move * dx[command]);
                    ex = ex - (move * dx[command]) < n ? ex - (move * dx[command]) : n-1;
                    if(sx > n-1) return 0;
                }
            }else{
                if(ex == n-1){
                    sx = sx - (move * dx[command]) > -1 ? sx - (move * dx[command]) : 0;  
                }else{
                    ex = ex - (move * dx[command]);
                    sx = sx - (move * dx[command]) > -1 ? sx - (move * dx[command]) : 0;
                    if(ex < 0) return 0;
                }
            }
            if (sx > ex || sy > ey) return 0;
            // System.out.println(i + " " + sx + " " + sy + " " + ex +  " " + ey);
        }
        
        return (ex - sx + 1) * (ey - sy + 1);
    }
}