import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        Map<String, Integer> d1 = new HashMap<>();
        Map<String, Integer> d2 = new HashMap<>();

        d1 = setDict(str1, d1);
        d2 = setDict(str2, d2);

        int hat = 0; // 교집합 원소의 개수
        int cup = 0; // 합집합 원소의 개수

        // d1 기준
        for (String t : d1.keySet()) {
            int num1 = d1.get(t); // d1의 value
            int num2 = 0; // d2의 value
            if (d2.get(t) != null) {
                num2 = d2.get(t);
            }

            hat += Math.min(num1, num2);
            cup += Math.max(num1, num2);
        }

        // d2 기준
        for (String t : d2.keySet()) {
            int num1 = 0; // d1의 value
            int num2 = d2.get(t); // d2의 value

            if (d1.get(t) != null) continue;

            hat += Math.min(num1, num2);
            cup += Math.max(num1, num2);
        }

        if (d1.size() == 0 && d2.size() == 0) return 65536;

        return 65536 * hat / cup;
    }
    
    static Map setDict(String str, Map<String, Integer> dict) {
        for (int i = 0; i < str.length()-1; i++) {
            String temp = preprocessing(str.substring(i, i+2));
            if (temp == "") continue;

            if (dict.get(temp) != null) {
                dict.put(temp, dict.get(temp) + 1);
            } else {
                dict.put(temp, 1);
            }
        }

        return dict;
    }

    static String preprocessing(String input) {
        String output = "";
        char[] arr = input.toLowerCase().toCharArray();

        if ((int)arr[0] < 97 || (int)arr[0] > 122 || (int)arr[1] < 97 || (int)arr[1] > 122) {
            // 알파벳 아닌 경우 걸러
            return output;
        } else {
            // 알파벳으로 이루어진 경우 소문자로 치환해서 리턴
            return input.toLowerCase();
        }
    }
    
}

