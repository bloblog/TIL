import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        // 이중연결
        for (int i = 0; i < n-1; i++) {
            nodes[i+1].prev = nodes[i];
            nodes[i].next = nodes[i+1];
        }

        Stack<Node> stack = new Stack<>(); // 삭제된 행

        Node now = nodes[k]; // 현재 노드
        for (String c : cmd) {
            if (c.equals("Z")) {
                // 실행 취소
                Node node = stack.pop();
                node.isDeleted = false;
                // 노드 다시 연결
                if (node.prev != null) {
                    node.prev.next = node;
                }
                if (node.next != null) {
                    node.next.prev = node;
                }

            } else if (c.equals("C")) {
                // 삭제
                stack.push(now);
                now.isDeleted = true;
                // 앞 뒤 노드 연결하기
                if (now.prev != null) {
                    now.prev.next = now.next;
                }
                if (now.next != null) {
                    now.next.prev = now.prev;
                }

                // 포인터 변경
                if (now.next == null) {
                    now = now.prev;
                } else {
                    now = now.next;
                }

            } else {
                String[] com = c.split(" ");
                int val = Integer.parseInt(com[1]);
                if (com[0].equals("U")) {
                    for (int i = 0; i < val; i++) {
                        now = now.prev;
                    }
                } else {
                    for (int i = 0; i < val; i++) {
                        now = now.next;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (nodes[i].isDeleted == true) sb.append("X");
            else sb.append("O");
        }

        return sb.toString();
    }
}

class Node {
    int idx;
    Node prev;
    Node next;
    boolean isDeleted;

    public Node(int idx) {
        this.idx = idx;
    }
}
