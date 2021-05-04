## 🅰 설계

* floyd-warshall 알고리즘을 활용하여 경유지 k를 거쳐 i에서 j까지 갈 수 있는 지를 확인합니다.

```java
for (int k = 0; k < N; k++) {
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      if (edge[i][k] == 0 || edge[k][j] == 0)
        continue;

      edge[i][j] = 1;
    }
  }
}
```



## ✅ 후기

- 쌤에게 배운 경출도를 적용했습니다.
- 3중 for문의 정확한 작용 방식이 기억이 나지 않습니다.
