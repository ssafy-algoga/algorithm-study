import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Jewelry implements Comparable<Jewelry> {
        int price;
        int weight;

        Jewelry(int price, int weight) {
            this.price = price;
            this.weight = weight;
        }

        @Override
        public int compareTo(Jewelry o) {
            return this.weight - o.weight;
        }
    }

    static int N, K;
    static long result=0;
    static ArrayList<Jewelry> jewels = new ArrayList<>();
    static ArrayList<Integer> bags = new ArrayList<>();
    static PriorityQueue<Integer> candidate = new PriorityQueue<>(Collections.reverseOrder());
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            Jewelry jew = new Jewelry(price, weight);
            jewels.add(jew);
        }

        Collections.sort(jewels); //무게 가벼운 순으로 정렬

        for (int k = 0; k < K; k++) {
            int weight = Integer.parseInt(br.readLine());
            bags.add(weight);
        }
        Collections.sort(bags); //가방 용량 작은순으로 정렬
        int i = 0;

        for(int bag :bags) { //작은가방부터 채워나가기
            while (i < N && bag >= jewels.get(i).weight) { //가방에 넣을수 있는 무게면
                candidate.add(jewels.get(i++).price); //후보군에 넣기
            }
            if (!candidate.isEmpty()) { //후보군이 있으면
                result += candidate.poll(); // 후보군중에 가장 비싼거 담기
            }
        }
        System.out.println(result);
    }
}
