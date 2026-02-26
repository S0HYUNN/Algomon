import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[] primes = {2, 3, 5, 7, 11, 13, 17};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		double A = Double.parseDouble(br.readLine()) / 100.0;
		double B = Double.parseDouble(br.readLine()) / 100.0;

		double pA = prime(A);
		double pB = prime(B);

		double result = pA + pB - pA * pB;

		bw.write(String.valueOf(result));

		bw.flush();
		bw.close();
	}

	static double prime(double p) {
		double[][] dp = new double[19][19];
		dp[0][0] = 1.0;
		for(int i = 1; i < 19; i++) {
			for(int j = 0; j <= i; j++) {
				dp[i][j] += dp[i - 1][j] * (1 - p);
				if(j > 0) {
					dp[i][j] += dp[i - 1][j - 1] * p;
				}
			}
		}

		double sum = 0.0;
		for(int prime : primes) {
			sum += dp[18][prime];
		}
		return sum;
	}
}
