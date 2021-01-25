package _1월_5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class 문자열뒤집기2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String sentence = br.readLine();
		StringBuilder sb = new StringBuilder();
		
		ArrayList<String> ans = new ArrayList<String>();
		Stack<Character> word = new Stack<Character>();
		
		int start =0;
		
		while(start < sentence.length()) {
			int end = start;
			
			if(sentence.charAt(start)=='<') {
				while(sentence.charAt(end) !='>') {
					end++;
				}
				ans.add(sentence.substring(start,end+1));
				start = end+1;
			}else if(sentence.charAt(end) ==' ') {
				ans.add(" ");
				end++;
				start = end;
			}
			else {
				while(end < sentence.length() && sentence.charAt(end) !='<' && sentence.charAt(end) !=' ') {
					word.push(sentence.charAt(end++));
				}

				while(!word.empty()) {
					sb.append(word.pop());
				}
				ans.add(sb.toString());
				sb.delete(0,sb.length());
				
				start = end;
				
			}
		}
		
		for (String s : ans) {
			System.out.print(s);
		}
	}
} 
