# 11404번 플로이드

[문제 보러가기](https://www.acmicpc.net/problem/11404)

## 🅰 설계

1. 배열을 선언하고 초기화할 때, 시작도시와 도착도시가 같은 경우는 0으로 초기화하고 나머지는 무한으로 초기화
2. 배열은 [시작도시][도착도시] = 비용 으로 만들기
3. 플로이드 알고리즘 활용
4. 플로이드 이후에도 배열의 값이 무한값이면 0으로 출력 

![17413](https://media.discordapp.net/attachments/802048763232780321/807830226913722409/image.png)

## 코드
public class boj_11404 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i==j) arr[i][j] = 0;
				else {
					arr[i][j] = 9999999; 
				}	
			}
		}
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a=0, b=0, c=0;
			a = Integer.parseInt(st.nextToken()); // 시작도시
			b = Integer.parseInt(st.nextToken()); // 도착도시
			c = Integer.parseInt(st.nextToken()); // 비용
			arr[a-1][b-1] = Math.min(c, arr[a-1][b-1]); // 값이 하나가 아닐 수도 있어서 더 작은 값 선택
		}
		
		// 플로이드 알고리즘
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == 9999999) {
					arr[i][j] = 0;
				}
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}

## ✅ 후기

플로이드 알고리즘 몰랐는데 검색해서 배웠다.  
플로이드 : 모든 정점에서 모든 정점으로의 최단 경로(비용) 구하기 (단, 거쳐가는 정점을 기준으로)   
분명히 맞게 썼는데 자꾸 값이 조금 다르게 나와서 문제를 다시 읽었더니  
"시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다."라고 써있었다.  
그래서 배열만들 때 `arr[a-1][b-1] = Math.min(c, arr[a-1][b-1]);` 을 추가했다.  
문제를 잘 읽어야겠다.