import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	/*
	 *       A C A Y K P
	 *    
	 *   C   0 1 1 1 1 1
	 *   A   1 1 2 2 2 2
	 *   P   1 1 2 2 2 3
	 *   C   1 2 2 2 2 3
	 *   A   1 2 3 3 3 3
	 *   K   1 2 3 3 4 4
	 *   
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		char [] word1 = br.readLine().toCharArray();
		char [] word2 = br.readLine().toCharArray();
		int [][] table= new int[word2.length+1][word1.length+1]; 
		for(int i=0;i<word2.length;i++){
			for(int j=0;j<word1.length;j++) {
				table[i+1][j+1] = (word1[j]==word2[i])? table[i][j]+1: Math.max(table[i][j+1], table[i+1][j]);
				//table[i+1][j+1] = (word1[j]==word2[i])? table[i][j]+1: max(table[i][j+1], table[i+1][j]);
			}
		}
		System.out.println(table[word2.length][word1.length]);
	}
	/*
	public static int max(int a, int b) {
		return (a>b)?a:b;
	}
	*/
}
