package Test;

import Graph_DS.DirectedGraph;
import Graph_DS.UnDirectedGraph;

public class AddNode_Adj_Remove_Test {
    public static void main(String[] args){

        UnDirectedGraph<Integer> graph = new UnDirectedGraph<>();
        graph.addNode(new String[]{"A","B","C","D","E"},new Integer[]{1,1,1,1,1});
        // : node id = A and data = 1
        // : node id = B and data = 1
        graph.addAdj("A","B","C"); // meanin : [adj(A,B),weight = 1]  , [adj(A,C),weight=1]
        graph.addAdj("A","E",3.27f); // [adj(A,E),weight = 3.27f]
        graph.addAdj("B","C","E","D");
        graph.addAdj("C","D","E");
        graph.print();
        graph.remove("B"); // remove node B
        graph.print();
        graph.indexOf("A"); // each node has an uniq number indexOf(String id) return this uniq number
        graph.getNode("A"); // return the node with this id
        graph.getNode("A").getIndex(); // this method equals to graph.indexOf() method
        graph.vertex(); // return the number of nodes

    }
}
