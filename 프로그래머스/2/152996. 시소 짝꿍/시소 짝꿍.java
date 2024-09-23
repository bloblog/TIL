import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;

        // 짝꿍 맵
        Map<Integer, boolean[]> map = new HashMap<>();
        // 배열에서 해당 무게 등장 횟수
        Map<Integer, Integer> cnt = new HashMap<>();

        for (int w : weights) {
            if (!cnt.containsKey(w)) {
                cnt.put(w, 1);
                getPairMap(w, map);
            } else {
                cnt.replace(w, cnt.get(w)+1);
            }
        }

//        System.out.println(cnt);

        for (int i : cnt.keySet()) {
            for (int j : cnt.keySet()) {
                if (i != j && map.get(i)[j]) {
                    answer += (long) cnt.get(i) * cnt.get(j);
                }
            }
        }

        answer /= 2;

        // 같은 숫자 쌍 처리
        for (int i : cnt.keySet()) {
            int val = cnt.get(i);
            if (val > 1) {
                answer += (long) val * (val-1) / 2;
            }
        }

        return answer;
    }
    
    static void getPairMap(int key, Map<Integer, boolean[]> map) {
        if (map.containsKey(key)) return;

        boolean[] value = new boolean[2001];

        value[key] = true;

        if (key % 3 == 0) {
            value[key * 2 / 3] = true;
            value[key * 4 / 3] = true;
        }

        if (key % 2 == 0) {
            value[key * 1 / 2] = true;
            value[key * 3 / 2] = true;
        }

        if (key % 4 == 0) {
            value[key * 3 / 4] = true;
        }

        value[key*2] = true;

        map.put(key, value);
    }
}