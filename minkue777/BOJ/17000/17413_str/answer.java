import java.io.*;

public class Main {

    static String reverseWords(String input) {
        if (input == null) return "";
        int forwardIdx = input.indexOf("<");
        if (forwardIdx != -1) {
            int backwardIdx = input.indexOf('>');
            return reverseWords(input.substring(0, forwardIdx)) +
                    input.substring(forwardIdx, backwardIdx+1) +
                    reverseWords(input.substring(backwardIdx+1));
        }
        int idx = input.indexOf(" ");
        if(idx == -1) return new StringBuffer(input).reverse().toString();
        return new StringBuffer(input.substring(0, idx)).reverse().toString() + " " +
                reverseWords(input.substring(idx + 1));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(reverseWords(input));
    }
}