package com.boj.자바vs시플플;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String str = br.readLine();

        String patternE = "[^a-zA-Z_]|^[^a-z]|[_]$|[_]{2}|[A-Z].*[_]|[_].*[A-Z]";
        Matcher matcherE = Pattern.compile(patternE).matcher(str);
        if(matcherE.find()){
            System.out.println("Error!");
            return;
        }

        StringBuffer sb = new StringBuffer();
        Matcher matcherS = Pattern.compile("^[a-z]+").matcher(str);
        while (matcherS.find()) {
            String start = matcherS.group();
            sb.append(start);
        }

        Matcher matcherC = Pattern.compile("[_]+.*").matcher(str);
        Matcher matcherJ = Pattern.compile("[A-Z]+.*").matcher(str);
        if (matcherC.find()) {
            Pattern pattern = Pattern.compile("(_[a-z])");
            matcherC = pattern.matcher(matcherC.group());
            while (matcherC.find()) {
                String upper = matcherC.group().replace("_", "").toUpperCase();
                matcherC.appendReplacement(sb, upper);
            }
            matcherC.appendTail(sb);
        } else if(matcherJ.find()) {
            Pattern pattern = Pattern.compile("([A-Z])");
            matcherJ = pattern.matcher(matcherJ.group());
            while (matcherJ.find()) {
                String lower = "_".concat(matcherJ.group().toLowerCase());
                matcherJ.appendReplacement(sb, lower);
            }
            matcherJ.appendTail(sb);
        }

        System.out.println(sb);
    }
}