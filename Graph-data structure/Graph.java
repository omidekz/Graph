
import java.util.ArrayList;

public class Graph<T> {
    private static final short DEFAULT_WEIGHT = 1;

    private ArrayList<Node<T>> nodes;
    Graph(){
        nodes = new ArrayList<>();
    }

    private Node<T> find(String id){
        for (int i = 0; i < nodes.size(); i++)
            if(nodes.get(i).getId().equals(id))
                return nodes.get(i);
        throw new Exceptions(Exceptions.NOT_FOUND + id);
    }

    void addNode(String id,T data){
        addNode(new Node<>(data,id));
    }
    void addNode(Node<T> node){
        node.setIndex(nodes.size());
        nodes.add(node);
    }

    void addAdj(Node<T> f,Node<T> t,float w){
        f.addAdj(t,w);
        t.addAdj(f,w);
    }
    void addAdj(Node<T> f,Node<T> t){
        addAdj(f,t,DEFAULT_WEIGHT);
    }
    void addAdj(String f,String t){
        addAdj(f,t,DEFAULT_WEIGHT);
    }
    void addAdj(String f,String t,float w){
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

    int indexOf(Node<T> node){
        return node.getIndex();
    }
    int indexOf(String id){
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

    void remove(String id){
        remove(find(id));
    }

    private void print(Node<T> node){
        System.out.print(node.getId()+" [");
        for (int i = 0; i < node.getAdjectives().size(); i++) {
            System.out.print(node.getAdjectives().get(i).getDest().getId()+",");
        }
        System.out.println("\b]");
    }
    void print(){
        for (int i = 0; i < nodes.size(); i++) {
            print(nodes.get(i));
        }
    }

    Node<T> getNode(String id){
        return find(id);
    }

    void clearMark(){
        for (Node<T> node : nodes) {
            node.unmark();
        }
    }

    int vertex(){
        return nodes.size();
    }

    private ArrayList<Node.Adj<T>> adjs(Node<T> node){
        return node.getAdjectives();
    }
    ArrayList<Node.Adj<T>> adjs(String id){
        return find(id).getAdjectives();
    }


}
