package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj4485 {

	static int dx[]= {1,-1,0,0};
	static int dy[]= {0,0,-1,1};
	
	
	static class State implements Comparable<State> {
		int x, y,dis;
		State(int x, int y,int dis)
		{
			this.x=x;
			this.y=y;
			this.dis=dis;
		}
		@Override
		public int compareTo(State o) {
			
			if(dis<o.dis)
			{
				return -1;
			}
			
			else return 1;
			
			
			
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		
		int tc=1;
		while(true)
		{
			int ans=0;
			int n=Integer.parseInt(in.readLine());
			if(n==0) break;
			
			sb.append("Problem ").append(tc+": ");
			
			int map[][]=new int[n+1][n+1];
			boolean check[][]=new boolean[n+1][n+1];
			
			for(int i=1; i<=n; i++)
			{
				 st=new StringTokenizer(in.readLine()," ");
				for(int j=1; j<=n; j++)
				{
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			PriorityQueue<State> PQ= new PriorityQueue<>(); //우선순위 큐 
			PQ.add(new State(1,1,map[1][1]));
			while(!PQ.isEmpty())
			{
				State now=PQ.poll();
				
				if(check[now.x][now.y]==false)
				{
					if(now.x==n&&now.y==n)
					{
						ans=now.dis;
						break;
					}
					check[now.x][now.y]=true;
					for(int i=0; i<=3; i++)
					{
						int nx=now.x+dx[i];
						int ny=now.y+dy[i];
	
						if(nx<1||nx>n||ny<1||ny>n||check[nx][ny]!=false) continue;
						PQ.add(new State(nx,ny,now.dis+map[nx][ny]));
					}	
					
				}
	
			}	
			sb.append(ans+"\n");
			tc++;
			
		}
		
		System.out.println(sb.toString());
		
		
		
	}
}
