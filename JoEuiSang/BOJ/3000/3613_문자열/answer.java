import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringBuilder sb = new StringBuilder();

		// 어떤 언어인지 체크
		if (checkLang(input) == 'c') { // c++ 언어이면
			//맨앞, 맨뒤에 '_'이 오면 에러
			if(input.charAt(0)=='_' || input.charAt(input.length()-1)=='_') {
				System.out.println("Error!");
				return;
			}
			
			//대문자가있으면 에러 || '_'가 연속으로 나오면 에러
			int cnt=0;
			for(int i=0; i<input.length(); i++) {
				if(Character.isUpperCase(input.charAt(i))) {
					System.out.println("Error!");
					return;
				}
				if(input.charAt(i)=='_') {
					cnt++;
				}else {
					cnt=0;
				}
				
				if(cnt==2) {
					System.out.println("Error!");
					return;
				}
			}
			
			String[] word = input.split("_");// 각 단어를 문자열 배열에 저장
			for (String s : word) {
				char c = s.charAt(0);
				sb.append(Character.toUpperCase(c)).append(s.substring(1));
			}
			sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));	//첫글자 소문자로
		} else { // java이면
			if (Character.isUpperCase(input.charAt(0))) { // 첫글자 대문자이면 에러
				System.out.println("Error!");
				return;
			} 
			
			int startIdx = 0;	//단어별 시작할 위치를 가질 변수
			for (int i = 0; i < input.length(); i++) {
				char c = input.charAt(i);

                //현재 문자가 대문자이면 현재문자 앞까지 substring처리
				if (Character.isUpperCase(c)) {
					if(startIdx==0) {
						sb.append(input.substring(startIdx, i) + "_");
						
						startIdx = i;
					}else {
						char lowC = Character.toLowerCase(input.charAt(startIdx));
						sb.append(lowC).append(input.substring(startIdx + 1, i) + "_");
						startIdx = i;
					}
				}
			}
			char lowC = Character.toLowerCase(input.charAt(startIdx));
			sb.append(lowC).append(input.substring(startIdx + 1));
			sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		}
		System.out.println(sb);
	}

	private static char checkLang(String input) {
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '_')
				return 'c';
		}
		return 'j';
	}
}