import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        List<User> arr = new ArrayList<>();
        Map<String, String> nickMap = new HashMap<>();

        for (int i = 0; i < record.length; i++) {
            String[] str = record[i].split(" ");

            if (str[0].equals("Leave")) {
                arr.add(new User(false, str[1]));
            } else {
                boolean flag = false; // 이미 들어왔던 유저인가?

                if (nickMap.keySet().contains(str[1])) {
                    // 만약 원래 들어왔던 유저면 닉네임 수정 필요
                    nickMap.remove(str[1]);
                    nickMap.put(str[1], str[2]);
                    flag = true;
                }

                // enter
                // change 일 경우 arr 에 안 넣어도 돼서 패스
                if (str[0].equals("Enter")) {
                    arr.add(new User(true, str[1]));

                    // 새로운 유저라면 닉네임 배열에 추가
                    if (!flag) {
                        nickMap.put(str[1], str[2]);
                    }
                }
            }
        }

        // answer 에 기록
        String[] answer = new String[arr.size()];

        for (int i = 0; i < arr.size(); i++) {
            String msg = "";

            // userId 에 맞는 nickname 찾기
            msg += nickMap.get(arr.get(i).userId);

            // 들어왔는지 나갔는지 메시지에 추가
            if (arr.get(i).status) {
                msg += "님이 들어왔습니다.";
            } else {
                msg += "님이 나갔습니다.";
            }

            answer[i] = msg;
        }

        return answer;
    }
}

class User {
    boolean status; // true : enter, false : leave
    String userId;

    public User(boolean status, String userId) {
        this.status = status;
        this.userId = userId;
    }
}