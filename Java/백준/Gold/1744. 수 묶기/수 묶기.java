import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        List<Integer> posNums = new ArrayList<>();
        List<Integer> negNums = new ArrayList<>();
        boolean flag = false;
        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(br.readLine());
            if(num < 0){
                negNums.add(num);
            }else if(num > 0){
                posNums.add(num);
            }else{
                flag = true;
            }
        }

        posNums.sort(Collections.reverseOrder());
        negNums.sort((o1,o2) -> o1 - o2);

        int ans = 0;
        for(int i = 0; i < posNums.size(); i += 2){
            if(i + 1 >= posNums.size()) {
                ans += posNums.get(i);
                break;
            }

            if(posNums.get(i) == 1 || posNums.get(i + 1) == 1){
                ans += posNums.get(i) + posNums.get(i + 1);
            }else ans += posNums.get(i) * posNums.get(i + 1);
        }

        for(int i = 0; i < negNums.size(); i += 2){
            if(i + 1 >= negNums.size()) {
                if(!flag) ans += negNums.get(i);
                break;
            }

            ans += negNums.get(i) * negNums.get(i + 1);
        }

        System.out.println(ans);
    }
}