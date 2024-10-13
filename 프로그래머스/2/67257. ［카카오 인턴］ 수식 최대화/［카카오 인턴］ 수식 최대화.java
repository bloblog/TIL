import java.util.*;

class Solution {
    public long solution(String expression) {
        max = 0;

        // 연산자 추출 및 숫자, 연산자 세팅
        Set<Character> opr = new HashSet<>(); // 연산자 담을 set
        List<String> content = new LinkedList<>(); // 연산자, 숫자
        int ed = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) < 48 || expression.charAt(i) > 57) {
                opr.add(expression.charAt(i));
                content.add(expression.substring(ed, i));
                content.add(expression.substring(i, i+1));
                ed = i+1;
            }
        }

        content.add(expression.substring(ed, expression.length()));

        Character[] arr = opr.toArray(new Character[]{});

        // 연산자 하나뿐이면 그냥 계산
        if (arr.length == 1) {
            String o = String.valueOf(arr[0]);
            max = Long.valueOf(content.get(0));
            switch (o) {
                case "+":
                    for (int i = 2; i < content.size(); i++) {
                        if (!content.get(i).equals("+")) {
                            max += Integer.valueOf(content.get(i));
                        }
                    }
                    break;
                case "-":
                    for (int i = 2; i < content.size(); i++) {
                        if (!content.get(i).equals("-")) {
                            max -= Integer.valueOf(content.get(i));
                        }
                    }
                    break;
                case "*":
                    for (int i = 2; i < content.size(); i++) {
                        if (!content.get(i).equals("*")) {
                            max *= Integer.valueOf(content.get(i));
                        }
                    }
                    break;
            }
            return Math.abs(max);
        }

        // 연산자 우선순위 배정 및 계산
        setOrder(0, content, arr, new int[arr.length], new boolean[arr.length]);

        return max;
    }
    
    static long max;

    static String basic_calc(String num1, String num2, String opr) {
        long n1 = Long.valueOf(num1);
        long n2 = Long.valueOf(num2);

        if (opr.equals("+")) {
            return String.valueOf(n1 + n2);
        }
        if (opr.equals("-")) {
            return String.valueOf(n1 - n2);
        }
        if (opr.equals("*")) {
            return String.valueOf(n1 * n2);
        }
        return null;
    }

    static long calc(List<String> content, int[] order, Character[] opr) {
        Deque<String> queue = new LinkedList<>();
        Deque<Integer> o = new LinkedList<>(); // 연산 순서 담은 deque

        for (int i = 0; i < content.size(); i++) {
            queue.add(content.get(i));
            o.add(i);
        }

        // 연산자 우선순위대로 정렬
        String[] sorted = new String[opr.length];
        for (int i = 0; i < opr.length; i++) {
            sorted[order[i]-1] = String.valueOf(opr[i]);
        }

        for (int i = 0; i < sorted.length; i++) {
            // 우선순위 i인 연산자 있을 때까지
            while (queue.contains(sorted[i])) {
                while (!queue.peekFirst().equals(sorted[i])) {
                    queue.addLast(queue.pollFirst());
                    o.addLast(o.pollFirst());
                }

                // 맨 앞이 해당 우선순위의 연산자가 되면
                queue.pollFirst();
                o.pollFirst();
                queue.addLast(basic_calc(queue.pollLast(), queue.pollFirst(), sorted[i]));
                o.addLast(o.pollLast());
                o.pollFirst();
//                System.out.println("queue = " + queue);
//                System.out.println("order = " + o);
            }

            // 한 연산자 계산 끝나고 난 다음 순서대로 정렬될 때까지
            while (o.peekFirst() != 0) {
                queue.addLast(queue.pollFirst());
                o.addLast(o.pollFirst());
            }
//            System.out.println(queue);

        }

        return Math.abs(Long.valueOf(queue.poll()));
    }


    static void setOrder(int idx, List<String> content, Character[] opr, int[] order, boolean[] visited) {
        if (idx == opr.length) {
            // 해당 우선순위대로 계산
            long result = calc(content, order, opr);
            if (result > max) max = result;
            return;
        }

        for (int i = 1; i <= opr.length; i++) {
            if (!visited[i-1]) {
                visited[i-1] = true;
                order[idx] = i;
                setOrder(idx+1, content, opr, order, visited);
                visited[i-1] = false;
            }
        }
    }
}