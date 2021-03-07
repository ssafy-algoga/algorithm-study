import java.io.*;
import java.util.*;

public class Main {
    static int[] longest;
    static final int INF = 987654321;

    static int binarySearch(int[] array, int high, int target) {
        int low = -1;
        while(low+1 < high) {
            int mid = (low + high)/2;
            if(array[mid] < target) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return high;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        longest = new int[size+1];
        Arrays.fill(longest, INF);
        longest[0] = -INF;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int low = -1;
        int high = 0;
        for(int i=0; i<size; i++) {
            int input = Integer.parseInt(st.nextToken());
            int idx = binarySearch(longest, high+1, input);
            if(longest[idx] == input) continue;
            longest[idx] = input;
            high = Math.max(high, idx);
        }
        System.out.println(high);
    }
}