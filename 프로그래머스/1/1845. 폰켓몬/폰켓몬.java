import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;

        // 폰켓몬 가짓수 계산
        Set<Integer> kind = new HashSet<>();

        for (int n : nums) {
            kind.add(n);
        }

        int size = kind.size();

        // size 가 n/2 보다 큰 경우 -> n/2
        // 작으면 size
        int len = nums.length;
        if (size >= len/2) answer = len/2;
        else answer = size;

        return answer;
    }
}