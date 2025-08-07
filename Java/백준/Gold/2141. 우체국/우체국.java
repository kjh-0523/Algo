import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static List<Village> nation;
    public static class Village {
        int pos, num;
        public Village(int pos, int num) {
            this.pos = pos;
            this.num = num;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nation = new ArrayList<>();
        StringTokenizer st = null;
        long sum = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            sum += n;
            nation.add(new Village(p,n));
        }

        nation.sort((o1,o2) -> o1.pos - o2.pos);
        long avg = 0;
        if(sum % 2 == 1){
            avg = (sum/2)+1;
        }else{
            avg = sum/2;
        }

        long nSum = 0;
        int ans = 0;
        for(Village v : nation){
            nSum += v.num;
            if(nSum >= avg){
                ans = v.pos;
                break;
            }
        }
        System.out.println(ans);
    }
}
