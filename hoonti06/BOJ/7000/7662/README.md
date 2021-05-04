## 🅰 설계

* 명령(cmd)의 index와 해당 수를 저장하는 min-heap, max-heap. (max-heap은 num값을 음수로 저장)

  ```java
  static PriorityQueue<Node> minHeap, maxHeap;
  
  static class Node implements Comparable<Node> {
      int idx;
      long num;
      Node(int idx, long num) {
          this.idx = idx;
          this.num = num;
      }
      @Override
      public int compareTo(Node o) {
          return Long.compare(this.num, o.num);
      }
  }
  ```

* cmd의 index가 삭제되었는지 여부를 저장하는 deleted 배열

  ```java
  static boolean[] deleted;
  ```

* dual priority queue의 사이즈를 의미하는 변수 size

* 명령 처리

  * D일 때

    * size == 0이면 skip

    * size > 0

      * size 하나 줄인다

      * 1일 때 (max)

        max-heap에서 아직 삭제가 되지 않은 원소를 만날 때까지 while문 반복

        ```java
        while (true) {
          Node del = maxHeap.poll();
          if (!deleted[del.idx]) {
            deleted[del.idx] = true;
            break;
          }
        }
        ```

        

      * -1일때

        min-heap에서 아직 삭제가 되지 않은 원소를 만날 때까지 while문 반복

        ```java
        while (true) {
          Node del = minHeap.poll();
          if (!deleted[del.idx]) {
            deleted[del.idx] = true;
            break;
          }
        }
        ```

  * I 일때

    * size를 하나 증가시키고 min-heap에는 수를 그대로, max-heap에는 수를 음수로 저장

      ```java
      minHeap.add(new Node(idx, num));
      maxHeap.add(new Node(idx, -num));
      size++;
      ```

* 결과 출력

  * 결과 출력

    size가 0 초과일 경우 이미 삭제된 원소들을 제거 후 최댓/최솟값을 출력

    ```java
    if (size > 0) {
      while (!minHeap.isEmpty() && deleted[minHeap.peek().idx])
        minHeap.poll();
      while (!maxHeap.isEmpty() && deleted[maxHeap.peek().idx])
        maxHeap.poll();
    
      sb.append(-maxHeap.peek().num).append(" ").append(minHeap.peek().num);
    } else sb.append("EMPTY");
    ```



## ✅ 후기

* max-heap을 구현할 때 32bit 정수를 음수로 저장하였는데, 이때 underflow가 발생하는 것을 캐치하지 못해 결국 질문을 살펴보고 깨닫게 되었습니다.
  * 그래서 num을 int에서 long으로 변경해주었습니다.
* 결과를 출력할 때에도 이미 삭제된 원소가 각 heap의 맨 앞에 위치하여 현재 dual priority queue에 저장되어 있는 최댓값, 최솟값을 가리는 경우를 바로 캐치해내지 못했습니다.
  * 테스트 케이스를 추가 작성해보고 알게 되었습니다.