import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        // 크루 도착 시간 분 단위로 변경해서 저장
        int N = timetable.length;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            String[] time = timetable[i].split(":");
            arr[i] = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
        }
        Arrays.sort(arr);

        // 셔틀 시간을 키로 하는 map
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(540 + i * t, new ArrayList<>()); // 9시부터 시작
        }

        // 셔틀에 크루 할당
        Integer[] keys = map.keySet().toArray(new Integer[] {});
        Arrays.sort(keys);
        int keyIdx = 0;
        for (int i = 0; i < N; i++) {
            // 이제 못 타는 경우
            if (keyIdx == keys.length) break;

            int now = keys[keyIdx];
            // 탈 수 있는 셔틀인지 체크
            if (arr[i] <= now) {
                // 정원초과 여부 체크
                if (map.get(now).size() < m) {
                    map.get(now).add(arr[i]);
                } else {
                    // 다음 셔틀 타
                    if (keyIdx + 1 == keys.length) break;
                    map.get(keys[++keyIdx]).add(arr[i]);
                }
            } else {
                // 탈 수 있는 셔틀 체크
                while (keyIdx < keys.length && arr[i] > keys[keyIdx]) {
                    keyIdx++;
                }
                if (keyIdx < keys.length) {
                    map.get(keys[keyIdx]).add(arr[i]);
                }
            }
        }

        int time = 0;
        // 마지막 셔틀 탈 수 있는지 체크
        if (map.get(keys[keys.length-1]).size() < m) {
            time = keys[keys.length-1];
        } else {
            // 가장 늦게 도착하는 크루보다 1분 빨리
            Collections.sort(map.get(keys[keys.length-1]), Collections.reverseOrder());
            time = map.get(keys[keys.length-1]).get(0) - 1;
        }
        
        return parseIntToTime(time);
    }

    static String parseIntToTime(int time) {
        String str = "";
        str += (time / 60 < 10 ? "0" + time / 60 : time / 60);
        str += ":";
        str += (time % 60 < 10 ? "0" + time % 60 : time % 60);

        return str;
    }
}