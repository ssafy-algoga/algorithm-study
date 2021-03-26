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