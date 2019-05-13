package Graph_DS;

import java.util.ArrayList;

public class Queue<T> {
    private static final String EMPTY_QUEUE = "Queue is Empty";
    private ArrayList<T> list;

    Queue(){
        list = new ArrayList<>();
    }

    boolean isEmpty(){
        return list.size() == 0;
    }

    void push(T d){
        list.add(d);
    }

    T pop(){
        if(isEmpty())
            throw new Exceptions(EMPTY_QUEUE + "and you call pop()");
        return list.remove(0);
    }

    T peek(){
        if(isEmpty())
            throw new Exceptions(EMPTY_QUEUE + "and you call pop()");
        return list.get(0);
    }
}
