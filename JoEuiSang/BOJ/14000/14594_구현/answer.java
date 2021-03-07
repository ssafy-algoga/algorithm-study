package 스터디._2월4주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class room {
	int no;
	int start, end;

	public room(int no, int start, int end) {
		this.no = no;
		this.start = start;
		this.end = end;
	}
}

public class Boj14594동방프로젝트small {
	static int N, M, X, Y, ans = 1;
	static room[] rooms;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		rooms = new room[N + 1];

		for (int i = 1; i <= N; i++) {
			rooms[i] = new room(i, i, i);
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());

			for (int num = X; num <= Y; num++) {
				for (int start = rooms[num].start; start <= rooms[num].end; start++) {
					rooms[start].start = rooms[X].start;
					rooms[start].end = rooms[Y].end;
				}
			}
		}

		int curEnd = rooms[1].end;
		for (int i = 1; i <= N; i++) {
			if (rooms[i].end != curEnd) {
				ans++;
				curEnd = rooms[i].end;
			}
		}

		System.out.println(ans);

	}

}
