import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> arr = new HashMap<>();

        for (String w : want) {
            arr.put(w, 0);
        } // 초기화

        // 첫째 날 체크
        for (int i = 0; i < 10; i++) {
            String p = discount[i];
            if (arr.containsKey(p)) {
                arr.replace(p, arr.get(p)+1);
            }
        }

        check(want, number, arr);

        // 다른 날들은 슬라이딩 윈도우로 체크
        for (int i = 0; i < discount.length-10; i++) {
            if (arr.containsKey(discount[i])) {
                arr.replace(discount[i], arr.get(discount[i]) - 1);
            }
            if (arr.containsKey(discount[10+i])) {
                arr.replace(discount[10+i], arr.get(discount[10+i])+1);
            }

            check(want, number, arr);
        }


        return answer;
    }
    
    static int answer;
    static void check(String[] want, int[] number, Map arr) {
        boolean flag = false;
        for (int i = 0; i < want.length; i++) {
            if ((int)arr.get(want[i]) != number[i]) {
                flag = true;
                break;
            }
        }
        if (!flag) answer++;
    }
}