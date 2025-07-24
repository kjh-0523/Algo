import java.io.*;
import java.util.*;

public class Main {
    public static String line;
    public static String bomb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine();
        bomb = br.readLine();

        int N = line.length();
        Stack<Character> stack = new Stack<>();
        for(int i = N-1; i > -1; i--){
            stack.push(line.charAt(i));
            if(!stack.isEmpty() && stack.peek() == bomb.charAt(0)){
                int idx = 0;
                while(idx < bomb.length()){
                    if(stack.isEmpty() || stack.peek() != bomb.charAt(idx)){
                        while(idx > 0){
                            idx--;
                            stack.push(bomb.charAt(idx));

                        }
                        break;
                    }else{
                        stack.pop();
                        idx++;
                    }
                }
            }
        }

        if(stack.isEmpty()){
            System.out.println("FRULA");
        }else {
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            System.out.println(sb.toString());
        }
    }
}
