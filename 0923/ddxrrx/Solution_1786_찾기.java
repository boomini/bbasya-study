package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution_1786_찾기 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] T = in.readLine().toCharArray();
		char[] P = in.readLine().toCharArray();

		int tLen = T.length, pLen = P.length;

		int[] pi = new int[pLen];
		for (int i = 1, j = 0; i < pLen; i++) {
			while (j > 0 && P[i] != P[j]) {
				j = pi[j - 1];
			}
			if (P[i] == P[j])
				pi[i] = ++j;
			else
				pi[i] = 0;
		}

		int cnt = 0;
		ArrayList<Integer> li = new ArrayList<Integer>();
		for (int i = 0, j = 0; i < tLen; ++i) {
			while (j > 0 && T[i] != P[j]) {
				j = pi[j - 1];
			}
			if (T[i] == P[j]) {
				if (j == pLen - 1) {
					++cnt;
					li.add(i + 2 - pLen);
					j = pi[j];
				} else {
					++j;
				}
			}
		}

		System.out.println(cnt);
		if (cnt > 0) {
			for (int i = 0; i < li.size(); i++) {
				System.out.print(li.get(i) + " ");
			}
		}
	}

}
