

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5643 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		
		int T=Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=T; tc++)
		{
		   sb.append("#").append(tc).append(" ");
			int ans=0;
			int n=Integer.parseInt(in.readLine()); //학생수
			int m=Integer.parseInt(in.readLine()); //키 비교횟수 
			
			//각 학생별 연결리스트 
			
			
			List<Integer> tall[]=new ArrayList[n+1];
			List<Integer> small[]=new ArrayList[n+1];
			
			for(int i=1; i<=n; i++)
			{
				tall[i]=new ArrayList<>();  // 내 기준으로 나보다 큰 애들을 저장
				small[i]=new ArrayList<>();	// 내 기준으로 나보다 작은애들을 저장 
			}
			
			for(int i=1; i<=m; i++)
			{
				// a <b 보다 작다 
				 st=new StringTokenizer(in.readLine()," ");
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				
				tall[a].add(b);
				small[b].add(a);
 
			}
			
			
			//나보다 키큰사람과 키작은사람을 카운트  n-1명이라면 내가 몇번째인지 알수있다 
			
			for(int i=1; i<=n; i++)
			{
				int cnt=0;
				boolean check[]=new boolean[n+1];
				check[i]=true; //시작지점 체크 
				Queue<Integer> Q=new LinkedList<>();
				
				Q.offer(i);
				
				while(!Q.isEmpty())
				{
					int now=Q.poll();
					
					for(int j=0; j<tall[now].size(); j++)
					{
						int next=tall[now].get(j);
						
						if(check[next]==false)
						{
							check[next]=true;
							cnt++;
							Q.add(next);
							
							
						}

					}

				}
				
				check=new boolean[n+1];
				check[i]=true; //시작지점 체크 
				Q.offer(i);
				
				while(!Q.isEmpty())
				{
					int now=Q.poll();
					
					for(int j=0; j<small[now].size(); j++)
					{
						int next=small[now].get(j);
						
						if(check[next]==false)
						{
							check[next]=true;
							cnt++;
							Q.add(next);
							
							
						}

					}

				}
				
				
				if(cnt==n-1)
				{
					ans++;
				}
				
				
			}
			
			sb.append(ans).append("\n");
	
			
			
		}
		System.out.println(sb.toString());
		
	}
}
