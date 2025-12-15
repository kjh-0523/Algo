import java.util.*;

class Solution {
    static int n, m;
    static String[] grid;

    //0=위, 1=오른쪽, 2=아래, 3=왼쪽
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public int[] solution(String[] g) {
        grid = g;
        n = grid.length;
        m = grid[0].length();

        boolean[][][] visited = new boolean[n][m][4];
        List<Integer> cycles = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int d = 0; d < 4; d++) {

                    if (visited[i][j][d]) continue;

                    int x = i, y = j, dir = d;
                    int length = 0;

                    while (!visited[x][y][dir]) {
                        visited[x][y][dir] = true;
                        length++;

                        int nx = (x + dx[dir] + n) % n;
                        int ny = (y + dy[dir] + m) % m;

                        char c = grid[nx].charAt(ny);
                        if (c == 'L') {
                            dir = (dir + 3) % 4;
                        } else if (c == 'R') {
                            dir = (dir + 1) % 4;
                        }

                        x = nx;
                        y = ny;
                    }

                    cycles.add(length);
                }
            }
        }

        Collections.sort(cycles);

        int[] answer = new int[cycles.size()];
        for (int i = 0; i < cycles.size(); i++) {
            answer[i] = cycles.get(i);
        }

        return answer;
    }
}
