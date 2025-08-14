import java.util.*;
import java.io.*;

class B5_10430{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr = br.readLine().split(" ");

        int A = Integer.parseInt(arr[0]);
        int B = Integer.parseInt(arr[1]);
        int C = Integer.parseInt(arr[2]);

        System.out.println((A+B)%C);
        System.out.println(((A%C) + (B%C))%C);
        System.out.println((A*B) % C);
        System.out.println( ((A%C) * ( B % C )) % C);



    }



}