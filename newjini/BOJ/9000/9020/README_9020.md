# 9020 골드바흐의 추측

[문제 보러가기](https://www.acmicpc.net/problem/9020)

## 🅰 설계

먼저 소수를 찾고, 소수의 차이가 최소가 되는 경우를 찾는다.
1. 소수를 찾는다.
2. 입력받은 짝수에서 찾은 소수(=소수1)를 뺀 후, 그 값 또한 소수(=소수2)가 된다면,  
3. 소수2 - 소수1 의 최소가 되는 경우를 찾는다.  
## 코드
``` java
public class boj_9020 {
	
	static int N; // 짝수 n
	static int[] found; // 소수 찾은 배열
	static int res; // 가장 작은 골드바흐 파티션의 차이
	static int[] result; // 골드바흐 파티션 2개 

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		int[] num = new int[tc];
		for (int t = 0; t < tc; t++) {
			num[t] = Integer.parseInt(br.readLine());
			N = num[t];
			found = new int[num[t]];
			found[0] = found[1] = 1;
			res = Integer.MAX_VALUE; // 최댓값으로 초기화
			result = new int[2];
			findNum(num[t]); // 소수 찾기
			System.out.println(result[0] + " " + result[1]);
		}

	}

	private static void findNum(int num) {
		
		for (int i = 2; i * i < num; i++) { // 에라토스테네스 체로 소수 찾기
			for (int j = i * i; j < num; j += i) {
				found[j] = 1; // 소수가 아닌 경우, 1로 표시
				
			}
		}
		
		
		for (int i = 2; i < num; i++) {
			if (found[i] == 0) { // i가 소수인 경우,
				int isPrime = N-i; // i의 짝꿍 찾고
				if(found[isPrime] == 0) { // i의 짝꿍도 소수라면,
					if(res > Math.abs(isPrime-i)){ // 서로 가장 작은 차이가 나는 소수 한 쌍 찾기
						res = Math.abs(isPrime-i);
						result[0] = i;
						result[1] = isPrime;
					}
				}
			}
		}
	}

}

``` 
## ✅ 후기

시간초과가 났는데 맨처음에 소수를 쫙 찾고, 소수 한 쌍을 조합으로 소수 2개를 뽑아서 났던 것 같다.  
소수 한 쌍을 구할 때, (찾은 소수, N -찾은 소수) 하면 된다는 것을 알게 되어서 시간초과를 해결할 수 있었다.