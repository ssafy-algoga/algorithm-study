# 1202번 보석 도둑
[문제 보러가기](https://www.acmicpc.net/problem/1202)

## 🅰 설계
0-1 knapsack problem의 독특한 버전이랄까요. 제가 생각한 문제의 의도는 두 가지입니다.
1. 그리디 알고리즘으로 풀 수 있는 최적화 문제라는 것을 파악할 것
2. 큰 입력범위에 대해 시간제한 안에 해결할 것

사실 1번은 고민하다보면 자연스럽게 떠오르기 때문에(가방에 한 개만 넣을 수 있으니
가장 값이 비싼 보석을 넣어야겠죠) 걸림돌이 되는건 2번입니다.
`무게제한이 낮은 가방부터 탐색하면서 그 가방이 담을 수 있는 보석 중 가장 비싼 보석을
담는다` 라는 아이디어를 바탕으로 전체적인 로직을 설계하면 다음과 같습니다.

1. 보석을 무게에 따른 오름차순, 무게제한도 무게에 따른 오름차순으로 정렬한다.
2. 아직 보석을 담지 않은 가방 중 무게제한이 가장 가벼운 가방을 선택한다.
3. 무게제한보다 무게가 가벼운 보석들을 전부 탐색하면서 가격이 가장 높은 보석을 가방에 담는다.
4. 2-3를 반복한다.

가장 먼저 떠오르는 방법은 배열이나 리스트에 보석을 전부 저장한 뒤 매 쿼리마다
(가방마다) 완전탐색하면서 가격이 가장 비싼 보석을 찾는 것입니다.
M개의 쿼리마다 최대 N번의 보석을 탐색해야 하니 시간복잡도는 O(NM)입니다.
N과 M의 제한이 30만 이하이기 때문에 택도 없는 방법이네요.
정렬되어 있다는 것에 착안해서 이진 탐색을 생각할 수도 있지만 이진 탐색은 특정 값을
가지는 원소를 찾는 것이지 최댓값을 찾아주는 알고리즘이 아닙니다.

다음으로 무게제한 이하의 보석들을 다른 리스트에 새로 옮겨 담으면서 가격을 기준으로 해서
오름차순으로 정렬상태를 계속해서 유지해 주는 방법입니다. 이 경우 가장 비싼 가격을
가지고 있는 보석은 항상 리스트에 맨 뒤에 있기 때문에 `E e = list.get(list.size() - 1`
로 O(1)에 탐색할 수 있습니다. 문제는 새로운 보석이 추가될 때 오름차순 상태로 유지하기
위해 삽입정렬을 하는 시간이 O(N)이라는 점입니다. 따라서 역시 시간제한에 걸립니다.

결국 새로운 보석을 원소에 추가하고 그 중에 최댓값을 가지는 보석을 탐색하는 두 가지
스텝을 전부 O(N) 미만의 시간복잡도를 가지도록 해결해야 한다는 의미입니다.
사실 인트로를 길게 했지만 위 내용은 우선순위 큐의 탄생배경입니다. 힙 자료구조를 통해
구현한 우선순위 큐는 원소의 삽입 및 삭제(최댓값 탐색)을 O(logN)에 해결할 수 있습니다.

여기까지 생각이 도달한다면 문제의 9할은 해결입니다. 나머지는 위의 로직을 구현하는 것뿐이죠.

```java
import java.io.*;
import java.util.*;

public class Main {
    static class Jewelry {
        int weight;
        int price;
        Jewelry(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfJewelry = Integer.parseInt(st.nextToken());
        int numOfBag = Integer.parseInt(st.nextToken());
        Jewelry[] jewelries = new Jewelry[numOfJewelry];
        for(int i=0; i<numOfJewelry; i++) {
            st = new StringTokenizer(br.readLine());
            jewelries[i] = new Jewelry(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }
        int[] bagLimit = new int[numOfBag];
        for(int i=0; i<numOfBag; i++) {
            bagLimit[i] = Integer.parseInt(br.readLine());
        }
        // 보석을 무게에 따른 오름차순으로, 가방을 무게제한에 따른 오름차순으로 정렬
        Arrays.sort(jewelries, (a, b) -> a.weight - b.weight);
        Arrays.sort(bagLimit);
        
        // 무게제한 이하의 보석을 옮겨담기 위한 우선순위 큐(Max Heap)
        PriorityQueue<Jewelry> pq = new PriorityQueue<>((a, b) -> b.price - a.price);
        // int로 선언했다가 틀렸습니다.
        long sum = 0;
        // 어느 보석까지 우선순위 큐에 담았는지를 저장하기 위한 포인터
        int pointer = 0;
        for(int i=0; i<numOfBag; i++) {
            int weightLimit = bagLimit[i];
            // 보석무게가 무게제한 이하라면 우선순위 큐에 삽입
            while(pointer < numOfJewelry &&
                    jewelries[pointer].weight <= weightLimit) {
                pq.offer(jewelries[pointer++]);
            }
            if(!pq.isEmpty()) {
                // 큐에서 가장 비싼 가격을 가진 보석을 꺼내서 가격 갱신
                sum += pq.poll().price;
            }
        }
        System.out.println(sum);
    }
}
```

## ✅ 후기
자료구조 문제를 풀다보면 그 자료구조(혹은 추상자료형)이 탄생한 배경을 자연스럽게
알게 되어서 재밌습니다.