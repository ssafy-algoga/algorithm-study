import java.util.*;
import java.io.*;

public class Main {
    static int[] numbers;
    static boolean[] isSelected;
    static int[] array;
    static int n, m;
    static StringBuilder sb = new StringBuilder();

    static void combination(int cnt) {
        // base case
        if (cnt == m) {
            for (int d : numbers) {
                sb.append(d).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if(!isSelected[i]) {
                /* the fact that array[i-1] is equal to array[i] and i-1 is not selected
                means that this is duplicate case */

                if(i != 0 && !isSelected[i-1] && array[i] == array[i-1]) continue;
                isSelected[i] = true;
                numbers[cnt] = array[i];
                combination(cnt + 1);
                // back-tracking
                isSelected[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numbers = new int[m];
        isSelected = new boolean[n];
        array = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) array[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(array);
        combination(0);
        System.out.println(sb);
    }
}