package algoga.week2;

import java.io.*;
import java.util.*;

public class 퀼린드롬 {
	static BufferedReader br;
	static StringTokenizer st;

	static HashMap<Character,Character> mp = new HashMap();
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		mp.put('A', 'A'); mp.put('a', 'A');
		
		mp.put('B', 'd'); mp.put('b', 'd');
		
		mp.put('C', 'C'); mp.put('c', 'C');
		
		mp.put('D', 'b'); mp.put('d', 'b');
		
		mp.put('E', '3'); mp.put('e', '3');
		
		mp.put('F', 'F'); mp.put('f', 'F');
		
		mp.put('G', 'G'); mp.put('g', 'G');
		
		mp.put('H', 'H'); mp.put('h', 'H');
		
		mp.put('I', 'I'); mp.put('i', 'I');
		
		mp.put('J', 'J'); mp.put('j', 'J');
		
		mp.put('K', 'K'); mp.put('k', 'K');
		
		mp.put('L', 'l'); mp.put('l', 'l');
		
		mp.put('M', 'M'); mp.put('m', 'M');
		
		mp.put('N', 'n'); mp.put('n', 'n');
		
		mp.put('O', 'O'); mp.put('o', 'O');
		
		mp.put('P', 'q'); mp.put('p', 'q');
		
		mp.put('Q', 'p'); mp.put('q', 'p');
		
		mp.put('R', '7'); mp.put('r', '7');
		
		mp.put('S', '2'); mp.put('s', '2');
		
		mp.put('T', 'T'); mp.put('t', 'T');

		mp.put('U', 'U'); mp.put('u', 'U');

		mp.put('V', 'V'); mp.put('v', 'V');

		mp.put('W', 'W'); mp.put('w', 'W');

		mp.put('X', 'X'); mp.put('x', 'X');

		mp.put('Y', 'Y'); mp.put('y', 'Y');

		mp.put('Z', '5'); mp.put('z', '5');
		
		mp.put('0', '0');
		mp.put('1', '1');
		mp.put('2', 'S');
		mp.put('3', 'E');
		mp.put('4', '4');
		mp.put('5', 'Z');
		mp.put('6', '6');
		mp.put('7', 'r');
		mp.put('8', '8');
		mp.put('9', '9');
		
		System.out.println(solve());
	}
	
	static String solve() throws IOException {
		String in = br.readLine();
		StringBuilder sb = new StringBuilder();
		int size = in.length();
		for(int i=0;i<size;i++) {
			if((size&1) == 1 && i == size/2) {
				sb.append(in.charAt(i));
				continue;
			}
			char fC = mp.get(in.charAt(i));
			char bC = mp.get(in.charAt(size-i-1));
			fC = mp.get(fC);
			if(fC != bC) return "-1";
			sb.append(bC);
		}
		
		return sb.toString();
	}
}
