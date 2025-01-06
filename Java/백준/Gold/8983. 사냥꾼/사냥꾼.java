import java.util.*;
import java.io.*;

public class Main {

    public static int M, N, L;
    public static int[] shooter;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        shooter = new int[M];
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < M; i++){
            shooter[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(shooter);

        int answer = 0;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int start = 0;
            int end = M-1;

            while(start <= end){
                int mid = (start + end)/2;
                int dis = Math.abs(shooter[mid] - x) + y;
                if(dis <= L){
                  answer++;
                  break;
                }

                if(x > shooter[mid]){
                    start = mid + 1;
                }else{
                    end = mid - 1;
                }
            }
        }

        System.out.println(answer);
    }
}