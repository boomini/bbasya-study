import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17471_2 {
// baekjoon 17471. 게리맨더링
// 노드를 2개로 분류 후 연결 가능한지 확인 (해당 노드만 고려해서)-> 인구 수 차이 비교

	static int N, min=10000, len;
	static boolean[][] arr;
	static int[] popul;
	static int[] parents;
	static int[] temp;
	static ArrayList<int[]> list = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input17471.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new boolean[N][N];
		popul = new int[N];
		int i, j;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (i=0; i<N; i++) {
			popul[i]=Integer.parseInt(st.nextToken());
		}
		
		for (i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			for (j=0; j<len; j++) {
				int num=Integer.parseInt(st.nextToken())-1;
				arr[i][num]=true;
				arr[num][i]=true;
			}
		}
		
		len = list.size();
		for (i=1; i<=N/2; i++) {
			temp = new int[i];
			comb(0, 0, i);
		}
		if (min==10000)	min=-1;
		System.out.println(min);
	}

	private static void comb(int start, int cnt, int K) {
		if (cnt==K) {
			int check[] = new int[N];
			
			for (int i=0; i<K; i++) {
				check[temp[i]]=2;
			}
			if (canConnect(check, 0)&&canConnect(check, 2)) {
				min=Math.min(min, Math.abs(totalPopul(check, 1)-totalPopul(check, 3)));
			}
			
			return;
		}
		
		for (int i=start; i<N; i++) {
			temp[cnt]=i;
			comb(i+1, cnt+1, K);
		}
	}

	private static int totalPopul(int[] check, int flag) {
		int total=0;
		for (int i=0; i<N; i++) {
			if (check[i]==flag) {
				total+=popul[i];
			}
		}
		return total;
	}

	private static boolean canConnect(int[] check, int flag) {
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i=0; i<N; i++) {
			if (check[i]==flag) {
				queue.add(i);
				break;
			}
		}
		while(!queue.isEmpty()) {
			int cur=queue.poll();
			check[cur]=flag+1;
			for (int i=0; i<N; i++) {
				if (arr[cur][i]&&check[i]==flag) {
					queue.add(i);
					check[i]=flag+1;
				}
			}
		}
		for (int i=0; i<N; i++) {
			if (check[i]==flag) {
				return false;
			}
		}
		return true;
	}

}
