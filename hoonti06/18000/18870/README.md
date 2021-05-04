## 🅰 설계

- <원소 값, 순서>를 저장하는 map (결과를 출력할 때 O(1)에 access 가능)

  ```java
  static HashMap<Integer, Integer> cntMap;
  ```

  

- 정렬되어 있는 배열에서 upper-bound를 이용하여 중복되는 원소를 건너뛰는 방식으로 진행하면서 순서(cnt)를 계산했습니다. 그 순서(value)를 원소값(key)과 함께 cntMap에 저장하였습니다.

  ```java
  int lower = 0;
  int cnt = 0;
  while (lower < N) {
    int key = sorted[lower];
    int upper = upperBound(key);
  
    cntMap.put(key, cnt);
    lower = upper;
    cnt++;
  }
  ```

* upper-bound를 직접 구현했습니다.

  ```java
  static int upperBound(int key) {
      int left = 0;
      int right = N-1;
      int res = N; // default 값 중요
      while (left <= right) {
          int mid = (left + right) / 2;
          if (key < sorted[mid]) {
              right = mid - 1;
              res = mid;
          } else {
              left = mid + 1;
          }
      }
      return res;
  }
  ```



## ✅ 후기

- upperbound를 직접 구현해봐서 좋았습니다.
- 시간이 많이 느린 것 같아서 다른 풀이 방법과 비교해봐야 할 것 같습니다.

