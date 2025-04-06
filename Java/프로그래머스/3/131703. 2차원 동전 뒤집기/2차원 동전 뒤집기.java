class Solution {
    static int n, m;
    static int min = Integer.MAX_VALUE;
    
    public int solution(int[][] beginning, int[][] target) {
        n = beginning.length;
        m = beginning[0].length;

        int[][] diff = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                diff[i][j] = (beginning[i][j] != target[i][j]) ? 1 : 0;

        boolean[] flippedRows = new boolean[n];
        dfs(0, flippedRows, diff);
        
        return (min == Integer.MAX_VALUE) ? -1 : min;
    }

    private void dfs(int row, boolean[] flippedRows, int[][] diff) {
        if (row == n) {
            int[][] temp = copy(diff);
            int cnt = 0;

            for (int i = 0; i < n; i++) {
                if (flippedRows[i]) {
                    flipRow(temp, i);
                    cnt++;
                }
            }
            
            for (int j = 0; j < m; j++) {
                if (temp[0][j] == 1) {
                    flipCol(temp, j);
                    cnt++;
                }
            }

            if (isAllZero(temp)) {
                min = Math.min(min, cnt);
            }

            return;
        }

        flippedRows[row] = false;
        dfs(row + 1, flippedRows, diff);

        flippedRows[row] = true;
        dfs(row + 1, flippedRows, diff);
    }

    private int[][] copy(int[][] map) {
        int[][] newMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, newMap[i], 0, m);
        }
        return newMap;
    }

    private void flipRow(int[][] map, int row) {
        for (int j = 0; j < m; j++) {
            map[row][j] ^= 1;
        }
    }

    private void flipCol(int[][] map, int col) {
        for (int i = 0; i < n; i++) {
            map[i][col] ^= 1;
        }
    }

    private boolean isAllZero(int[][] map) {
        for (int[] row : map) {
            for (int val : row) {
                if (val != 0) return false;
            }
        }
        return true;
    }
}
