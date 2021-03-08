# 1991번 트리순회
[문제 보러가기](https://www.acmicpc.net/problem/1991)

## 🅰 설계
각각 트리순회 방식은 어떻게 이루어지는지 이해하고 있었기 때문에 따로 그림을 그리고 설계하진 않았습니다.

처음 생각한 것은 이진트리이기 때문에 배열을 통해 노드들을 표현하면 어떨까 생각했습니다. 루트가 1이고 자식이 2n, 2n+1인 방식으로요.
문제는 노드가 순서대로 들어온다는 보장도 없었고, 완전이진트리가 아니기 때문에 배열이 엄청많이 필요할 수도 있어서 이생각은 좀 코딩해보다가 버렸습니다.

다음은 정석적으로 Node클래스를 만들어서 트리를 만드는 방법입니다.
```java
	static class Node{
		int left;
		int right;
		char value;
		Node(char v,int l,int r){
			value=v;
			left=l;
			right=r;
		}
	}
```
값을 가지고 있고 left, right를 가지고있는 단순한 노드입니다.
```java
	static Node [] nodes = new Node[27];
  //A - 64 = 1
  //Z - 64 = 26
  
  	static int insert(char n) {
		if(n=='.')
			return 0;
		else
			return n-64;
	}
```
모든 노드는 A~Z의 값을 가지기 때문에 배열을 27개를 썼습니다. 0번은 빈값을 처리하기 위해 두었습니다.
따라서 insert를 수행하면 각 알파벳에 해당하는 노드가 해당 위치에 저장됩니다. 
```java
	static void pre(int n) {
		if(n>26)return;
		if(n==0)return;
		result.append(nodes[n].value);
		pre(nodes[n].left);
		pre(nodes[n].right);
	}
	static void in(int n) {
		if(n>26)return;
		if(n==0)return;
		in(nodes[n].left);
		result.append(nodes[n].value);
		in(nodes[n].right);
	}
	static void post(int n) {
		if(n>26)return;
		if(n==0)return;
		post(nodes[n].left);
		post(nodes[n].right);
		result.append(nodes[n].value);
	}
```
넵... 각각 전위,중위,후위순회입니다. 배열범위를 넘어가거나 배열의0번을 가리키는 경우를 빼고 재귀를 돌아 탐색합니다. 

### 전체코드
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static class Node{
		int left;
		int right;
		char value;
		Node(char v,int l,int r){
			value=v;
			left=l;
			right=r;
		}
	}
	static Node [] nodes = new Node[27];
	static StringBuilder result = new StringBuilder(); 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int n=0;n<N;n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char v = st.nextToken().charAt(0);
			char l = st.nextToken().charAt(0);
			char r = st.nextToken().charAt(0);
			nodes[insert(v)]= new Node(v,insert(l),insert(r));
		}
		pre(1);
		result.append("\n");
		in(1);
		result.append("\n");
		post(1);
		System.out.println(result);
	}
	static int insert(char n) {
		if(n=='.')
			return 0;
		else
			return n-64;
	}
	static void pre(int n) {
		if(n>26)return;
		if(n==0)return;
		result.append(nodes[n].value);
		pre(nodes[n].left);
		pre(nodes[n].right);
	}
	static void in(int n) {
		if(n>26)return;
		if(n==0)return;
		in(nodes[n].left);
		result.append(nodes[n].value);
		in(nodes[n].right);
	}
	static void post(int n) {
		if(n>26)return;
		if(n==0)return;
		post(nodes[n].left);
		post(nodes[n].right);
		result.append(nodes[n].value);
	}
}
```
## ✅ 후기
### 새롭게 알게되거나 공유해서 알게된 점
없습니다  
### 고생한 점
완전이진트리가 아니라는 생각을 좀 빨리했었으면 쓸데없이 배열로 구현하려는 생각은 안했을 것 같습니다.
