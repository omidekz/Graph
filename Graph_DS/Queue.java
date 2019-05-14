package Graph_DS;

import java.util.ArrayList;

public class Queue<T> {
    private static final String EMPTY_QUEUE = "Queue is Empty";
    private ArrayList<T> list;

    public Queue(){
        list = new ArrayList<>();
    }

    public boolean isEmpty(){
        return list.size() == 0;
    }

    public void push(T d){
        list.add(d);
    }

    public T pop(){
        if(isEmpty())
            throw new Exceptions(EMPTY_QUEUE + "and you call pop()");
        return list.remove(0);
    }

    public T peek(){
        if(isEmpty())
            throw new Exceptions(EMPTY_QUEUE + "and you call pop()");
        return list.get(0);
    }
}
