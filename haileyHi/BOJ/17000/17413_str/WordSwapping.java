import java.io.*;

public class WordSwapping {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = reader.readLine();
        writer.write(solution(s));
        writer.flush();
        writer.close();
    }
    public static String solution(String p){
        boolean isTag = false;

        StringBuilder sb = new StringBuilder();
        StringBuilder tmp = new StringBuilder();

        for(int i = 0; i < p.length(); i++){
            char c = p.charAt(i);

            //TAG확인
            if(c == '<'){
                isTag = true;
            }else if (c == '>') {
                isTag = false;
                sb.append(c);
                continue;
            }

            if(isTag) {//TAG 내용 담기 및 태그 담기 전에 담아야 할 단어 있는지 체크
                if(tmp.length() > 0){
                    sb.append(tmp.reverse());
                    tmp.delete(0,tmp.length());
                }
                sb.append(c);
            }else{//TAG 아니면 빈칸 만나기 전까지 tmp에 뒤집을 단어 담으면 됨.
                if(c != ' ') {
                    tmp.append(c);
                }else {
                    sb.append(tmp.reverse()).append(c);
                    tmp.delete(0,tmp.length());
                }
            }

            if(i == p.length() -1 && tmp.length() > 0){//마지막까지 갔는데 아직 뒤집을 단어가 남아있는 경우
                sb.append(tmp.reverse());
            }
        }
        return sb.toString();
    }
}