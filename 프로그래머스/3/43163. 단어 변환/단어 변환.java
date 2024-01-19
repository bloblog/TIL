import java.util.LinkedList;
import java.util.Queue;

class Word {
    int idx;
    String str;

    public Word(int idx, String str) {
        this.idx = idx;
        this.str = str;
    }
}

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;


        Word[] arr = new Word[words.length];

        for (int i = 0; i < words.length; i++) {
            arr[i] = new Word(i, words[i]);
        }

        // target 단어의 인덱스 찾아
        int end = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].str.equals(target)) {
                end = i;
            }
        }
        
        // 없으면 0 반환
        if (end == -1) return 0;

        int[] dist = new int[words.length];
        bfs(begin, arr, dist);

//        System.out.println(Arrays.toString(dist));

        return dist[end];
    }
    
    static void bfs(String st, Word[] arr, int[] dist) {
        Queue<Word> queue = new LinkedList<>();

        queue.add(new Word(-1, st));

        while (!queue.isEmpty()) {
            Word t = queue.poll();

            // 한 글자만 다른 단어들을 큐에 넣는다
            for (int idx = 0; idx < arr.length; idx++) {
                Word s = arr[idx];

                char[] charT = t.str.toCharArray();
                char[] charS = s.str.toCharArray();

                int diff = 0;
                for (int i = 0; i < s.str.length(); i++) {
                    if (charT[i] != charS[i]) diff++;
                }

                if (dist[s.idx] == 0 && diff == 1) {
                    queue.add(s);
                    if (t.idx == -1) {
                        dist[s.idx] = 1;
                    }
                    else {
                        dist[s.idx] = dist[t.idx] + 1;
                    }
                }
            }
        }
    }
}