import java.util.*;

class Solution {
    public static int solution(int coin, int[] cards) {
        int answer = 1;
        int n = cards.length;
        
        Set<Integer> initCardsSet = new HashSet<>();
        Deque<Integer> leftCards = new ArrayDeque<>();
        Set<Integer> pickedCardsSet = new HashSet<>();

        for (int i = 0; i < n / 3; i++) {
            initCardsSet.add(cards[i]);
        }
        
        for (int i = n - 1; i >= n / 3; i--) {
            leftCards.push(cards[i]);
        }

        while (true) {
            if (leftCards.isEmpty()) {
                break;
            }

            // 카드 두 장 뽑기
            pickedCardsSet.add(leftCards.pop());
            if (!leftCards.isEmpty()) {
                pickedCardsSet.add(leftCards.pop());
            }
            
            boolean isFind = false;
            
            // 원본 카드셋에서 n + 1 조합 찾기
            for (int i : new HashSet<>(initCardsSet)) {
                int other = (n + 1) - i;
                if (initCardsSet.contains(other) && i != other) {
                    initCardsSet.remove(i);
                    initCardsSet.remove(other);
                    answer++;
                    isFind = true;
                    break;
                }
            }
            
            if (isFind) continue;
            
            // 코인이 1개 있는 경우, 초기 카드셋에서 하나, 뽑은 카드셋에서 하나
            if (coin >= 1 && !initCardsSet.isEmpty() && !pickedCardsSet.isEmpty()) {
                for (int i : new HashSet<>(initCardsSet)) {
                    int other = (n + 1) - i;
                    if (pickedCardsSet.contains(other)) {
                        initCardsSet.remove(i);
                        pickedCardsSet.remove(other);
                        answer++;
                        coin--;
                        isFind = true;
                        break;
                    }
                }
            }
            
            if (isFind) continue;
            
            // 코인이 2개 있는 경우, 뽑은 카드셋에서 두 개 사용
            if (coin >= 2 && pickedCardsSet.size() >= 2) {
                for (int i : new HashSet<>(pickedCardsSet)) {
                    int other = (n + 1) - i;
                    if (pickedCardsSet.contains(other) && i != other) {
                        pickedCardsSet.remove(i);
                        pickedCardsSet.remove(other);
                        answer++;
                        coin -= 2;
                        isFind = true;
                        break;
                    }
                }
            }
            
            if (!isFind) break;
        }
        
        return answer;
    }
}