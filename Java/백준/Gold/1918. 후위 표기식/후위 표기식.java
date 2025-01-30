import java.util.*;
import java.io.*;

class Main
{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        int N = input.length();
        for(int i = 0; i < N; i++){
            char cur = input.charAt(i);
            if(cur == '+' || cur == '-' || cur == '*' || cur == '/'){
                while(!stack.isEmpty() && priority(stack.peek()) >= priority(cur)){
                    sb.append(stack.pop());
                }
                stack.push(cur);
            }else if(cur == '('){
                stack.push(cur);
            }else if(cur == ')'){
                while(!stack.isEmpty() && stack.peek() != '('){
                    sb.append(stack.pop());
                }
                stack.pop();
            }else{
                sb.append(cur);
            }
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        System.out.println(sb.toString());

    }

    public static int priority(char o){
        if(o == '(' || o == ')'){
            return 0;
        }else if(o == '+' || o == '-'){
            return 1;
        }else{
            return 2;
        }
    }
}