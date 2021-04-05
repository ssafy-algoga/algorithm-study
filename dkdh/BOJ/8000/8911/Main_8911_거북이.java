package boj.m3w5_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_8911_거북이 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		// 북 동 남 서
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		
		for (int t = 1; t <= T; t++) {			
			// 거북이 방향
			int d = 0;
			
			// 거북이 위치
			int x = 0;
			int y = 0;
			
			// maxY maxX minY minX
			int[] mm = {0, 0, 0, 0};
			
			String program = br.readLine();
			
			for (int i = 0, size=program.length(); i < size; i++) {
				int p = program.charAt(i);
				
				if(p == 'F' || p == 'B') {
					if(p == 'B') {
						d = (d+2)%4;
					}
					x += dx[d];
					y += dy[d];
					
					if(d == 0)
						mm[0] = Math.max(mm[0], y);
					else if(d==1)
						mm[1] = Math.max(mm[1], x);
					else if(d==2)
						mm[2] = Math.min(mm[2], y);
					else
						mm[3] = Math.min(mm[3], x);
					
					if(p=='B')
						d = (d+2)%4;
				}
				else if(p == 'L') {
					d = (d+3)%4;
				}
				else if(p == 'R') {
					d = (d+1)%4;
				}
				
			}
			
			int w = mm[1] - mm[3];
			int h = mm[0] - mm[2];
			
			sb.append(w*h).append("\n");
		}
		
		System.out.print(sb.toString());
	}

}
