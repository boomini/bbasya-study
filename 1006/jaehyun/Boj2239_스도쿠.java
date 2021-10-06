package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Boj2239_스도쿠 {

	static class State {
		int x, y;
		State(int x, int y)
		{
			this.x=x; this.y=y;
		}
	}
	static int flag=0;
	static List<State> list=new ArrayList<>();
	static int map[][]=new int[10][10];
	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));

	   for(int i=1; i<=9; i++)
	   {
		   String input=in.readLine();
		   
		   for(int j=1; j<=9; j++)
		   {
			   
			   map[i][j]=input.charAt(j-1)-48;
			   
			   if(map[i][j]==0) list.add(new State(i,j));
		   }
  
	   }
		
		
	   simul(0); 

	}

	static void simul(int cnt)
	{
		if(cnt==list.size())
		{
			flag=1;
			for(int i=1; i<=9; i++)
			{
				for(int j=1; j<=9; j++)
				{
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
		
		else if(flag==0)
		{
		int x=list.get(cnt).x; //행
		int y=list.get(cnt).y; //열
		
		boolean check[]=new boolean[10]; 
		
		for(int i=1; i<=9; i++) //행과 열의 숫자 체크 
		{
			check[map[x][i]]=true;
			check[map[i][y]]=true;
		}
		int sx=0,sy=0;
		
		// 9개의 구역 중 어디에 속하는지? 
		if(x<=3&&y<=3) { sx=1; sy=1;}
		else if(x<=3&&y<=6) {sx=1; sy=4;}
		else if(x<=3&&y<=9) { sx=1; sy=7;}
		else if(x<=6&&y<=3) {sx=4;  sy=1;}
		else if(x<=6&&y<=6) {sx=4; sy=4;}
		else if(x<=6&&y<=9) {sx=4; sy=7;}
		else if(x<=9&&y<=3) {sx=7; sy=1;}
		else if(x<=9&&y<=6) {sx=7; sy=4;}
		else {  sx=7; sy=7;}
		
		for(int i=sx; i<sx+3; i++)
		{
			for(int j=sy; j<sy+3; j++)
			{
				check[map[i][j]]=true;
			}
		}
		
		for(int i=1; i<=9; i++)
		{
			if(check[i]==false&&flag==0)
			{
				map[x][y]=i;
				simul(cnt+1);
				map[x][y]=0;
				
			}
			
			
		}
			
			
			
			
		}
		
		
		
		
	}
	
}
