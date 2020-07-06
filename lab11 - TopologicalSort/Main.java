package lab11;

import java.io.*;
import java.util.*;

public class Main {

	public static int[] calIndegree(String vertex[], int edges[][]) {
		int indegree[] = new int[vertex.length + 1];

		for (int i = 1; i <= vertex.length; i++) {
			for (int j = 1; j <= vertex.length; j++) {
				indegree[i] = indegree[i] + edges[j][i]; // �� vertex�� indegree ���� ���� ��
			}
		}
		return indegree;
	}

	public static void toposort(String vertex[], int edges[][]) {
		int indegree[] = calIndegree(vertex, edges); // indegree ����ؼ� �迭�� ��������.
		ArrayList<Integer> result = new ArrayList<>();
		int ver;

		while (result.size() < vertex.length) { // result�� ��� vertex �� ������ �ߴ�
			ArrayList<Integer> temp = new ArrayList<>(); // �������� ���ŵǴ� list, ������ �� ������ 0�� ���� �����Ͽ� for���� ���

			for (ver = 1; ver <= vertex.length; ver++) {
				if (indegree[ver] == 0) { // indegree���� 0�� vertex�� result�� temp�� �����ϰ�
					result.add(ver);
					temp.add(ver);
					indegree[ver] = -1; // �ش� vertex�� indegree ���� -1�� ����
				}
			}

			for (int i = 1; i <= vertex.length; i++) { // ���� vertex�� ���õ� vertex���� indegree ���� �ٿ���
				for (int j = 0; j < temp.size(); j++) { // temp����Ʈ�� ���� �� (�� �������� indegree ���� 0�̾��� vertex)
					if (edges[temp.get(j)][i] == 1) // vertex�� ����Ǿ� �ִ� edge ������ �� ������ edge�� indegree���� -1
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