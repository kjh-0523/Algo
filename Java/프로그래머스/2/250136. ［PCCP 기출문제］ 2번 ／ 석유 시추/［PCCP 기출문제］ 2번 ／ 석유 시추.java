import java.util.*;

class Solution {
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int[][] map;
    public static int N, M, idx;
    public static List<Integer> size;

    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        size = new ArrayList<>();
        size.add(0);
        idx = 1;
        map = new int[N][M];

        // BFS로 석유 덩어리 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (land[i][j] == 1 && map[i][j] == 0) {
                    bfs(i, j, land);
                    idx++;
                }
            }
        }

        // 최대 석유량 찾기
        int answer = 0;
        for (int i = 0; i < M; i++) {
            Set<Integer> idxs = new HashSet<>();
            int sum = 0;

            for (int j = 0; j < N; j++) {
                if (map[j][i] > 0) {
                    idxs.add(map[j][i]);
                }
            }

            for (int id : idxs) {
                sum += size.get(id);
            }
            answer = Math.max(answer, sum);
        }
        return answer;
    }

    public static void bfs(int x, int y, int[][] land) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        map[x][y] = idx;
        int cnt = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] > 0 || land[nx][ny] == 0) continue;

                map[nx][ny] = idx;
                q.offer(new int[]{nx, ny});
            }
        }
        size.add(cnt);
    }
}
