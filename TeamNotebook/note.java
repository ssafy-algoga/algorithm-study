import java.util.*;

public class note {

    static int size;
    static int[] choice;
    static boolean[] isSelected;

    /*
     에라토스테네스의 체 알고리즘
     시간복잡도 : O(N * log(log(N)) ~ O(N)

     @param size : 최댓값
     @return : 1 ~ size 까지 소수여부를 담은 boolean 배열
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

     @param perm : 순열을 표현하는 배열
     @return 다음 순열이 존재한다면 true, 존재하지 않는다면 false
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

     @param perm   : 순열을 표현하는 배열
     @param return : 입력으로 주어진 순열의 순서
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

     @param choice(static)      : 메소드가 종료 후 order번째 순열이 담겨있는 배열
     @param size(static)        : 순열을 이루는 숫자의 개수 1 ~ size
     @param isSelected(static)  : 숫자의 선택 여부가 담겨있는 배열
     @param cnt                 : 재귀호출을 위한 변수. 처음 호출시 반드시 cnt에 size 입력
     @param order               : 구하고 싶은 순열의 순서
     */

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

    /*
     1 ~ size로 이루어진 순열을 출력하는 알고리즘

     @param choice(static)      : 메소드가 종료 후 order번째 순열이 담겨있는 배열
     @param size(static)        : 순열을 이루는 숫자의 개수 1 ~ size
     @param isSelected(static)  : 숫자의 선택 여부가 담겨있는 배열
     @param cnt                 : 현재까지 선택한 숫자의 개수. 처음 호출시 반드시 0을 입력
     */
    static void permutation(int cnt) {
        if(cnt == size) {
            for(int idx = 0; idx < size; idx++) {
                System.out.print(choice[idx] + " ");
            }
            System.out.println();
            return;
        }

        for(int idx = 1; idx <= size; idx++) {
            if(!isSelected[idx]) {
                choice[cnt] = idx;
                isSelected[idx] = true;
                permutation(cnt+1);
                isSelected[idx] = false;
            }
        }
    }

    /*
     1(or 0) ~ size로 이루어진 배열에서 n개를 선택하는 알고리즘

     @param choice(static) : 메소드가 종료 후 order번째 순열이 담겨있는 배열
     @param size(static)   : 순열을 이루는 숫자의 개수 1 ~ size
     @param cnt            : 현재까지 선택한 숫자의 개수. 처음 호출시0을 입력
     @param start          : loop 시작 위치. 처음 호출시 1(or 0)을 입력
     @param numToSelect    : 선택할 숫자의 개수
     */
    static void combination(int cnt, int start, int numToSelect) {
        if(cnt == numToSelect) {
            for(int idx = 0; idx < numToSelect; idx++) {
                System.out.print(choice[idx] + " ");
            }
            System.out.println();
            return;
        }

        for(int idx = start; idx <= size; idx++) {
            choice[cnt] = idx;
            combination(cnt+1, idx+1, numToSelect);
        }
    }

    /* target 배열에서 n개를 선택하는 알고리즘

     @param choice(static) : 메소드가 종료 후 order번째 순열이 담겨있는 배열
     @param cnt            : 현재까지 선택한 숫자의 개수. 처음 호출시0을 입력
     @param start          : loop 시작 위치. 처음 호출시 1(or 0)을 입력
     @param numToSelect    : 선택할 숫자의 개수
     @param target         : 선택할 숫자가 담겨 있는 배열
     */
    static void combination(int cnt, int start, int numToSelect, int[] target) {
        if(cnt == numToSelect) {
            for(int idx = 0; idx < numToSelect; idx++) {
                System.out.print(choice[idx] + " ");
            }
            System.out.println();
            return;
        }

        for(int idx = start; idx < target.length; idx++) {
            choice[cnt] = target[idx];
            combination(cnt+1, idx+1, numToSelect, target);
        }
    }

    /*
     2차원 배열을 시계방향으로 90도만큼 회전하는 알고리즘

     @param array : 회전할 2차원 배열
     */
    static void clockwiseRotation(int[][] array) {
        int row = array.length;
        int col = array[0].length;
        int[][] rotatedArray = new int[col][row];
        for(int i=0; i < row; i++) {
            for(int j=0; j < col; j++) {
                rotatedArray[j][row-1-i] = array[i][j];
            }
        }
        array = rotatedArray;
    }

    /*
     2차원 배열을 반시계방향으로 90도만큼 회전하는 알고리즘

     @param array : 회전할 2차원 배열
     */
    static void counterClockwiseRotation(int[][] array) {
        int row = array.length;
        int col = array[0].length;
        int[][] rotatedArray = new int[col][row];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                rotatedArray[col-1-j][i] = array[i][j];
            }
        }
        array = rotatedArray;
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

        /* permutation(int) 테스트 코드
        size = 4;
        choice = new int[size];
        isSelected = new boolean[size+1];
        permutation(0);
         */

        /* combination(int, int, int, int[]) 테스트 코드
        int numToSelect = 3;
        int[] target = new int[] {1, 2, 4, 5};
        choice = new int[numToSelect];
        isSelected = new boolean[target.length];
        combination(0, 0, 3, target);
         */

        /* combination(int, int, int) 테스트 코드
        size = 5;
        int numToSelect = 3;
        choice = new int[numToSelect];
        isSelected = new boolean[size+1];
        combination(0, 1, numToSelect);
         */
    }
}
