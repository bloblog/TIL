import java.util.PriorityQueue;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];

        PriorityQueue<Filename> pq = new PriorityQueue<>();

        for (int i = 0; i < files.length; i++) {
            String head = "";
            String num = "";
            for (int j = 0; j < files[i].length(); j++) {
                char c = files[i].charAt(j);
                if (!Character.isDigit(c)) {
                    if (num.length() == 0) {
                        head += c;
                    } else break;
                } else {
                    num += c;
                }
            }
            pq.add(new Filename(i, head.toLowerCase(), Integer.valueOf(num), files[i]));
        }

        for (int i = 0; i < files.length; i++) {
            answer[i] = pq.poll().originFilename;
        }
        
        return answer;
    }
}

class Filename implements Comparable<Filename> {
    int idx;
    String head;
    int number;
    String originFilename;

    public Filename(int idx, String head, int number, String originFilename) {
        this.idx = idx;
        this.head = head;
        this.number = number;
        this.originFilename = originFilename;
    }

    @Override
    public int compareTo(Filename f) {
        if (this.head.equals(f.head) && this.number == f.number) {
            return this.idx - f.idx;
        }
        if (this.head.equals(f.head) && this.number != f.number) {
            return this.number - f.number;
        }
        return this.head.compareTo(f.head);
    }

}