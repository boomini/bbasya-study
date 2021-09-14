package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1463_1로만들기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader in=new BufferedReader(new InputStreamReader (System.in));
		
		int N= Integer.parseInt(in.readLine());
		
		int[] DP= new int[N+1];
		
		
		for(int i=N; i>=1; i--)
		{
			int min=1000000;
			if(i*2<=N)
			{
				min=Math.min(min,1+DP[i*2]);
				
			}
			
			if(i+1<=N)
			{
				min=Math.min(min,1+DP[i+1]);
				
			}
			
			if(i*3<=N)
			{
				min=Math.min(min,1+DP[i*3]);
			}
			if(min!=1000000)
          DP[i]=min;
		}
		System.out.println(DP[1]);
	}

}
