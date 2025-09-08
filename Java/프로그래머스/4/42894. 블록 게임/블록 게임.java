import java.util.*;

class Solution {
    public static int N, ans;
    public static boolean flag;
    public static List<Set<List<Integer>>> pos;
    public static int[][] map;
    public static boolean[][] visited;
    public static int[] dx = {0, 1, 0};
    public static int[] dy = {1, 0, -1};

    public int solution(int[][] board) {
        init();

        N = board.length;
        map = board;

        ans = 0;
        flag = true;
        while(flag){
            flag = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0 || visited[i][j]) continue;
                    bfs(i, j, map[i][j]);
                }
            }
        }

        return ans;
    }

    public static void bfs(int x, int y, int num) {
        Set<List<Integer>> block = new HashSet<>();
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        block.add(List.of(0, 0));

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 3; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] != num) continue;
                visited[nx][ny] = true;
                block.add(List.of(nx - x, ny - y));
                q.offer(new int[]{nx, ny});
            }
        }

        int idx = matching(block);
        if (idx == -1) return;

        if (canDrop(idx, x, y)) {
            destroyBlock(x, y, block);
            ans++;
            flag = true;
        }
    }

    // 검은 블록 놓을 수 있는지 확인
    public static boolean canDrop(int idx, int x, int y) {
        List<int[]> positions = new ArrayList<>();
        if (idx == 0) {
            positions.add(new int[]{x + 1, y + 1});
            positions.add(new int[]{x + 1, y + 2});
        } else if (idx == 1) {
            positions.add(new int[]{x + 2, y - 1});
        } else if (idx == 2) {
            positions.add(new int[]{x + 2, y + 1});
        } else if (idx == 3) {
            positions.add(new int[]{x + 1, y - 1});
            positions.add(new int[]{x + 1, y - 2});
        } else if (idx == 4) {
            positions.add(new int[]{x + 1, y - 1});
            positions.add(new int[]{x + 1, y + 1});
        }

        for (int[] pos : positions) {
            int row = pos[0];
            int col = pos[1];

            if (row < 0 || row >= N || col < 0 || col >= N) return false;

            for (int r = 0; r < row; r++) {
                if (map[r][col] != 0) return false;
            }
        }

        return true;
    }

    // 블록 제거
    public static void destroyBlock(int x, int y, Set<List<Integer>> block) {
        for (List<Integer> cell : block) {
            int r = x + cell.get(0);
            int c = y + cell.get(1);
            if (r >= 0 && r < N && c >= 0 && c < N)
                map[r][c] = 0;
        }
    }

    // 블록 모양 초기화
    public static void init() {
        pos = new ArrayList<>();

        pos.add(Set.of(List.of(0, 0), List.of(1, 0), List.of(1, 1), List.of(1, 2)));
        pos.add(Set.of(List.of(0, 0), List.of(1, 0), List.of(2, 0), List.of(2, -1)));
        pos.add(Set.of(List.of(0, 0), List.of(1, 0), List.of(2, 0), List.of(2, 1)));
        pos.add(Set.of(List.of(0, 0), List.of(1, 0), List.of(1, -1), List.of(1, -2)));
        pos.add(Set.of(List.of(0, 0), List.of(1, 0), List.of(1, 1), List.of(1, -1)));
    }

    // 블록 매칭
    public static int matching(Set<List<Integer>> block) {
        for (int i = 0; i < pos.size(); i++) {
            if (pos.get(i).equals(block)) return i;
        }
        return -1;
    }
}
