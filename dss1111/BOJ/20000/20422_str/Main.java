import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
	static HashMap <Character,Character> map = new HashMap<>();
	static char[] input;
	static StringBuilder sb1 = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		makeMap();
		input = br.readLine().toCharArray();
		System.out.println(check());
	}
	static String check() {
		int size =input.length;
		for(int i=0;i<size;i++) {
			if(size%2==1 && i == size/2) {
				sb1.append(input[i]);
				continue;
			}
			char front = getQu(input[i]);
			if(front=='-') {
				return "-1";
			}
			char back = getQu(input[size-i-1]);
			sb1.append(back);
		}
		return sb1.toString();
	}
	static char getQu(char a) {
		if(map.containsKey(a)) {
			return map.get(a);
		}
		else 
			return '-';
	}
	static void makeMap() {  //퀼린드롬표
		map.put('A', 'A'); map.put('a', 'A');
		map.put('B', 'd'); map.put('b', 'd');
		map.put('D', 'b'); map.put('d', 'b');
		map.put('E', '3'); map.put('e', '3');
		map.put('H', 'H'); map.put('h', 'H');
		map.put('I', 'I'); map.put('i', 'I');
		map.put('L', 'l'); map.put('l', 'l');
		map.put('M', 'M'); map.put('m', 'M');
		map.put('N', 'n'); map.put('n', 'n');
		map.put('O', 'O'); map.put('o', 'O');
		map.put('P', 'q'); map.put('p', 'q');
		map.put('Q', 'p'); map.put('q', 'p');
		map.put('R', '7'); map.put('r', '7');
		map.put('S', '2'); map.put('s', '2');
		map.put('T', 'T'); map.put('t', 'T');
		map.put('U', 'U'); map.put('u', 'U');
		map.put('V', 'V'); map.put('v', 'V');
		map.put('W', 'W'); map.put('w', 'W');
		map.put('X', 'X'); map.put('x', 'X');
		map.put('Y', 'Y'); map.put('y', 'Y');
		map.put('Z', '5'); map.put('z', '5');
		map.put('0', '0');
		map.put('1', '1');
		map.put('2', 'S');
		map.put('3', 'E');
		map.put('5', 'Z');
		map.put('7', 'r');
		map.put('8', '8');
	}
}
