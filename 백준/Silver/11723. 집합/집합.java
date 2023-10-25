import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int M  = Integer.valueOf(br.readLine());
		
		Set<Integer> s = new HashSet<>();
		
		Set<Integer> full = new HashSet<>();
		for (int i = 1; i<= 20; i++) {
			full.add(i);
		}
		
		for (int i = 0; i < M; i++) {
			// 연산자
			String[] input = br.readLine().split(" ");
			String d = input[0];
			
			int val = 0;
			if (!d.equals("empty") && !d.equals("all")) {
				val = Integer.valueOf(input[1]);
			}
			
			// 연산 내용
			if (d.equals("add")) {
				s.add(val);
				
			} else if (d.equals("remove")) {
				s.remove(val);
				
			} else if (d.equals("check")) {
				
				if (s.contains(val)){
					bw.write("1\n");
				} else {
                    bw.write("0\n");
				}
				
			} else if (d.equals("toggle")) {
				if (s.contains(val)){
					s.remove(val);
				} else {
					s.add(val);
				}
				
			} else if (d.equals("all")) {
				for (int n = 1; n<= 20; n++) {
					s.add(n);
				}
				
			} else if (d.equals("empty")) {
				s = new HashSet<>();
			}
		}
		
		bw.flush();
		
	}
	
}
