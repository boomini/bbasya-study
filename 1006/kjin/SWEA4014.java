import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4014 {
// SWEA 4014. 활주로 건설
	static int N,X;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input4014.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			X=Integer.parseInt(st.nextToken());
			arr=new int[N][N];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					arr[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			int result=0;
			
			for (int i=0; i<N; i++) {
				result+=wconstruct(i);
			}
			for (int j=0; j<N; j++) {
				result+=hconstruct(j);
			}
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	private static int hconstruct(int j) {	// 세로로
		int cnt=1;
		int h=arr[0][j];
		for (int i=1; i<N; i++) {
			if (h==arr[i][j]) {
				cnt++;
			} else if(h+1==arr[i][j] && cnt>=X) {	// 한칸 높고 경사로 길이가 충분하면
				cnt=1;
				h++;
			} else if (h-1==arr[i][j]) {	// 한칸 낮을 때
				int xcnt=0;
				for (int k=i; k<N; k++) {	
					if (arr[k][j]==h-1) {	// 한칸 낮은 높이가 얼마동안 있는지
						xcnt++;
					} else {
						break;
					}
				}
				if (xcnt>=X) {	// 경사로보다 길면 가능
					h--;
					i+=X-1;
					cnt=0;
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		}
		
		return 1;
	}

	private static int wconstruct(int i) {	// 가로로
		int cnt=1;
		int h=arr[i][0];
		for (int j=1; j<N; j++) {
			if (h==arr[i][j]) {
				cnt++;
			} else if(h+1==arr[i][j] && cnt>=X) {	// 한칸 높고 경사로 길이가 충분하면
				cnt=1;
				h++;
			} else if (h-1==arr[i][j]) {	// 한칸 낮을 때
				int xcnt=0;
				for (int k=j; k<N; k++) {	
					if (arr[i][k]==h-1) {	// 한칸 낮은 높이가 얼마동안 있는지
						xcnt++;
					} else {
						break;
					}
				}
				if (xcnt>=X) {	// 경사로보다 길면 가능
					h--;
					j+=X-1;
					cnt=0;
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		}
		
		return 1;
	}

}
