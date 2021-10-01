import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17143 {
// Baekjoon 17143. 낚시왕
	static class shark{
		int r, c, s, d, z;

		public shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	static int R, C, M, size;
	static int[][] arr;
	static shark[] sharks;
	static boolean[] catched;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input17143.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		arr=new int[R][C];
		M=Integer.parseInt(st.nextToken());
		sharks=new shark[M+1];
		catched=new boolean[M+1];
		
		int k=0;
		for (int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken())-1;
			int s=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			int z=Integer.parseInt(st.nextToken());
			sharks[++k]=new shark(r, c, s, d, z);
			arr[r][c]=k;
		}
		
		for (int i=0; i<C; i++) {
			catchShark(i);
			moveSharks(i);
		}
		
		System.out.println(size);
		br.close();
	}
	
	private static void moveSharks(int cnt) {
		int[][] moved=new int[R][C];
		for (int k=1; k<=M; k++) {
			if (!catched[k]) {
				move(sharks[k], k, moved);
			}
		}
		arr=moved;
	}

	private static void move(shark sh, int k, int[][] moved) {
		int nr=sh.r;
		int nc=sh.c;
		int nd=sh.d;

		if (nd==1) {	// 위로
			nr=sh.r-sh.s%(2*(R-1));
			if (nr<0) {	// 위로 가다가 아래로 방향 전환
				nr=-nr;
				nd=2;
				if (nr>R-1) {	// 아래로 가다가 위로 방향 전환
					nr=2*(R-1)-nr;
					nd=1;
				}
			}
			
		} else if (nd==2) {	// 아래로
			nr=(sh.r+sh.s)%(2*(R-1));
			if (nr>R-1) {	// 아래로 가다가 위로 방향 전환
				nr=2*(R-1)-nr;
				nd=1;
				if (nr<0) {	// 위로 가다가 아래로 방향 전환
					nr=-nr;
					nd=2;
				}
			}
			
		} else if (nd==3) {	// 오른쪽로
			nc=(sh.c+sh.s)%(2*(C-1));
			if (nc>C-1) {	// 오른족로 가다가 왼쪽으로 방향 전환
				nc=2*(C-1)-nc;
				nd=4;
				if (nc<0) {	// 왼쪽으로 가다가 오른족으로 방향 전환
					nc=-nc;
					nd=3;
				}
			}
			
		} else if (nd==4) {	// 왼쪽으로
			nc=sh.c-sh.s%(2*(C-1));
			if (nc<0) {	// 왼쪽으로 가다가 오른쪽으로 방향 전환
				nc=-nc;
				nd=3;
				if (nc>C-1) {	// 오른쪽으로 가다가 왼쪽으로 방형 전환
					nc=2*(C-1)-nc;
					nd=4;
				}
			}
		}
		
		if (moved[nr][nc]!=0) {	// 상어가 있을 때 
			if (sh.z<sharks[moved[nr][nc]].z) { 	// 현재 상어가 기존 상어 먹음
				catched[k]=true;
				return;
			}
			else { 	// 기존 상어가 현재 상어 먹음
				catched[moved[nr][nc]]=true;
			}
		}
		
		sharks[k].r=nr;
		sharks[k].c=nc;
		sharks[k].d=nd;
		moved[nr][nc]=k;
	}

	private static void catchShark(int cc) {
		for (int i=0; i<R; i++) {
			int k=arr[i][cc];
			if (k!=0&&!catched[k]) {
				size+=sharks[k].z;
				catched[k]=true;
				break;
			}
		}
	}

}
