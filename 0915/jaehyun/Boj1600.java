package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1600 {


	static class State{
		
		int x, y, move, cnt;
		
		State(int x, int y, int move, int cnt)
		{
			this.x=x;
			this.y=y;
			this.move=move;
			this.cnt=cnt;
		}
		
	}
	
	static int dx[]= {1,-1,0,0};
	static int dy[]= {0,0,-1,1};
	
	static int hx[]= {-1,-2,-1,-2,1,2,1,2};  //말로 움직이는 좌표 
	static int hy[]= {-2,-1,2,1,-2,-1,2,1};
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader (System.in));
		StringTokenizer st;
		int ans=Integer.MAX_VALUE;
		int count=0;
		
		int K,W,H;
		
         K=Integer.parseInt(in.readLine());
         
         st=new StringTokenizer(in.readLine()," ");
		
         W=Integer.parseInt(st.nextToken());
         H=Integer.parseInt(st.nextToken());
         
         int map[][]=new int[H+1][W+1];
         int check[][][]=new int[K+1][H+1][W+1]; 
         
         for(int i=1; i<=H; i++)
         {
        	 st=new StringTokenizer(in.readLine()," ");
        	 for(int j=1; j<=W; j++)
        	 {
        		 map[i][j]=Integer.parseInt(st.nextToken());
        	 }
         }
         
         Queue <State> Q= new LinkedList<>();
         
         check[K][1][1]=1;
         Q.offer(new State(1,1,0,K));
         
         
         while(!Q.isEmpty())
         {
        	 count++;
        	 State now=Q.poll();
        	 int x=now.x;
        	 int y=now.y;
        	 int move=now.move;
        	 int cnt=now.cnt;

        	 if(x==H&&y==W)
			 {
				 ans=Math.min(ans,move);
			 }
        	 
        	 for(int i=0; i<=3;  i++)
        	 {
        		
        		 int nx=x+dx[i];
        		 int ny=y+dy[i];
        		 
        		 if(nx<1||nx>H||ny<1||ny>W) continue;
        			 
        		 
        		 if(map[nx][ny]==0&&check[cnt][nx][ny]==0)
        		 {
        			 check[cnt][nx][ny]=1;
        			 
        			
        			 
        			 
        			 Q.offer(new State(nx,ny,move+1,cnt));
        			 
        		 }
        		 
        		 
        	 }
        	 
        	 if(cnt>=1)
        	 {
        		 for(int i=0; i<=7; i++)
        		 {
        			
        			 int nx=x+hx[i];
        			 int ny=y+hy[i];
        			 
        			 if(nx<1||nx>H||ny<1||ny>W) continue;
        			 
        			 if(map[nx][ny]==0&&check[cnt-1][nx][ny]==0)
            		 {
            			 check[cnt-1][nx][ny]=1;

            			 
            			 Q.offer(new State(nx,ny,move+1,cnt-1));
            			 
            		 }
        		 }
        	 }
  
         }
         if(ans==Integer.MAX_VALUE)
        	 System.out.println(-1);
         else
         System.out.println(ans);
         
         System.out.println(count);
	}
}
