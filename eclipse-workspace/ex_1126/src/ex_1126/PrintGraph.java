package ex_1126;

public class PrintGraph {
	
	void graph (int[] arrint ) {
		int[] arr = arrint;
		char[][] graph = new char[10][];
		int cnt=0;
		
		for (int i=0;i<10;i++)  {
			for (int j=0;j<arr.length;j++) {
				if (arr[j]==i) {
					cnt++;
				}
			}
			
			graph[i] = new char[cnt];
			
			for (int n=0;n<cnt;n++) {
				graph[i][n] = '#';
			}
			
			cnt=0;
			
		}
		
		for (int i=0;i<graph.length;i++) {
			StringBuilder line = new StringBuilder();

		    for (char c : graph[i]) {
		        line.append(c);
		    }

		    System.out.println(i + " 의 개수: " + line + " " + graph[i].length);
		}
		
	}

}
