import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        // 정보 변환해서 저장
        PriorityQueue<Music> pq = new PriorityQueue<>();
        for (int i = 0; i < musicinfos.length; i++) {
            String[] split = musicinfos[i].split(",");

            int st = timeToInt(split[0]);
            int ed = timeToInt(split[1]);
            int len = ed - st;

            String title = split[2];

            // 코드 길이 변환
            List<String> code = new ArrayList<>();
            List<String> origin = toCodeList(split[3]);
            for (int j = 0; j < len; j++) {
                code.add(origin.get(j % origin.size()));
            }

            pq.add(new Music(i, st, ed, len, title, code));
        }

        List<String> mCode = toCodeList(m);
        while (!pq.isEmpty()) {
            Music music = pq.poll();
            List<String> t = music.code; // 비교할 코드 리스트
            for (int st = 0; st <= t.size() - mCode.size(); st++) {
                boolean flag = true;
                for (int i = 0; i < mCode.size(); i++) {
                    if (!t.get(st + i).equals(mCode.get(i))) {
                        flag = false;
                        break;
                    }
                }
                if (flag) return music.title;
            }

        }

        return "(None)";
    }
    
    static int timeToInt(String str) {
        String[] time = str.split(":");
        int hour = Integer.parseInt(time[0]);
        int min = Integer.parseInt(time[1]);
        return hour * 60 + min;
    }

    static List<String> toCodeList(String str) {
        List<String> arr = new ArrayList<>();
        int idx = 0;
        while (idx < str.length()) {
            if (idx < str.length()-1 && str.charAt(idx+1) == '#') {
                arr.add(str.substring(idx, idx+2));
                idx += 2;
            } else {
                arr.add(str.substring(idx, ++idx));
            }
        }
        return arr;
    }
}

class Music implements Comparable<Music>{
    int idx; // 입력순서
    int start; // 분 기준
    int end; // 분 기준
    int length; // 재생시간
    String title;
    List<String> code;

    public Music(int idx, int start, int end, int length, String title, List<String> code) {
        this.idx = idx;
        this.start = start;
        this.end = end;
        this.length = length;
        this.title = title;
        this.code = code;
    }

    @Override
    public int compareTo(Music o) {
        if (o.length == this.length) {
            return this.idx - o.idx;
        }
        return o.length - this.length;
    }

    @Override
    public String toString() {
        return "Music{" +
                "idx=" + idx +
                ", start=" + start +
                ", end=" + end +
                ", length=" + length +
                ", title='" + title + '\'' +
                ", code=" + code +
                '}';
    }
}