class Solution {
    // 더할 경우 해당 숫자와 1을 곱해주고,
    // 뺄 경우 해당 숫자와 -1을 곱해준다.
    static int[] mul = new int[] {1, -1};
    static int cnt = 0;
    
    static void dfs(int[] numbers, int target, int num) {
        if (num == numbers.length) {
            int result = 0;
            for (int i = 0; i < numbers.length; i++) {
                result += numbers[i];
            }
            if (result == target) {
                cnt++;
            }
            return;
        }

        for (int i = 0; i < 2; i++) {
            numbers[num] = numbers[num] * mul[i];
            dfs(numbers, target, num+1);
        }
	}
    
    public int solution(int[] numbers, int target) {
        // dfs 로 끝까지 갔다가 타겟넘버 나오면 cnt+1 해주자
        // 0번째 숫자부터 시작
        dfs(numbers, target, 0); 
        return cnt;
    }
}
