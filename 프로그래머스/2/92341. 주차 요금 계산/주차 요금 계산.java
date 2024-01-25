import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};

        // 차량번호 = key
        // 입차시간과의 차를 구해서 누적 계산
        Map<String, Integer> timeMap = new HashMap<>(); // value = 입차 시간
        Map<String, Integer> sumMap = new HashMap<>(); // value = 누적 계산

        for (int i = 0; i < records.length; i++) {
            String[] str = records[i].split(" ");
            if (!timeMap.containsKey(str[1])) {
                // 입차
                timeMap.put(str[1], calc(str[0]));
            } else if (timeMap.containsKey(str[1]) && !sumMap.containsKey(str[1])) {
                sumMap.put(str[1], calc(str[0]) - timeMap.get(str[1]));
                timeMap.remove(str[1]);

            } else if (str[2].equals("OUT") && sumMap.containsKey(str[1])) {
                // 이미 누적시간 맵에 키가 있는 경우 누적 계산해준다
                sumMap.replace(str[1], sumMap.get(str[1]) + calc(str[0]) - timeMap.get(str[1]));
                timeMap.remove(str[1]);
            }
        }


        // 만약 출차하지 않은 경우도 계산
        for (String s : timeMap.keySet()) {
            if (!sumMap.containsKey(s)) {
                sumMap.put(s, 1439-timeMap.get(s));
            } else {
                sumMap.replace(s, sumMap.get(s) + 1439-timeMap.get(s));
            }
        }

        // 누적 계산 맵 차량 번호로 정렬
        List<String> keySet = new ArrayList<>(sumMap.keySet());
        Collections.sort(keySet);

        answer = new int[keySet.size()];
        for (int i = 0; i < keySet.size(); i++) {
            int time = sumMap.get(keySet.get(i));
            if (time <= fees[0]) {
                answer[i] = fees[1];
            } else {
                int val = ((time-fees[0]) % fees[2] == 0 ? (int)(time-fees[0]) / fees[2] : (int)(time-fees[0]) / fees[2] + 1);
                answer[i] = fees[1] + val * fees[3];
            }
        }

        return answer;
    }
    static int calc (String time) {
        // 시각을 분으로 치환하는 메서드
        String[] s = time.split(":");
        return Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
    }
}