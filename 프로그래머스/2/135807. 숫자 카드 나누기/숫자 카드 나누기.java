import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        // 조건 1 -> 철수 기준
        List<Integer> arr1 = getComm(arrayA);

        // 철수 카드 공약수 중에 영희 카드를 나누지 못하는 가장 큰 수
        boolean flag = true;
        for (int t : arr1) {
            for (int b : arrayB) {
                if (b % t == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer = t;
                break;
            }
        }

        // 조건 2 -> 영희 기준
        List<Integer> arr2 = getComm(arrayB);

        // 영희 카드 공약수 중에 영희 카드를 나누지 못하는 가장 큰 수
        flag = true;
        for (int t : arr2) {
            if (t <= answer) break;
            for (int a : arrayA) {
                if (a % t == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer = t;
                break;
            }
        }

        return answer;
    }
    static List<Integer> getDiv(int n) {
        // 약수 리스트 리턴
        List<Integer> div = new ArrayList<>();

        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                div.add(i);
                if (i != n / i) div.add(n / i);

            }
        }

        return div;
    }

    static List<Integer> getComm(int[] nums) {
        // 수 배열의 공약수 리턴
        List<Integer> comm = new ArrayList<>();

        // 한 숫자의 약수 구하기
        List<Integer> div = getDiv(nums[0]);
        int[] visited = new int[nums[0]+1];


        // 다른 수들 공약수 체크
        // 나누어 떨어지면 add 아니면 빼
        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];
            int len = div.size();
            for (int j = 0; j < len; j++) {
                int t = div.get(j);
                if (n % t == 0) {
                    visited[t]++;
                }
            }
        }

        // 1 빼고 진행 + 내림차순 정렬
        for (int i = visited.length-1; i >= 2; i--) {
            if (visited[i] == nums.length-1) {
                comm.add(i);
            }
        }

        return comm;
    }
}