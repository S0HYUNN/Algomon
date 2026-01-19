import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");

        HashSet<Integer> hash = new HashSet<>();

        int[] arr = new int[n];
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
            hashMap.put(arr[i], hashMap.getOrDefault(arr[i], 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = arr[i] + arr[j];

                if (arr[i] == 0 && arr[j] == 0) {
                    if (hashMap.getOrDefault(sum, 0) >= 3) {
                        hash.add(sum);
                    }
                } else if (arr[i] == 0 || arr[j] == 0) {
                    if (hashMap.getOrDefault(sum, 0) >= 2) {
                        hash.add(sum);
                    }

                } else {
                    hash.add(sum);
                }

            }
        }

        int cnt = 0;
        for (int num : arr) {
            if (hash.contains(num)) cnt++;
        }
        System.out.println(cnt);


    }
}