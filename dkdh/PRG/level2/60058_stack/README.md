# 60058번 괄호 변환
[문제 보러가기](https://programmers.co.kr/learn/courses/30/lessons/60058)

## 🅰 설계
제시된 알고리즘 그대로 짰으므로, 알고리즘 단계의 몇 가지만 설명드리고자 합니다.   
문자열을 만드는 데 있어서는 StringBuilder를 활용했습니다.

#### 2단계
문자열을 더 이상 분리할 수 없는 '균형잡힌 괄호 문자열' u와 v로 나누는 데 있어서는   
더 이상 분리할 수 없다라는 말의 의미는 문자열을 앞이나 뒤에서(무관이지만 편하게 앞에서부터 읽는 걸로 했습니다) 순서대로 읽을 때 최초로 나타나는 균형잡힌 괄호 문자열과 같기 때문에   
문자열을 앞에서부터 읽을 때 '(' 와 ')'의 쌍이 맞아지는(개수가 동일해지는) 첫 문자열로 해석하였습니다.

코드 이해를 위해 함수로 빼서 구현했습니다.   
문자열을 앞에서부터 읽는다고 정하였으므로, 입력 문자열의 맨 앞에서 어디까지 u인지를 말해주는 size를 반환하도록 구현했습니다.

```jsx
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
```

이를 기반으로 String의 subString 메소드를 이용해 u와 v를 나누었습니다.
```jsx
String u = sb.substring(0, u_size);
String v = sb.substring(u_size, p.length());
```

#### 3단계
문자열 u가 올바른 괄호 문자열인지 판단은, 이미 입력 문자열이 균형잡힌 괄호 문자열일 때 판단하게 되므로   
괄호가 올바른 순서로 나오는지만 판단하면 됩니다.

문자열을 순서대로 읽었을 때, '('의 개수가 ')'보다 많거나 같은 상태를 유지해야 올바른 괄호 문자열이 됩니다.   
')'가 '('보다 많아지는 순간 해당 ')'는 짝이 없는 것이기 때문에 올바른 괄호 문자열이 될 수 없습니다.   
개수가 동일함은 이미 보장되었기 때문에 문자열을 읽으면서 '('의 개수가 ')'보다 많거나 같은 상태를 유지하면 문자열을 모두 읽었을 때 무조건 올바른 괄호 문자열이 됩니다.

```jsx
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
```

#### 4-4단계
u의 첫 번째와 마지막 문자를 제거하는 데에는 다시 한 번 String의 subString을 이용했습니다.
```jsx
String u2 = u.substring(1, u.length()-1);
```

후에는 반복문을 돌리며 String의 charAt을 이용해 u2의 문자를 검사하고, 이와 반대되는 괄호 문자를 넣었습니다.
```jsx
for (int i = 0, size=u2.length(); i < size; i++) {
			if(u2.charAt(i)=='(')
				ans.append(")");
			else
				ans.append("(");
		}
```

## ✅ 후기
#### 새롭게 알게되거나 공유해서 알게된 점
String과 StringBuilder의 subString 메소드를 알게 되었습니다 앞으로도 자주 쓸 것 같네욤   
프로그래머스 문제는 뭔가 재밌네요

문자열의 문자마다 검사하거나 반복문 돌릴 때 charAt을 사용하는 방법이랑 toCharArray를 사용하는 방법이 있는데 charAt이 더 편한 것 같아요
#### 고생한 점
