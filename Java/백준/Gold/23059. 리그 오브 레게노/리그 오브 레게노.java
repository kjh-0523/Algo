import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

class Main
{
    public static int N;
    static Map<String, ArrayList<String>> relation;
    static Map<String, Integer> isDegree;

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        relation = new HashMap<>();
        isDegree = new HashMap<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");

            String p = st.nextToken();
            String n = st.nextToken();

            if(relation.get(p) == null){
                relation.put(p, new ArrayList<>());
                isDegree.put(p, 0);
            }

            if(isDegree.get(n) == null){
                relation.put(n, new ArrayList<>());
                isDegree.put(n, 0);
            }

            relation.get(p).add(n);
            isDegree.put(n, isDegree.get(n) + 1);
        }

        Queue<String> q = new LinkedList<>();

        ArrayList<String> isDegreeZero = new ArrayList<>();

        for(String key : isDegree.keySet()){
            if(isDegree.get(key) == 0){
                isDegreeZero.add(key);
            }
        }

        Collections.sort(isDegreeZero);

        for(String key : isDegreeZero){
            q.offer(key);
        }

        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            int size = q.size();

            ArrayList<String> str = new ArrayList<>();
            for(int i = 0; i < size; i++){
                String now = q.poll();

                sb.append(now).append("\n");
                for(String next : relation.get(now)){
                    isDegree.put(next, isDegree.get(next) - 1);

                    if(isDegree.get(next) == 0){
                        str.add(next);
                    }
                }
            }
            Collections.sort(str);

            for(String next : str){
                q.offer(next);
            }
        }

        boolean flag = true;

        for(String key : isDegree.keySet()){
            if(isDegree.get(key) != 0){
                flag = false;
                break;
            }
        }
        if(flag){
            System.out.println(sb);
        }else{
            System.out.println("-1");
        }
    }
}