# 1991번 트리 순회
[문제 보러가기](https://www.acmicpc.net/problem/1991)

## 🅰 설계
문제를 크게 두 가지로 쪼갰습니다.
1. 입력이 들어왔을 때 트리에 새로운 노드를 추가하는 문제
2. 트리가 완성되었을 때 세 가지 방법으로 순회하는 문제

사실 2번은 어렵지 않은 문제이기 때문에 1번에 좀 더 많은 시간을 쏟았습니다.
1번 문제를 2가지 메소드로 나누어서 구현했습니다.
1. 입력된 데이터를 바탕으로 해당 노드를 찾는 searchNode 메소드
2. 찾은 노드에 자식 노드를 추가하는 insert 메소드

일단 문제 전반에 사용했던 Node 클래스입니다.

```java
static class Node {
    String data;
    Node left;
    Node right;

    Node(String data) {
        this.data = data;
    }
}
```

### searchFind 메소드

```java 
static Node searchNode(Node node, String target) {
    if(node == null) return null;
    if(node.data.equals(target)) return node;
    // 재귀 호출을 통해 모든 노드를 탐색
    Node searchLeft = searchNode(node.left, target);
    Node searchRight = searchNode(node.right, target);
    Node ret = null;
    if(searchLeft != null) ret = searchLeft;
    else if(searchRight != null) ret = searchRight;
    return ret;
}
```

트리의 모든 노드를 탐색하는 방법은 dfs의 일종으로 필연적으로 재귀호출을 사용하게 됩니다.
왼쪽 노드 -> 오른쪽 노드 순서로 방문하면서 만약 전체를 순회해도 노드를 찾지 못한다면
null을 리턴합니다.

### insert 메소드

```java 
static void insert(String parent, String left, String right) {
    Node parentNode = searchNode(root, parent);
    if(!left.equals(".")) {
        parentNode.left = new Node(left);
    }
    if(!right.equals(".")) {
        parentNode.right = new Node(right);
    }
}
```

트리 탐색의 시작은 항상 root 노드입니다. 입력 문자열이 `.`인 경우는 자식 노드가
없는 케이스이므로 이 상황만 예외처리를 해줍니다.
트리를 전위, 중위, 후위순회하는 코드는 특별할 것이 없습니다.

### 전체 코드
```java
import java.util.*;
import java.io.*;

public class Main {
    // 이진 트리를 위한 Node 클래스
    static class Node {
        String data;
        Node left;
        Node right;
        // left와 right 노드는 항상 null로 초기화
        Node(String data) {
            this.data = data;
        }
    }
    
    // target을 데이터로 갖는 node를 리턴하는 메소드
    static Node searchNode(Node node, String target) {
        // 만약 node가 null이라면 null을 반환
        if(node == null) return null;
        // 지금 노드의 데이터가 target이라면 현재노드를 반환
        if(node.data.equals(target)) return node;
        // 왼쪽 노드와 오른쪽 노드를 방문하는 재귀호출
        Node searchLeft = searchNode(node.left, target);
        Node searchRight = searchNode(node.right, target);
        Node ret = null;
        // 왼쪽 노드에서 원하는 노드를 찾은 경우
        if(searchLeft != null) ret = searchLeft;
        // 오른쪽 노드에서 원하는 노드를 찾은 경우
        else if(searchRight != null) ret = searchRight;
        return ret;
    }

    static void insert(String parent, String left, String right) {
        // parent 데이터를 갖는 노드를 찾은 후
        Node parentNode = searchNode(root, parent);
        // 자식이 있다면 자식 노드를 추가
        if(!left.equals(".")) {
            parentNode.left = new Node(left);
        }
        if(!right.equals(".")) {
            parentNode.right = new Node(right);
        }
    }

    static void preorder(Node node) {
        if(node == null) return;
        sb.append(node.data);
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(Node node) {
        if(node == null) return;
        inorder(node.left);
        sb.append(node.data);
        inorder(node.right);
    }

    static void postorder(Node node) {
        if(node == null) return;
        postorder(node.left);
        postorder(node.right);
        sb.append(node.data);
    }

    static Node root;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfNodes = Integer.parseInt(br.readLine());
        root = new Node("A");
        for(int i=0; i<numOfNodes; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            insert(st.nextToken(), st.nextToken(), st.nextToken());
        }

        preorder(root);
        sb.append("\n");
        inorder(root);
        sb.append("\n");
        postorder(root);
        System.out.println(sb);
    }
}
```

## ✅ 후기
이 문제는 3주 정도 전에 트리 관련된 문제를 몰아서 풀면서 풀었던 문제였습니다.
그 당시엔 트리에 익숙하지 않아서 꽤 힘들게 문제를 풀었었는데요. 
그때 풀었던 솔루션으로 pr을 작성하려고 코드를 찬찬히 살펴보니 정답만 맞았지
코드가 너무 엉망이라 그대로 pr을 쓸수가 없겠더군요. 다시 풀면서 훨씬 깔끔한
로직과 코드로 완성이 되었습니다. 그리고 3주 전에 스스로 잘 풀었다는 생각에
뿌듯했던 모습이 떠올라 피식했습니다. 사실 알고리즘 같이 긴 시간 실력을 키워야 하는
과목에서 3주는 스스로 변화를 느끼지 못할 정도로 짧은 시간이라고 생각합니다.
그래도 이번에 다시 문제를 풀면서 3주 전 보다 꽤 많이 성장했구나라는 생각이 들었습니다.
알고리즘 스터디 덕분이겠죠? ㅎㅎ 여유시간이 많지 않아서 할지말지 많이 고민했었는데
하길 잘했다는 생각이 듭니다.

