import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_JewelThief_1202 {
    public static class Jewel {
        int weight;
        int value;

        public Jewel(int w, int v) {
            this.weight = w;
            this.value = v;
        }

    }

    public static int N, K;
    public static int[] bag;
    public static Jewel[] jewels;
    public static PriorityQueue<Jewel> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bag = new int[K];
        jewels = new Jewel[N];
        pq = new PriorityQueue<Jewel>((a,b) -> b.value - a.value);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewels, Comparator.comparingInt(o -> o.weight));
        Arrays.sort(bag);

        long sum = 0;
        int idx = 0;
        for (int i = 0; i < K; i++) {
            int capa = bag[i];
            while (idx < N && jewels[idx].weight <= capa){
                pq.offer(jewels[idx++]);
            }

            if (!pq.isEmpty()) {
                sum += pq.poll().value;
            }

        }
        System.out.println(sum);
    }
}
