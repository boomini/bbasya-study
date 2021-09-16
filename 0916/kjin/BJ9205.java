import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ9205 {
// Baekjoon 9205. 맥주 마시면서 걸어가기
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input9205.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			int arr[][] = new int[N+2][2];
			boolean visited[] = new boolean[N+2];
			for (int i=0; i<N+2; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0]=Integer.parseInt(st.nextToken());
				arr[i][1]=Integer.parseInt(st.nextToken());
			}
			
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.add(0);
			int cur=0;
			while(!queue.isEmpty()) {
				cur = queue.poll();
				if (cur==N+1)	break;
				visited[cur]=true;
				for (int i=0; i<N+2; i++) {
					if (!visited[i]&&distance(arr[cur], arr[i])<=1000) {
						queue.add(i);
						System.out.println(i);
						visited[i]=true;
					}
				}
			}
			System.out.println();
			if (cur==N+1) {
				sb.append("happy").append("\n");
			} else {
				sb.append("sad").append("\n");
			}
			
		}
		System.out.println(sb);
	}

	private static int distance(int[] x, int[] y) {
//		System.out.println(Math.abs(x[0]-x[1])+Math.abs(y[0]-y[1]));
		return Math.abs(x[0]-y[0])+Math.abs(x[1]-y[1]);
	
	}

}
