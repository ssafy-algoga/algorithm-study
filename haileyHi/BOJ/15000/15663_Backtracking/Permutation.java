import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class Main {
    static boolean[] chosen;
    static int[] numbers;
    static int[] permutations;
    public static int N;
    public static int M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        numbers = new int[N];
        permutations = new int[M];
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }
        chosen = new boolean[N];
        Arrays.sort(numbers);
        search(0);
    }
    public static void search(int cnt){
        if(cnt == M){
            StringBuilder builder = new StringBuilder();
            for (int i : permutations) {
                if(builder.length()!=0) builder.append(" ");
                builder.append(i);
            }
            System.out.println(builder);
        }else{
            for (int i = 0; i < N; i++) {
                if(!chosen[i]) {
                    if(i >= 1 && !chosen[i-1] && numbers[i-1] == numbers[i]) continue;
                    chosen[i] = true;
                    permutations[cnt] = numbers[i];
                    search(cnt + 1);
                    chosen[i] = false;
                }
            }
        }
    }
}
