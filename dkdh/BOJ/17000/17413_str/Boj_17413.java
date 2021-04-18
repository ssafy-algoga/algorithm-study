package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_17413 {

	public static void main(String[] args) throws IOException {
		//입력을 받기 위한 BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//입력받은 문자열을 char형 배열에 저장
		char[] chars = br.readLine().toCharArray();
		int size=chars.length;
		
		//단어를 뒤집어 저장하기 위해 일시적으로 단어를 저장할 배열
		char[] word = new char[size];
		int wordLength = 0;
		
		//출력을 한 번에 하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		
		//tag 안인지 판단하는 flag
		boolean isTag = false;
		
		//메인 반복문. 한 문자씩 입력 받는 개념으로 접근
		for (int i = 0; i < size; i++) {
			
			if(chars[i]=='<') {
				isTag = true;
				
				//이 전에 뒤집어 저장해야 할 단어가 있으면 뒤집어서 저장
				if(wordLength!=0) {
					for (int j = 0; j < wordLength ; j++) {
						chars[i-j-1] = word[j];
					}
					wordLength = 0;
				}
			}
			else if(chars[i]=='>') {
				isTag = false;
				continue;
			}
			
			//태그 안이 아닐 때 == 단어 or 공백
			if(!isTag) {
				if(!(chars[i]==' ')) {
					word[wordLength++] = chars[i];
				}
				else {
					//공백이 등장한 경우 반드시 뒤집어 저장해야 할 단어가 있기 때문에 저장된 단어를 뒤집어 저장
					for (int j = 0; j < wordLength ; j++) {
						chars[i-j-1] = word[j];
					}
					wordLength = 0;

				}
			}
			
		}
		
		//문자열이 단어로 끝나는 경우. 마지막으로 뒤집어 저장할 단어가 있는지 확인하고 있으면 뒤집어 저장
		if(wordLength!=0) {
			for (int j = 0; j < wordLength ; j++) {
				chars[size-j-1] = word[j];
			}
			wordLength = 0;
		}
		
		//출력
		for (int i = 0; i < size; i++) {
			sb.append(chars[i]);
		}
		System.out.println(sb.toString());
	}

}
