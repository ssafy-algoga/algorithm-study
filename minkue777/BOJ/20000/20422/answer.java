import java.io.*;
import java.util.*;

public class Main {
    static Map<Character, Character> matching = new HashMap<>();
    static void initMap() {
        matching.put('A', 'A'); matching.put('E', '3'); matching.put('H', 'H');
        matching.put('I', 'I'); matching.put('M', 'M'); matching.put('O', 'O');
        matching.put('S', '2'); matching.put('T', 'T'); matching.put('U', 'U');
        matching.put('V', 'V'); matching.put('W', 'W'); matching.put('X', 'X');
        matching.put('Y', 'Y'); matching.put('Z', '5'); matching.put('b', 'd');
        matching.put('d', 'b'); matching.put('i', 'i'); matching.put('l', 'l');
        matching.put('m', 'm'); matching.put('n', 'n'); matching.put('o', 'o');
        matching.put('p', 'q'); matching.put('q', 'p'); matching.put('r', '7');
        matching.put('u', 'u'); matching.put('v', 'v'); matching.put('w', 'w');
        matching.put('x', 'x'); matching.put('0', '0'); matching.put('1', '1');
        matching.put('2', 'S'); matching.put('3', 'E'); matching.put('5', 'Z');
        matching.put('7', 'r'); matching.put('8', '8');
    }

    static char changeAlphabet(char c) {
        if(c >= 65 && c <= 90)
            return (char)(c + 'a'-'A');
        else if(c >= 97 && c <= 122)
            return (char)(c - ('a'-'A'));
        else return c;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        initMap();
        char[] input = br.readLine().toCharArray();
        int isEven = input.length % 2;
        if(isEven == 1) {
            int center = input.length/2;
            if(!matching.containsKey(input[center])) {
                input[center] = changeAlphabet(input[center]);
            }
            if(!(matching.containsKey(input[center]) &&
                    matching.get(input[center]) == input[center])) {
                System.out.println(-1);
                return;
            }
        }

        int left = input.length/2;
        int right = input.length/2 + isEven-1;

        while(--left >= 0 && ++right < input.length) {
            char lc = input[left];
            char rc = input[right];
            if(!(matching.containsKey(lc) ||
                    matching.containsKey(changeAlphabet(lc))) ||
                    !(matching.containsKey(rc) ||
                            matching.containsKey(changeAlphabet(rc)))) {
                System.out.println(-1);
                return;
            }
            if(!matching.containsKey(lc)) {
                input[left] = changeAlphabet(lc);
                lc = input[left];
            }
            if(!matching.containsKey(rc)) {
                input[right] = changeAlphabet(rc);
                rc = input[right];
            }
            if(matching.get(lc) != rc && lc != changeAlphabet(rc)) {
                System.out.println(-1);
                return;
            }
            if(lc == changeAlphabet(rc))
                input[right] = lc;
        }
        System.out.println(String.valueOf(input));
    }
}