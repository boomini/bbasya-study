package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_특이한자석 {
public static void main(String[] args) throws IOException {
	
	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st;
	StringBuilder sb=new StringBuilder();
	
	int list[][]=new int[5][9]; 
	int score[]= {0,1,2,4,8};
	
	int tc=Integer.parseInt(in.readLine());
	
	for(int t=1; t<=tc; t++)
	{
		int k=Integer.parseInt(in.readLine());
		
		sb.append("#").append(t).append(" ");
		
		for(int i=1; i<=4; i++)
		{
			st=new StringTokenizer(in.readLine()," ");
			for(int j=1; j<=8; j++)
			{
				list[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		for(int i=1; i<=k; i++)  
		{
			st=new StringTokenizer(in.readLine()," ");
		    
			int number=Integer.parseInt(st.nextToken());
			int dir=Integer.parseInt(st.nextToken());
			
			int rotation[]=new int[5]; 
			rotation[number]=dir;
			
			int left=number-1;
			int right=number+1;
			while(left>=1)  // 왼쪽과 왼쪽+1을 비교 
			{				
				if(list[left][3]!=list[left+1][7]) 
				{
					if(rotation[left+1]==-1)
					{
						rotation[left]=1;
					}
					else {
						rotation[left]=-1;
					}
					
					left--;	
				}
				
				else {
					break;
				}
			}

			while(right<=4)  //오른쪽과 오른쪽 -1을 비교 
			{
				if(list[right-1][3]!=list[right][7])   //오른쪽의 7번과 오른쪽-1의 3번을 비교 
				{
					if(rotation[right-1]==-1)
					{
						rotation[right]=1;
					}
					else {
						rotation[right]=-1;
					}
					
					right++;
				}
				
				
				else {
					break;
				}
	
			}

			for(int j=1; j<=4; j++)
			{
				if(rotation[j]==1) 
				{
					int tmp=list[j][8];
					for(int a=8; a>=2; a--)
					{
						list[j][a]=list[j][a-1];
					}
					
					list[j][1]=tmp;

				}
				
				else if(rotation[j]==-1)
				{
					int tmp=list[j][1];
					
					for(int a=1; a<=7; a++)
					{
						list[j][a]=list[j][a+1];
					}
					list[j][8]=tmp;
					
				}						
			}
		}
		int sum=0;
		for(int i=1; i<=4; i++)
		{
			if(list[i][1]==1)
			{
				sum=sum+score[i];
			}
		}
		
		sb.append(sum).append("\n");
	}
	System.out.println(sb.toString());
}
}
