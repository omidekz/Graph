package Algorithms.Searching;

import Graph_DS.Exceptions;
import Graph_DS.UnDirectedGraph;
import Graph_DS.Node;
import Graph_DS.Queue;

import java.util.Arrays;

public class Searching {
    public static void main(String[] args) {
        UnDirectedGraph<Integer> graph = new UnDirectedGraph<>();
        //  uniq number is gt 0 and l vertexs => uniq number -> un ==> 0<=un<vertexs
        // A un = 0 B un = 1 C un = 2 and...
        graph.addNode(new String[]{"A","B","C","D","E","F","G","H"},new Integer[]{1,1,1,1,1,1,1,1});
        graph.addAdj("A","B","H");
        graph.addAdj("C","B","G","F");
        graph.addAdj("E","F","D");
        graph.addAdj("H","F","G");

        graph.print();

        System.out.println("------DFS------");
        DFS(graph,"A");
        DFS(graph,"C");
        DFS(graph,"D");
        System.out.println("------BFS-------");
        int[] dis;
        dis=BFS(graph,"A");
        System.out.println(Arrays.toString(dis));
        BFS(graph,"C");
        System.out.println(Arrays.toString(dis));
        BFS(graph,"D");
        System.out.println(Arrays.toString(dis));


        dis = BFS(graph,"A");
        System.out.println("Shortest path between A to D");
        System.out.println(getPath(graph.getNode("D"),dis));

        System.out.println(numberOfPartitions(graph));


    }

    private static void DFS(UnDirectedGraph<Integer> graph, String srcID){
        DFS(graph.getNode(srcID));
        System.out.println();
        graph.clearMark();
    }
    private static void DFS(Node<Integer> node){
        if(node.isMark())
            return;
        node.mark();
        System.out.print(node.getId()+" ");
        for (int i = 0; i < node.getAdjectives().size(); i++) {
            DFS(node.getAdjectives().get(i).getDest());
        }
    }

    /**
     * dis is true when all weight be equal
     * */
    private static int[] BFS(UnDirectedGraph<Integer> graph, String srcID){
        Queue<Node<Integer>> queue = new Queue<>();
        Node<Integer> node = graph.getNode(srcID);
        node.mark();
        queue.push(node);

        int[] dis = new int[graph.vertex()];
        dis[node.getIndex()] = 0;

        while (!queue.isEmpty()){
            node = queue.pop();
            System.out.print(node.getId()+" ");
            for (int i = 0; i < node.getAdjectives().size(); i++) {
                Node<Integer> tmp = node.getAdjectives().get(i).getDest();
                if(!tmp.isMark()){
                    dis[tmp.getIndex()] = dis[node.getIndex()] + 1;
                    tmp.mark();
                    queue.push(tmp);
                }
            }
        }
        System.out.println();
        graph.clearMark();
        return dis;
    }

    private static String getPath(Node<Integer> node,int[] distances){
        if(distances[node.getIndex()] == 0)
            return node.getId();
        for (int i = 0; i < node.getAdjectives().size(); i++) {
            Node<Integer> tmp = node.getAdjectives().get(i).getDest();
            if(distances[node.getIndex()] == distances[tmp.getIndex()]+1)
                return getPath(tmp,distances)+" "+node.getId();
        }
        throw new Exceptions("There is no with by this distances ");
    }

    private static int numberOfPartitions(UnDirectedGraph<Integer> graph){
        if(!graph.containUnMarkNode())
            return 0;
        DFS(graph.getUnMarkNode());
        System.out.println();
        return numberOfPartitions(graph)+1;
    }

}
