import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

//크루스칼 알고리즘 
public class D4_3124 {

	static class Edge implements Comparable<Edge> {
		
		int a, b, e;
		
		Edge(int a, int b, int e)
		{
			this.a=a;
			this.b=b;
			this.e=e;
		}

		@Override
		public int compareTo(Edge o) {
			
		return this.e-o.e;
			
		}

	}
	
	
	
	static int V,E;
	static int parent[];
	static List<Edge> list;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		
		
		int tc=Integer.parseInt(in.readLine());
		
		for(int t=1; t<=tc; t++)
		{
			sb.append("#").append(t).append(" ");  
			
			list=new ArrayList<Edge>();
			st=new StringTokenizer(in.readLine()," ");
			//정점과 간선 
			V=Integer.parseInt(st.nextToken());
			E=Integer.parseInt(st.nextToken());
			
			// 집합 초기화
			parent=new int[V+1];
			for(int i=1; i<=V; i++)
			{
				parent[i]=i;
			}
			
			
			for(int i=1; i<=E; i++)
			{
				st=new StringTokenizer(in.readLine()," ");
				int a,b,c;
				
				a=Integer.parseInt(st.nextToken());
				b=Integer.parseInt(st.nextToken());
				c=Integer.parseInt(st.nextToken());
				
				list.add(new Edge(a,b,c));
				
				
			}
			Collections.sort(list);
			
			long ans=0;
			int cnt=0;
			for(int i=0; i<list.size(); i++)
			{
				Edge now=list.get(i);
				
				if(find(now.a)!=find(now.b))
				{
					Union(now.a,now.b);
					cnt++;
					ans+=now.e;
					
					if(cnt==V-1)
					{
						break;
					}
					
				}
	
			}

			sb.append(ans).append("\n");
			
			
		}
		
		System.out.println(sb.toString());
		
	}
	
	static int find(int a)
	{
		if(parent[a]==a)
		{
			return a;
		}
		
		else {
			
		return	parent[a]=find(parent[a]);
		
		}

	}

	static boolean Union(int a, int b)
	{
		a= find(a);
		b=find(b);
		
		if(a!=b)
		{
			parent[b]=a;
			return true;
		}
		
		return false;
		
		
		
	}
}
