import java.io.*;
import java.util.*;

public class Main {
    static boolean isJavaStyle(String str) {
        if(str.contains("_")) return false;
        String[] strArr = str.split("(?=[A-Z])");
        for(int idx = 0 ; idx <strArr.length; idx++) {
            char ch = strArr[idx].charAt(0);
            if(idx == 0 && !Character.isLowerCase(ch)) {
                return false;
            } else if (idx > 0 && !Character.isUpperCase(ch)) {
                return false;
            }
        }
        return true;
    }

    static boolean isCStyle(String str) {
        if(str.contains("__")) return false;
        if(str.charAt(str.length()-1) == '_') return false;
        String[] strArr = str.split("_");
        for(String s : strArr) {
            if(s.equals("") || !s.equals(s.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    static String cToJava(String str) {
        StringBuilder sb = new StringBuilder();
        String[] strArr = str.split("_");
        for(int idx = 0; idx < strArr.length; idx++) {
            String s = strArr[idx];
            sb.append(idx == 0 ? s.charAt(0) : Character.toUpperCase(s.charAt(0)));
            sb.append(s.substring(1));
        }
        return sb.toString();
    }

    static String javaToC(String str) {
        StringBuilder sb = new StringBuilder();
        String[] strArr = str.split("(?=[A-Z])");
        for(int idx = 0; idx < strArr.length; idx++) {
            String s = strArr[idx];
            sb.append(Character.toLowerCase(s.charAt(0)))
                    .append(s.substring(1));
            if(idx != strArr.length-1) {
                sb.append("_");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        if(isCStyle(input)) {
            System.out.println(cToJava(input));
        } else if(isJavaStyle(input)) {
            System.out.println(javaToC(input));
        } else {
            System.out.println("Error!");
        }
    }
}