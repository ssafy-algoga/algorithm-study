package com.ssafy.단어뒤집기2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br;
	static StringBuilder sb;
	static StringBuilder sbForReversed;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("단어뒤집기2_input.txt"));
		
		br = new BufferedReader(new InputStreamReader(System.in));
		char[] str = br.readLine().toCharArray();
		
		sb = new StringBuilder();
		sbForReversed = new StringBuilder();
		
		boolean isInTag = false;
		boolean isInWord = true;

		for (int j = 0; j < str.length; j++) {
			if (str[j] == '<') {
				if (isInWord) {
					sb.append(sbForReversed.reverse());
					sbForReversed.setLength(0);
					isInWord = false;
				}
				isInTag = true;
				sb.append(str[j]);
			}

			else if (str[j] == '>') {
				sb.append(str[j]);
				isInTag = false;
				isInWord = true;
			}

			else if (str[j] == ' ') {
				if (sbForReversed.length() > 0) {
					sb.append(sbForReversed.reverse());
					sbForReversed.setLength(0);
				}
				sb.append(' ');
			} else {
				if (isInTag)
					sb.append(str[j]);
				if (isInWord) {
					sbForReversed.append(str[j]);
					if (j == str.length - 1) {
						sb.append(sbForReversed.reverse());
						sbForReversed.setLength(0);
					}
				}
			}

		}
		System.out.println(sb);
	}
}
