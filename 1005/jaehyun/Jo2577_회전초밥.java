import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jo2577_회전초밥 {

	
	public static void main(String[] args) throws IOException {
		
	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
	
	StringTokenizer st=new StringTokenizer(in.readLine()," ");
	
	
	int n=Integer.parseInt(st.nextToken()); //접시의 수 
	int d=Integer.parseInt(st.nextToken()); //초밥의 가짓수 
	int k=Integer.parseInt(st.nextToken()); //연속해서 먹는 접시수 
	int c=Integer.parseInt(st.nextToken()); // 쿠폰 번호 
	
	int ans=1;
	int list[]=new int[n+1]; // 접시에 들어있는 초밥의 종류들
	int check[]=new int[d+1]; // 어떤 초밥을 먹었는지 체크  
	int cnt=0; //현재 먹은 초밥의 종류
	
	
	for(int i=1; i<=n; i++)
	{
		list[i]=Integer.parseInt(in.readLine());
	}
	

	 
	int sp=1; int ep=k;  //연속된 접시의 시작지점과 끝지점 

	for(int i=1; i<=k; i++)
	{
		check[list[i]]++;
		
	   if(check[list[i]]==1)
	   {
		   cnt++;
		   
	   }
		
		
	}
	while(true)
	{
			if(check[c]==0) //연속해서 먹은 초밥들 중에서 무료쿠폰으로 제공되는 초밥이 없다면? 
			{
				ans=Math.max(ans,cnt+1);

			}
			
			else {
				ans=Math.max(ans,cnt);
			}
			
			
			
			if(ep==k-1) break;  // ep가 k-1이라면 중단 
			
			
			
			check[list[sp]]--;
			
			if(check[list[sp]]==0)  //해당 초밥을 먹은 횟수가 0이 된다면 종류에서 -1 
			{
				cnt--;
			}
			
			sp++;  //sp+1
			ep++;  //ep+1
			if(sp==n+1) sp=1;
			if(ep==n+1) ep=1;  //n+1이 됐다면 1로 
			
			
			check[list[ep]]++;
		     if(check[list[ep]]==1)
		     {
		    	 cnt++;
		     }
            
		
		
	}
	
	
		System.out.println(ans);
		
		
		
	}
	
	
}
