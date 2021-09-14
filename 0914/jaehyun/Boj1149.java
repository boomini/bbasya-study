

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1149_RGB거리 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
	int N=Integer.parseInt(in.readLine());
	
	int DP[][]=new int[N+1][3];  //0 빨강  1은 초록 2는 파랑 
		
	int cost[][]= new int[N+1][3]; //각 집을 빨강 초록 파랑으로 칠하는데 드는 비용
	
	for(int i=1; i<=N; i++)
	{
		st=new StringTokenizer(in.readLine()," ");
		
		cost[i][0]=Integer.parseInt(st.nextToken());
		cost[i][1]=Integer.parseInt(st.nextToken());
		cost[i][2]=Integer.parseInt(st.nextToken());
		
	}

	
	for(int i=1; i<=N; i++)
	{
		DP[i][0]=cost[i][0]+Math.min(DP[i-1][1],DP[i-1][2]);  //현재 칠하려는 색이 빨간색일 경우 
		DP[i][1]=cost[i][1]+Math.min(DP[i-1][0],DP[i-1][2]);  //현재 칠하려는 색이 초록색일 경우
		DP[i][2]=cost[i][2]+Math.min(DP[i-1][0],DP[i-1][1]);  //현재 칠하려는 색이 초록색일 경우

	}
	int val= Math.min(DP[N][0], DP[N][1]);

	System.out.println( Math.min(DP[N][2],val));
	}
}
