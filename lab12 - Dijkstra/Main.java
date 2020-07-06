package lab12;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("shortest_input.txt"));
		Scanner sc = new Scanner(System.in);

		String vertex[] = br.readLine().split(" ");
		String edge[] = br.readLine().split(" ");
		int edges[][] = new int[vertex.length + 1][vertex.length + 1];

		for (int i = 0; i < edge.length; i++) { // edges 값 넣어주기
			String line[] = edge[i].split("-");
			edges[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = Integer.parseInt(line[2]);
		}

		for (int i = 1; i <= vertex.length; i++) {
			for (int j = 1; j <= vertex.length; j++) {
				System.out.print(edges[i][j] + " ");
			}
			System.out.println();
		}

		while (true) { // N 입력 될 떄까지 계속 진행
			System.out.print("start : ");
			int startv = sc.nextInt();
			System.out.print("end : ");
			int endv = sc.nextInt();

			Dijkstra(vertex, edges, startv, endv);

			System.out.print("restart?(Y/n) : ");
			String next = sc.next();
			if (next.equals("N"))
				break;
			else if (next.equals("Y"))
				continue;
			else {
				System.out.println("Error");
				break;
			}
		}
		br.close();
		sc.close();
	}

	public static void Dijkstra(String vertex[], int edges[][], int startv, int endv) {
		int dist[] = new int[vertex.length + 1]; // vertex는 1부터 시작하므로
		boolean visit[] = new boolean[vertex.length + 1];
		int resultver[] = new int[vertex.length];
		int resultindex = 0;
		for (int i = 1; i <= vertex.length; i++) {
			dist[i] = edges[startv][i]; // dist 초기화
			visit[i] = false;
		}
		visit[startv] = true;
		resultver[resultindex++] = startv;
		print(dist, visit);

		int nowver = startv;

		while (nowver != endv) {
			int next = 0;
			int nextver = 0;

			for (int j = 1; j <= vertex.length; j++) {
				if (visit[j] == false) {
					if (next == 0 && edges[nowver][j] != 0) {
						next = edges[nowver][j]; // next에 첫 값 저장
						nextver = j;
					} else {
						if (edges[nowver][j] != 0 && next > edges[nowver][j]) {
							next = edges[nowver][j];
							nextver = j;
						}
					}
				}
			}
			visit[nextver] = true;
			resultver[resultindex++] = nextver;
			dist = calDist(edges, visit, dist, nextver);
			print(dist, visit);

			nowver = nextver;
		}

		if (visit[endv] == false) {
			System.out.println("no where to go " + endv);
		} else {
			for (int i : resultver)
				System.out.print(i);
		}
	}

	public static int[] calDist(int edges[][], boolean visit[], int dist[], int ver) { // 지금 방문하는 점

		for (int i = 1; i < visit.length; i++) {
			if (edges[ver][i] != 0) {
				dist[i] = dist[i] < dist[ver] + edges[ver][i] ? dist[i] : dist[ver] + edges[ver][i];
			}
		}

		return dist;
	}

	public static void print(int dist[], boolean visit[]) {
		for (int i : dist)
			System.out.print(i + " ");
		for (boolean i : visit)
			System.out.print(i + " ");

		System.out.println();
	}
}
