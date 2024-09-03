import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;

        // 한 칸 한 칸 전진하면서 토핑 가짓수 체크
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        // 초기 세팅 (map1에 첫번째 원소, map2에 나머지 원소 다)
        map1.put(topping[0], 1);
        for (int i = 1; i < topping.length; i++) {
            int t = topping[i];
            if (map2.containsKey(t)) {
                map2.replace(t, map2.get(t)+1);
            } else {
                map2.put(t, 1);
            }
        }

        // 체크
        if (map1.size() == map2.size()) answer++;

        for (int i = 1; i < topping.length-1; i++) {
            // 하나씩 전진하며 체크
            int t = topping[i];

            // map1 세팅
            if (map1.containsKey(t)) {
                map1.replace(t, map1.get(t)+1);
            } else {
                map1.put(t, 1);
            }

            // map2 세팅
            if (map2.get(t) > 1) {
                map2.replace(t, map2.get(t)-1);
            } else {
                map2.remove(t);
            }

            // 체크
            if (map1.size() == map2.size()) answer++;
        }

        return answer;
    }
}