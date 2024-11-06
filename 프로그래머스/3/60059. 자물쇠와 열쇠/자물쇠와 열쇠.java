class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int len = lock.length;
        for (int d = 0; d < 4; d++) {
            // d = 회전 횟수
            int r = 1 - len;
            int c = 1 - len;
            for (int i = r; i < key.length; i++) {
                for (int j = c; j < key.length; j++) {
                    int[][] temp = scale(i, j, len, key);
                    if (check(temp, lock)) {
                        return true;
                    }
                }
            }
            key = rotate(key);
        }
        return false;
    }
    
    static int[][] rotate(int[][] key) {
        // 시계방향으로 회전
        int n  = key.length;
        int[][] rotate_key = new int[n][n];
        int r = 0;
        int c = 0;
        for (int i = n-1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (key[i][j] == 1) {
                    rotate_key[r][c] = 1;
                }
                r++;
            }
            c++;
            r = 0;
        }
        return rotate_key;
    }

    static int[][] scale(int r, int c, int len, int[][] key) {
        // key 배열을 lock 크기에 맞게 조정
        // [r, c] 에서 시작
        int[][] scaled = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i+r < 0 || i+r >= key.length || j+c < 0 || j+c >= key.length) {
                    scaled[i][j] = 0;
                } else {
                    scaled[i][j] = key[i+r][j+c];
                }
            }
        }

        return scaled;
    }

    static boolean check(int[][] key, int[][] lock) {
        // 조건: key 와 lock 의 크기가 같아야 함
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                if (key[i][j] == lock[i][j]) return false;
            }
        }
        return true;
    }
}