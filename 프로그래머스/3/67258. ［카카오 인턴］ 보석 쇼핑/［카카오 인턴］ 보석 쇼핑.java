import java.util.*;


class Solution {
    public int[] solution(String[] gems) {
        // 유니크한 보석 구하기
        Set<String> uniq = new HashSet<>();
        for (String g : gems) uniq.add(g);
        int n = uniq.size();

        // 보석 개수 카운팅할 map
        Map<String, Integer> cnt = new HashMap<>();
        for (String s : uniq) cnt.put(s, 0);

        int len = Integer.MAX_VALUE;

        // 초기 세팅
        int st = 0;
        int ed = n-1;
        int answer_st = 0;
        int answer_ed = 0;
        int kind = 0; // 보석 종류 개수

        for (int i = 0; i <= n - 1; i++) {
            if (cnt.get(gems[i]) == 0) kind++;
            plusOne(gems[i], cnt);
        }

        while (st < gems.length - n + 1) {

            // 이미 범위 넘어간 경우 필터링
            if (ed - st >= len) {
                if (cnt.get(gems[st]) - 1 == 0) kind--;
                minusOne(gems[st++], cnt);
                continue;
            }

            if (kind == n) {
                if (len > ed - st) {
                    len = ed - st;
                    answer_st = st;
                    answer_ed = ed;
                }
                if (cnt.get(gems[st]) - 1 == 0) kind--;
                minusOne(gems[st++], cnt);
            } else {
                if (ed + 1 >= gems.length) {
                    if (cnt.get(gems[st]) - 1 == 0) kind--;
                    minusOne(gems[st++], cnt);
                } else {
                    if (cnt.get(gems[ed + 1]) == 0) kind++;
                    plusOne(gems[++ed], cnt);

                }
            }
        }

        return new int[] {answer_st+1, answer_ed+1};
    }
    
    static void plusOne(String gem, Map<String, Integer> cnt) {
        cnt.replace(gem, cnt.get(gem)+1);
    }

    static void minusOne(String gem, Map<String, Integer> cnt) {
        cnt.replace(gem, cnt.get(gem)-1);
    }

    static boolean check(Map<String, Integer> cnt) {
        for (int val : cnt.values()) {
            if (val == 0) return false;
        }
        return true;
    }
}