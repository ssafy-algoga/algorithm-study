# 12015번 가장 긴 증가하는 부분수열
[문제 보러가기](https://www.acmicpc.net/problem/12015)

## 🅰 설계
![image](https://user-images.githubusercontent.com/37682970/110363875-71e8fe80-8086-11eb-9947-460dad52c83d.png)

LIS 공부하다가 같이 풀면 좋을 것 같아서 가져왔습니다. 
Table을 만들어가면서 현재 인덱스의 값을 보면서 테이블에 들어갈 적당한 자리가 있으면 테이블을 갱신하는 방식으로 풀었습니다.
```java
		for(int i=0;i<N;i++) {
			if(i==0) { // 첫번째 숫자면 그대로 테이블에 넣기
				table[index]=arr[0];
			}
			else {
				if(table[index]<arr[i]) { //최장 증가 수열이면 테이블에 추가
					table[++index]=arr[i];
					continue;
				}
				// 아니면 적당한 자리에 넣고 테이블을 갱신
				int temp = index;
				while(temp>=0 && table[temp]>=arr[i]) {
					temp--;
				}
				table[++temp]=arr[i];
			}
		}
```
공부한 내용을 코드로 구현했습니다. 코드가 그리 길진 않아서 보긴 어렵지 않으실거라 생각합니다.  

## 전체코드 
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int [] arr;
	static int [] table;
	static int index;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		table = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=0;i<N;i++) {
			if(i==0) { // 첫번째 숫자면 그대로 테이블에 넣기
				table[index]=arr[0];
			}
			else {
				if(table[index]<arr[i]) { //최장 증가 수열이면 갱신
					table[++index]=arr[i];
					continue;
				}
				// 아니면 적당한 자리에 넣고 테이블을 갱신
				int temp = index;
				while(temp>=0 && table[temp]>=arr[i]) {
					temp--;
				}
				table[++temp]=arr[i];
			}
		}
		System.out.println(index+1);
	}
}
```

## ✅ 후기
### 새롭게 알게되거나 공유해서 알게된 점
DP는 너무어려워요... 그저 하나씩 푸는방법을 배워갈뿐.

### 고생한 점
없습니다.   

https://jason9319.tistory.com/113 설명 잘 되어있는데 참고해보세요
