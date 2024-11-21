import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        // 지원자 조건 딕셔너리 세팅
        HashMap<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < info.length; i++) {
            String[] s = info[i].split(" ");

            // 점수를 제외한 조건 조합별로 맵에 저장
            boolean[] sel = new boolean[4];
            comb(0, 0, sel, s, map);
        }

//        System.out.println(map);
        
        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        // 쿼리로 검색
        for (int i = 0; i < query.length; i++) {
            String[] s = query[i].split(" and ");
            String[] foodAndScore = s[3].split(" ");

            // map key 형태로 변환
            String key = "";
            for (int j = 0; j < 3; j++) {
                if (!s[j].equals("-")) {
                    key += s[j];
                }
            }

            if (!foodAndScore[0].equals("-")) {
                key += foodAndScore[0];
            }

            if (!map.containsKey(key)) answer[i] = 0;
            else {
                List<Integer> val = map.get(key);
                answer[i] = val.size() - binarySearch(val, Integer.parseInt(foodAndScore[1]));
                
            }
        }

        return answer;
    }
    
    static int binarySearch(List<Integer> scores, int target) {
        int left = 0, right = scores.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (scores.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    static void comb(int idx, int cnt, boolean[] sel, String[] s, HashMap<String, List<Integer>> map) {
        if (idx == 4) {
            // 해당 조건 문자열로 묶어서 맵에 저장
            String key = "";
            for (int i = 0; i < 4; i++) {
                if (sel[i]) {
                    key += s[i];
                }
            }
            if (!map.containsKey(key)) {
                List<Integer> val = new ArrayList<>();
                val.add(Integer.parseInt(s[4]));
                map.put(key, val);
            } else {
                List<Integer> val = map.get(key);
                val.add(Integer.parseInt(s[4]));
                map.replace(key, val);
            }
            return;
        }

        if (cnt > 4) return;

        sel[idx] = true;
        comb(idx+1, cnt+1, sel, s, map);

        sel[idx] = false;
        comb(idx+1, cnt, sel, s, map);
    }
}