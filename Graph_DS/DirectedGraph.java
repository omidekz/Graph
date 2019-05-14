package Graph_DS;

import java.util.ArrayList;

public class DirectedGraph<T> extends Graph<T> {

    public DirectedGraph(){
        nodes = new ArrayList<>();
    }

    @Override
    public void addAdj(Node<T> node, Node<T> node_1, float w) {
        node.addAdj(node_1,w);
    }

    @Override
    public T remove(String id) {
        return remove(find(id)).getData();
    }

    //always = O(n + m)
    private Node<T> remove(Node<T> node){
        nodes.remove(node.getIndex());
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).remove(node);
            nodes.get(i).setIndex(i);
        }
        return node;
    }

}