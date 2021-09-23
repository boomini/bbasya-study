package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Boj1786 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
	char[] text=in.readLine().toCharArray();
	char[] p=in.readLine().toCharArray();
	int[] pi=new int[p.length];
	
	
	//부분일치 테이블 만들기(전처리)
	
	//접미사 i=1       접두사 j=0
	
	for(int i=1, j=0; i<p.length; i++)
	{
		while(j>0&&p[i]!=p[j])
		{
			j=pi[j-1];	
		}
		
		//j가 0이 돼서 빠져나온 경우와  p[i]과 p[j]가 같아서 빠져나온 경우 
		
		if(p[j]==p[i])
		{
			pi[i]=++j;
		}
		
		//j가 0 인경우?는 pi[i]의 값이 0  

		
	}
		
//	for(int i=0; i<pi.length; i++)
//	{
//		System.out.print(pi[i]);
//	}
	
	//
	
	
	List<Integer> list=new ArrayList<>();  // 텍스트에서 패턴이 시작하는 위치 
	
	// 텍스트에 패턴 찾기 
	
	for(int i=0,j=0; i<text.length; i++)
	{
		while(j>0&&text[i]!=p[j])
		{
			j=pi[j-1];
			
		}
		
		
		// 패턴의 j와 텍스트의 i가 같은 경우거나 j가 0이여서 빠져나온 경우 
		
		if(text[i]==p[j])
		{
			
			
			if(j+1==p.length) //패턴의 끝까지 다 같다면?
			{
				list.add(i+2-p.length);
				
				j=pi[j];
				
			}
			
			else {
				j++;
			}
			
		}
		
		
		
		
	}
	
	
	System.out.println(list.size());
	
	for(int i=0; i<list.size(); i++)
	{
		sb.append(list.get(i)).append(" ");
	
		
	}
	
	System.out.println(sb.toString());
		
	}
}
