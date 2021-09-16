import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA3307_2 {
// SW Expert Academy 3307. 최장 증가 부분 수열
// nlogn
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input3307.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t=1; t<=T; t++) {
			sb.append("#").append(t).append(" ");
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			int[] lis = new int[N];
			for (int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int size=0, dup=0;
			for (int i=0; i<N; i++) {
				int index = Arrays.binarySearch(lis, 0, size, arr[i]);
				if (index<0) {
					index = -1*index-1;
					lis[index]=arr[i];
					if (index==size)	size++;
				} else {
					dup++;
				}
			}
			sb.append(size+dup).append("\n");
		}
		System.out.println(sb);
	}

}
