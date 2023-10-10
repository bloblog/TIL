class Solution {
    public int solution(int[] numbers, int target) {
        
        int n = numbers.length;
		int cnt = 0;
		
		// 더하기 계산할 요소들과 빼기 계산할 요소들을 선택한다
		// 비트마스킹 사용해서 0이면 양수, 1이면 음수로 치환
		for (int i = 0; i <(1<<n); i++) {
			int sum =0;
			for (int j = 0; j < n; j++) {
				if ((i & (1<<j)) > 0) {
					// 해당 위치의 숫자가 1이라면
					sum+= numbers[j] * (-1);
				} else {
					// 0이라면
					sum+= numbers[j];
				}
			}
			if (target == sum) {
				cnt++;
			}
		}
		
		return cnt;
	}
}
