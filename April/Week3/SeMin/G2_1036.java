import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, BigInteger> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String[] word = new String[n];
        int[] arr = new int[36];

        int min = 100, max = 0;
        for (int i = 0; i < n; i++) {
            word[i] = br.readLine();

            min = Math.min(word[i].length(), min);
            max = Math.max(word[i].length(), max);

            for (int j = 0; j < word[i].length(); j++) {
                char number = word[i].charAt(j);

                BigInteger bInt = new BigInteger("0");
                // 최댓값을 구하기 위한 개수 구하기
                int power = word[i].length() - j;
                int key = number >= 'A' ? number - '7' : number - '0';

                BigInteger value = map.getOrDefault(key, bInt);
                bInt = BigInteger.valueOf(35);
                bInt = bInt.pow(power).multiply(BigInteger.valueOf(35 - key)).add(value);


                map.put(key, bInt);
            }
        }

		// 변화 값이 가장 큰 수 대로 숫자를 오름차 정렬
        List<Map.Entry<Integer, BigInteger>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));


        int k = Integer.parseInt(br.readLine());
        
        if (k != 0) {
            for (int i = 0; i < k; i++) {
                if (list.size() <= i) break;
                Map.Entry<Integer, BigInteger> entry = list.get(i);
                arr[entry.getKey()] = 35;
            }
        }

        int[] nums = new int[53];
        calculateNum(word, arr, nums);

        int idx = change36(nums);

        // 36진수로 바꿔서 출력
        for (int i = idx - 1; i >= 0; i--) {
            char cc = (char) (nums[i] >= 10 ? (nums[i] + '7') : (nums[i] + '0'));
            sb.append(cc);
        }

        System.out.println(sb.toString().isEmpty() ? "0" : sb.toString());
    }

    private static void calculateNum(String[] word, int[] arr, int[] nums) {
        // 각 자리수의 값 계산
        for (int i = 0; i < word.length; i++) {

            for (int j = 0; j < word[i].length(); j++) {
                char c = word[i].charAt(word[i].length() - j - 1);

                if (arr[getIndex(c)] != 0) nums[j] += 35;
                else nums[j] += getIndex(c);
            }
        }
    }

    // 36진수 계산
    private static int change36(int[] nums) {
        int idx = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > 0) {
                if (nums[i] / 36 > 0) {
                    nums[i+1] += nums[i] / 36;
                    nums[i] %= 36;
                }
                idx = Math.max(idx, i+1); // 끝에 있는 수의 자릿수를 세기
            }
        }
        return idx;
    }

    private static int getIndex(char c) {
        return c >= 'A' ? c - '7' : c - '0';
    }

}