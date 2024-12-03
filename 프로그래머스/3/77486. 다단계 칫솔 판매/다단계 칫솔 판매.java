import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;
        int[] answer = new int[n];

        // 각자 부모 배열 만들기
        // 민호는 -1
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            String t = enroll[i];
            for (int j = 0; j < n; j++) {
                if (referral[j].equals("-")) {
                    p[j] = -1;
                } else if (referral[j].equals(t)) {
                    p[j] = i;
                }
            }
        }

        // 기본 profit 세팅
        // 직원이름을 키로 하는 맵 생성
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < seller.length; i++) {
            List<Integer> val = map.getOrDefault(seller[i], new ArrayList<>());
            val.add(amount[i] * 100);
            map.put(seller[i], val);
        }

        // 직원 부모 타고 올라가면서 배분
        for (int i = 0; i < n; i++) {
            if (map.keySet().contains(enroll[i])) {
                divide(i, map.get(enroll[i]), p, answer);
                // answer 배열에 실제 판매액 반영
                for (int v : map.get(enroll[i])) {
                    answer[i] += v;
                }
            }
        }

        return answer;
    }

    static void divide(int st, List<Integer> profit, int[] p, int[] answer) {
        // st 부모 찾아가면서 profit 배분
        for (int i = 0; i < profit.size(); i++) {
            int now = st;
            int pf = profit.get(i);
            while (pf > 0) {
                // 분배 해준만큼 차감
                int fee = (int) (pf * 0.1);
                answer[now] -= fee;

                // 부모가 민호면 answer 에 반영 x
                if (p[now] == -1) break;

                now = p[now];
                answer[now] += fee;
                pf = fee;
            }
        }
    }
}