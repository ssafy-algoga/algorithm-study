# 15663번 N과 M9

[문제 보러가기](https://www.acmicpc.net/problem/15663)

## 🅰 설계

### 1. 입력 정보 저장 및 정렬

- numbers : 숫자 저장
- isSelected :  해당 숫자를 골랐는지 확인하는 용도로 쓰이는 배열
- choices : 고른 숫자를 담는 배열	

```java
numbers = new int[N];
st =new StringTokenizer(br.readLine());
for(int i = 0; i<N; i++) { 
    numbers[i] = Integer.parseInt(st.nextToken());
}

Arrays.sort(numbers);

isSelected = new boolean[N];
choices = new int[M];	
```

### 2. 순열 시작  

변수 `past`를 이용하여 순열에서 중복 제거 해주었다. 

```java
public static void main(String[] args) throws IOException{
    ...
    permutation(0); // 순열 시작
	...
}
static void permutation(int count) {
    if(count == M) { //뽑고 싶은 수만큼 뽑았다면
        append();	// 뽑은 모든 수를 Stringbuilder에 저장
        return;	// 리턴
    }
    int past = -1; // 이전에 뽑은 숫자
    for(int i=0; i<N; i++) {
        int now = numbers[i]; // 숫자 고르기
        if(isSelected[i] || past == now) continue; //숫자를 이미 골랐거나 이전에 뽑은 숫자와 동일한지 확인
        past = now; //이전에 뽑은 숫자를 지금 뽑은 숫자로 업데이트
        choices[count] = now; //현재 뽑은 숫자 담기
        isSelected[i] = true; // 현재 뽑은 숫자 뽑았다고 체크  
        permutation(count+1); //숫자를 하나 뽑았으므로 뽑을 숫자 인덱스 증가
        isSelected[i] = false; // 현재 뽑은 숫자 다시 뽑을 수 있도록 false
    }
}
```



## ✅ 후기

- 수업시간을 복기하며 순열을 구현할 수 있어서 좋았습니다. 순열에서 중복되는 경우를 제거하기 위해 디버깅으로 재귀 호출되는 과정을 보며 아이디어를 고민해보았습니다. 


