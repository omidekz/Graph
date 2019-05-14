package Graph_DS;

import java.util.ArrayList;

public abstract class Graph<T> {
    protected static final short DEFAULT_WEIGHT = 1;
    protected ArrayList<Node<T>> nodes;

    public void addNode(Node<T> node){
        node.setIndex(nodes.size());
        nodes.add(node);
    }
    public void addNode(String[] ids,T[] ds){
        if(ids.length != ds.length)
            throw new Exceptions(Exceptions.BAD_LENGTH);
        for (int i = 0; i < ids.length; i++) {
            addNode(new Node<>(ds[i],ids[i]));
        }
    }
    public void addNode(String id,T d){
        addNode(new Node<>(d,id));
    }

    //-------------------------------------------
    public abstract void addAdj(Node<T> node,Node<T> node_1,float w);

    public void addAdj(Node<T> f,Node<T> t){
        addAdj(f,t,DEFAULT_WEIGHT);
    }
    public void addAdj(String f,String t){
        addAdj(f,t,DEFAULT_WEIGHT);
    }
    public void addAdj(String[] ids,float[] weights){
        if(ids.length-1 != weights.length)
            throw new Exceptions("_IDS_ length != weights length");
        for (int i = 1; i <weights.length ; i++) {
            addAdj(ids[0],ids[i],weights[i-1]);
        }
    }
    public void addAdj(String... ids){
        for (int i = 1; i < ids.length; i++) {
            addAdj(ids[0],ids[i]);
        }
    }
    public void addAdj(String f,String t,float w){
        Node<T> from;
        Node<T> to;
        try {
            from = find(f);
            to = find(t);
            addAdj(from,to,w);
        }
        catch (Exceptions e){}
    }

    public abstract T remove(String id);

    public int indexOf(Node<T> node){
        return node.getIndex();
    }
    public int indexOf(String id){
        return indexOf(find(id));
    }

    public Node<T> getNode(String id){
        return find(id);
    }
    protected Node<T> find(String id){
        for (Node<T> node : nodes) {
            if (node.getId().equals(id))
                return node;
        }
        throw new Exceptions(Exceptions.NOT_FOUND + id);
    }
    private int findIndex(String id){
        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).getId().equals(id))
                return i;
        }
        throw new Exceptions(Exceptions.NOT_FOUND + id);
    }

    public int vertex(){
        return nodes.size();
    }

    private void print(Node<T> node){
        System.out.print(node.getId()+" [");
        for (int i = 0; i < node.getAdjectives().size(); i++) {
            System.out.print(node.getAdjectives().get(i).getDest().getId()+",");
        }
        if(node.getAdjectives().size() == 0)
            System.out.println("]");
        else
            System.out.println("\b]");
    }
    public void print(){
        for (int i = 0; i < nodes.size(); i++) {
            print(nodes.get(i));
        }
    }

    public void clearMark(){
        for (Node<T> node : nodes) {
            node.unmark();
        }
    }
    public boolean containUnMarkNode(){
        for (Node<T> node : nodes) {
            if (!node.isMark())
                return true;
        }
        return false;
    }
    public Node<T> getUnMarkNode(){
        for (Node<T> node : nodes) {
            if (!node.isMark())
                return node;
        }
        throw new Exceptions("There is no UnMark node in list");
    }

    protected ArrayList<Node.Adj<T>> adjs(Node<T> node){
        return node.getAdjectives();
    }
    public ArrayList<Node.Adj<T>> adjs(String id){
        return find(id).getAdjectives();
    }
}