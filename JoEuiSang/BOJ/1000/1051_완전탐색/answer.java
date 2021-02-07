package 스터디._2월1주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 숫자정사각형 {
	static int R; //행 크기
	static int C; //열 열기
	static int[][] map; //입력 맵

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		map = new int[R][C];
		int answer = 1;  //초기값 주의!! 0을 주면 틀림.

		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c) - '0';
			}
		}

		// 좌상단 기준 순회
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				int width = 0; //넓이
				int inc = 0;	// 증가길이
				int target = map[r][c];	//좌상단 값

				while (true) {
					inc++;	//증가길이 ++
					boolean flag = true;	//모든 꼭지점의 값이 같은지 판단
					int[] dn = { r, r + inc, r + inc };	//행과 열의 증가에 따른 각 꼭지점 좌효
					int[] dm = { c + inc, c, c + inc };
					if (r + inc < R && c + inc < C) {
						for (int i = 0; i < 3; i++) {
							int nr = dn[i];
							int nc = dm[i];
							System.out.println(i+": "+(nr) + "," + (nc));
							if (target != map[nr][nc]) { //좌상단과 다른값이면
								flag = false;
								break;
							}
						}
						if (flag) { //모든값이 같다면
							width = (inc + 1) * (inc + 1);
							if (answer < width)
								answer = width;
						}

					} else {
						break;
					}
				}
			}
		}
		System.out.println(answer);
	}

}
