import java.util.*;
import java.io.*;

public class Main {
    public static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        long start = 666;
        int cnt = 0;
        while(true){
            if(String.valueOf(start).contains("666")){
                cnt++;
            }
            if(cnt == N){
                break;
            }
            start++;
        }
        System.out.println(start);
    }
}