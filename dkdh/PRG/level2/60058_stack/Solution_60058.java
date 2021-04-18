package prg;

public class Solution_60058 {

    public String solution(String p) {
    	// 빈 문자열이면 빈 문자열 반환
        if(p.equals(""))
            return "";
        
        // p가 올바른 괄호 문자열인지 검사
        if(isRight(p))
        	return p;
        
        int u_size = divideString(p);
        
        StringBuilder sb = new StringBuilder(p);
        
        String u = sb.substring(0, u_size);
        String v = sb.substring(u_size, p.length());
        
        if(isRight(u)==true) {
        	return u + solution(v);
        }
        
        StringBuilder ans = new StringBuilder("");
        ans.append("(");
        ans.append(solution(v));
        ans.append(")");
              
        String u2 = u.substring(1, u.length()-1);

        for (int i = 0, size=u2.length(); i < size; i++) {
			if(u2.charAt(i)=='(')
				ans.append(")");
			else
				ans.append("(");
		}
        
        String answer = ans.toString();
        
        return answer;
    }
    
    // 균형잡힌 괄호 문자열을 입력받아 올바른 괄호 문자열인지 boolean 반환하는 함수
    public boolean isRight(String n) {
    	char[] chars = n.toCharArray();
    	
    	int size = chars.length;
    	
    	int cnt = 0;
    	
    	for (int i = 0; i < size; i++) {
			if(chars[i]=='(')
				cnt++;
			else
				cnt--;
			if(cnt<0)
				return false;
		}
    	
    	return true;
    }
	
    // 문자열을 입력받아 최소단위의 균형잡힌 괄호 문자열 크기 반환
    public int divideString(String p) {
    	char[] chars = p.toCharArray();
    	
    	int size = chars.length;
    	
    	int cnt = 0;
    	
    	for (int i = 0; i < size; i++) {
			if(chars[i]=='(')
				cnt++;
			else
				cnt--;
			if(cnt==0)
				return i+1;
		}
    	return 0;
    }
}
