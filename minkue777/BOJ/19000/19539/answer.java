import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int numOfTress = Integer.parseInt(br.readLine());
        int numOfOnes = 0;
        int numOfTwos = 0;
        int[] height = new int[numOfTress];
        st = new StringTokenizer(br.readLine());
        for(int idx = 0; idx < numOfTress; idx++) {
            height[idx] = Integer.parseInt(st.nextToken());
            numOfOnes += height[idx] % 2;
            numOfTwos += height[idx] / 2;
        }
        if(numOfTwos >= numOfOnes && (numOfTwos-numOfOnes) % 3 ==0) {
            System.out.println("YES");
        } else System.out.println("NO");
    }
}