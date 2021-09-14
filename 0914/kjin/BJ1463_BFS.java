import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ1463_BFS {
// baekjoon 1463. 1로 만들기 
// bfs
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input1463.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt=0;
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(N);
		boolean used[] = new boolean[N+1];
		loop:
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i=0; i<size; i++) {
				int temp = queue.poll();
				if (used[temp])	continue;
				used[temp]=true;
				if (temp==1) 
					break loop;
				if(temp%3==0)
					queue.add(temp/3);
				if(temp%2==0)
					queue.add(temp/2);
				queue.add(temp-1);
			}
			cnt++;
		}
		System.out.println(cnt);
	}

}
