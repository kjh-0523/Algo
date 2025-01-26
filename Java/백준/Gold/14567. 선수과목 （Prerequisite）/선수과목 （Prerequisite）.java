import java.util.*;
import java.io.*;

class Main
{
    public static int N,M;
    public static int[] inDegree;
    public static ArrayList<Integer>[] list;
    public static Queue<Integer> q;
    public static int[] result;

    public static void init(){
        inDegree = new int[N+1];
        list = new ArrayList[N+1];
        for(int i = 0; i < N+1; i++){
            list[i] = new ArrayList<>();
        }
        q = new LinkedList<>();
        result = new int[N+1];
        Arrays.fill(result, 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine()," ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(e);
            inDegree[e]++;
        }

        for(int i = 1; i < N+1; i++){
            if(inDegree[i] == 0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int next : list[cur]) {
                if (--inDegree[next] == 0) {
                    result[next] = result[cur] + 1;
                    q.add(next);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < N+1; i++){
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
    }
}