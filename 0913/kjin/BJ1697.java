import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1697 {
// Baekjoon 1697. 숨바꼭질
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		
		System.setIn(new FileInputStream("input13549.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		boolean visited[] = new boolean[1000000];
		int cnt = 0;
		int sum=0;
		if (N>=K) {
			cnt = N-K;
		} else {
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.add(N);
//			loop:
			while(!queue.isEmpty()) {
				int size = queue.size();
				for (int i=0; i<size; i++) {
					int temp = queue.poll();
//					System.out.println(temp);
					if (temp<0)	continue;
					if (visited[temp] && temp!=K) {
						continue;
					}
					visited[temp]=true;
					if (temp==K) {
						sum++;
						
					} else if (temp<K){
						queue.add(temp*2);
						queue.add(temp+1);
						queue.add(temp-1);
					} else {
						queue.add(temp-1);
					}
					
				}
				if (sum!=0)	break;
				cnt++;
			}
		}
		System.out.println(cnt);
//		System.out.println(sum);
		
		
	}

}
