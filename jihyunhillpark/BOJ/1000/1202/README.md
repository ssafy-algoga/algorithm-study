# 1202번 보석도둑
[문제 보러가기](https://www.acmicpc.net/problem/1202)

## 🅰 설계
아무도 깨어있지 않은 새벽에 상덕이랑 같이 보석 좀 훔쳐볼까 풀어봤습니다.👥👀💎💍

1. 처음 이 문제를 접했을 때는 가장 가치가 큰 보석을 넣는 greedy 알고리즘을 적용하려고 했습니다.
그래서 jewelry라는 __2차원 배열__ 을 만들어서 N개의 주얼리의 무게[0]와 가치[1]를 저장하여, 1차 정렬을 보석의 가치로 (내림차순), 2차 정렬을 보석의 무게(오름차순)을 유지하도록 했습니다.<br>
<br>
이렇게되면 앞에서부터 차례로 보석을 선택하면 최적화된 보석의 최대 값어치를 얻을 수 있다고 생각했습니다.<br>
**한계** : 가방의 용량이 다 다르면 괜찮을 수도 있지만, 같은 용량의 가방이 여러번 나올 경우 탐색이 꼬입니다.
<br>
2. 배열의 한개 우선순위 큐를 사용하는 것을 고려했습니다. 이 때, 1번에서 정한 보석들의 우선순위는 바꾸지 않고 별도의 임시 큐를 만들어 poll한 보석들을 저장하도록 한 뒤, 한 가방에 대응하는 보석이 정해지면 임시큐에 있는 원소들을 다시 원래 있던 보석 우선순위큐에 넣었습니다.<br>
**한계** : 시간초과가 떴습니다. 생각해보면 매 가방마다 최대 값을 찾기 위해 뺐던 것을 다시 복구하는 알고리즘 자체가 굉장히 비효율적입니다.
<br>
3. 다시 풀이를 reset한 뒤 어떻게 알고리즘이 돌아가야하는지를 따졌습니다. 생각을 해보니 정말 심플하게 문제를 보면 됐습니다. __a.가방 용량으로 들 수 있는 모든 보석 중에__ __b.최대 가치의 보석을 담아야 합니다.__ 가방의 최대무게를 정렬하지 않으면 매번 담을 수 있는 보석들의 개수가 늘어났다 줄어들었다 할 것 입니다. 이에 가방 최대무게를 __오름차순으로 정렬__ 하면 가방에 담을 때마다 담을 수 있는 보석들이 그대로이거나 기존 가능한 보석들에 추가만 하면 되기에, __보석을 담을 때마다 최고의 값어치를 얻을 수 있는 그리디 알고리즘을 적용할 수 있습니다.__
<br>

먼저 두개의 큐를 생성합니다. 하나는 무게가 가벼울수록 우선순위에 있는 큐로 가방에 들어갈 수 있는 모든 보석을 보기 위함이고, 다른 하나는 보석의 가치만 저장된 max heap으로서 최대 가치의 보석을 뽑기 위함입니다.
```java
  //a. 보석 무게가 작은 순이 우선순위를 가지는 우선순위 큐이다.
  PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] arr1, int[] arr2) {
				return arr1[0] - arr2[0];
			}
	});
  //b. 현재 가방이 들 수 있는 제한된 무게 내에서 담을 수 있는 모든 보석들 중 최대 값어치를 가져오는 우선순위 큐.
	PriorityQueue<Integer> pool = new PriorityQueue<>(Collections.reverseOrder()); //보석의 값어치가 높을수록 우선순위가 높은 max heap
```
보석에 대한 입력이 들어올 때마다 큐에 저장합니다.
```java
  for(int i = 0 ; i< N ; i++) {
    st = new StringTokenizer(in.readLine()," ");
    jewelry = new int[2];
    jewelry[0] = Integer.parseInt(st.nextToken()); //보석 무게 저장
    jewelry[1] = Integer.parseInt(st.nextToken()); //보석 값어치 저장
    pq.add(jewelry); //큐에 추가~
  }
```
가방이 담을 수 있는 최대 무게의 경우 오름차순으로 정렬합니다. 오름차순 정렬을 해야 그리디 알고리즘을 적용할 수 있습니다.
\* 가방에 보석을 담을 때 담을 수 있는 용량이 작은 가방이 제약이 가장크고(들어갈 수 있는 보석들의 수가 가장 적음) 용량이 큰 가방이 제약이 가장 작습니다(들어갈 수 있는 보석들의 수가 가장 많음).
```java
  for(int i = 0 ; i< K ; i++) //가방 무게 받아와서 가방 무게 배열에 저장
    knapsack[i] = Integer.parseInt(in.readLine());
  Arrays.sort(knapsack); //가방 무게를 오름차순으로 정렬
```
그 다음 보석을 넣은 우선순위 큐에서 가방에 담을 수 있는 모든 보석들을 가져와 해당하는 값어치들을 pool 큐에 넣습니다. pool.poll()을 하면 현재 넣을 수 있는 모든 보석들 중 최고 값어치를 가져올 수 있습니다.
```java
  for(int i = 0 ; i < K ; i++) {
    //from 가장 작은 용량 가방 to 제일 큰 용량
    while(!pq.isEmpty()) {
      int[] e = pq.poll();
      if(e[0] <= knapsack[i] )//현재 가방 용량보다 작거나 같은 무게의 보석들은
        pool.add(e[1]); //pool에 넣는다.
      else {
        pq.add(e); //현재 가방 용량을 초과하는 보석이 나왔으므로 바로 직전까지 꺼낸 보석들만 담을 수 있고, 다음 작업을 위해 다시 넣어준다.
        break;
      }
    }
    if(pool.size() != 0) {
      value += pool.poll(); //담을 수 있는 모든 보석들 중에서 가장 큰 값어치를 하는 보석을 담는다.
    }
  }
```

<br>
4. 입력 범위를 잘 확인하지 않아 결과를 int형으로 했는데 자꾸 \"틀렸습니다\"가 떴습니다. long으로 바꾸어 주었습니다...흠흠...long때문이었다니 오열합니다...😭
<br>

## ✅ 후기
// 새롭게 알게되거나 공유해서 알게된 점// 고생한 점
#### 우선순위를 잘못 잡아거나 입력범위를 잘 확인하지 않아서 삽질을 많이 했습니다. 문제 조건에 따라 어떤 자료형을 쓸지가 중요하다는 것을 깨달았습니다. 더불어 마구니가 끼었을 때는 풀이를 reset시키는 것도 필요함을 깨닫는 보석털이 경험이었습니다.👥