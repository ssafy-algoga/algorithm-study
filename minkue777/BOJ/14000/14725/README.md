# 14725번 개미굴
[문제 보러가기](https://www.acmicpc.net/problem/14725)

## 🅰 설계
트라이 자료구조를 설계만 한다면 트리를 순회하는 것은 어렵지 않습니다.
다만 사전순으로 앞에 있는 문자열을 먼저 출력해야 하기때문에
`HashMap`이 아닌 `TreeMap`을 써야합니다.

```java
import java.io.*;
import java.util.*;

public class Main {
    static TrieNode root = new TrieNode();

    static class TrieNode {
        Map<String, TrieNode> childNodes = new TreeMap<>();

        void insert(String[] keys) {
            TrieNode node = root;
            for(String key : keys) {
                node = node.childNodes.computeIfAbsent(key,
                        c -> new TrieNode());
            }
        }

        static void search(TrieNode node, int depth) {
            for(String key : node.childNodes.keySet()) {
                StringBuilder sb = new StringBuilder();
                for(int cnt = 0; cnt < depth; cnt++) {
                    sb.append("--");
                }
                sb.append(key);
                System.out.println(sb);
                search(node.childNodes.get(key), depth+1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfAnt = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int idx = 0; idx < numOfAnt; idx++) {
            st = new StringTokenizer(br.readLine());
            int numOfString = Integer.parseInt(st.nextToken());
            String[] keys = new String[numOfString];
            for(int cnt = 0; cnt < numOfString; cnt++) {
                keys[cnt] = st.nextToken();
            }
            root.insert(keys);
        }
        TrieNode.search(root, 0);
    }
}
```

## ✅ 후기
`computeIfAbsent` 라는 좋은 API가 있더군요. `putIfAbsent`는 먼저 value값에 해당하는 계산을 마친 후
키가 존재하면 put을 하지 않는 방식이지만 `computeIfAbsent`는 먼저 키의 유무를 체크한 후 키가 있다면
연산을 수행하지 않기 때문에 좀 더 효과적입니다.