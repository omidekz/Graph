package Graph_DS;

import java.util.ArrayList;

public class Node<T> {
    public static class Adj<T>{
        private Node<T> dest;
        private float w;

        public Adj(Node<T> s,float weight){
            dest = s;
            w = weight;
        }

        public Node<T> getDest() {
            return dest;
        }

        public float getW() {
            return w;
        }

        public void setW(float w) {
            this.w = w;
        }

    }
    //-------------------------------
    private T data;
    private String id;
    private ArrayList<Adj<T>> adjectives;
    private int index;
    private boolean mark=false;

    public Node(T data, String id) {
        this();
        this.data = data;
        this.id = id;
    }

    private Node(){
        adjectives = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void mark(){
        setMark(true);
    }
    public void unmark(){
        setMark(false);
    }
    public void setMark(boolean m){
        mark = m;
    }
    public boolean isMark(){
        return mark;
    }

    public void addAdj(Adj<T> adj){
        adjectives.add(adj);
    }

    public void addAdj(Node<T> node,float w){
        addAdj(new Adj<>(node,w));
    }

    public ArrayList<Adj<T>> getAdjectives() {
        return adjectives;
    }

    public void remove(Node<T> node){
        String id = node.getId();

        for (int i = 0; i < getAdjectives().size(); i++) {
            if(adjectives.get(i).getDest().getId().equals(id)) {
                adjectives.remove(i);
                break;
            }
        }
    }
}
