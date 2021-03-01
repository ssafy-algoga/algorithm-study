import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_Goldbach_6588 {
    public static boolean[] checkPrime = new boolean[1000001];
    public static StringBuilder sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();
        Arrays.fill(checkPrime, true);
        setPrime();
        while (true){
            int n = sc.nextInt();
            if(n == 0) break;
            find(n);
        }
        System.out.println(sb);
    }

    public static void setPrime(){
        for(int i = 2; i <= 1000000; i++){
            if(checkPrime[i]) {
                for (int j = 2 * i; j <= 1000000; j += i) {
                    checkPrime[j] = false;
                }
            }
        }
    }

    public static void find(int num) {

        for(int i = 3; i <= num/2; i++){
            if(checkPrime[i] && checkPrime[num - i]){
                sb.append(num).append(" = ").append(i).append(" + ").append(num - i).append("\n");
                return;
            }
        }
        sb.append("Goldbach's conjecture is wrong.\n");
        return;
    }

}
