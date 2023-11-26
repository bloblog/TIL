import java.io.*;
import java.util.*;

public class Main {
	static List<int[]> list[]; // 인접 리스트
	static int N, D, res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// String -> Integer 변환 메서드 stoi
		N=stoi(st.nextToken());
		D=stoi(st.nextToken());
		
		list = new ArrayList[10001];
		for(int i=0; i<list.length; i++) {
			list[i]=new ArrayList<>();
		}
		
		res = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = stoi(st.nextToken());
			int b = stoi(st.nextToken());
			int c = stoi(st.nextToken());
			list[a].add(new int[] {b,c});
		}
		// 인풋 받기 끝
		
		int dp[] = new int[10001];
		// 초기화 = 지름길 하나도 안 쓴 경우의 거리
		init(dp);
		
		for(int i=0; i<=D; i++) {
			
			// dp[i-1] = 지름길 선택 및 갱신된 값
			// 그냥 갔을 때랑 갱신된 값 +1 이랑 비교해서 넣기
			if(i!=0)
				dp[i]=Math.min(dp[i-1]+1, dp[i]);
			
			// 지름길이 있는 경우
			if(list[i].size()>0) {
				for(int tmp[]: list[i]) {
					int val = tmp[1]; // 거리
					int end = tmp[0]; // 도착점
					if(dp[end] > dp[i]+val) {
						dp[end] = dp[i]+val;
					}
				} // for each 돌면서 같은 출발점 중 가장 빠른 지름길 찾는다
			}
			
		}
		System.out.println(dp[D]);
	}
	
	static void init(int arr[]) {
		for(int i=0; i<arr.length; i++) {
			arr[i]=i;
		}
	}
	static int stoi(String s) {
		return Integer.valueOf(s);
	}
}