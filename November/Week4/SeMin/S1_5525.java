import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int S = Integer.parseInt(br.readLine());

        char[] word = br.readLine().toCharArray();
        
        int count = 0;
        int answer = 0;
        for(int i = 1; i < S - 1; i++){
            if(word[i - 1] == 'I' && word[i] == 'O' && word[i + 1] == 'I'){
                count++;
                if(count == N){
                    answer++;
                    count--;
                }
                i++;
            }
            else count = 0;
            
        }
        System.out.println(answer);
    }
}