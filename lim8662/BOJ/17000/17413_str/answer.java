import java.io.*;
import java.util.*;

class boj_17413 {
	// 단어 뒤집기 2 silver3
	
	// baekjoon online judge
	// <ab cd>ef gh<ij kl>
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder str = new StringBuilder(br.readLine()); // 입력 문자열 저장
		StringBuilder ans = new StringBuilder(); // 출력 문자열 저장
		int cnt = 0;
		
		//태그와 문자열을 분리하여 저장할 배열
		for (char c : str.toString().toCharArray())
			if (c == '<')
				cnt++;
		if(cnt == 0) cnt = 1; // 태그가 없는 경우 
		
		String[] sub = new String[cnt * 2];

		cnt = 0;
		// 맨 처음부터 탐색 및 분리
		while (str.length() > 0) {
			// 태그면
			if (str.charAt(0) == '<') {
				int close = str.indexOf(">");

				// 분리
				sub[cnt++] = str.substring(0, close + 1);
				str.delete(0, close + 1);
			}
			// 태그가 아니면
			else {
				int open = str.indexOf("<");
				if (open > 0) { // 뒤에 태그가 있으면
					// 분리
					sub[cnt++] = str.substring(0, open);
					str.delete(0, open);
				
				} else { // 단어만 있으면
					sub[cnt++] = str.toString();
					str.delete(0, str.length());
				}
			}
		}

		for (int i = 0; i < cnt; i++) {
			// 태그면 append
			if (sub[i].charAt(0) == '<') {
				ans.append(sub[i]);
			}
			// 단어들이면
			else {
				// 단어 분리
				String[] word = sub[i].split(" ");

				// 뒤집어 append
				for (String w : word) {
					StringBuilder rvs = new StringBuilder(w);
					rvs.reverse();
					ans.append(rvs).append(" ");
				}
				// 마지막 공백 제거
				ans.deleteCharAt(ans.length() - 1);
			}
		}
		System.out.println(ans.toString());
	}

}
