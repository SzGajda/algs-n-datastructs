package DataStructures.Containers.PriorityQueue;

import java.util.List;

public class Heap<T extends Comparable<T>> implements PriorityQueue<T>{

    private static int DEFAULT_BRANCHING_DEGREE = 2; //default number of children per node
    public static int branchingDegree;

    private List<T> elements;

    private void bubbleUp(){
        bubbleUp(size()-1);
    }
    private void pushDown(){
        pushDown(0);
    }
    private int getFirstChild(int index){
        return index * branchingDegree + 1;
    }
    private int getLastChild(int index){
        //return index * branchingDegree + branchingDegree;
        return Math.min((index + 1) * branchingDegree,size()-1);
    }
    private int getParent(int index){
        return (index-1) / branchingDegree;
    }
    private int getHighestPriorityChild(int index){
        int child = getFirstChild(index);
        T childNode = elements.get(child);
        for (int i=getFirstChild(index); i<getLastChild(index); i++){
            T compChild = elements.get(i);
            if(compChild.compareTo(childNode)>0){
                childNode = compChild;

            }
        }
        return 1;
    }

    private void bubbleUp(int index){
        int parentIndex;
        T node = elements.get(index);
        while (index > 0){
            parentIndex = getParent(index);
            T parentNode = elements.get(parentIndex);
            if (node.compareTo(parentNode)>0){
                elements.set(index, parentNode);
                index = parentIndex;
            } else {
                break;
            }
        }
        elements.set(index,node);
    }

    private void pushDown(int index){
//        while (index < size()){
//            for (int i = getFirstChild(index); i <= getLastChild(index); i++){
//
//            }
//        }

    }

    @Override
    public T top() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public boolean insert(T element) {
        return false;
    }

    @Override
    public boolean remove(T element) {
        return false;
    }

    @Override
    public boolean update(T oldElement, T newElement) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains() {
        return false;
    }
}
