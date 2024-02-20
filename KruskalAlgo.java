import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

public class KruskalAlgo {

    static int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        } else {
            return find(parent, parent[i]);
        }
    }

    static void union(int[] parent, int x, int y) {
        int xRoot = find(parent, x);
        int yRoot = find(parent, y);
        if (xRoot != yRoot) {
            parent[yRoot] = xRoot;
        }
    }

    public static List<Edge> kruskalMST(Edge[] graph, int V) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] parent = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        for (Edge edge : graph) {
            pq.add(edge);
        }

        List<Edge> mst = new ArrayList<>();

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int x = find(parent, edge.src);
            int y = find(parent, edge.dest);

            if (x != y) {
                mst.add(edge);
                union(parent, x, y);
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        int V = 4;
        Edge[] graph = new Edge[4];
        graph[0] = new Edge(0, 1, 10);
        graph[1] = new Edge(0, 2, 6);
        graph[2] = new Edge(0, 3, 5);
        graph[3] = new Edge(1, 2, 4);

        List<Edge> mst = kruskalMST(graph, V);

        System.out.println("Minimum Spanning Tree Edges:");
        for (Edge edge : mst) {
            System.out.println(edge.src + " -- " + edge.dest + " == " + edge.weight);
        }
    }
}
