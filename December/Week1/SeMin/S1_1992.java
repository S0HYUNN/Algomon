import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0 ; i < N ; i++ ) {
            String[] s = br.readLine().split("");
            for (int j = 0; j < N ; j++ ) {
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }

        check(0, 0, N);
    }


    static void check(int row, int col, int size){
        int num = aa(row, col, size);
        if(num != -1){
            System.out.print(num);
            return;
        }

        System.out.print("(");
        
        int newSize = size / 2;

        check(row, col, newSize); // 왼쪽 위
        check(row, col + newSize, newSize); // 오른쪽 위

        check(row + newSize, col , newSize); // 왼쪽 아래
        check(row + newSize, col + newSize, newSize); // 오른쪽 아래

        System.out.print(")");
    }

    static int aa(int row, int col, int size){
        int num = arr[row][col];
        for (int i = row; i < row + size; i++ ) {
            for (int j = col; j < col + size; j++ ) {
                if(arr[i][j] != num) return -1;
            }
        }
        return num;
    }
}