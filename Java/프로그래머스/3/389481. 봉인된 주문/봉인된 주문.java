import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        Set<Long> bannedOrders = new HashSet<>();
        for (String ban : bans) {
            bannedOrders.add(convertString(ban));
        }

        long target = n;
        List<Long> sortedBannedOrders = new ArrayList<>(bannedOrders);
        Collections.sort(sortedBannedOrders);
        
        for (long base : sortedBannedOrders) {
            if (target >= base) {
                target++;
            } else {
                break;
            }
        }

        return convert(target);
    }

    private String convert(long num) {
        StringBuilder result = new StringBuilder();
        while (num-- > 0) {
            char c = (char) ('a' + (num % 26));
            result.append(c);
            num /= 26;
        }
        return result.reverse().toString();
    }

    private long convertString(String input) {
        long result = 0;
        for (char c : input.toCharArray()) {
            int value = c - 'a' + 1;
            result = result * 26 + value;
        }
        return result;
    }
}
