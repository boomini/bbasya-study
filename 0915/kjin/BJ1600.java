import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1600 {
// Baekjoon 1600. 말이 되고픈 원숭이
	static int K, W, H;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input1600.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H][W];
		for (int h=0; h<H; h++) {
			st = new StringTokenizer(br.readLine());
			for (int w=0; w<W; w++) {
				arr[h][w] = Integer.parseInt(st.nextToken());
			}
		}
		int result=-1;
		
		boolean visited[][][] = new boolean[H][W][K+1];
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {0, 0, K, 0});		// 행, 열, 남은 K, 이동길이
		int d[][] = new int[][]{ {1,0,0}, {-1,0,0}, {0,1,0}, {0,-1,0},	// 일반 이동
			{2,1,1}, {1,2,1}, {-2,-1,1}, {-1,-2,1}, {2,-1,1}, {-2,1,1}, {1,-2,1}, {-1,2,1} };	// 말 이동
		
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			if (cur[0]==H-1&&cur[1]==W-1) {	// 도착
				result=cur[3];
				break;
			}
			visited[cur[0]][cur[1]][cur[2]]=true;
			for (int i=0; i<12; i++) {
				int nr = cur[0]+d[i][0];
				int nc = cur[1]+d[i][1];
				int nk = cur[2]-d[i][2];	// 말인경우 1씩 감소
				if (nr>=0&&nr<H&&nc>=0&&nc<W&&nk>=0&&arr[nr][nc]==0&&!visited[nr][nc][nk]) {
					queue.add(new int[] {nr, nc, nk, cur[3]+1});
					visited[nr][nc][nk]=true;
				}
			}
		}
		System.out.println(result);
	}

}
