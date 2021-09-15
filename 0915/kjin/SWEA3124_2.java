import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA3124_2 {
// SW Expert Academy 3124. 최소 스패닝 트리
// 크루스칼
	static void make() {
		parents = new int[V+1];
		for (int i=1; i<=V; i++) {
			parents[i]=i;
		}
	}
	
	static int find(int a) {
		if (parents[a]==a)	return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int ap = find(a);
		int bp = find(b);
		if (ap==bp)	return false;
		parents[bp]=ap;
		return true;
	}
	
	static class Edge implements Comparable<Edge>{
		int s, e, w;

		public Edge(int s, int e, int w) {
			super();
			this.s = s;
			this.e = e;
			this.w = w;
		}


		@Override
		public int compareTo(Edge o) {
			return this.w-o.w;
		}
		
	}
	
	static int V, E;
	static int parents[];
	static Edge edgeList[];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input3124.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int t=1; t<=T; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edgeList = new Edge[E];
			int k=0;
			for (int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				edgeList[k++] = new Edge(a, b, w);
			}
			
			Arrays.sort(edgeList);
			
			make();
			int cnt=0;
			long result=0;
			for (Edge e: edgeList) {
				if (union(e.s, e.e)) {
					result+=(long)e.w;
					cnt++;
				}
				if (cnt==V-1) {
					break;
				}
			}
			
			
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}

}
