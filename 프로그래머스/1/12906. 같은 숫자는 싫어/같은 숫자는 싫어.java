import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer;

        Stack<Integer> stack = new Stack<>();

        stack.push(arr[0]);
        
        for (int i = 1; i < arr.length; i++){
            if (stack.peek() != arr[i]) {
                stack.push(arr[i]);
            }
        } // 인풋 받기 끝

        answer = new int[stack.size()];
        
        // 역순으로 담아야 함!
        for (int i = stack.size() - 1; i >= 0; i--){
            answer[i] = stack.pop();
        }

        return answer;
    }
}