public class Main {

    // return the index to make the shortest balanced substring of p
    static int shortestBalancedSubstringIdx(String w) {
        int flag = 0;
        for(int i=0; i<w.length(); ++i) {
            flag += (w.charAt(i) == '(') ? 1 : -1;
            if (flag == 0) return i;
        }
        return w.length()-1;
    }
    // check whether w is a correct parenthesis or not
    static boolean isCorrect(String w) {
        int flag = 0;
        for(int i=0; i<w.length(); ++i) {
            flag += (w.charAt(i) == '(') ? 1 : -1;
            if(flag < 0) return false;
        }
        return true;
    }

    static String solution(String w) {
        // return empty string
        if(w.equals("")) return "";
        // if input is correct, return itself
        if(isCorrect(w)) return w;
        // 2. split input string
        int idx = shortestBalancedSubstringIdx(w);
        String u = w.substring(0, idx+1);
        String v = w.substring(idx+1);
        // 3. if u is correct, return u + recursive v
        if(isCorrect(u)) return u + solution(v);
        // 4. if u is not correct
        StringBuilder sb = new StringBuilder();
        // 4-1 ~ 4-3 concatenate (, recursive(v) and )
        sb.append("(").append(solution(v)).append(")");
        // 4-4 remove first and last parenthesis and reverse direction
        u = u.substring(1, u.length()-1);
        StringBuilder nu = new StringBuilder();
        for(char c : u.toCharArray()) {
            nu.append((c == '(') ? ')' : '(');
        }
        // 4-5 return v + u
        return sb.append(nu.toString()).toString();
    }
}