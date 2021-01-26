package com.ssafy.단어뒤집기2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static StringBuilder sbForStack;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("단어뒤집기2_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		sbForStack = new StringBuilder();

		char[] str = br.readLine().toCharArray();

		boolean isInBracket = false;
		boolean isInStack = true;

		for (int j = 0; j < str.length; j++) {
			if (str[j] == '<') {
				if (isInStack) {
					sb.append(sbForStack.reverse().toString());
					sbForStack.setLength(0);
					isInStack = false;
				}
				isInBracket = true;
				sb.append(str[j]);
			}

			else if (str[j] == '>') {
				sb.append(str[j]);
				isInBracket = false;
				isInStack = true;
			}

			else if (str[j] == ' ') {
				if (sbForStack.length() > 0) {
					sb.append(sbForStack.reverse().toString());
					sbForStack.setLength(0);
				}
				sb.append(' ');
			} else {
				if (isInBracket)
					sb.append(str[j]);
				if (isInStack) {
					sbForStack.append(str[j]);
					if (j == str.length - 1) {
						sb.append(sbForStack.reverse().toString());
						sbForStack.setLength(0);
					}
				}
			}

		}
		System.out.println(sb.toString());
	}
}