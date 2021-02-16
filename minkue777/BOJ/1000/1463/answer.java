import java.io.*;
import java.util.*;

public class Main {
    static int[] cache;
    static int makeOne(int n) {
        if(cache[n] != 0) return cache[n];
        if(n == 1) return 0;

        int a = makeOne(n-1);
        if(n % 2 == 0) a = Math.min(a, makeOne(n/2));
        if(n % 3 == 0) a = Math.min(a, makeOne(n/3));
        return cache[n] = a+1;
    }

    static int makeOne1(int n) {
        cache[1] = 0;
        for(int i=2; i<=n; i++) {
            int a = cache[i-1];
            if(i % 2 == 0) a = Math.min(a, cache[i/2]);
            if(i % 3 == 0) a = Math.min(a, cache[i/3]);
            cache[i] = a+1;
        }
        return cache[n];
    }
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        cache = new int[n+1];
        System.out.println(makeOne1(n));
    }
}