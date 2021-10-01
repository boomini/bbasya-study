import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4013 {
// SW Expert Academy 4013. 특이한 자석
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input4013.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t=1; t<=T; t++) {
			sb.append("#").append(t).append(" ");
			int K=Integer.parseInt(br.readLine());
			arr=new int[4][8];
			for (int i=0; i<4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<8; j++) {
					arr[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i=0; i<K; i++) {
				st=new StringTokenizer(br.readLine());
				int num=Integer.parseInt(st.nextToken());
				int dir=Integer.parseInt(st.nextToken());
				rotate(num-1, dir);
				
			}
			
			int cnt=0; 
			for (int i=0; i<4; i++) {
				cnt+=Math.pow(2, i)*arr[i][0];
			}
			sb.append(cnt).append("\n");
			
		}
		System.out.println(sb);
		br.close();
	}

	private static void rotate(int num, int dir) {		
		int[] rot=new int[4];	// 회전 방향
		rot[num]=dir;
		for (int i=num+1; i<4; i++) {	// 오른쪽 시계들 회전방향 설정
			if (arr[i-1][2]!=arr[i][6]) {
				rot[i]=-rot[i-1];
			}
		}
		
		for (int i=num-1; i>=0; i--) {	// 왼쪽 시게들 회전 방향 설정
			if (arr[i+1][6]!=arr[i][2]) {
				rot[i]=-rot[i+1];
			}
		}
		
		for (int i=0; i<4; i++) {	// 회전
			int[] temp=arr[i].clone();
			int d=rot[i];
			for (int j=0; j<8; j++) {
				arr[i][(j+d+8)%8]=temp[j];
			}
		}
		
	}

}
