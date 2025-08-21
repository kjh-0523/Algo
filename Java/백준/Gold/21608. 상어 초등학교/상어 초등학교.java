import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] map;
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,-1,1};

    public static class Student{
        int no;
        Set<Integer> likes;

        public Student(int no){
            this.no = no;
            this.likes = new HashSet<>();
        }
    }

    public static class Score implements Comparable<Score>{
        int like, empty;
        int x, y;

        public Score(int like, int empty, int x, int y) {
            this.like = like;
            this.empty = empty;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Score o){
            if(this.like == o.like){
                if(this.empty == o.empty){
                    if(this.x == o.x) return this.y - o.y;
                    else return this.x - o.x;
                }
                else return o.empty - this.empty;
            }
            return o.like - this.like;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = null;
        Student[] students = new Student[N * N];
        for(int i = 0; i < N * N; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            int s3 = Integer.parseInt(st.nextToken());
            int s4 = Integer.parseInt(st.nextToken());

            students[i] = new Student(n);
            students[i].likes.add(s1);
            students[i].likes.add(s2);
            students[i].likes.add(s3);
            students[i].likes.add(s4);
        }

        map = new int[N][N];
        for(Student s : students){
            PriorityQueue<Score> pq = new PriorityQueue<>();

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(map[i][j] > 0) continue;
                    int l = 0;
                    int e = 0;

                    for(int k = 0; k < 4; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(nx < 0 || nx > N-1 || ny < 0 || ny > N-1) continue;
                        if(map[nx][ny] == 0) e++;
                        if(s.likes.contains(map[nx][ny])) l++;
                    }
                    pq.offer(new Score(l,e,i,j));
                }
            }

            Score best = pq.peek();
            map[best.x][best.y] = s.no;
        }

        // for(int i = 0; i < N; i++){
        //     System.out.println(Arrays.toString(map[i]));
        // }
        Arrays.sort(students, (o1,o2) -> {return o1.no - o2.no;});
        int score = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int cnt = -1;
                int idx = map[i][j]-1;
                for(int k = 0; k < 4; k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx < 0 || nx > N-1 || ny < 0 || ny > N-1) continue;
                    if(students[idx].likes.contains(map[nx][ny])) {
                        cnt++;
                    }
                }
                if(cnt > -1){
                    score += (int) Math.pow(10,cnt);
                }
            }
        }

        System.out.println(score);
    }
}
