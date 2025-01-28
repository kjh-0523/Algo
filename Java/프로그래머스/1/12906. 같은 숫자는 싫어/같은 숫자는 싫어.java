import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        ArrayList<Integer> answer = new ArrayList<>();
        int N = arr.length;
        answer.add(arr[0]);
        for (int i = 1; i < N; i++) {
            if (answer.get(answer.size() - 1) == arr[i]) continue;
            answer.add(arr[i]);
        }

        int[] result = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }
}
