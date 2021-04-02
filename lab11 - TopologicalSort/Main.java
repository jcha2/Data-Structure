package lab11;

import java.io.*;
import java.util.*;

public class Main {

	public static int[] calIndegree(String vertex[], int edges[][]) {
		int indegree[] = new int[vertex.length + 1];

		for (int i = 1; i <= vertex.length; i++) {
			for (int j = 1; j <= vertex.length; j++) {
				indegree[i] = indegree[i] + edges[j][i]; // 각 vertex의 indegree 값은 열의 합
			}
		}
		return indegree;
	}

	public static void toposort(String vertex[], int edges[][]) {
		int indegree[] = calIndegree(vertex, edges); // indegree 계산해서 배열로 가져오기.
		ArrayList<Integer> result = new ArrayList<>();
		int ver;

		while (result.size() < vertex.length) { // result에 모든 vertex 가 들어오면 중단
			ArrayList<Integer> temp = new ArrayList<>(); // 루프마다 갱신되는 list, 루프를 돌 때마다 0인 값을 저장하여 for에서 사용

			for (ver = 1; ver <= vertex.length; ver++) {
				if (indegree[ver] == 0) { // indegree값이 0인 vertex를 result와 temp에 저장하고
					result.add(ver);
					temp.add(ver);
					indegree[ver] = -1; // 해당 vertex의 indegree 값을 -1로 설정
				}
			}
			for (int i = 1; i <= vertex.length; i++) { // 삭제 vertex와 관련된 vertex들의 indegree 값을 줄여줌
				for (int j = 0; j < temp.size(); j++) { // temp리스트에 들어온 값 (현 루프에서 indegree 값이 0이었던 vertex)
					if (edges[temp.get(j)][i] == 1) // vertex에 연결되어 있는 edge 있으면 그 목적지 edge의 indegree값을 -1
						indegree[i] -= 1;
				}
			}
		}

		for (int i = 0; i < vertex.length; i++) {
			System.out.print(result.get(i) + " ");
		}
		System.out.println();

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("topo_input.txt"));

		String vertex[] = br.readLine().split(" ");
		String edge[] = br.readLine().split(" ");
		int edges[][] = new int[vertex.length + 1][vertex.length + 1];

		for (int i = 0; i < edge.length; i++) {
			String line[] = edge[i].split("-");
			edges[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = 1;
		}

		toposort(vertex, edges);

		br.close();
	}
}
