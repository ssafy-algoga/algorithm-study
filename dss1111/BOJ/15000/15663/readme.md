# 15663번: N과 M (9)

[문제 보러가기](https://www.acmicpc.net/problem/15663)

## 🅰 설계

재귀적으로 문제를 풀어보았습니다.  
boolean visited 로 탐색여부를 체크하고, 종료조건을 체크하는 그렇게 복잡하지 않은 과정입니다.  

```
void permutation(int[] resultArr,int level) // int 배열에 진행과정을 담고 level을 두어서 몇단계가 진행되었는지 체크했습니다.
```

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {
	static LinkedHashSet<String> set= new LinkedHashSet<String>();
	static boolean[] visited;
	static int[] arr;
	static int N,M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		visited = new boolean[N];
		
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		int [] resultArr;
		Arrays.sort(arr);
		for(int i=0;i<N;i++) {
			visited[i] = true;
			resultArr=new int[M];
			resultArr[0]=arr[i];
			permutation(resultArr,1);
			visited[i] = false;
		}
		
		for(String s : set) {
			System.out.println(s);
		}
	}
	public static void permutation(int[] resultArr,int level) {
		int i;
		if(level==M) {
			String str="";
			for(i=0;i<M;i++) {
				str+=Integer.toString(resultArr[i])+" ";
			}
			set.add(str);
			return;
		}
		for(i=0;i<N;i++) {
			if(!visited[i]) {
				visited[i]=true;
				resultArr[level]=arr[i];
				permutation(resultArr,level+1);
				visited[i]=false;
			}
		}
	}
}
```

## ✅ 후기

### 새롭게 알게되거나 공유해서 알게된 점

LinkedHashSet은 순서가 섞이지 않고 들어온 순서대로 출력해볼수 있는 set니다. 알고나니 과제에서도 쏠쏠하게 사용하게 되었습니다.  
이문서를 작성하면서 종완님의 코드를 구경했는데 int[]가 아닌 String을 통해 진행과정을 담는 stack을 구현하셨던데 제가한 방법보다 좋은 방법인 것 같습니다.  
https://github.com/LEEJ0NGWAN/algorithm-study/tree/main/LEEJ0NGWAN/BOJ/15000/15663  
또한 String을 직접 더하는 방법보다 StringBuilder를 썼으면 좀 더 빨랐을 것 같습니다.  

### 고생한 점

처음에는 map의 value를 이용해서 진행과정을 담아볼까 했는데 새로 put을 하면 순서가 뒤섞여서 set으로 변경하게 되었습니다.  
처음에는 Hashset을 사용해보다가 순서를 보장하지 않기때문에 LinkedHashSet으로 변경했습니다.  
