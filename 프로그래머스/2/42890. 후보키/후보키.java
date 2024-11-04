import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        answer = 0;
        int n = relation[0].length;

        // 속성 번호
        int[] col = new int[n];
        for (int i = 0; i < n; i++) {
            col[i] = i;
        }

        // 후보키 리스트
        List<int[]> arr = new ArrayList<>();

        // 속성 cnt개 선택
        int cnt = 1;
        while (cnt <= n) {
            int[] sel = new int[cnt];
            comp(0, 0, sel, col, relation, arr);
            cnt++;
        }

        return answer;
    }
    
    static int answer;

    static void comp(int idx, int sIdx, int[] sel, int[] nums, String[][] relation, List<int[]> arr) {
        if (idx == sel.length) {
            // 선택된 컬럼이 유일성 및 최소성 만족하는지 체크
            if (check(relation, sel) && checkMin(arr, sel)) {
                answer++;
                arr.add(Arrays.copyOf(sel, sel.length));
            }

            return;
        }

        if (sIdx == nums.length) {
            return;
        }

        sel[idx] = nums[sIdx];
        comp(idx+1, sIdx+1, sel, nums, relation, arr);
        comp(idx, sIdx+1, sel, nums, relation, arr);
    }

    static boolean check(String[][] relation, int[] sel) {
        // sel 컬럼을 선택했을 때 유일성 만족하는지 체크

        // 유일성 판단 위한 set
        Set<String> set = new HashSet<>();

        for (String[] r : relation) {
            String val = "";
            for (int i = 0; i < sel.length; i++) {
                val += r[sel[i]];
            }
            set.add(val);
        }

        if (set.size() == relation.length) {
            return true;
        }

        return false;
    }
    
    static boolean checkMin(List<int[]> arr, int[] sel) {
        // sel 의 컬럼이 이미 후보키에 등록되어있는지 체크
        for (int[] a : arr) {
            int cnt = 0;
            for (int t : a) {
                for (int s : sel) {
                    if (t == s) {
                        cnt++;
                        break;
                    }
                }
                if (cnt == a.length) return false;
            }
        }
        return true;

    }
}