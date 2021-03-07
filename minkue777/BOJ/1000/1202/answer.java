import java.io.*;
import java.util.*;

public class Main {
    static class Jewelry {
        int weight;
        int price;
        Jewelry(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfJewelry = Integer.parseInt(st.nextToken());
        int numOfBag = Integer.parseInt(st.nextToken());
        Jewelry[] jewelries = new Jewelry[numOfJewelry];
        for(int i=0; i<numOfJewelry; i++) {
            st = new StringTokenizer(br.readLine());
            jewelries[i] = new Jewelry(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }
        int[] bagLimit = new int[numOfBag];
        for(int i=0; i<numOfBag; i++) {
            bagLimit[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(jewelries, (a, b) -> a.weight - b.weight);
        Arrays.sort(bagLimit);

        PriorityQueue<Jewelry> pq = new PriorityQueue<>((a, b) -> b.price - a.price);
        long sum = 0;
        int pointer = 0;
        for(int i=0; i<numOfBag; i++) {
            int weightLimit = bagLimit[i];
            while(pointer < numOfJewelry &&
                    jewelries[pointer].weight <= weightLimit) {
                pq.offer(jewelries[pointer++]);
            }
            if(!pq.isEmpty()) {
                sum += pq.poll().price;
            }
        }
        System.out.println(sum);
    }
}