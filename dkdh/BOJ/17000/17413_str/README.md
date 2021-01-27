# 17413번 단어 뒤집기2
[문제 보러가기](https://www.acmicpc.net/problem/17413)

## 🅰 설계
태그 문자 <> 쌍과 순서가 문제에 의해 보장되어 있기 때문에 태그 문자를 확인하고 태그는 그대로, 단어는 뒤집어서 출력하는 방식으로 생각했습니다.

단어를 뒤집을 때 사용한 방식은 단어를 인식해 새로운 배열에 저장해두고, 이를 거꾸로 해서 저장하거나 출력하는 방식을 고려했습니다.

전체적인 방식은 문자열을 입력받아 char형 배열에 저장해두고, 한 문자씩 확인하면서 isTag 플래그를 이용해 해당 문자가 태그에 해당하는지 아닌지 구분해 태그에 해당한다면 그대로 두고, 단어는 거꾸로 해서 다시 해당 배열에 저장하는 식으로 했습니다.

마지막으로 char형 배열을 반복문을 돌며 StringBuilder에 쌓아 출력하는 방식입니다.

### 구현
입력은 BufferedReader를 이용했습니다.   
```jsx
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
```

단어마다 거꾸로 뒤집어서 저장해줄 것이기 때문에 단어를 일시적으로 저장할 배열을 하나 선언해줬습니다. 최대 입력받은 문자열의 크기만한 단어가 들어올 수 있기 때문에 입력 문자열의 길이인 size로 배열을 생성했습니다.   
wordLength 변수를 통해 단어의 길이를 저장하고, 이 값이 0인지를 확인함으로써 뒤집어서 저장해야 할 단어가 있는지도 판단합니다.
```jsx
char[] word = new char[size];
int wordLength = 0;
```

isTag 변수를 통해 각 문자가 태그에 속하는지 그렇지 않은지를 판단합니다. 태그에 속하면 아무것도 하지 않고, 속하지 않는다면 단어 혹은 공백이므로 문자라면 word 배열에 저장하고, 공백이라면 이 전에 단어가 나온 것이므로 word 배열에 저장된 단어를 뒤집어 원래 문자열을 저장한 배열에 덮어쓰고, 새로운 단어를 저장합니다.
```jsx
boolean isTag = false;
```

### 코드
```jsx
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_17413 {

	public static void main(String[] args) throws IOException {
		//입력을 받기 위한 BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//입력받은 문자열을 char형 배열에 저장
		char[] chars = br.readLine().toCharArray();
		int size=chars.length;
		
		//단어를 뒤집어 저장하기 위해 일시적으로 단어를 저장할 배열
		char[] word = new char[size];
		int wordLength = 0;
		
		//출력을 한 번에 하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		
		//tag 안인지 판단하는 flag
		boolean isTag = false;
		
		//메인 반복문. 한 문자씩 입력 받는 개념으로 접근
		for (int i = 0; i < size; i++) {
			
			if(chars[i]=='<') {
				isTag = true;
				
				//이 전에 뒤집어 저장해야 할 단어가 있으면 뒤집어서 저장
				if(wordLength!=0) {
					for (int j = 0; j < wordLength ; j++) {
						chars[i-j-1] = word[j];
					}
					wordLength = 0;
				}
			}
			else if(chars[i]=='>') {
				isTag = false;
				continue;
			}
			
			//태그 안이 아닐 때 == 단어 or 공백
			if(!isTag) {
				if(!(chars[i]==' ')) {
					word[wordLength++] = chars[i];
				}
				else {
					//공백이 등장한 경우 반드시 뒤집어 저장해야 할 단어가 있기 때문에 저장된 단어를 뒤집어 저장
					for (int j = 0; j < wordLength ; j++) {
						chars[i-j-1] = word[j];
					}
					wordLength = 0;

				}
			}
			
		}
		
		//문자열이 단어로 끝나는 경우. 마지막으로 뒤집어 저장할 단어가 있는지 확인하고 있으면 뒤집어 저장
		if(wordLength!=0) {
			for (int j = 0; j < wordLength ; j++) {
				chars[size-j-1] = word[j];
			}
			wordLength = 0;
		}
		
		//출력
		for (int i = 0; i < size; i++) {
			sb.append(chars[i]);
		}
		System.out.println(sb.toString());
	}

}
```

## ✅ 후기
### 새롭게 알게되거나 공유해서 알게된 점
toCharArray라는 좋은 메소드가 있더군요..!!

처음에는 뒤집어서 원래 배열에 저장하는 게 아니라 그냥 sb.append를 중간중간 집어넣고 마지막에 toString으로 출력했는데 그것보다 위처럼 하는 게 시간이 조금 덜 들더라구요   
큰 차이는 없지만 이 쪽이 이해하기 편해서 이쪽으로 작성했습니다.

마크다운으로 코드를 예쁘게 넣는 방법을 알게 되었습니다.
### 고생한 점
생각을 좀 정리하고 코딩해야 되는데 아이디어가 떠오르면 바로바로 코딩해버려서   
습관을 잘 들여야 할 것 같아요
