import java.util.*;
import java.io.*;

public class Main {
    static String s1, s2;
    static int[][] cache;

    static int lcs(int idx1, int idx2) {
        if(idx1 == 0 || idx2 == 0) return 0;
        if(cache[idx1][idx2] != -1) return cache[idx1][idx2];
        if(s1.charAt(idx1-1) == s2.charAt(idx2-1)) {
            return cache[idx1][idx2] = lcs(idx1-1, idx2-1)+1;
        }
        return cache[idx1][idx2] = Math.max(lcs(idx1-1, idx2), lcs(idx1, idx2-1));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s1 = br.readLine();
        s2 = br.readLine();
        cache = new int[s1.length()+1][s2.length()+1];
        for(int[] row : cache) Arrays.fill(row, -1);
        lcs(s1.length(), s2.length());
        System.out.println(cache[s1.length()][s2.length()]);
    }
}
