package org.ex3.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

	private static List<Node> nodes = new ArrayList<Node>();
	private static List<Edge> edges = new ArrayList<Edge>();

	public static void main(String[] args) {

		// Definiujemy ile wêz³ów bêdzie w grafie
		for (int i = 0; i < 10; i++) {
			nodes.add(new Node("Node_" + i, "Node " + i));
		}

		// Dodajemy po³¹czenia miêdzy wêz³ami do grafu - > wartoœci wziête z instrukcji (Variant 4)
		// 1
		edges.add(new Edge(nodes.get(1), nodes.get(1), 0));

		// 2
		edges.add(new Edge(nodes.get(2), nodes.get(2), 0));
		edges.add(new Edge(nodes.get(1), nodes.get(2), 37));

		// 3
		edges.add(new Edge(nodes.get(3), nodes.get(3), 0));
		edges.add(new Edge(nodes.get(1), nodes.get(3), 45));
		edges.add(new Edge(nodes.get(2), nodes.get(3), 108));

		// 4
		edges.add(new Edge(nodes.get(4), nodes.get(4), 0));
		edges.add(new Edge(nodes.get(1), nodes.get(4), 93));
		edges.add(new Edge(nodes.get(2), nodes.get(4), 193));
		edges.add(new Edge(nodes.get(3), nodes.get(4), 28));

		// 5
		edges.add(new Edge(nodes.get(5), nodes.get(5), 0));
		edges.add(new Edge(nodes.get(1), nodes.get(5), 108));
		edges.add(new Edge(nodes.get(2), nodes.get(5), 115));
		edges.add(new Edge(nodes.get(3), nodes.get(5), 73));
		edges.add(new Edge(nodes.get(4), nodes.get(5), 93));

		// 6
		edges.add(new Edge(nodes.get(6), nodes.get(6), 0));
		edges.add(new Edge(nodes.get(1), nodes.get(6), 205));
		edges.add(new Edge(nodes.get(2), nodes.get(6), 41));
		edges.add(new Edge(nodes.get(3), nodes.get(6), 121));
		edges.add(new Edge(nodes.get(4), nodes.get(6), 54));
		edges.add(new Edge(nodes.get(5), nodes.get(6), 78));

		// 7
		edges.add(new Edge(nodes.get(7), nodes.get(7), 0));
		edges.add(new Edge(nodes.get(1), nodes.get(7), 49));
		edges.add(new Edge(nodes.get(2), nodes.get(7), 78));
		edges.add(new Edge(nodes.get(3), nodes.get(7), 155));
		edges.add(new Edge(nodes.get(4), nodes.get(7), 77));
		edges.add(new Edge(nodes.get(5), nodes.get(7), 29));
		edges.add(new Edge(nodes.get(6), nodes.get(7), 118));

		// 8
		edges.add(new Edge(nodes.get(8), nodes.get(8), 0));
		edges.add(new Edge(nodes.get(1), nodes.get(8), 58));
		edges.add(new Edge(nodes.get(2), nodes.get(8), 89));
		edges.add(new Edge(nodes.get(3), nodes.get(8), 103));
		edges.add(new Edge(nodes.get(4), nodes.get(8), 93));
		edges.add(new Edge(nodes.get(5), nodes.get(8), 45));
		edges.add(new Edge(nodes.get(6), nodes.get(8), 128));
		edges.add(new Edge(nodes.get(7), nodes.get(8), 21));

		// 9
		edges.add(new Edge(nodes.get(9), nodes.get(9), 0));
		edges.add(new Edge(nodes.get(1), nodes.get(9), 102));
		edges.add(new Edge(nodes.get(2), nodes.get(9), 40));
		edges.add(new Edge(nodes.get(3), nodes.get(9), 115));
		edges.add(new Edge(nodes.get(4), nodes.get(9), 130));
		edges.add(new Edge(nodes.get(5), nodes.get(9), 101));
		edges.add(new Edge(nodes.get(6), nodes.get(9), 39));
		edges.add(new Edge(nodes.get(7), nodes.get(9), 99));
		edges.add(new Edge(nodes.get(8), nodes.get(9), 87));

		// Szukamy najkrótszego po³¹czenia pomiedzy dwoma wêz³ami
		findShortestConnection(1, 9);
	}
	
	private static void findShortestConnection(int pointA, int pointB){
		// Definujemy, ¿e bêdzie to algorytm Dijkstra na grafie z 'edges' po³¹czeniami 
		Dijkstra dijkstra = new Dijkstra(new Graph(edges));
		// Przeszukujemy wszystkie po³¹czenia wychodz¹ce w wêz³a A 
		dijkstra.predefineAllConnections(nodes.get(pointA));
		// Tutaj wyci¹gamy ju¿ przygotowany wynik dla wêz³a B
		LinkedList<Node> foundConnection = dijkstra.getShortestPath(nodes.get(pointB));

		int totalDistance = 0;
		
		if (foundConnection != null) {
			System.out.println("Found shortest path:");
			for (Node node : foundConnection) {
				totalDistance += dijkstra.distances.get(node);
				System.out.println(node.name);
			}
		}
		
		System.out.println("Total distance = " + totalDistance);
	}
}
