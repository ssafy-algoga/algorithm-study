# 60058번 괄호변환
[문제 보러가기](https://programmers.co.kr/learn/courses/30/lessons/60058)

## 🅰 설계
```
import java.util.Stack;
class Solution {
    Stack<Character> stack1 = new Stack<>(); //정방향
    Stack<Character> stack2 = new Stack<>(); //역방향
    public String solution(String p) {
        String answer = "";
        char now;
        for(int i=0;i<p.length();i++)
        {
            now=p.charAt(i);
            if(now=='(')
            {
                if(!stack2.empty()){
                    answer+=')';
                    stack2.pop();
                }
                else{
                    answer+='(';
                    stack1.push('(');
                }
            }
            if(now==')')
            {
                if(!stack1.empty()){
                    answer+=')';
                    stack1.pop();
                }
                else{
                    answer+='(';
                    stack2.push(')');
                }
            }
        }
        return answer;
    }
}
```
처음에는 단순하게 잘못된 괄호를 바로잡는 줄 알았습니다.   
맞는 괄호는 그대로두고 잘못된괄호는 방향을 바꾸어주는 것으로 코드를 작성했습니다 ~~삽질1~~
![image](https://user-images.githubusercontent.com/37682970/105850899-b2843f80-6025-11eb-8767-7bfa5b2edccc.png)  
설계이후  
```
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
class Solution2 {
	Queue<Character> queue=new LinkedList<>();
    Stack<Character> stack = new Stack<>();
    public String solution(String p) {
        String answer = "";
        char now;
        int pair=0;
        int count=0;
        for(int i=0;i<p.length();i++){
        	now=p.charAt(i);
        	if(stack.isEmpty()&&queue.isEmpty()) {
        		if(now=='(') {
        			queue.add(now);
        			pair++;
        		}
        		if(now==')') {
        			stack.push(now);
        			pair--;
        		}
        	}
        	else {
        		if(pair>0) {
       				queue.add(now);
        			if(now=='(') {
        				pair++;
        			}
        			if(now==')') {
        				pair--;
        			}
        		}
        		if(pair<0) {
        			stack.push(now);
        			if(now=='(') {
        				pair++;
        			}
        			if(now==')') {
        				pair--;
        			}
        		}
        	}
        	if(pair==0) {
        		while(!queue.isEmpty()) {
        			answer+=queue.poll();
        		}
        		String reverse="";
        		while(!stack.isEmpty()) {
        			reverse+=stack.pop();
        		}
        		if(reverse.length()>0)
        		{
        			count++;
        			reverse=reverse.substring(1, reverse.length()-1);
        		}
        		answer+=reverse;
        	}
        }
        for(int i=0;i<count;i++)
        {
        	answer="("+answer+")";
        }
        return answer;
    }
}
```
재귀를 사용해서 짜보았으나. 맨 바깥에 괄호를 쳐야한다는 규칙을 다짜고나서야 파악했고   
올바르지 않은 괄호를 처리하려고 보니 u,v로 나누어 코드를 짜지 않아서 너무 복잡해졌습니다 ~~삽질2~~  
```
class Solution {
    public String solution(String p) {
    	return getResult(p);	//바깥에 문자를 추가하기 위해 static 호출
    }
    static String getResult(String p) {	
    	if(p.length()==0)	
    		return "";
    	String u,v;
    	int cut = cutIndex(p);				// 균형을 이루는 인덱스 카운트 , 이것을 기준으로 자름
    	if(p.charAt(0)=='('){				// '('로 시작하면 올바른 괄호
    		u=p.substring(0,cut);			// u에는 올바른 괄호부분
    		v=p.substring(cut,p.length());  // 나머지
    		return u+getResult(v);			// 결과만 얻고 나머지 재귀돌려버리기
    	}
    	else {								// 올바르지 않은 괄호
    		u=p.substring(1,cut-1);			// u의 맨앞과 맨뒤빼고 
    		v=p.substring(cut,p.length());	// 나머지
    		String reverse="";				// 원래 StringBuilder 쓰려했는데 뒤집는게 아니라 괄호를 변경하는 것
    		for(int i=0;i<u.length();i++) {	
    			if(u.charAt(i)==')')		// ')'  -> '('
    				reverse+='(';
    			else						// '('	-> ')'
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
    		else {							// ')' 면 --
    			count--;
    		}
    	}
    	return p.length();					// 자를 위치 리턴
    }
}
```
완성된 코드입니다. static이랑 cutIndex같은 것은 이전의 코드에서도 같은 방식으로 처리했었습니다.    
괄호를 생각해보면 무조건 균형을 이루니까 '('로 시작하면 맞는괄호고 아니면 올바르지 않은 괄호더라구요!  
아 그리고 스택을써서 문자열을 뒤집는거 자체가 문제파악을 잘못했던거였습니다.  
순서를 뒤집는게 아니라 ( -> ) 이렇게 바꾸는 거라는걸 늦게나마 파악했습니다.   
(https://keepgoing0328.tistory.com/entry/2020%EC%B9%B4%EC%B9%B4%EC%98%A4-%EA%B3%B5%EC%B1%84-%EA%B4%84%ED%98%B8-%EB%B3%80%ED%99%98-%EC%9E%90%EB%B0%94)  
저랑 엄청 비슷하게 하신분이 있더라구요 참고하셔도 좋을 것 같아요
## ✅ 후기
### 새롭게 알게되거나 공유해서 알게된 점
stack대신 StringBuilder를 사용해서 뒤집는 것을 연주님 코드를 보고 알게되었습니다. 문자열 뒤집기를 사용하지 않으면서 사용하는 코드는 지웠습니다.  
내 생각대로 풀지말고 주어진 조건을 잘읽는 습관을 들여야겠습니다.  
### 고생한 점
문제에서 주어진 조건이 별로 명확하지 않은것 같기도 하고
한문제 푸는데 5시간 걸렸습니다...
