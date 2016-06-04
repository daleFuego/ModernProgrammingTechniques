package org.ex3.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

	private List<Edge> edges;
	public Map<Node, Integer> distances = new HashMap<Node, Integer>();
	private Set<Node> assignedNodes = new HashSet<Node>();
	private Set<Node> unassignedNodes = new HashSet<Node>();
	private Map<Node, Node> previousNodes = new HashMap<Node, Node>();

	public Dijkstra(Graph graph) {
		this.edges = new ArrayList<Edge>(graph.edges);
	}

	public void predefineAllConnections(Node source) {
		// Inicjujemy zmienne
		distances.put(source, 0);
		unassignedNodes.add(source);
		
		// Dop�ki mamy jaki� nieprzypisany w�ze�
		while (unassignedNodes.size() > 0) {
			// Wyci�gamy w�ze� z najmniejsz� warto�ci�
			Node node = getMinimum(unassignedNodes);
			
			assignedNodes.add(node);
			unassignedNodes.remove(node);
			
			// Znajdujemy najkr�tszy dystans od podanego w�z�a
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Node node) {
		// Wyci�gamy po��czone w�z�y z danym 'node'
		List<Node> sorroundingNodes = getSorroundings(node);
		
		// Filtrujemy najlepsze odleg�o�ci
		for (Node target : sorroundingNodes) {
			if (findLowestValue(target) > findLowestValue(node) + getDistance(node, target)) {
				distances.put(target, findLowestValue(node) + getDistance(node, target));
				previousNodes.put(target, node);
				unassignedNodes.add(target);
			}
		}
	}

	private int getDistance(Node node, Node target) {
		for (Edge edge : edges) {
			if (edge.source.equals(node) && edge.destination.equals(target)) {
				return edge.weight;
			}
		}
		
		return Integer.MAX_VALUE;
	}

	private List<Node> getSorroundings(Node node) {
		List<Node> sorroundings = new ArrayList<Node>();
		
		for (Edge edge : edges) {
			if (edge.source.equals(node) && !isAlreadyAssigned(edge.destination)) {
				sorroundings.add(edge.destination);
			}
		}
		
		return sorroundings;
	}

	private Node getMinimum(Set<Node> nodes) {
		Node minimum = null;
		// W�ze� trafia do tych przypisanych, je�li znalaz�a si� dla niego najkr�tsza �cie�ka
		for (Node node : nodes) {
			if (minimum == null) {
				minimum = node;
			} else {
				if (findLowestValue(node) < findLowestValue(minimum)) {
					minimum = node;
				}
			}
		}
		
		return minimum;
	}

	private boolean isAlreadyAssigned(Node node) {
		return assignedNodes.contains(node);
	}

	private int findLowestValue(Node destination) {
		if (distances.get(destination) == null) {
			return Integer.MAX_VALUE;
		} else {
			return distances.get(destination);
		}
	}

	public LinkedList<Node> getShortestPath(Node node) {
		LinkedList<Node> path = new LinkedList<Node>();

		if (previousNodes.get(node) == null) {
			return null;
		}
		
		path.add(node);
		
		while (previousNodes.get(node) != null) {
			node = previousNodes.get(node);
			path.add(node);
		}
		
		// Segregujemy po kolei
		Collections.reverse(path);
		
		return path;
	}

}
