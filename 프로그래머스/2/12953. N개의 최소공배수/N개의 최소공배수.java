import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int[] arr) {
        int answer = 1;

        while (true) {
            int div = 1;
            Map<Integer, Integer> map = getNums(arr);

            for (int i : map.keySet()) {
                if (i != 1 && map.get(i) > 1) {
                    div = i;
                    break;
                }
            }

            if (div == 1) break;

            for (int j =0; j < arr.length; j++) {
                if (arr[j] % div == 0) {
                    arr[j] /= div;
                }
            }
            
            answer *= div;
        }

        for (int i = 0; i < arr.length; i++) answer *= arr[i];

        return answer;
    }
    
    static Map<Integer, Integer> getNums(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j <= Math.sqrt(arr[i]); j++) {
                if (arr[i] % j == 0) {
                    if (!map.containsKey(j)) map.put(j, 1);
                    else map.put(j, map.get(j)+1);

                    if (arr[i] / j != j) {
                        if (!map.containsKey(arr[i] / j)) map.put(arr[i] / j, 1);
                        else map.put(arr[i] / j, map.get(arr[i] / j) + 1);
                    }
                }
            }
        }

        return map;
    }
}