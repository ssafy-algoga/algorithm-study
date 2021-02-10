import java.util.Arrays;
import java.util.Scanner;

public class boj_17413 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		String[] ch = str.split("");

		int mode = 0; // 0이면 반대로 출력, 1이면 그대로 출력
		
		StringBuffer word = new StringBuffer();
		for (int i = 0; i < ch.length; i++) {
			if(ch[i].equals("<")) {
				mode = 1;
				System.out.print(word.reverse().toString()+ch[i]);
				word = new StringBuffer();
			}
			else if(ch[i].equals(" ")) {
				
				if(mode == 0) { 
					System.out.print(word.reverse().toString());
				}
				else {
					System.out.print(word);
				}
				word = new StringBuffer();
				System.out.print(ch[i]);
			}
			else if(ch[i].equals(">")) {
				System.out.print(word.toString()+ch[i]);
				word = new StringBuffer();
				mode = 0; 
			}
			else {
				word.append(ch[i]); // 아무것도 아니면 word 붙이기
			}
		}
		System.out.print(word.reverse().toString());

	}

}
