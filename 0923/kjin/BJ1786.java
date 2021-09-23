package com.ssafy.kmp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ1786 {
// Baekjoon 1786. 찾기
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input1786.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] T = br.readLine().toCharArray();
		char[] P = br.readLine().toCharArray();

		int tlen = T.length;
		int plen = P.length;
		
		int[] pi = new int[plen];
		for (int i=1, j=0; i<plen; i++) {
			while(j>0&&P[i]!=P[j]) {
				j=pi[j-1];
			}
			if (P[i]==P[j]) {
				pi[i]=++j;
			}
		}
				
		int cnt=0;
		ArrayList<Integer> list = new ArrayList<>();
		for (int i=0, j=0; i<tlen; i++) {
			while(j>0&&T[i]!=P[j]) {
				j=pi[j-1];
			}
			
			if (T[i]==P[j]) {
				if (j==plen-1) {
					cnt++;
					list.add(i-j);
					j=pi[j];
				} else {
					j++;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append("\n");
		if (cnt>0) {
			for (int i=0; i<cnt; i++) {
				sb.append(list.get(i)+1).append(" ");
			}
		}
		
		System.out.println(sb);
	}

}
