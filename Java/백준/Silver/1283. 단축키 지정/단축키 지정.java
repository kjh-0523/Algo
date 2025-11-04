import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        Set<Character> key = new HashSet<>();
        key.add(' ');
        StringBuilder sb = new StringBuilder();

        top:
        for (int i = 0; i < n; i++) {
            int l = words[i].length();
            String upperWord = words[i].toUpperCase();
            char last = ' ';
            int idx = -1;
            for(int j = 0; j < l; j++){
                if(last == ' ' && !key.contains(upperWord.charAt(j))){
                    key.add(upperWord.charAt(j));
                    sb.append(wrapAt(words[i], j)).append("\n");
                    continue top;
                }
                if (idx == -1 && !key.contains(upperWord.charAt(j))) {
                    idx = j;
                }
                last = words[i].charAt(j);
            }
            if(idx != -1) {
                sb.append(wrapAt(words[i], idx)).append("\n");
                key.add(upperWord.charAt(idx));
            }else{
                sb.append(words[i]).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static String wrapAt(String str, int index) {
        return str.substring(0, index)
            + "[" + str.charAt(index) + "]"
            + str.substring(index + 1);
    }
}