import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2239 {
// Baekjoon 2239. 스도쿠
	static boolean[] fix=new boolean[81];
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input2239.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] sudoku=new char[9][9];
		int[] arr=new int[81];	// 1차원 배열로 변환
		for (int i=0; i<9; i++) {
			sudoku[i]=br.readLine().toCharArray();
		}
		
		int k=0;
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				arr[k]=sudoku[i][j]-'0';
				if (arr[k]!=0) {
					fix[k]=true;	// 고정되어 있는 애들
				}
				k++;
			}
		}
		find(0, arr);
		
		br.close();
	}
	
	private static void find(int n, int[] ar) {
		int[] arr=ar.clone();
		if (n==81) {	// 다됐으면 출력하고 종료
			for (int i=0; i<81; i++) {
				System.out.print(arr[i]);
				if (i%9==8) {
					System.out.println();
				}
			}
			System.exit(0);;
		}
		if (arr[n]!=0||fix[n]) {	// 숫자가 있거나 고정인 경우 다음꺼 찾기
			find(n+1, arr);
		}
		int k=1;
		for (k=1; k<10; k++) {
			if (!fix[n]&&isValid(n, k, arr)) {	// 고정 안돼있고 유효한 경우
				arr[n]=k;
				find(n+1, arr);
			}
		}
	}

	private static boolean isValid(int n, int num, int[] arr) {
		for (int i=0; i<81; i+=9) {	// 같은 열 중복 방지
			if (arr[n%9+i]==num) {
				return false;
			}
		}
		
		for (int i=0; i<9; i++) {	// 같은 행 중복 방지
			if (arr[n/9*9+i]==num) {
				return false;
			}
		}
		
		int index=n/27*27+(n%9)/3*3;	
		for (int i=index; i<index+27; i+=9) {	// 네모칸 중복 방지
			for (int j=i; j<i+3; j++) {
				if (arr[j]==num) {
					return false;
				}
			}
		}
		
		return true;
	}

}