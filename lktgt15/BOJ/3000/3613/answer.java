import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		String in = br.readLine();
		
		int underbar_streak = 0; // 연속해서 언더바가 나오는 경우
		boolean java = false,c = false,error = false; // java인 경우, C++인 경우, 조건에 맞지 않는 경우
		for(int i=0;i<in.length();i++) {
			char cur = in.charAt(i);
			if(cur == '_') { // _를 사용하면 C++형식일 가능성이 있음
				c = true;
				if(i == in.length()-1 || i == 0 || underbar_streak == 1) error = true; // _를 사용하면서 C++형식에 위배됨
				underbar_streak++; // 연속해서 나오는 _ 개수를 위해 값을 증가시킴
			}
			else if(cur >= 'A' && cur <= 'Z') { // 대문자를 사용하면 Java형식일 가능성이 있음
				java = true;
				if(i == 0) error = true; // 첫 문자가 대문자면 Java형식에 위배됨
				underbar_streak = 0; // _의 개수 초기화
			}
			else underbar_streak = 0; // _의 개수 초기화
		}
		if(java && c || error) {
			System.out.println("Error!");
			return;
		}
		if(java) System.out.println(toC(in));
		else System.out.println(toJava(in));
	}
	
	static String toC(String in) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<in.length();i++) {
			char cur = in.charAt(i);
			if(cur >= 'A' && cur <= 'Z') { // 대문자인 경우
				sb.append('_').append(Character.toLowerCase(cur)); // _를 추가하고 lowercase로 변환
			}
			else sb.append(cur);
		}
		return sb.toString();
	}
	
	static String toJava(String in) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<in.length();i++) {
			char cur = in.charAt(i);
			if(cur == '_') { // _인 경우
				sb.append(Character.toUpperCase(in.charAt(++i))); // _는 무시하고 다음 문자를 uppercase로 변환
			}
			else sb.append(cur);
		}
		return sb.toString();
	}
}