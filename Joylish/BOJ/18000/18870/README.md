# 18870번 좌표압축

[문제 보러가기](https://www.acmicpc.net/problem/18870)

## 🅰 설계

- set : 중복된 원소를 없애기 
- list : set 자료구조를 인덱스로 원소에 접근하기 위해 List 자료구조로 변경   
- arr :  입력된 순서대로 값이 저장된 배열 

```java
static Set<Integer> set = new HashSet<>();
static List<Integer> list;
static int[] arr;

for (int i = 0; i < n; i++) {
    int num = Integer.parseInt(st.nextToken());
    arr[i] = num;
    set.add(num);
}

list = new ArrayList<>(set);
Collections.sort(list);
int len = list.size();
```

-  map : 정렬된 list에 대한 값을 key로 갖고 그에 대한 인덱스를 value로 갖는다. 

```java
Map<Integer, Integer> map = new HashMap<>();
for (int i = 0; i < len; i++) {
    map.putIfAbsent(list.get(i), i);
}

for(int num : arr){
    sb.append(map.get(num)).append(" ");
}
```



## ✅ 후기

- 그 전까지 자료구조 map과 set을 잘 다루지 않았지만 이 문제를 풀면서 이 두가지 자료구조를 쓸 수 있어 좋았습니다. 

