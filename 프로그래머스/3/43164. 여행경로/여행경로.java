import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {
        int n = tickets.length;
        String[] answer = new String[n+1];
        answer[0] = "ICN";

        Arrays.sort(tickets, (a,b) -> a[1].compareTo(b[1]));
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (tickets[i][0].equals("ICN")) {
                dfs(0, i, answer, visited, tickets);
                if (answer[n-1] != null) return answer;
            }
        }

        return answer;
    }
    
    static void dfs(int cnt, int idx, String[] answer, boolean[] visited, String[][] ticket) {
        if (cnt == visited.length-1) {
            visited[idx] = true;
            answer[cnt+1] = ticket[idx][1];
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i] && ticket[idx][1].equals(ticket[i][0])) {
                visited[idx] = true;
                answer[cnt+1] = ticket[idx][1];
                dfs(cnt+1, i, answer, visited, ticket);
                if (answer[visited.length-1] != null) return;
                visited[idx] = false;
            }
        }
    }
}