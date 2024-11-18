import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        move = Integer.MAX_VALUE;
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < name.length(); i++) {
            // 알파벳 변환 횟수 먼저 카운트
            answer += cntToA(name.charAt(i));
            // 변환이 필요한 char 위치 저장
            if (name.charAt(i) != 'A' && i != 0) {
                dq.addLast(i);
            }
        }

        // dq 길이만큼 방향 배열 만들기
        int[] dir = new int[dq.size()];
        dfs(name.length(), 0, dir, dq);

        return answer + move;
    }
    
    static int move;
    static void dfs(int len, int idx, int[] dir, Deque<Integer> dq) {
        if (idx == dq.size()) {
            // dir 대로 방향 전환하며 카운트 계산
            count(len, dir, dq);

            return;
        }

        for (int d : new int[] {-1, 1}) {
            dir[idx] = d;
            dfs(len, idx+1, dir, dq);
        }

    }

    static void count(int len, int[] dir, Deque<Integer> dq) {
        // 복사
        Deque<Integer> copy = new ArrayDeque<>();
        copy.addAll(dq);

        // 시작 인덱스는 0
        int now = 0;
        int cnt = 0;
        for (int d : dir) {
            if (d == 1) {
                // 오른쪽 이동
                int t = copy.pollFirst();
                if (now <= t) {
                    cnt += t - now;
                } else {
                    cnt += t + len - now;
                }
                now = t;
            } else {
                // 왼쪽 이동
                int t = copy.pollLast();
                if (now <= t) {
                    cnt += len - t + now;
                } else {
                    cnt += now - t;
                }
                now = t;
            }
        }
        if (cnt < move) {
            move = cnt;
        }

    }

    static int cntToA(char c) {
        return Math.min(c - 'A', 91 - c);
    }
}
