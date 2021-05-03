# 7662번 이중 우선순위 큐

[문제 보러가기](https://www.acmicpc.net/problem/7662)

## 🅰 설계

#### 1) 첫 번째 방법

- minq :  ‘D -1’는 Q 에서 최솟값을 삭제하는 연산을 할 queue
- maxq : ‘D 1’는 Q에서 최댓값을 삭제하는 연산을 할 queue
- map : 삽입된 원소의 사용 횟수

```java
static PriorityQueue<Integer> minq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
static PriorityQueue<Integer> maxq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
static Map<Integer, Integer> map = new HashMap<>();
```

- ‘I n’은 정수 n을 Q에 삽입하는 연산
  - queue에 삽입한 원소를 세는 map에 해당 원소와 추가되었으므로 사용횟수 1회로 map에 추가한다
- D일 때, 1이면 최댓값을 queue에서 삭제해야한다. queue에 들어온 원소 사용횟수를 관리하는 map에서 maxq에서 가장 최댓값인 원소를 삭제할 것이다. 따라서 map에서 해당원소에 대한 값에 -1한다.
- D일 때, -1이면 최솟값을 queue에서 삭제해야한다. queue에 들어온 원소 사용횟수를 관리하는 map에서 minq에서 가장 최솟값인 원소를 삭제할 것이다. 따라서 map에서 해당원소에 대한 값에 -1한다.

```java
while (numOfOper-- > 0) {
    String[] op = br.readLine().split(" ");
    int val = Integer.parseInt(op[1]);
    if (op[0].equals("I")) {
        maxq.add(val);
        minq.add(val);
        map.put(val, map.getOrDefault(val, 0) + 1);
    } else if (op[0].equals("D") && val == 1) {
        if (maxq.isEmpty()) continue;
        map.replace(maxq.peek(), map.get(maxq.peek()) - 1);
    } else if (op[0].equals("D") && val == -1) {
        if (minq.isEmpty()) continue;
        map.replace(minq.peek(), map.get(minq.peek()) - 1);
    }
    // 한 번의 연산 이후 queue 상태 업데이트
    while (!maxq.isEmpty() && map.get(maxq.peek()) == 0) maxq.poll();
    while (!minq.isEmpty() && map.get(minq.peek()) == 0) minq.poll();
}
if (maxq.isEmpty()) sb.append("EMPTY").append("\n");
else sb.append(maxq.poll()).append(" ").append(minq.poll()).append("\n");

```

- 한 번의 연산 이후 queue 상태 업데이트
  - map에서 value가 0인 원소를 maxq와 minq에서 뺀다

#### 2) 두 번째 방법

- q : 삽입된 원소의 사용 횟수
  - 자료구조 treemap을 사용한다. treemap 은 q에 들어온 원소의 순서를 보장한다.

```java
static TreeMap<Integer, Integer> q = new TreeMap<>();
```

- val이 -1일 때 =  ‘D -1’는 Q 에서 최솟값을 삭제하는 연산
  - `q.firstkey()` : q에서 맨 앞에 있는 값
- val이 1일 때 =  ‘D 1’는 Q 에서 최댓값을 삭제하는 연산
  - `q.lastkey()` : q에서 맨 뒤에 있는 값

```java
while (numOfOper-- > 0) {
    String[] op = br.readLine().split(" ");
    int val = Integer.parseInt(op[1]);
    if (op[0].equals("I")) {
        q.put(val, q.getOrDefault(val, 0) + 1);
    } else {
        if (q.isEmpty()) continue;
        int key = val == -1 ? q.firstKey() : q.lastKey();
        // 현재 q에 있는 key 인지 확인
        // 1이면 아예 사라지기 때문에 remove
        // 1이 아니면 1 줄이기
        if (q.get(key) == 1) q.remove(key);
        else q.replace(key, q.get(key) - 1);
    }
}
if (q.isEmpty()) sb.append("EMPTY").append("\n");
else sb.append(q.lastKey()).append(" ").append(q.firstKey()).append("\n");
```



## ✅ 후기

- Map관련 함수를 구경하며 유용한 메서드가 있다는 것을 알게되었습니다.
  - getOrDefault(key, default) : key가 없으면 default값을 할당하고 반환
  - putIfAbsent() : key가 없으면 default값을 할당
  - ceilingEntry() : 제공된 키 값보다 크거나 같은 값 중 가장 작은 키의 Entry를 반환
  - ceilingKey() : 제공된 키 값보다 크거나 같은 값 중 가장 작은 키의 키값을 반환
  - floorEntry() : 제공된 키 값보다 같거나 작은 값 중 가장 큰 키의 Entry를 반환
  - floorKey() : 제공된 키 값보다 같거나 작은 값 중 가장 큰 키의 키값을 반환