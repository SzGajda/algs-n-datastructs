package DataStructures.Containers.PriorityQueue;

public interface PriorityQueue<T> {
    T top();
    T peek();
    boolean insert(T element);
    boolean remove(T element);
    boolean update(T oldElement, T newElement);
    int size();
    boolean contains();
}
