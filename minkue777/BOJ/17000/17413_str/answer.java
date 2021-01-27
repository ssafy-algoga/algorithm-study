import java.io.*;

public class Main {

    static String reverseWords(String input) {
        // base-case
        if (input == null) return "";
        // process <> first
        int forwardIdx = input.indexOf("<");
        if (forwardIdx != -1) {
            int backwardIdx = input.indexOf('>');
            return reverseWords(input.substring(0, forwardIdx)) +
                    input.substring(forwardIdx, backwardIdx+1) +
                    reverseWords(input.substring(backwardIdx+1));
        }
        int idx = input.indexOf(" ");
        // case: input is only one word
        if(idx == -1) return new StringBuffer(input).reverse().toString();
        // others
        return new StringBuffer(input.substring(0, idx)).reverse().toString() + " " +
                reverseWords(input.substring(idx + 1));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(reverseWords(input));
    }
}