class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        // 높이가 1인 경우도 고려
        int max = triangle[0][0];

        for (int i = 1; i < n; i++) {
            // 대각선왼, 대각선오 숫자를 모두 갖지 않는 경우
            // 즉, 해당 배열에서 인덱스가 0이거나 배열 길이 len-1 인 경우
            // 하나 있는 숫자만 더해준다
            int[] parent = triangle[i-1];
            int[] arr = triangle[i];
            
            for (int j = 0; j < arr.length; j++) {
                if (j == 0) {
                    arr[j] += parent[j];
                } else if (j == arr.length-1) {
                    arr[j] += parent[parent.length-1];
                } else {
                    arr[j] += Math.max(parent[j-1], parent[j]);
                }

                // 마지막 줄 최대값 돌면서 구하기
                if (i == n-1) {
                    if (arr[j] > max) max = arr[j];
                }
            }
            triangle[i] = arr;
        }
        return max;
    }
}