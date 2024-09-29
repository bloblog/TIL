import java.util.*;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        Map<String, Integer> map = new HashMap<>();

        for (String o : orders) {
            for (int c : course) {
                for (String s : pickN(o, c)) {
                    if (map.containsKey(s)) {
                        map.replace(s, map.get(s)+1);
                    } else {
                        map.put(s, 1);
                    }
                };
            }
        }

        PriorityQueue<Set> pq = new PriorityQueue<>();

        for (String key : map.keySet()) {
            pq.add(new Set(key, map.get(key)));
        }

        List<String> answer = new ArrayList<>();
        int lenIdx = course.length-1;
        int max = 0;
        while (!pq.isEmpty()) {
            Set t = pq.poll();
            if (lenIdx >= 0 && t.menu.length() < course[lenIdx]) lenIdx--;

            if (lenIdx >= 0 && course[lenIdx] == t.menu.length() && t.cnt > 1) {
                max = t.cnt;
                answer.add(t.menu);
                lenIdx--;
            } else if (t.cnt == max) {
                answer.add(t.menu);
            }
        }
        String[] ans = answer.toArray(new String[] {});
        Arrays.sort(ans);

        return ans;

    
    }
    
    static String[] pickN(String menu, int n) {
        // 손님이 시킨 단품메뉴 조합에서 n개 뽑은 조합
        char[] sel = new char[n];
        List<String> list = new ArrayList<>();
        String[] comb = new String[] {};

        comb(0, 0, n, menu, sel, list);

        return list.toArray(comb);
    }
    
    static void comb(int idx, int sIdx, int n, String menu, char[] sel, List<String> list) {
        if (sIdx == n) {
            String str = "";
            char[] copy = Arrays.copyOf(sel, n);
            Arrays.sort(copy);
            for (char c : copy) str += c;
            list.add(str);
            return;
        }

        if (idx == menu.length()) {
            return;
        }

        sel[sIdx] = menu.charAt(idx);
        comb(idx+1, sIdx+1, n, menu, sel, list);
        comb(idx+1, sIdx, n, menu, sel, list);
    }
}

class Set implements Comparable<Set> {
    String menu;
    int cnt;

    public Set() {}
    public Set(String menu, int cnt) {
        this.menu = menu;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Set s) {
        if (this.menu.length() == s.menu.length()) {
            return s.cnt - this.cnt;
        }
        return s.menu.length() - this.menu.length();
    }

    @Override
    public String toString() {
        return this.menu + " => " + this.cnt;
    }
}