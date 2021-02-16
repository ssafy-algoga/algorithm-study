import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class P9663 {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, count=0;
    static int[] arr;
    static boolean[] col, ul, ur;

    static void dfs(int i) {
        if (i==N) {
            count++;
            return;
        }

        for (int j=0; j<N; j++) {

            if (col[j] || ul[i+j] || ur[i+Math.abs(j-N+1)])
            continue;

            arr[i] = j;
            col[j] = ul[i+j] = ur[i+Math.abs(j-N+1)] = true;
            dfs(i+1);
            col[j] = ul[i+j] = ur[i+Math.abs(j-N+1)] = false;
        }
    }
    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        col = new boolean[N];
        ur = new boolean[2*N];
        ul = new boolean[2*N];

        dfs(0);

        bw.write(count+"\n"); bw.flush();
        bw.close(); br.close();
    }
}

