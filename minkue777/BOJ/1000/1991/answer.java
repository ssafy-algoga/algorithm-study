import java.util.*;
import java.io.*;

public class Main {
    static class Node {
        String data;
        Node left;
        Node right;

        Node(String data) {
            this.data = data;
        }
    }

    static Node searchNode(Node node, String target) {
        if(node == null) return null;
        if(node.data.equals(target)) return node;
        Node searchLeft = searchNode(node.left, target);
        Node searchRight = searchNode(node.right, target);
        Node ret = null;
        if(searchLeft != null) ret = searchLeft;
        else if(searchRight != null) ret = searchRight;
        return ret;
    }

    static void insert(String parent, String left, String right) {
        Node parentNode = searchNode(root, parent);
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