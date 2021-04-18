import java.io.BufferedReader;
import java.io.InputStreamReader;

class boj_3613_str {
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine(); // 변수명
		StringBuilder out = new StringBuilder();
		
		if(str.matches("^[a-z][a-z_?]*[a-z]$") && !str.contains("__")) { // C++
			String[] word = str.split("_");
			
			out.append(word[0]);
			for (int i = 1; i < word.length; i++) { // 두번째 단어부터 첫글자 대문자로
				out.append( word[i].substring(0, 1).toUpperCase() + word[i].substring(1) );
			}
			
			System.out.println(out.toString());
			return;
		}
		
		if(str.matches("^[a-z][a-zA-Z]*$")) { // Java
			char[] chars = str.toCharArray();
			
			for (int i = 0; i < chars.length; i++) {
				if( Character.isUpperCase(chars[i]) ) { // 대문자 찾으면 (_소문자)로 변경
					out.append('_').append( Character.toLowerCase(chars[i]) );
				} else 
					out.append(chars[i]);
			}
			System.out.println(out.toString());
			return;
		}
		System.out.println("Error!");
	}
}	

