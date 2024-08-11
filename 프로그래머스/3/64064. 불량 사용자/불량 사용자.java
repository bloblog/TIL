import java.util.*;
class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        sel = new String[banned_id.length];
        visited = new boolean[user_id.length];
        set = new HashSet<>();

        perm(0, banned_id, user_id);

        return set.size();
    }
    static String[] sel;
    static boolean[] visited;
    static Set<Map> set;

    static void perm(int idx, String[] banned_id, String[] user_id) {
        if (idx == banned_id.length) {
            // 검증
            for (int i = 0; i < banned_id.length; i++) {
                if (!check(banned_id[i], sel[i])) {
                    return;
                }
            }
            // 검증 완료
            save(sel, user_id);
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if (!visited[i]) {
                sel[idx] = user_id[i];
                visited[i] = true;
                perm(idx+1, banned_id, user_id);
                visited[i] = false;
            }
        }

    }

    static void save(String[] s, String[] user_id) {
        Map<String, Integer> map = new HashMap<>();
        for (String u : user_id) map.put(u, 0);
        for (String id : s) map.replace(id, map.get(id)+1);
        set.add(map);
    }

    static boolean check(String b, String u) {
        char[] b_char = b.toCharArray();
        char[] u_char = u.toCharArray();

        if (b_char.length != u_char.length) return false;

        for (int i = 0; i < b_char.length; i++) {
            if (b_char[i] == '*') continue;
            if (b_char[i] != u_char[i]) return false;
        }

        return true;
    }
}