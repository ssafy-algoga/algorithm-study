# 1202번: 보석 도둑

[문제 보러가기](https://www.acmicpc.net/problem/1202)

## 🅰 설계
![image](https://user-images.githubusercontent.com/37682970/109420063-e206dd00-7a13-11eb-92c3-1196c284f62a.png)
```
4 4
//보석
1 100
2 200
13 300
10 500
//가방
10
10
10
14
```
가방 용량이 큰 것 부터 담는다면 위의경우 가방 용량이 14인 것에 담을 수 있는 가장 비싼보석 (무게10 가격500) 보석을 담게됩니다.  
따라서 가방 용량이 적은 것부터 담는게 핵심이였던 것 같습니다.  

설계그림에서 그렸듯, 가방 용량이 작은것 부터 담아나간다면 내가 담을 수 있는 보석중 가장 비싼것을 고르면 다음 가방 용량은 지금보다 크거나 같기 때문에 항상 최선의 선택을 할 수 있게 됩니다.

담을 수 있는 후보군들중에 가장비싼 보석을 찾는 것은 PQ(max heap)를 이용하면 효과적으로 찾을 수 있습니다.  

```java
    static class Jewelry implements Comparable<Jewelry> {
        int price;
        int weight;

        Jewelry(int price, int weight) {
            this.price = price;
            this.weight = weight;
        }

        @Override
        public int compareTo(Jewelry o) {
            return this.weight - o.weight;
        }
    }
```
보석은 price와 weight를 가지고 있습니다.  
```java
        for(int bag :bags) { //작은가방부터 채워나가기
            while (i < N && bag >= jewels.get(i).weight) { //가방에 넣을수 있는 무게면
                candidate.add(jewels.get(i++).price); //후보군에 넣기
            }
            if (!candidate.isEmpty()) { //후보군이 있으면
                result += candidate.poll(); // 후보군중에 가장 비싼거 담기
            }
        }
```
작은 가방부터 담아나가면서 넣을 수 있는 무게면 후보군에 넣고 pq를 이용해서 후보군중 가장 비싼 보석을 담습니다.  

## 전체코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Jewelry implements Comparable<Jewelry> {
        int price;
        int weight;

        Jewelry(int price, int weight) {
            this.price = price;
            this.weight = weight;
        }

        @Override
        public int compareTo(Jewelry o) {
            return this.weight - o.weight;
        }
    }

    static int N, K;
    static long result=0;
    static ArrayList<Jewelry> jewels = new ArrayList<>();
    static ArrayList<Integer> bags = new ArrayList<>();
    static PriorityQueue<Integer> candidate = new PriorityQueue<>(Collections.reverseOrder());
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            Jewelry jew = new Jewelry(price, weight);
            jewels.add(jew);
        }

        Collections.sort(jewels); //무게 거벼운 순으로 정렬

        for (int k = 0; k < K; k++) {
            int weight = Integer.parseInt(br.readLine());
            bags.add(weight);
        }
        Collections.sort(bags); //가방 용량 작은순으로 정렬
        int i = 0;

        for(int bag :bags) { //작은가방부터 채워나가기
            while (i < N && bag >= jewels.get(i).weight) { //가방에 넣을수 있는 무게면
                candidate.add(jewels.get(i++).price); //후보군에 넣기
            }
            if (!candidate.isEmpty()) { //후보군이 있으면
                result += candidate.poll(); // 후보군중에 가장 비싼거 담기
            }
        }
        System.out.println(result);
    }
}
```

## ✅ 후기

### 새롭게 알게되거나 공유해서 알게된 점

compareTo 잘써야겠습니다.  
설계를 변경한다면 기존 코드를 살리려 하지말고 코드를 새롭게 짜는 것도 한 방법인 것 같습니다.  

### 고생한 점

처음에는 가방용량이 큰 것 부터 담으려다가 생각을 잘못했단 걸 알게되어서 코드를 바꾸었는데 그러다보니 코드를 짜는 과정에서 여기저기 오류가 많았습니다.  
가장 값비싼 보석을 얻는방법을 pq로 구현하면 된다고 생각은 했는데.. 생각을 코드로 구현하기가 쉽지 않았습니다.  
도와주신 minkue777,Joylish 님 감사합니다..
