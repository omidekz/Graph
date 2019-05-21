*   you can use of [DirectedGraph\<T>](Graph_DS/DirectedGraph.java) and [UnDirectedGraph\<T>](Graph_DS/UnDirectedGraph.java)
```java
    public class Test_Directed{
        public static void main(String[] args){
          DirectedGraph<Integer> graph = new DirectedGraph<>();
          // you have to set an Id and pass the data for add a node
          graph.addNode("A",5); // node with data = 5 and id = A
          graph.addNode("B",4);
          
          //we has an adj from A to B and weight = 3f
          graph.addAdj("A","B",3f);
          
          //we has an adj from B to A and weight = 1f
          //default weight = 1
          graph.addAdj("B","A");
          
          //we can remove a node with Id
          graph.remove("A");
          
          //we access to a node with its id
          graph.getNode("A");
          
          //each node has boolean var for marking that
          graph.getNode("A").isMark();
          //mark it with
          graph.getNode("A").mark(); // or graph.getNode("A").setMark(boolean mark);
          
          //we can clear nodes mark
          graph.clearMark();
          //we can know to is there a node that not marking?
          graph.containUnMarkNode();
          //and get them
          graph.getUnMarkNode();
          
          //each node has an index i that between 0 and vertexs
          // 0<= i < vertexs
          graph.getNode("A").getIndex();          
          
        }
    }
```

*   [Algorithms](Algorithms) is here


