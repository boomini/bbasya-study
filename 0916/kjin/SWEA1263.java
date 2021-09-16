import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1263 {
// SW Expert Academy 1263. 사람 네트워크2
// bfs
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input1263.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int arr[][] = new int[N+1][N+1];
			int node[] = new int[N+1];
			
			for (int i=1; i<=N; i++) {
				for (int j=1; j<=N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int min = Integer.MAX_VALUE;
			for (int i=1; i<=N; i++) {
				for (int j=1; j<=N; j++) {
					node[j]=Integer.MAX_VALUE;
				}
				node[i]=0;
				int cnt=0;
				boolean used[] = new boolean[N+1];
				Queue<Integer> queue = new LinkedList<Integer>();
				queue.add(i);
				int connect=0;
				while(!queue.isEmpty()) {
					int size=queue.size();
					for (int s=0; s<size; s++) {
						int index = queue.poll();
						used[index]=true;
						for (int j=1; j<=N; j++) {
							if (!used[j]&&arr[index][j]==1) {
								node[j]=Math.min(node[j], 1+connect);
								cnt++;
								used[j]=true;
								queue.add(j);
							}
						} 
					}
					connect++;
					if (cnt==N-1) {
						break;
					}
				}
				int sum=0;
				for (int j=1; j<=N; j++) {
					sum+=node[j];
				}
				min = Math.min(min, sum);
			}

			sb.append(min);
			System.out.println(sb);
		}
	}

}
