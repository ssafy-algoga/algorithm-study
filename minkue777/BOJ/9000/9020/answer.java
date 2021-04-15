import java.io.*;
import java.util.*;

public class Main {
    static final int n = 10000;
    static boolean[] isPrime = new boolean[n+1];

    static void eratosthenes() {
        int sqrtn = (int)Math.sqrt(n);
        for(int i=2; i<sqrtn; i++) {
            if(isPrime[i]) {
                for(int j=i*i; j<=n; j+=i)
                    isPrime[j] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Arrays.fill(isPrime, true);
        eratosthenes();
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            int target = Integer.parseInt(br.readLine());
            for(int i = target/2; i>=0; i--) {
                if(isPrime[i] & isPrime[target - i]) {
                    sb.append(i).append(" ").append(target-i).append("\n");
                    break;
                }
            }
        }
        System.out.println(sb);
    }
}