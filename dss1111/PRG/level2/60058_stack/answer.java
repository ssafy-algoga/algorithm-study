class Solution {
    public String solution(String p) {
    	return getResult(p);	//바깥에 문자를 추가하기 위해 static 호출
    }
    static String getResult(String p) {	
    	if(p.length()==0)	
    		return "";
    	String u,v;
    	int cut = cutIndex(p);			        // 균형을 이루는 인덱스 카운트 , 이것을 기준으로 자름
    	if(p.charAt(0)=='('){				// '('로 시작하면 올바른 괄호
    		u=p.substring(0,cut);			// u에는 올바른 괄호부분
    		v=p.substring(cut,p.length());          // 나머지
    		return u+getResult(v);			// 결과만 얻고 나머지 재귀돌려버리기
    	}
    	else {					// 올바르지 않은 괄호
    		u=p.substring(1,cut-1);			// u의 맨앞과 맨뒤빼고 
    		v=p.substring(cut,p.length());	// 나머지
    		String reverse="";				// 원래 StringBuilder 쓰려했는데 뒤집는게 아니라 괄호를 변경하는 것
    		for(int i=0;i<u.length();i++) {	
    			if(u.charAt(i)==')')		// ')'  -> '('
    				reverse+='(';
    			else			       // '('	-> ')'
    				reverse+=')';			
    		}
    		return "("+getResult(v)+")" + reverse;	// 문제 설명이 좀 이상한데 결과돌려보니 이렇게 하는게 맞음
    	}
    }
    static int cutIndex(String p) {			//자를 부분 찾기
    	int count=0;						
    	for(int i=0;i<p.length();i++) {
    		if(count==0 && i!=0){			//맨처음은  count가 0이니 제외
    			return i;					//count가 0이면 균형
    		}
    		if(p.charAt(i)=='(') {			// '(' 면 ++
    			count++;
    		}
    		else {				    // ')' 면 --
    			count--;
    		}
    	}
    	return p.length();					// 자를 위치 리턴
    }
}
