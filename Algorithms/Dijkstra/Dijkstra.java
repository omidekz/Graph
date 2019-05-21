package Algorithms.Dijkstra;

import Graph_DS.Node;
import Graph_DS.UnDirectedGraph;

import java.util.Arrays;

public class Dijkstra {


    public static void main(String[] args) {
        UnDirectedGraph<Integer> graph = new UnDirectedGraph<>();
        graph.addNode(new String[]{"A","B","C","D","E"},new Integer[]{2,2,2,2,2});

        graph.addAdj(new String[]{"A","B","E"},new float[]{2,4});
        graph.addAdj("B","C",3);
        graph.addAdj("C","D",3);
        graph.addAdj("C","E",2);
        graph.addAdj("D","E",5);
        graph.print();

        System.out.println(Arrays.toString(dijkstra(graph,"A")));
    }


    private static float[] dijkstra(UnDirectedGraph<Integer> graph, String srcID){

        float[] distances = new float[graph.vertex()];
        int srcIndex = graph.getNode(srcID).getIndex();
        Arrays.fill(distances,Integer.MAX_VALUE);
        distances[srcIndex] = 0;

        Compare<Node<Integer>> compare = (parent, child) -> {
            if(distances[parent.getIndex()] > distances[child.getIndex()])
                return Answer.BIGGER;
            return Answer.LESSER;
        };
        Heap<Node<Integer>> heap = new Heap<>(compare,true);

        for (int i = 0; i < graph.vertex(); i++) {
            heap.add(graph.getNode(i));
        }

        while (!heap.isEmpty()) {
            Node<Integer> node = heap.remove();
            node.mark();
            for (Node.Adj<Integer> adj : node.getAdjectives()) {
                if (adj.getW() + distances[node.getIndex()] < distances[adj.getDest().getIndex()]) {
                    distances[adj.getDest().getIndex()] =
                            adj.getW() +
                            distances[node.getIndex()];
                }
            }
        }
        return distances;

    }
}
