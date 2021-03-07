package com.boj.퀼린드롬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static HashMap<Character, Character> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        char[] name = br.readLine().toCharArray();
        StringBuilder front = new StringBuilder();
        StringBuilder end = new StringBuilder();
        setMap();

        char mid = name[name.length / 2];
        boolean isTerminated = false;
        if (name.length % 2 == 1) {
            if (map.get(mid) == null) {
                if (Character.isAlphabetic(mid)) {
                    char alt = transferChar(mid);
                    if (map.get(alt) != null) mid = map.get(alt);
                    else isTerminated = true;
                } else isTerminated = true;
            }
            if (isTerminated) {
                System.out.println(-1);
                return;
            }
        }

        int half = name.length / 2;
        for (int i = 0; i < half; i++) {
            char c = name[i];
            char _c = name[name.length - 1 - i];

            if (!Character.isAlphabetic(_c) && map.get(_c) == null) {
                System.out.println(-1);
                return;
            }

            if (map.get(c) != null && (map.get(c) == _c || map.get(c) == transferChar(_c))) {
                front.append(c);
                end.append(map.get(c));
                continue;
            }

            if (map.get(c) == null) {
                if (Character.isAlphabetic(c)) {
                    char alt = transferChar(c);
                    if (map.get(alt) != null) {
                        front.append(alt);
                        end.append(map.get(alt));
                        continue;
                    }
                }
            }

            System.out.println(-1);
            return;
        }
        end = end.reverse();
        System.out.println(name.length % 2 == 1 ? front.append(mid).append(end) : front.append(end));
    }

    static char transferChar(char c) {
        return Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c);
    }

    private static void setMap() {
        map.put('A', 'A');
        map.put('E', '3');
        map.put('H', 'H');
        map.put('I', 'I');
        map.put('M', 'M');
        map.put('O', 'O');
        map.put('S', '2');
        map.put('T', 'T');
        map.put('U', 'U');
        map.put('V', 'V');
        map.put('W', 'W');
        map.put('X', 'X');
        map.put('Y', 'Y');
        map.put('Z', '5');
        map.put('b', 'd');
        map.put('d', 'b');
        map.put('i', 'i');
        map.put('l', 'l');
        map.put('m', 'm');
        map.put('n', 'n');
        map.put('o', 'o');
        map.put('p', 'q');
        map.put('q', 'p');
        map.put('r', '7');
        map.put('u', 'u');
        map.put('v', 'v');
        map.put('w', 'w');
        map.put('x', 'x');
        map.put('0', '0');
        map.put('1', '1');
        map.put('2', 'S');
        map.put('3', 'E');
        map.put('5', 'Z');
        map.put('7', 'r');
        map.put('8', '8');
    }
}