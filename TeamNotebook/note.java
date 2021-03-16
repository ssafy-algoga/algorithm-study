import java.util.*;

public class note {
    /*
    에라토스테네스의 체 알고리즘
    시간복잡도 : O(N * log(log(N)) ~ O(N)

    @ pram size : 최댓값
    @ return : 1 ~ size 까지 소수여부를 담은 boolean 배열
     */
    static boolean[] eratosthenes(int size) {
        boolean[] isPrime = new boolean[size+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        int sqrtn = (int)Math.sqrt(size);
        for(int i = 2; i <= sqrtn; i++) {
            // 만약 i가 소수라면
            if(isPrime[i]) {
                // i의 배수들은 전부 합성수로 변경
                for(int j = i*i; j <= size; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }

    /*
    주어진 순열의 다음 순열(사전순)을 구하는 메소드
    while(nextPermutation(perm)) 으로 사용하기 위해서
    첫 순열은 주어져야함에 유의

    @ param perm : 순열을 표현하는 배열
    @ return 다음 순열이 존재한다면 true, 존재하지 않는다면 false
     */
    static boolean nextPermutation(int[] perm) {
        // step1 peek 찾기
        int size = perm.length;
        int i = size-1;
        while(i > 0 && perm[i-1] >= perm[i]) --i;
        if(i == 0) return false;
        // step2 교환할 위치 찾기
        int j = size-1;
        while(perm[i-1] >= perm[j]) --j;
        // step3 교환
        swap(perm, i-1, j);
        // step4 오름차순 정렬
        int k = size-1;
        while(i < k) {
            swap(perm, i++, k--);
        }
        return true;
    }

    /*
    주어진 순열이 사전순으로 몇 번째 순열인지 구하는 메소드

    @ pram perm : 순열을 표현하는 배열
    @ return : 입력으로 주어진 순열의 순서
     */
    static long nthPermutation(int[] perm) {
        int n = perm.length;
        if(n == 1) return 1;
        long num = 0;
        for(int i=1; i<n; i++) {
            num += (perm[i] < perm[0]) ? 1 : 0;
        }
        return num * factorial(n-1) + nthPermutation(Arrays.copyOfRange(perm, 1, n));
    }

    /*
    1 ~ size 으로 이루어진 순열에서 사전순으로 order번째 오는 순열을 반환하는 메소드

    @ pram choice(static) : 메소드가 종료 후 order번째 순열이 담겨있는 배열
           size           : 순열을 이루는 숫자의 개수 1 ~ size
           cnt            : 재귀호출을 위한 변수. 처음 호출시 반드시 cnt에 size 입력
           order          : 구하고 싶은 순열의 순서
    @ return None. 결과 순열은 choice에 담김
     */

    static int[] choice;
    static boolean[] isSelected;
    static int size;

    static void nthPermutation(int cnt, long order) {
        if(cnt == 0) return;

        long criterion = factorial(cnt-1);
        int idx = 1;
        while(isSelected[idx]) idx++;

        while(criterion < order) {
            order -= criterion;
            do idx++;
            while(isSelected[idx]);
        }
        isSelected[idx] = true;
        choice[size - cnt] = idx;
        nthPermutation(cnt-1, order);
    }

    static void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    static long factorial(int n) {
        if(n == 1 || n == 0) return 1;
        return n * factorial(n-1);
    }

    public static void main(String[] args) {
        /* eratosthenes 테스트 코드
        boolean[] isPrime = eratosthenes(100);
        for(int i=1; i<=100; i++) {
            if(isPrime[i]) System.out.print(i + " ");
        }
         */
        
        /* nextPermutation 테스트 코드
        int[] input = {1, 2, 3, 4};
        while(nextPermutation(input)) {
            for(int d : input) {
                System.out.print(d + " ");
            }
            System.out.println();
        }
         */

        /* nthPermutation(int[]) 테스트 코드
        int[] input = {1, 2, 3, 4};
        System.out.println(nthPermutation(input));
         */
        
        /* nthPermutation(int, long) 테스트 코드
        size = 4;
        choice = new int[size];
        isSelected = new boolean[size+1];
        nthPermutation(size, 1);
        for(int d : choice) {
            System.out.print(d + " ");
        }
         */
    }
}
