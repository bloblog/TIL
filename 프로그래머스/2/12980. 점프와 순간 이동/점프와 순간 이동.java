import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 1;

        // 반씩 내려가면서 홀수 만나면 건전지 +1
        while (n > 1) {
            if (n % 2 == 1) {
                ans++;
                n--;
                continue;
            }
            n /= 2;
        }
        
        return ans;
    }
}