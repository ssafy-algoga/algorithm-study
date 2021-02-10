# 15663번 N과 M(9)

[문제 보러가기](https://www.acmicpc.net/problem/15663)

## 🅰 설계

1. 입력받은 배열을 먼저 sort한다. ( ex) 9 7 1 9 -> 1 7 9 9 )
2. 순열을 이용해서 현재 배열 index, depth( 몇 번 돌았는지 m과 비교 ) 를 확인한다.
3. 중복 방지를 위해 마지막에 넣은 변수와 다른 지 확인한다.
4. 재귀가 끝나고 다음을 위해 방문했던 값을 제거하고 다시 재귀.

![15663](https://media.discordapp.net/attachments/363682044208873480/808273975691968512/image.png)

## 코드

```
public class boj_15663 {
	static int n;
	static int m;
	static int[] arr;
	static StringBuilder sb;
	static boolean[] isSelected;
	static LinkedList<Integer> result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		arr = new int[n];
		result = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr); // 입력 받은 배열 sort
		ArrayList<Integer> result = new ArrayList<>(); // 수열 생성을 위한 배열 
		isSelected = new boolean[n]; 
		sb = new StringBuilder(); 
		permu(0);
		
	}

	private static void permu(int cnt) {
		if (cnt == m) { // depth가 m이면 출력 
			for (int i : result) { 
				System.out.print(i+" ");
			}
			System.out.println();
			return;
		}
		int lastNum = -1; // 중복 방지 변수
		for (int i = 0; i < n; i++) {
			if(isSelected[i] || lastNum == arr[i]) continue;
			
			result.add(arr[i]);
			lastNum = arr[i];
			isSelected[i] = true;
			permu(cnt+1);
			isSelected[i] = false;
			result.remove(result.size()-1);
		}
	}
}
```

### 이전 코드
이해하지 못하고 도움받아 썼던 코드
```
private static void func(int idx, int depth, ArrayList<Integer> result) {
		if (depth == m) { // depth가 m이면 출력 
			for (int i : result) { // 5) arr[0] arr[1] 출력
				System.out.print(i+" ");
			}
			System.out.println();
			return;
		}
		int lastNum = -1; // 중복 방지 변수
		for (int i = 0; i < n; i++) {
			
			if (!visited[i] && lastNum != arr[i]) { // 1) arr[0] 3) arr[1] 7) arr[2] ... 반복
					
				visited[i] = true;
				lastNum = arr[i];
				result.add(arr[i]); // 2) arr[0] 4) arr[1]
				func(i, depth + 1, result); // depth 증가
				result.remove(result.size() - 1); // 6) arr[0] 만 남는다. 
				visited[i] = false;
			}
		}
	}
```

## ✅ 후기

처음에 중복방지 변수를 생각 못하고 출력할 때 중복을 걸렀더니 코드가 복잡해졌다.(set 활용하고 ..)  
재귀가 어려워서 너무 헷갈려서 도움을 받았다.   
아직도 헷갈린 것 같아서 많이 풀어봐야 할 것 같다.  
--> 순열을 다시 복습하고 순열을 적용해서 풀었다. 조금은 이해한 것 같다.