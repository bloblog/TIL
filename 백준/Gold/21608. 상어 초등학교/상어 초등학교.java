import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        Student[] std = new Student[N*N];
        for (int i = 0; i< N*N; i++) {
            st = new StringTokenizer(br.readLine());
            std[i] = new Student(Integer.parseInt(st.nextToken()), new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
        } // 인풋 받기 끝

        arr = new int[N][N]; // 자리 결과
        for (Student s : std) {
            Seat[][] res = getSeatInfo(s, arr);
            setSeat(s, res);
        }

        // 다 앉히고 만족도 조사
        int sum = 0;
        for (Student s : std) {
            int count = 0;
            for (int d = 0; d < 4; d++) {
                int nr = s.getPos()[0] + dr[d];
                int nc = s.getPos()[1] + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >=N) continue;

                // 주변에 좋아하는 친구 있는 경우
                for (int a : s.like) {
                    if (a == arr[nr][nc]) {
                        count++;
                    }
                }
            }

            if (count == 1) sum += 1;
            else if (count == 2) sum += 10;
            else if (count == 3) sum += 100;
            else if (count == 4) sum += 1000;
        }

        System.out.println(sum);
    }

    static int[][] arr;
    // 가장 적합한 자리에 앉히는 메서드
    static void setSeat(Student s, Seat[][] res) {

        // 좋아하는 학생 수 최대 찾기
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (res[i][j].cnt > max) max = res[i][j].cnt;
            }
        }

        // 자리 돌면서 앉힐 자리 찾자
        int r = -1, c = -1;
        int max_b = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (arr[i][j] != 0) continue;

                if (res[i][j].cnt == max) {
                    if (res[i][j].blank > max_b) {
                        max_b = res[i][j].blank;
                        r = i;
                        c = j;
                    }
                }
            }
        }
        s.setPos(new int[] {r, c});
        arr[r][c] = s.num;
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    // 학생 정보와 자리 현황 받아서 각 자리 정보 반환
    static Seat[][] getSeatInfo(Student s, int[][] arr) {
        Seat[][] seats = new Seat[N][N]; // 각 자리 정보

        for (int i = 0; i < N; i++) {
            for (int j = 0; j< N; j++) {
                seats[i][j] = new Seat(0,0);
                // 자리 이미 누가 앉았으면 넘어가
                if (arr[i][j] != 0) continue;

                for (int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >=N) continue;

                    // 비어있는 경우
                    if (arr[nr][nc] == 0) {
                        seats[i][j].blank++;
                    }

                    // 주변에 좋아하는 친구 있는 경우
                    for (int a : s.like) {
                        if (a == arr[nr][nc]) {
                            seats[i][j].cnt++;
                        }
                    }
                }
            }
        }
        return seats;
    }
}

class Student {
    int num;
    int[] like;
    int[] pos = new int[] {0,0};

    public Student(int num, int[] like) {
        this.num = num;
        this.like = like;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }
}

class Seat {
    int cnt; // 좋아하는 친구 수
    int blank; // 인접한 칸 중 비어있는 칸 수

    public Seat(int cnt, int blank) {
        this.cnt = cnt;
        this.blank = blank;
    }
}