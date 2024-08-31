package DataStructures.Containers.PriorityQueue;

import java.util.ArrayList;
import java.util.List;

public class Heap<T extends Comparable<T>> implements PriorityQueue<T>{

    private static int DEFAULT_BRANCHING_DEGREE = 2; //default number of children per node
    public int branchingDegree;

    private List<T> elements;

    public Heap(){
        this.elements = new ArrayList<>();
        this.branchingDegree = DEFAULT_BRANCHING_DEGREE;
    }
    public Heap(int branchingDegree){
        if (branchingDegree < 2){
            throw new IllegalArgumentException("Branching degree of a heap should be no smaller than 2. Provided value: " + branchingDegree);
        }
        this.elements = new ArrayList<>();
        this.branchingDegree = branchingDegree;

    }

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
        for (int i=getFirstChild(index)+1; i<=getLastChild(index); i++){
            T compChild = elements.get(i);
            if(compChild.compareTo(childNode)>0){
                child = i;
            }
        }
        return child;
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
        T node = elements.get(index);

        while (index < size()){
            int child = getHighestPriorityChild(index);
            T childNode = elements.get(child);
            if (childNode.compareTo(node) > 0){
                elements.set(index,childNode);
                index = child;
            } else {
                break;
            }
        }
        elements.set(index, node);
    }

    @Override
    public T top() {
        if(elements.isEmpty()){
            return null;
        }
        T node = elements.get(0);
        remove(node);
        if(!elements.isEmpty()){
            int last = elements.size()-1;
            T lastNode = elements.get(last);
            elements.set(0,lastNode);
            elements.remove(last);
            pushDown();
        }
        return node;
    }

    @Override
    public T peek() {
        return elements.get(0);
    }

    @Override
    public boolean insert(T element) {
        if (elements.contains(element)){
            return false;
        } else {
            int index = elements.size();
            elements.add(index,element);
            bubbleUp(index);
            return true;
        }
    }

    @Override
    public boolean remove(T element) {
        if (elements.isEmpty() | !elements.contains(element)){
            return false;
        }
        int i = elements.indexOf(element);
        int n = elements.size();
        if (i == elements.size()-1){ //if last leaf
            elements.remove(element);
        } else {

        }

        return true;
    }

    @Override
    public boolean update(T oldElement, T newElement) {
        if (!elements.contains(oldElement)){
            return false;
        } else {
            int i = elements.indexOf(oldElement);
            elements.set(i,newElement);
            if(oldElement.compareTo(newElement)>0){
                pushDown(i);
            } else {
                bubbleUp(i);
            }
        }
        return true;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean contains(T element) {
        return elements.contains(element);
    }
}
