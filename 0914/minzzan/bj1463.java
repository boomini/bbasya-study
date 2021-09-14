import java.util.Scanner;

public class bj1463 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int[] D = new int[N + 1];

		D[1] = 0;

		if (N > 1) {
			for (int i = 2; i <= N; i++) {
				int a = Integer.MAX_VALUE;
				int b = Integer.MAX_VALUE;
				int c = Integer.MAX_VALUE;

				if (i % 3 == 0) {
					a = 1 + D[i / 3];
				}
				if (i % 2 == 0) {
					b = 1 + D[i / 2];
				}
				c = 1 + D[i - 1];
				D[i] = Math.min(a, Math.min(b, c));
			}
		}

		System.out.println(D[N]);
	}
}
