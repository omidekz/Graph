package Graph_DS;

import java.util.ArrayList;

public class Graph<T> {
    private static final short DEFAULT_WEIGHT = 1;

    private ArrayList<Node<T>> nodes;
    public Graph(){
        nodes = new ArrayList<>();
    }

    private Node<T> find(String id){
        for (int i = 0; i < nodes.size(); i++)
            if(nodes.get(i).getId().equals(id))
                return nodes.get(i);
        throw new Exceptions(Exceptions.NOT_FOUND + id);
    }

    public void addNode(String[] ids,T[] datas){
      if(ids.length != datas.length)
        throw new Exceptions("_IDS_ length != _Datas_");
      for(int i = 0 ; i < ids.length;i++){
        addNode(ids[i],datas[i]);
      }
    }
    public void addNode(String id,T data){
        addNode(new Node<>(data,id));
    }
    public void addNode(Node<T> node){
        node.setIndex(nodes.size());
        nodes.add(node);
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
    public void addAdj(Node<T> f,Node<T> t,float w){
        f.addAdj(t,w);
        t.addAdj(f,w);
    }
    public void addAdj(Node<T> f,Node<T> t){
        addAdj(f,t,DEFAULT_WEIGHT);
    }
    public void addAdj(String f,String t){
        addAdj(f,t,DEFAULT_WEIGHT);
    }
    public void addAdj(String f,String t,float w){
        Node<T> from;
        Node<T> to;
        boolean ff = false;
        try {
            from = find(f);
            ff = true;
            to = find(t);
            addAdj(from,to,w);
        }
        catch (Exceptions e){
            if(!ff)
                throw new Exceptions(Exceptions.NOT_FOUND + f);
            else
                throw new Exceptions(Exceptions.NOT_FOUND + t);
        }
    }

    private int findIndex(String id){
        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).getId().equals(id))
                return i;
        }
        throw new Exceptions(Exceptions.NOT_FOUND + id);
    }

    public int indexOf(Node<T> node){
        return node.getIndex();
    }
    public int indexOf(String id){
        return find(id).getIndex();
    }

    private void remove(Node<T> node){
        for (int i = 0; i < node.getAdjectives().size(); i++) {
            node.getAdjectives().get(i).getDest().remove(node);
        }
        nodes.remove(node.getIndex());
        for (int i = node.getIndex(); i <nodes.size() ; i++) {
            nodes.get(i).setIndex(i);
        }
    }

    public void remove(String id){
        remove(find(id));
    }

    private void print(Node<T> node){
        System.out.print(node.getId()+" [");
        for (int i = 0; i < node.getAdjectives().size(); i++) {
            System.out.print(node.getAdjectives().get(i).getDest().getId()+",");
        }
        System.out.println("\b]");
    }
    public void print(){
        for (int i = 0; i < nodes.size(); i++) {
            print(nodes.get(i));
        }
    }

    public Node<T> getNode(String id){
        return find(id);
    }

    public void clearMark(){
        for (Node<T> node : nodes) {
            node.unmark();
        }
    }

    public int vertex(){
        return nodes.size();
    }

    private ArrayList<Node.Adj<T>> adjs(Node<T> node){
        return node.getAdjectives();
    }
    ArrayList<Node.Adj<T>> adjs(String id){
        return find(id).getAdjectives();
    }
}
