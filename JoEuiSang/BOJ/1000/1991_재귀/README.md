# 1991 트리 순회
[문제 보러가기](https://www.acmicpc.net/problem/1991)

## 🅰 설계
1. 각 노드의 값, 자식들에 대한 정보를 나타내는 Node 클래스를 사용했습니다.
2. 각 알파벳을 A : 1 ~ Z: 26 인덱스값으로 가지고 자신의 자식을 나타내는 배열을 활용하였습니다.




## 주요 코드 설명
### 알파벳 노드 클래스
```java
class Node {
	int root, left, right;

	public Node(int root, int left, int right) {
		super();
		this.root = root;
		this.left = left;
		this.right = right;
	}

}
```




### 알파벳 노드간의 관계 생성하기

```java
for (int tc = 1; tc <= N; tc++) {
    st = new StringTokenizer(br.readLine());
    int idx = st.nextToken().charAt(0) - 'A' + 1; // . : -18 A~Z : 1~26
    int left = st.nextToken().charAt(0) - 'A' + 1;
    int right = st.nextToken().charAt(0) - 'A' + 1;

    tree[idx] = new Node(idx, left, right);
}
```



### 전위순회

```java
sb.append((char) (tree[idx].root + 'A' - 1));

if (left > 0) {// 왼쪽자식이 있으면
    preOrder(tree, left);
}

if (right > 0) {// 오른쪽 자식이 있으면
    preOrder(tree, right);
}
```

### 후위순회

```java
if (left > 0) {// 왼쪽자식이 있으면
			postOrder(tree, left);
}

if (right > 0) {// 오른쪽 자식이 있으면
    postOrder(tree, right);
}

sb.append((char) (tree[idx].root + 'A' - 1));
```

### 중위순회

```java
if (left > 0) {// 왼쪽자식이 있으면
    inOrder(tree, left);
}

sb.append((char) (tree[idx].root + 'A' - 1));

if (right > 0) {// 오른쪽 자식이 있으면
    inOrder(tree, right);
}
```

## ✅ 후기
### 특별히 어려웠던 점은 없었고 캐릭터타입과 아스키 코드값을 변환하는게 좀 헷갈렸습니다.