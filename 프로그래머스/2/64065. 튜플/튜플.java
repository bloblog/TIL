import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int[] solution(String s) {
        PriorityQueue<Set> pq = new PriorityQueue<>();
        List<Integer> arr = new ArrayList<>();
        int max = 0; // 집합 최장길이

        for (String str : s.split(",")) {
            if (str.charAt(str.length()-1) == '}') {
                arr.add(clearStr(str));
                pq.add(new Set(arr.size(), arr));
                if (arr.size() > max) max = arr.size();
                arr = new ArrayList<>();
            } else {
                arr.add(clearStr(str));
            }
        }

        int[] answer = new int[max];
        int idx = 0;
        int[] check = new int[100001];
        // 집합 원소 길이 짧은 순으로 앞에서부터 넣는다
        // 중복 아닌 것만
        while (!pq.isEmpty()) {
            for (int i : pq.poll().element) {
                if (check[i] > 0) continue;
                answer[idx++] = i;
                check[i]++;
            }
        }

        return answer;
    }
    
    static int clearStr(String s) {
        String strNum = "";
        for (char c : s.toCharArray()) {
            if (c != '{' && c != '}') {
                strNum += c;
            }
        }

        return Integer.valueOf(strNum);
    }
}

class Set implements Comparable<Set>{
    int len;
    List<Integer> element;

    public Set(int len, List<Integer> element) {
        this.len = len;
        this.element = element;
    }

    @Override
    public int compareTo(Set s) {
        return this.len - s.len;
    }

    public String toString() {
        return "len = " + this.len + " element = " + this.element;
    }
}