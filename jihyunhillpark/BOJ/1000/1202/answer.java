import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{

	static int[] jewelry;
	static int[] knapsack;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long value = 0;
		knapsack = new int[K];
		//a. 보석 무게가 작은 순이 우선순위를 가지는 우선순위 큐이다.
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] arr1, int[] arr2) {
				return arr1[0] - arr2[0]; //보석 무게가 작은 순이 우선순위를 가지는 우선순위큐이다.
			}
		});
		//b. 현재 가방이 들 수 있는 제한된 무게 내에서 담을 수 있는 모든 보석들 중 최대 값어치를 가져오는 우선순위 큐.
		PriorityQueue<Integer> pool = new PriorityQueue<>(Collections.reverseOrder()); //보석의 값어치가 높을수록 우선순위가 높은 max heap
		for(int i = 0 ; i< N ; i++) {
			st = new StringTokenizer(in.readLine()," ");
            jewelry = new int[2];
			jewelry[0] = Integer.parseInt(st.nextToken()); //보석 무게 저장
			jewelry[1] = Integer.parseInt(st.nextToken()); //보석 값어치 저장
			pq.add(jewelry); //큐에 추가~
		}

		for(int i = 0 ; i< K ; i++) //가방 무게 받아와서 가방 무게 배열에 저장
			knapsack[i] = Integer.parseInt(in.readLine());
		Arrays.sort(knapsack); //가방 무게를 오름차순으로 정렬

		for(int i = 0 ; i < K ; i++) {
      //from 가장 작은 용량 가방 to 제일 큰 용량
			while(!pq.isEmpty()) {
				int[] e = pq.poll();
				if(e[0] <= knapsack[i] )//현재 가방 용량보다 작거나 같은 무게의 보석들은
					pool.add(e[1]); //pool에 넣는다.
				else {
					pq.add(e); //현재 가방 용량을 초과하는 보석이 나왔으므로 바로 직전까지 꺼낸 보석들만 담을 수 있고, 다음 작업을 위해 다시 넣어준다.
					break;
				}
			}
			if(pool.size() != 0) {
				value += pool.poll(); //담을 수 있는 모든 보석들 중에서 가장 큰 값어치를 하는 보석을 담는다.
			}
		}
		System.out.println(value);
	}
}
