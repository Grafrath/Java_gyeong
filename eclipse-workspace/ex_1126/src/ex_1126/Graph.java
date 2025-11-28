package ex_1126;
import java.util.*;

public class Graph {

	public static void main(String[] args) {
		// 
		int arr[] = new int[100];
		PrintGraph print = new PrintGraph();
		
		for (int i=0;i<arr.length;i++) {
			arr[i] = (int)(Math.random() * 10);
		}
		
		print.graph(arr);
		
	}

}
