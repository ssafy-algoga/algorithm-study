# 15663번 N과 M(9)
[문제 보러가기](https://www.acmicpc.net/problem/15663)

## 🅰 설계
![image](https://user-images.githubusercontent.com/23499504/107151229-cd9b6b80-69a4-11eb-963f-97bba84462b5.png)
1. 받은 수 배열에 담기
2. 재귀로
3. M개 되면 출력

**중복 제거할 방법**
1. Set에 담기
2. 재귀 함수에서 확인하기

(2번 방법으로 풀었습니다 출력할 때)
9 7 9 1 입력된 상황에서 중복이 담기는 경우
(받은 수를 우선 정렬하니까)
1 7 9 9 ->
1 7
1 9
1 9(여기서 중복된 순열이 발생)
7 1
	앞에서 사용한 숫자와 같은 숫자인데 앞의 것이 false면 사용되고 chosen[i]= false;된 상황이니까 이걸 확인해주자!

```
    for (int i = 0; i < N; i++) {
        if(!chosen[i]) {
            if(i >= 1 && !chosen[i-1] && numbers[i-1] == numbers[i]) continue;
                chosen[i] = true;
                selectedNums[cnt] = numbers[i];
                search(cnt + 1);
                chosen[i] = false;
            }
        }
```

```java
public class Main {
    static boolean[] chosen;
    static int[] numbers;
    static int[] permutations;
    public static int N;
    public static int M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        numbers = new int[N];
        permutations = new int[M];
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }
        chosen = new boolean[N];
        Arrays.sort(numbers);
        search(0);
    }
    public static void search(int cnt){
        if(cnt == M){
            StringBuilder builder = new StringBuilder();
            for (int i : permutations) {
                if(builder.length()!=0) builder.append(" ");
                builder.append(i);
            }
            System.out.println(builder);
        }else{
            for (int i = 0; i < N; i++) {
                if(!chosen[i]) {
                    if(i >= 1 && !chosen[i-1] && numbers[i-1] == numbers[i]) continue;
                    chosen[i] = true;
                    permutations[cnt] = numbers[i];
                    search(cnt + 1);
                    chosen[i] = false;
                }
            }
        }
    }
}

```

# **로직은 맞는데 뭐가 틀렸지 ?? 하면서 수정한 부분**	

![image](https://user-images.githubusercontent.com/23499504/107226031-ec5d3900-6a5c-11eb-8f16-297aab526ad5.png)

```java
static int[] numbers;
static int[] selectedNums;
//static List<String> sortedPerms;
static int[] permutations;
```
하나하나 골랐던 원소들로 담고 제거할 때, add와 remove함수를 이용하려고 처음에 ArrayList를 이용했는데 
계속 틀렸다고 해서 이것을 int[] 배열로 바꾸자 문제가 해결됐습니다.
인덱스를 지정할 변수를 생성하려다가 메서드 파라미터로 넘어가는 cnt를 이용해 넘겨서 문제를 해결 할 수 있었습니다.


## ✅ 후기
Collections.sort(리스트)이용해서 ArrayList정렬할 수 있음.

