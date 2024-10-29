import java.util.*;
import java.io.*;

public class Main{
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        double N = Double.parseDouble(st.nextToken());
        double M = Double.parseDouble(st.nextToken());
        
        System.out.println(N/M);
    }
}