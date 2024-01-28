import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] sound = sc.next().toCharArray();

        List<ArrayList> arr = new ArrayList<>();
        int answer = 0;
        char[] element = new char[] {'q','u','a','c','k'};
        for (int i = 0; i < sound.length; i++) {
            int idx = -1;
            switch (sound[i]) {
                case 'q':
                    idx = 0;
                    break;
                case 'u':
                    idx = 1;
                    break;
                case 'a':
                    idx = 2;
                    break;
                case 'c':
                    idx = 3;
                    break;
                case 'k':
                    idx = 4;
            }

            boolean flag = false; // 소리 올바른지 체크
            for (int j = 0; j < arr.size(); j++) {
                if (arr.get(j).size() % 5 == idx) {
                    arr.get(j).add(element[idx]);
                    flag = true;
                    break;
                }
            }

            if (!flag && idx == 0) {
                // 'q' 인 경우에만 예외 처리
                arr.add(new ArrayList<Character>());
                arr.get(arr.size()-1).add('q');
            } else if (!flag) {
                answer = -1;
                break;
            }
        }

        // k 까지 잘 들어갔나 체크
        for (ArrayList a : arr) {
            if ((Character) a.get(a.size()-1) != 'k') {
                answer = -1;
                break;
            }
        }

        if (answer != -1) {
            answer = arr.size();
        }

        System.out.println(answer);
    }
}
