package Algorithms.Dijkstra;

import java.util.ArrayList;
import java.util.Random;

public class Heap<T> {

    private ArrayList<T> heap;
    private Compare<T> compare;
    private Answer minHeap;


    T remove() {
        T head = heap.remove(0);
        if (heap.size() != 0) {
            T last = heap.remove(heap.size()-1);
            add(last);
        }
        return head;
    }

    boolean isEmpty(){
        return heap.size()==0;
    }

    Heap(Compare<T> compare,boolean min) {
        heap = new ArrayList<>();
        this.compare = compare;
        minHeap = min?Answer.LESSER :Answer.BIGGER;
    }

    T top(){
        return heap.get(0);
    }

    void add(T data) {
        for (int i = 0; i < heap.size(); i++) {
            if(heap.get(i).equals(data))
                return;
        }
        heap.add(data);
        int item = heap.size()-1;
        int parent = item/2;
        while (item != parent &&
                compare.compare(heap.get(parent),heap.get(item)) != minHeap){
            ALSwap(heap,item,parent);
            item = parent;
            parent = item/2;
        }
    }

    private static void ALSwap(ArrayList al, int i, int j){
        Object obj = al.remove(i);
        al.add(i-1,al.remove(j));
        al.add(j,obj);

    }
    void print(){
        int deep = (int)(Math.log(heap.size())/Math.log(2))+1;
        int j=0;
        int pointer = j;
        for (int i = 0; i < deep; i++) {
            for (; j < (pointer + Math.pow(2,i)) && j<heap.size(); j++) {
                System.out.print(heap.get(j)+"\t");
            }
            pointer = j;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Heap<i> heap = new Heap<i>((p,c) -> p.i%2==0?Answer.LESSER :Answer.BIGGER,false);
        for (int i = 0; i <= 10; i++) {
            heap.add(new i(i));
        }

        heap.print();
    }

    public T getRandom(){
        return getRandom(new Random());
    }
    public T getRandom(Random random) {
        return heap.remove(random.nextInt(heap.size()));
    }

    static class i{
        int i;
        i(int t){
            i=t;
        }

        @Override
        public String toString() {
            return i+"";
        }
    }


}
enum Answer {
    BIGGER, LESSER
}
interface Compare<T> {


    Answer compare(T parent,T child);
}