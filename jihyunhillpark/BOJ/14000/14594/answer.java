import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		int from,to,fill,intgrt,cnt = 1;
		int[] clubs = new int[N]; //동아리 방 번호를 담을 배열입니다.
		for(int i = 0; i < N; i++)
			clubs[i] = i+1;
		for(int i = 0 ; i < M;i++) {
			st = new StringTokenizer(in.readLine()," ");
			from = Integer.parseInt(st.nextToken()); //부술 동아리 구간 처음 부분
			to = Integer.parseInt(st.nextToken()); //부술 동아리 구간 끝 부분
			fill = clubs[from-1]; // fill = 첫번 쨰로 시작되는 방으로 통합 되는 것임. 채울 방 번호를 담음  
			intgrt = clubs[to-1]; // intgrt = 부술 동아리 구간의 끝 방 번호로, 뒤에 통합되었던 방들까지 바꿔줘야 함.
			Arrays.fill(clubs, from, to, fill); //입력받은 구간 끝 까지 방을 통합한다.
			for(int j = to; j < N ;j++) { // 구간 끝에 해당하는 방 역시 전에 뒷방들과 통합되었다면 같이 통합해줘야 한다.
				if( clubs[j] == intgrt ) clubs[j] = fill;
				else break;
			}
		}
		for(int i = 1; i < N; i++)
			if(clubs[i] != clubs[i-1]) cnt++; //방번호가 달라지면 방 갯수를 +1하며 동아리 방의 개수를 구한다.
		System.out.println(cnt);
	}
}
