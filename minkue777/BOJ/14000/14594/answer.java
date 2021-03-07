import java.io.*;
import java.util.*;

public class Main {
    static int[] room;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        room = new int[n+1];
        for(int i=1; i<=n; i++) room[i] = i;
        int numOfBreaks = Integer.parseInt(br.readLine());
        for(int i=0; i<numOfBreaks; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            room[start] = Math.max(room[start], end);
        }

        int count = 0;
        int criterion = room[1];
        for(int i=1; i<room.length; i++) {
            if(room[i] > criterion) criterion = room[i];
            if(i == criterion) count++;
        }
        System.out.println(count);
    }
}