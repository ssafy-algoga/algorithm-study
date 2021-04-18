package 스터디._2월1주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 플로이드 {
	static int[][] graph;
	static int cityCnt; // 도시
	static int bus; // 버스
	public static final int MAX = 1000000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cityCnt = Integer.parseInt(br.readLine());
		bus = Integer.parseInt(br.readLine());
		graph = new int[cityCnt + 1][cityCnt + 1];

		for (int i = 1; i <= cityCnt; i++) {
			for (int j = 1; j <= cityCnt; j++) {
				if (i == j) {
					continue;
				}
				graph[i][j] = MAX;
			}
		}

		for (int m = 0; m < bus; m++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[start][end] = Math.min(graph[start][end], cost);    
		}

		floyd();
		print();
	}
	
	private  static void floyd() {
        // 거쳐가는  K
        for(int k = 1; k <= cityCnt; k++) {
            // 출발하는  i
            for(int i=1; i <= cityCnt; i++) {
                // 도착하는 j
                for(int j=1; j <= cityCnt; j++) {
                    //i-> k -> j 와  i-> j 거리를 비교해서 작은 값
                    graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
                }
            }
        }
    }
	
	public static void print() {
        StringBuilder sb = new StringBuilder();
        for(int i=1; i <= cityCnt; i++) {
            for(int j=1; j <= cityCnt; j++) {
                if(graph[i][j] >= MAX) sb.append("0 ");
                else sb.append(graph[i][j] + " ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }
}
