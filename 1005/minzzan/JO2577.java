package hwalgo29_서울_07반_김민정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// JO2577 회전 초밥
public class JO2577{

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken()); // 벨트에 놓인 접시 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓 수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰번호

		int[] sushi = new int[N];
		int[] eat = new int[d + 1];

		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(in.readLine());
		}

		int cnt = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < k; i++) {
			if (eat[sushi[i]] == 0)
				cnt++; // 처음 먹어보는 종류의 초밥이라면 카운트 + 1
			eat[sushi[i]]++;
		}
		max = cnt;

		for (int i = 1; i < N; i++) {
			if (max <= cnt) {
				if (eat[c] == 0)
					max = cnt + 1;
				else
					max = cnt;
			}

			eat[sushi[i - 1]]--;
			if (eat[sushi[i - 1]] == 0)
				cnt--;

			if (eat[sushi[(i + k - 1) % N]] == 0)
				cnt++;
			eat[sushi[(i + k - 1) % N]]++;
		}

		System.out.println(max);

	}

}

/*
 * 시간초과 코드
 * 
 * package hwalgo29_서울_07반_김민정;
 * 
 * import java.io.BufferedReader; import java.io.IOException; import
 * java.io.InputStreamReader; import java.util.LinkedList; import
 * java.util.StringTokenizer;
 * 
 * // JO2577 회전 초밥 public class Solution {
 * 
 * public static void main(String[] args) throws NumberFormatException,
 * IOException { BufferedReader in = new BufferedReader(new
 * InputStreamReader(System.in)); StringTokenizer st = new
 * StringTokenizer(in.readLine());
 * 
 * int N = Integer.parseInt(st.nextToken()); // 벨트에 놓인 접시 수 int d =
 * Integer.parseInt(st.nextToken()); // 초밥의 가짓 수 int k =
 * Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수 int c =
 * Integer.parseInt(st.nextToken()); // 쿠폰번호
 * 
 * int[] sushi = new int[N + k - 1];
 * 
 * for (int i = 0; i < N; i++) { sushi[i] = Integer.parseInt(in.readLine()); }
 * for (int i = N; i < N + k - 1; i++) { sushi[i] = sushi[i - N]; }
 * 
 * int cnt = 0; int max = Integer.MIN_VALUE; for (int i = 0; i < N; i++) {
 * LinkedList<Integer> temp = new LinkedList<>(); for (int j = i; j < i + k;
 * j++) { if (!temp.contains(sushi[j])) { cnt++; } temp.add(sushi[j]); if
 * (temp.size() == k) { if (!temp.contains(c)) { cnt++; } } } max =
 * Math.max(max, cnt); cnt = 0; }
 * 
 * System.out.println(max);
 * 
 * }
 * 
 * }
 * 
 */