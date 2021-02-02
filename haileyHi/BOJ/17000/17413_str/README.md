# 17413번 단어 뒤집기 2
[문제 보러가기](https://www.acmicpc.net/problem/17413)

## 🅰 설계

![image](https://user-images.githubusercontent.com/23499504/105865438-4827cb00-6036-11eb-8c50-6709a4eb06e6.png)


단어를 뒤집는다고 해서
1. stack에 담고 사이즈만큼 반복하여 pop하는 방법과 
2. StringBuilder의 reverse를 이용하는 방법 두 가지를 생각해보았습니다.

StringBuilder가 반복문을 덜 써서 StringBuilder로 만들었습니다.
```java
StringBuilder sb = new StringBuilder();
StringBuilder tmp = new StringBuilder();
```
정답을 담아 반환해 줄 sb와 뒤집을 단어 저장에 tmp를 사용했습니다.

**Tag 확인**
```java
boolean isTag;
```
Tag는 그냥 담으면 되니까 <를 만나면 isTag를 true로 >를 만나면 Tag를 false로 바꿔주었습니다.

**Tag 아닌 단어 뒤집기**
isTag == false 일 때 tmp에 c를 append 해주었고
빈칸이나 <를 만나면 sb에 뒤집어서 붙였습니다.


```java
  if(i == p.length() -1 && tmp.length() > 0){//마지막까지 갔는데 아직 뒤집을 단어가 남아있는 경우
    sb.append(tmp.reverse());
  }
```
하나씩 끊어오는 반복문 마지막에서 아직 남아있는 tmp가 있다면 뒤집어서 마저 붙여줬습니다.

## 전체 코드
```java
import java.io.*;

public class WordSwapping {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = reader.readLine();
        writer.write(solution(s));
        writer.flush();
        writer.close();
    }
    public static String solution(String p){
        boolean isTag = false;

        StringBuilder sb = new StringBuilder();
        StringBuilder tmp = new StringBuilder();

        for(int i = 0; i < p.length(); i++){
            char c = p.charAt(i);

            //TAG확인
            if(c == '<'){
                isTag = true;
            }else if (c == '>') {
                isTag = false;
                sb.append(c);
                continue;
            }

            if(isTag) {//TAG 내용 담기 및 태그 담기 전에 담아야 할 단어 있는지 체크
                if(tmp.length() > 0){
                    sb.append(tmp.reverse());
                    tmp.delete(0,tmp.length());
                }
                sb.append(c);
            }else{//TAG 아니면 빈칸 만나기 전까지 tmp에 뒤집을 단어 담으면 됨.
                if(c != ' ') {
                    tmp.append(c);
                }else {
                    sb.append(tmp.reverse()).append(c);
                    tmp.delete(0,tmp.length());
                }
            }

            if(i == p.length() -1 && tmp.length() > 0){//마지막까지 갔는데 아직 뒤집을 단어가 남아있는 경우
                sb.append(tmp.reverse());
            }
        }
        return sb.toString();
    }
}

```
## ✅ 후기
// 새롭게 알게되거나 공유해서 알게된 점
StringBuilder 초기화는 setLength(0)으로도 할 수 있단 걸 알았습니다.
*Java Api문서는 정말 유용하다. 읽을 때마다 새로운 걸 발견할 수 있다.*

// 고생한 점
tmp를 초기화해야하는데 sb를 초기화하는 실수를 저질러서 어리둥절했던 점...

마크다운은 너무 어려웠습니다.. 문제 푸는 시간보다 이거 작성하는 시간이 더 오래 걸려서 조금 슬펐습니다.... 익숙해지면 빨라지지 않을까하는 작은 기대를 가졌습니다.