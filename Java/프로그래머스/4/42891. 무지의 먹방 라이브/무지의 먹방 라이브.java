import java.util.*;

class Solution {
    
    public static class Food{
        int idx, time;
        public Food(int idx, int time){
            this.idx = idx;
            this.time = time;
        }
    }
    
    public int solution(int[] food_times, long k) {
        int n = food_times.length;
        long sum = 0;
        List<Food> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(new Food(i + 1, food_times[i]));
            sum += food_times[i];
        }

        if (sum <= k) return -1;

        list.sort(Comparator.comparingInt(f -> f.time));

        long total = 0;
        int prev = 0;
        for (int i = 0; i < n; i++) {
            long curTime = list.get(i).time;
            long diff = curTime - prev;
            long remainCount = n - i;
            long spend = diff * remainCount;
            if (total + spend <= k) {
                total += spend;
                prev = (int) curTime;
            } else {
                List<Food> remain = new ArrayList<>(list.subList(i, n));
                remain.sort(Comparator.comparingInt(f -> f.idx));
                int index = (int)((k - total) % remain.size());
                return remain.get(index).idx;
            }
        }

        return -1;
    }
}