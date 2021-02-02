import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack <Character> stack = new Stack<>();
		String input=br.readLine();
		String result="";
		char now;
		for(int i=0;i<input.length();i++){
			now=input.charAt(i);
			if(now=='<'){	// '<' 면 스택을 통해 reverse입력  이후 '>'나올때까지 정상적으로 입력
				while(!stack.empty()){
					result+=stack.pop();
				}
				result+='<';
				while(!(input.charAt(++i)=='>')) {
					result+=input.charAt(i);
				}
				result+='>';
			}
			else if(now==' ') {	// ' ' 면 스택을 통해 지금까지 쌓였던 char들 reverse 입력
				while(!stack.empty()){
					result+=stack.pop();
				}
				result+=" ";
			}					// 그냥 문자들 (역방향)
			else{
				stack.push(now);
			}
		}
		while(!stack.empty()){	// 문자의 끝. 지금까지 쌓였던거 reverse입력
			result+=stack.pop();
		}
		System.out.println(result);
	}
}
