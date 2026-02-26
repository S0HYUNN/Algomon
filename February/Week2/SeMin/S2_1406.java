import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr = br.readLine().split("");
        Deque<String> stack1 = new ArrayDeque<>();
        Deque<String> stack2 = new ArrayDeque<>();

        for(String s : arr){
            stack1.push(s);
        }

        int M = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < M; tc++){
            String[] command = br.readLine().split(" ");
            String cmd = command[0];
            switch (cmd){
                case "L":
                    if(!stack1.isEmpty()) stack2.push(stack1.pop());
                    break;
                case "D":
                    if(!stack2.isEmpty()) stack1.push(stack2.pop());
                    break;
                case "B":
                    if(!stack1.isEmpty()) stack1.pop();
                    break;
                case "P":
                    stack1.push(command[1]);
                    break;
            }

        }

        StringBuilder sb = new StringBuilder();

        for(String s : stack1){
            sb.append(s);
        }
        sb.reverse();

        for(String s : stack2){
            sb.append(s);
        }

        System.out.println(sb);
    }
}