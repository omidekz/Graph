package Graph_DS;

import java.util.ArrayList;

public class UnDirectedGraph<T> extends Graph<T> {

    public UnDirectedGraph(){
        nodes = new ArrayList<>();
    }

    @Override
    public void addAdj(Node<T> f,Node<T> t,float w){
        f.addAdj(t,w);
        t.addAdj(f,w);
    }

    // worstecase = O(n+m)
    private Node<T> remove(Node<T> node){
        nodes.remove(node.getIndex());
        for (int i = 0; i < node.getAdjectives().size(); i++) {
            node.getAdjectives().get(i).getDest().remove(node);
        }
        for (int i = node.getIndex(); i <nodes.size() ; i++) {
            nodes.get(i).setIndex(i);
        }
        return node;
    }

    @Override
    public T remove(String id) {
        return remove(find(id)).getData();
    }

}